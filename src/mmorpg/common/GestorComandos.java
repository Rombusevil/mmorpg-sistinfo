package mmorpg.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import mmorpg.acciones.iComando;
import mmorpg.entes.actor.Actor;
import mmorpg.entes.actor.ImpActor;
import mmorpg.entes.actor.PJ;
import mmorpg.mundo.ImpMundo;
import mmorpg.mundo.Mundo;
import mmorpg.server.database.DataBaseManager;
import mmorpg.server.database.EstadoPjAGuardar;

public class GestorComandos implements Runnable {

	private List<Socket> socketList;	// Esta lista la usa el server para hablar
										// con los clientes, forwardeando
	private List<Actor> pjList; //Lista de Pjs para ejecutar comandos, que es diferente de la lIstaPJ del Mundo

	private List<Actor> newPjList;	// Lista auxiliar con los pjs a conectar
	private List<Actor> killPjList; // Lista auxiliar con los pjs a desconectar
	private Actor pjCliente;		// Guarda el PJ del Cliente, para saber quien sos vos
	private List<Socket> killSocketList; // Cuando alguien se desconecta, lo sacamos de la lista de sockets con esta lista

	// Estan bien usados los monitores?? y los synchronized¿?
	private Object listMonitor; // Este monitor funcaba
	private Object monitor;
	private Boolean server; 	// Si es server forwardea comandos
	private DataBaseManager dataBase;
	
	private Mundo mundo;

	/* Constructor */
	public GestorComandos(Boolean server, Mundo mundo) {
		socketList = new ArrayList<Socket>();   // init lista de sockets
		pjList = new LinkedList<Actor>();		// init lista de pjs
		listMonitor = new Object();
		monitor = new Object();
		this.server = server;
		this.mundo = mundo;
		this.killPjList = new LinkedList<Actor>();
		this.killSocketList = new LinkedList<Socket>();  

		if (!server) {
			newPjList = new LinkedList<Actor>();
		} else {
			dataBase = new DataBaseManager("actores");
		}

	}

	// Escucha comandos
	@Override
	public void run() {
		while (true) {
			synchronized (this.listMonitor) {
				Iterator<Socket> it = this.getSocketList().iterator(); // Iterador de sockets
				ObjectInputStream in = null;
				iComando cmd = null;

				while (it.hasNext()) {
					boolean sendComando = false;
					boolean ejecutado = false;
					try {
						Socket skt = it.next();
						if (skt.getInputStream().available() > 0) {
							in = new ObjectInputStream(skt.getInputStream());
							// si metemos un if(in.avaiable() > 0 ) muere todo
							cmd = (iComando) in.readObject();
							Actor pjDelComando = cmd.getPj();
							sendComando = true;

							if ((isNewConnection(cmd) && !(this.server))) {
								System.out.println("------>Se conecto alguien!!!");
								this.pjList.add(cmd.getPj()); // <- Para que el GC pueda ejecutar comandos sobre este PJ
								this.newPjList.add(cmd.getPj()); // <-- Para instanciarlos en el Mundo (del cliente)
							}

							Iterator<Actor> it2 = this.getPjList().iterator();	// Recorro la lista de PJs
							while (it2.hasNext()) {								// Para comparar el PJ que vino en el cmd
								Actor pjDeLaLista = it2.next();					// Con algun PJ de la Lista

//								if(server){//guardo los datos del pj
//									guardaPjBd((ImpActor)pjDeLaLista);
//								}					
								
								// EJECUTA COMANDOS EN EL SERVER
								if (this.server) {
									if ((((PJ) pjDeLaLista).getUsr().equals(((PJ) pjDelComando).getUsr())) && !(isNewConnection(cmd))) {
										System.out.println("ENTRE A EJECUTAR - Server");
										cmd.setPj(pjDeLaLista);
										cmd.ejecutarEnDireccion();	
										cmd.ejecutarConexion(null ,this.killPjList, this.mundo);
									}
								}
								// EJECUTA COMANDOS EN EL CLIENTE
								if (!this.server && !(ejecutado)) {
									if ((((PJ) pjDeLaLista).getUsr().equals(((PJ) pjDelComando).getUsr())) && !(isNewConnection(cmd))) {
										if (!(((PJ) pjDeLaLista).getUsr().equals(((PJ) this.pjCliente).getUsr()))) { // El PjLista != PjCliente
											ejecutado = true;
										}
										System.out.println("ENTRE A EJECUTAR - Cliente");
										cmd.setPj(pjDeLaLista);
										cmd.ejecutarEnDireccion();
										cmd.ejecutarConexion(this.newPjList, this.killPjList, this.mundo);
									}
								}
							}// end while it2
							
							this.pjList.removeAll(this.killPjList); // Borra de la lista a los que se desconectaron
							this.killPjList.clear();
							
							
							if(!this.server){							// Esto solo lo hace el cliente
								this.pjList.addAll(this.newPjList);		// Agrego a la lista a los que se conectaron
								this.newPjList.clear();
							}
							
							
							
							
						}
						
//						if(!this.killPjList.isEmpty()){
//							this.killSocketList.add(skt); // Agrego el socket a la lista de sockets a ser borrados
//							
//						}
						
						
						// if si es server
						// Hacer--->forwardearComando(cmd);
						if (this.server && sendComando ) {
							Iterator<Socket> itForward = this.getSocketList().iterator();
							ObjectOutputStream out = null;

							while (itForward.hasNext()) {
//								if(this.killSocketList.contains(skt)){
//									itForward.next();
//								}
								Socket sktForward = itForward.next();
								out = new ObjectOutputStream(sktForward.getOutputStream());
								out.writeObject(cmd);
								out.flush();
							}
							sendComando = false;
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
		// synchronized (this.listMonitor) {
		this.getPjList().add(pj);

		try {
			this.getSocketList().add(socket);
		} catch (NullPointerException e) {
			System.out.println("GC - Recibi un nulo en la lista de Socket");
			// Si recibo un socket nulo, no hago nada
		}
		System.out.println(" Server - GC - Agregado un PJ y Socket  la lista");
		// }
	}

	public void forwardearConexionPJ(iComando cmd) {
		// this.agregarPjSocket(cmd.getPj(), null);
		this.pjList.add(cmd.getPj());
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

	public void guardaPjBd(ImpActor pj) {
		EstadoPjAGuardar estado = pj.getFichaDePersonaje().creaEstadoPjAGuardar();

		dataBase.guardaPj((PJ) pj);
	}

	/* Getters y Setters */

	private List<Socket> getSocketList() {
		return socketList;
	}

	public List<Actor> getPjList() {
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

	public List<Actor> getNewPjList() {
		return newPjList;
	}

	public void setPjList(List<Actor> pjList2) {
		this.pjList = pjList2;
	}

	public Actor getPjCliente() {
		return pjCliente;
	}

	public void setPjCliente(Actor pjCliente) {
		this.pjCliente = pjCliente;
	}

	@Deprecated
	public boolean isNewConnection(iComando cmd) {
		try{
			System.out.println("isNewConnection:" + cmd.getAccion().getNewConnection());
			if (cmd.getAccion().getNewConnection()) {
				return true;
			}else{
				return false;
			}
		}catch (RuntimeException e){
			System.out.println("IsNewConnection: False (Deprecado)");
			return false;
		}		
	}

	public void setMundo(ImpMundo mundo2) {
		this.mundo = mundo2;		
	}
	
	

}
