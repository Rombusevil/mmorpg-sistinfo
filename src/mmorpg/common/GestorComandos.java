package mmorpg.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
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

	// Listas Principales
	private HashMap<Socket, Boolean> socketList;	// Esta lista la usa el server para hablar
										// con los clientes, forwardeando
	private List<Actor> pjList; //Lista de Pjs para ejecutar comandos, que es diferente de la lIstaPJ del Mundo

	// Listas Auxiliares
	private List<Actor> newPjList;	// Lista auxiliar con los pjs a conectar
	private List<Actor> killPjList; // Lista auxiliar con los pjs a desconectar
	private List<Socket> killSocketList; // Lista auxiliar con los sockets a desconectar
	
	// Listas-copia para iterar
	private List<Actor> itPjList;		// Copia para iterar
//	private List<Socket> itSocketList;	// Copia para iterar

	private Actor pjCliente;		// Guarda el PJ del Cliente, para saber quien sos vos
	// Estan bien usados los monitores?? y los synchronized¿?
	private Object listMonitor; // Este monitor funcaba
	private Object monitor;
	private Boolean server; 	// Si es server forwardea comandos
	private DataBaseManager dataBase;
	
	private Mundo mundo;

	/* Constructor */
	public GestorComandos(Boolean server, Mundo mundo) {
		socketList = new HashMap<Socket, Boolean>();   // init lista de sockets
		pjList = new LinkedList<Actor>();		// init lista de pjs
		listMonitor = new Object();
		monitor = new Object();
		this.server = server;
		this.mundo = mundo;
		
		// Inicios las auxiliares para modificar
		this.killPjList = new LinkedList<Actor>();
		this.killSocketList = new LinkedList<Socket>();  
		
		// Inicio las copias para iterar
		this.itPjList = new LinkedList<Actor>();

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
			synchronized (this.socketList) {
				Iterator<Map.Entry<Socket,Boolean>> it = socketList.entrySet().iterator();
				ObjectInputStream in = null;
				iComando cmd = null;
				
				while (it.hasNext()) {					
					boolean sendComando = false;
					boolean ejecutado = false;
					try {
						Map.Entry<Socket, Boolean> skt = it.next();
						//Socket skt = it.next();
						
					
						if (skt.getKey().getInputStream().available() > 0) {
						//if (skt.getInputStream().available() > 0) {
							in = new ObjectInputStream(skt.getKey().getInputStream());
							
							// si metemos un if(in.avaiable() > 0 ) muere todo
							cmd = (iComando) in.readObject();
							Actor pjDelComando = cmd.getPj();
							sendComando = true;

							if ((isNewConnection(cmd) && !(this.server))) {
								System.out.println("Cliente - Se conectó alguien! (puedo haber sido yo mismo)");
								this.pjList.add(cmd.getPj()); 	// <- Para que el GC pueda ejecutar comandos sobre este PJ
								this.newPjList.add(cmd.getPj());// <-- Para instanciarlos en el Mundo (del cliente)
							}
							
							//Copio la lista de Pjs en la lista para iterar e itero sobre ella
							this.itPjList.clear();
							this.itPjList.addAll(this.pjList);	
							
							Iterator<Actor> it2 = this.itPjList.iterator();	// Recorro la lista de PJs
							while (it2.hasNext()) {								// Para comparar el PJ que vino en el cmd
								Actor pjDeLaLista = it2.next();					// Con algun PJ de la Lista
								
								// EJECUTA COMANDOS EN EL SERVER
								if (this.server) {
									if ((((PJ) pjDeLaLista).getUsr().equals(((PJ) pjDelComando).getUsr())) && !(isNewConnection(cmd))) {
										System.out.println("ENTRE A EJECUTAR - Server");
										cmd.setPj(pjDeLaLista);
										cmd.ejecutarEnDireccion();	
										cmd.ejecutarConexion(null ,this.killPjList, this.mundo, skt, dataBase);
										//this.mundo.buscaYDestruyeMuertos(this.killPjList);
									}
								}
								// EJECUTA COMANDOS EN EL CLIENTE
								System.out.println("Ejecutado vale:" + ejecutado);
								if (!this.server && !(ejecutado)) {
									if ((((PJ) pjDeLaLista).getUsr().equals(((PJ) pjDelComando).getUsr())) && !(isNewConnection(cmd))) {
										if (!(((PJ) pjDeLaLista).getUsr().equals(((PJ) this.pjCliente).getUsr()))) { // El PjLista != PjCliente
											ejecutado = true;
										}
										System.out.println("ENTRE A EJECUTAR - Cliente");
										cmd.setPj(pjDeLaLista);
										cmd.ejecutarEnDireccion();
										cmd.ejecutarConexion(this.newPjList, this.killPjList, this.mundo, skt, null);
										//this.mundo.buscaYDestruyeMuertos(this.killPjList);
									}
								}
								
								
								
							}// end while it2							
							
							//System.out.println("ANTES DEL REMOVEALL");
							this.pjList.removeAll(this.killPjList); // Borra de la lista a los que se desconectaron
							//System.out.println("DESPUES DEL REMOVEALL");
							this.killPjList.clear();						
							
//							if(!this.server){							// Esto solo lo hace el cliente
//								this.pjList.addAll(this.newPjList);		// Agrego a la lista a los que se conectaron
//								this.newPjList.clear();
//							}
							
						}
					
//						if(!this.killPjList.isEmpty()){
//							this.killSocketList.add(skt); // Agrego el socket a la lista de sockets a ser borrados
//							
//						}
						
						// if si es server
						// Hacer--->forwardearComando(cmd);
						if (this.server && sendComando ) {
							
							Iterator<Map.Entry<Socket, Boolean>> itForward = socketList.entrySet().iterator();
							ObjectOutputStream out = null;

							while (itForward.hasNext()) {									
//								if(this.killSocketList.contains(skt)){
//									itForward.next();
//								}
								
								Map.Entry<Socket, Boolean> sktForward = itForward.next();
								if(sktForward.getValue()==true){ //Si está activo forwardeo
									//Socket sktForward = itForward.next();
									out = new ObjectOutputStream(sktForward.getKey().getOutputStream());
									out.writeObject(cmd);
									out.flush();
									System.out.println("- SERVER - Forwardeo comando");
								}
								else{
									System.out.println("- SERVER - Socket Inact. no forwardeo");
								}
							}
							sendComando = false;
						}
						
						Thread.sleep(10);

					} catch (ConcurrentModificationException e){
						System.out.println("GC - ConcurrentModificationException it.remove()");
						it.remove();	// Si falla algo (cliente desconectado), remuevo el cliente del iterador
						e.printStackTrace();
					} catch (RuntimeException e) {
						System.out.println("GC - RuntimeException it.remove()");
						it.remove();	// Si falla algo (cliente desconectado), remuevo el cliente del iterador
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
			//this.mundo.buscaYDestruyeMuertos();
		}// end while(true)
	}

	// Agrega un PJ a la lista de PJs para forwardear
	public void agregarPjSocket(Actor pj, Socket socket) {
		listMonitor = new Object();
		// synchronized (this.listMonitor) {
		this.getPjList().add(pj);

		try {
			//this.getSocketList().add(socket);
			this.getSocketList().put(socket, true);			
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

	
/* Getters y Setters */

	private HashMap<Socket,Boolean> getSocketList() {
		return socketList;
	}

	public List<Actor> getPjList() {
		return pjList;
	}

// Este método está deprecado. Se rompió cuando cambié las cosas y ni da ponerse a arreglarlo.
//	public void printList() {
//		Iterator<Actor> it = this.getPjList().iterator();
//		while (it.hasNext()) {
//			Actor pj = it.next();
//			System.out.println("PJ Lista:" + pj);
//		}
//
//		Iterator<Socket> it2 = this.getSocketList().iterator();
//		while (it2.hasNext()) {
//			Socket s = it2.next();
//			System.out.println("PJ Socket:" + s);
//		}
//	}

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
