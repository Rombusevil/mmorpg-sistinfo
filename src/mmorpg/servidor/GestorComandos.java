package mmorpg.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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

	private Object listMonitor; // Este monitor funcaba

	private Object monitor;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket socketClientToServer;	// Esto lo usa el cliente para hablar
											// con el server
											// mediante mandaComando()

	/* Constructor */
	public GestorComandos() {
		socketList = new LinkedList<Socket>();  // init lista de sockets
		pjList = new LinkedList<Actor>();		// init lista de pjs
		listMonitor = new Object();
		monitor = new Object();
	}

	// Escucha comandos
	@Override
	public void run() {
		while (true) {
			synchronized (this.listMonitor) {
				Iterator <Socket>it = this.getSocketList().iterator(); // Iterador de
																// sockets
				while (it.hasNext()) {
					try {
						Socket skt = it.next();
						in = new ObjectInputStream(skt.getInputStream());
						iComando cmd = (iComando) in.readObject();
						Actor pjDelComando = cmd.getPj();

						Iterator<Actor> it2 = this.getPjList().iterator();
						while (it2.hasNext()) {
							Actor pjDeLaLista = it2.next();
							if (pjDeLaLista.equals(pjDelComando)) {
								cmd.setPj(pjDeLaLista);
								cmd.ejecutate();
							}
						}

						// if si es server
						// forwardearComando(cmd);
						Thread.sleep(10);
					} catch (RuntimeException e) {
						it.remove();	// Si falla algo (cliente desconectado),
										// remuevo el cliente del iterador
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();	// Si falla el thread
					} catch (ClassNotFoundException e) {
						e.printStackTrace();	// Si falla el in.readObject()
					} catch (IOException e) {
						e.printStackTrace();
					}
				}// end iterator
			} // end monitor
		}// end while(true)
	}

	// Agrega un PJ a la lista de PJs para forwardear
	public void agregarPjSocket(Actor pj, Socket socket) {
		listMonitor = new Object();
		//synchronized (this.listMonitor) {
			this.getPjList().add(pj);
			this.getSocketList().add(socket);
			System.out.println("Estoy adentro del agregarpjsocket"+socket);
		//}
	}

	// Metodo que usa el cliente para enviar un comando al server
	public void mandarComando(iComando cmd) {
		try {
			out.writeObject(cmd);
			out.flush();
		} catch (IOException e) {
			System.out.println("Error al mandarComando(cmd)");
			e.printStackTrace();
		}
	}

	// Metodo que usa el Cliente para configurar el socket del GestorComandos
	public void setSocket(Socket skt, ObjectInputStream input, ObjectOutputStream output) {
		this.socketClientToServer = skt;
		this.in = input;
		this.out = output;
//		try {
//			setupStreams();
//		} catch (IOException e) {
//			System.out.println("Error al config Streams");
//			e.printStackTrace();
//		}
//		
	}

	

	// Metodo para configurar los Streams
	private void setupStreams() throws IOException {
		System.out.println("GC - Configurando Streams...");
		out = new ObjectOutputStream(socketClientToServer.getOutputStream());
		out.flush();
		System.out.println("GC - OutStream config");
		in = new ObjectInputStream(socketClientToServer.getInputStream());
		System.out.println("GC - InStream config");
		System.out.println("GC - STREAMS CONFIG");
	}

	/* Getters y Setters */

	private List<Socket> getSocketList() {
		return socketList;
	}

	private void setSocketList(List<Socket> socketList) {
		this.socketList = socketList;
	}

	private List<Actor> getPjList() {
		return pjList;
	}

	private void setPjList(List<Actor> pjList) {
		this.pjList = pjList;
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
			System.out.println("PJ Lista:" + s);
		}

	}

}
