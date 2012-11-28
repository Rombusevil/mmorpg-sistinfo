package mmorpg.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GestorComandos implements Runnable {

	private List<Socket> socketList;	// Esta lista la usa el server para hablar
										// con los clientes, forwardeando
	private List<Actor> pjList;
	
	//Estan bien usados los monitores?? y los synchronized¿?
	private Object listMonitor; // Este monitor funcaba
	private Object monitor;
	private Boolean server; 	//Si es server forwardea comandos
	

	/* Constructor */
	public GestorComandos(Boolean server) {
		socketList = new ArrayList<Socket>();   // init lista de sockets
		pjList = new LinkedList<Actor>();		// init lista de pjs
		listMonitor = new Object();
		monitor = new Object();
		this.server = server;
	}

	// Escucha comandos
	@Override
	public void run() {
		while (true) {
			synchronized (this.listMonitor) {
				Iterator <Socket>it = this.getSocketList().iterator(); // Iterador de sockets
				ObjectInputStream in = null;
				iComando cmd = null;				
				
				while (it.hasNext()) {
					Boolean sendComando = false;
					try {
						Socket skt = it.next();
						if(skt.getInputStream().available() > 0){
							in = new ObjectInputStream(skt.getInputStream());	
							// si metemos un if(in.avaiable() > 0 )  muere todo
							cmd = (iComando) in.readObject();
							Actor pjDelComando = cmd.getPj();
							sendComando = true;
	
							Iterator<Actor> it2 = this.getPjList().iterator();	// Recorro la lista de PJs
							while (it2.hasNext()) {								// Para comparar el PJ que vino en el cmd
								Actor pjDeLaLista = it2.next();					// Con algun PJ de la Lista
								
								System.out.println("PJ DE LA LISTA:"+pjDeLaLista);
								System.out.println("PJ DEL COMANDO:"+pjDelComando);							
								System.out.println("COMPARACION: "+ (  ((PJ)pjDeLaLista).getUsr().equals(((PJ)pjDelComando).getUsr())   ));
								
								if (   ((PJ)pjDeLaLista).getUsr().equals(((PJ)pjDelComando).getUsr())  ) {
									System.out.println("ENTRE A EJECUTAR A TU VIEJA");
									cmd.setPj(pjDeLaLista);
									cmd.ejecutate();									
								}
							}
						}
						
						// if si es server
						// Hacer--->forwardearComando(cmd);
						if(server && sendComando){
							Iterator <Socket>itForward =this.getSocketList().iterator();
							ObjectOutputStream out = null;
							
							while(itForward.hasNext()){
								Socket sktForward = itForward.next();
								out = new ObjectOutputStream(sktForward.getOutputStream());
								out.writeObject(cmd);
								out.flush();
							}
						}
						
						Thread.sleep(10);
					} catch (RuntimeException e) {
						it.remove();	// Si falla algo (cliente desconectado), remuevo el cliente del iterador
						System.out.println("GC - RuntimeException it.remove()");
						e.printStackTrace();
					} catch (InterruptedException e) {
						System.out.println("GC -  InterruptedException (Thread)");
						e.printStackTrace();	// Si falla el thread
					} catch (ClassNotFoundException e) {
						System.out.println("GC - ClassNotFoundException, readObject()");
						e.printStackTrace();	// Si falla el in.readObject()
					} catch (IOException e) {
						System.out.println("GC - IOException");
						e.printStackTrace();
					}
				}// end iterator
			} // end monitor
		}// end while(true)
	}

	// Agrega un PJ a la lista de PJs para forwardear
	public void agregarPjSocket(Actor pj, Socket socket) {
		
		listMonitor = new Object();
//		synchronized (this.listMonitor) {
			this.getPjList().add(pj);
			this.getSocketList().add(socket);
			System.out.println(" Server - GC - Agregado un PJ, Socket, OIS a la lista");
//		}
	}

	// Metodo que usa el cliente para enviar un comando al server
	public void mandarComando(iComando cmd, Socket socket) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(cmd);
			out.flush();    // <--- Si saco este flush, agrega el PJ a la lista al conectar
							// si lo dejo, lo agrega al recibir un comando del PJ, antes no.
		} catch (IOException e) {
			System.out.println("Error al mandarComando(cmd)");
			e.printStackTrace();
		}
	}

	/* Getters y Setters */

	private List<Socket> getSocketList() {
		return socketList;
	}

	private List<Actor> getPjList() {
		return pjList;
	}

	public void printList() {
		Iterator<Actor> it = this.getPjList().iterator();
		while (it.hasNext()) {
			Actor pj = it.next();
			System.out.println("PJ Lista:" + pj);
		}

		Iterator<Socket> it2 = this.getSocketList().iterator();
		while (it2.hasNext()) {
			Socket s = it2.next();
			System.out.println("PJ Socket:" + s);
		}
	}

}
