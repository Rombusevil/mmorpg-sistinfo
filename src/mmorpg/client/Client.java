package mmorpg.client;




import java.io.EOFException;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import mmorpg.acciones.Accion;
import mmorpg.acciones.CmdJugadorAccion;
import mmorpg.acciones.iComando;

import mmorpg.acciones.enDireccion.MovedorAbajo;
import mmorpg.common.GestorComandos;
import mmorpg.entes.actor.Actor;
import mmorpg.entes.actor.ImpActor;
import mmorpg.entes.actor.PJ;
import mmorpg.mundo.ImpMundo;
import mmorpg.mundo.Mundo;


public class Client implements Runnable {

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String serverIP;
	private Socket connection;
	private String user;
	private String pass;
	private String character;
	

	private ImpMundo mundo;
	private Actor pj;

	private GestorComandos gc;
	private Boolean isRunning = true;

	public Client(String host, Integer port, String user, String pass, String character) {
		this.serverIP = host;
		this.user = user;
		this.pass = pass;
		this.character = character;
		Mundo mundoInicialAux = new ImpMundo(1, 1, null);
		this.gc = new GestorComandos(false, mundoInicialAux);
	}

	@Override
	public void run() {
		startRunning();
	}

	// Conecta al server
	public void startRunning() {
		try {

			conectarAlServer();
			setupStreams();
			escucharComandos();
			
			try {
				out.writeObject(this.user);
				out.flush();
				out.writeObject(this.pass);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// FIN USR/PWD
			List<Actor> listaDePjsExistentes = new LinkedList<Actor>();
			// Recibo el PJ de la BD y el Mundo
			try {
				pj = (Actor) in.readObject();
				mundo = (ImpMundo) in.readObject();
				listaDePjsExistentes = (List<Actor>) in.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("Error recibiendo Mundo y Pj");
				e.printStackTrace();
			}
			// FIN RECIBIR PJ y MUNDO
			
			// CREO GC una vez que recibi el mundo
			this.gc.setMundo(mundo); // Crea el GC en el cliente (false es para indicar que no es Server)

			// Agrega el PJ recibido al GC para utilizarlo en la copia local del mundo
			gc.agregarPjSocket(pj, connection);
			gc.setPjCliente(this.pj);
			System.out.println(listaDePjsExistentes);
			gc.setPjList(listaDePjsExistentes);

			Accion nuevaConexion = new MovedorAbajo(); // No importa el tipo de accion
			nuevaConexion.setNewConnection(true);  // Lo que importa es mandar un comando con el campo en true
			iComando commandoConnection = new CmdJugadorAccion();
			commandoConnection.setAccion(nuevaConexion);
			commandoConnection.setPj(pj);
			gc.mandarComando(commandoConnection, connection);

			whileRunning();			
		} catch (EOFException e) {
			System.out.println("Cliente cerro la conexion");
		} catch (IOException e) {
			System.out.println("CL - io exception");
			e.printStackTrace();	// Si salta este se chingo todo
		} finally {
			cerrarConexion();
		}
	}

	// Main loop
	private void whileRunning() throws IOException {
		do {
			agregarNuevosJugadores();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		} while (isRunning);
	}

	/**
	 * Inicia el run() del GestorComandos
	 */
	private void escucharComandos() {
		Thread t = new Thread(gc);
		t.start();
	}

	public void agregarNuevosJugadores() {
		LinkedList<Actor> newPjListAux;
		LinkedList<Actor> listAux = new LinkedList<Actor>();
		newPjListAux = (LinkedList<Actor>) gc.getNewPjList();

		Iterator<Actor> it = newPjListAux.iterator();
		while (it.hasNext()) {
			Actor pjNuevo = it.next();

			if (!(((PJ) pjNuevo).getUsr().equals(((PJ) this.pj).getUsr()))) {
				System.out.println("------------");
				System.out.println("Pos de Actor x:"+((ImpActor)pjNuevo).getFichaDePersonaje().getXpos()+" y:"+((ImpActor)pjNuevo).getFichaDePersonaje().getYpos());
				mundo.poneActorEn(((ImpActor)pjNuevo).getFichaDePersonaje().getXpos(), ((ImpActor)pjNuevo).getFichaDePersonaje().getYpos(), (ImpActor) pjNuevo);
			}
			listAux.add(pjNuevo);
		}

		newPjListAux.removeAll(listAux);
		listAux = null;
	}

	// Se conecta al server
	private void conectarAlServer() throws IOException {
		connection = new Socket(InetAddress.getByName(serverIP), 3334); // IP + Puerto del server
		System.out.println("Conexion establecida con: " + connection.getInetAddress().getHostName());
	}

	// Configura los Streams
	private void setupStreams() throws IOException {
		System.out.println("Cliente - Configurando Streams...");
		out = new ObjectOutputStream(connection.getOutputStream());
		System.out.println("Cliente - Outstream configurado");
		out.flush();
		in = new ObjectInputStream(connection.getInputStream());
		System.out.println("Cliente - Streams configurados");
	}

	// Cierra todo el crap down
	private void cerrarConexion() {
		try {
			out.close();
			in.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GestorComandos getGestorComandos(){
		return this.gc;
	}
	
	public Socket getSocket(){
		return this.connection;
	}
	
	public Actor getPj(){
		return this.pj;
	}
	
	public ImpMundo getMundo(){
		return this.mundo;
	}

}
