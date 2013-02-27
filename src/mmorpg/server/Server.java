package mmorpg.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JTextField;

import mmorpg.common.GestorComandos;
import mmorpg.entes.actor.Actor;
import mmorpg.mundo.ImpMundo;
import mmorpg.mundo.Mundo;
import mmorpg.server.database.DataBaseManager;
import mmorpg.userInterface.output.ImpDibujoCharVacio;



public class Server implements Runnable, Serializable {
	
	/* Sockets Related */
	private int port;				// Puerto del server
	private ServerSocket serverSocket;		// A este socket se conectan los clientes
	private Socket socket;					// Socket conectado que se envia al GestorSesiones
	
	private Object monitor;
	
	private Boolean isRunning = true;
	
	private GestorSesiones gestorSesiones;
	private GestorComandos gestorComandos;
	private DataBaseManager dataBase;
	
	private Mundo mundo;
	
	public Server(){
		
		try {
			
	        Properties config = new Properties();
	        config.load(new FileInputStream("config.properties"));
	        this.port=Integer.parseInt(config.getProperty("serverPort"));
	        
	    } catch (IOException ex) {
	        System.out.println("Error al leer puerto del server en config.properties"+ex);
	    }
		
		
		monitor = new Object();
		dataBase = new DataBaseManager("actores"); // Crea la Base de Datos
		
		this.mundo = new ImpMundo(20, 10, new ImpDibujoCharVacio());
		
		gestorSesiones = new GestorSesiones();
		gestorComandos = new GestorComandos(true, mundo);
			
	}	
	
	
	
	public void mainServer(){
		
		try {
			serverSocket = new ServerSocket(this.port);	
			
			while(isRunning){
				System.out.println("Esperando conexiones");
				esperarConexiones();
				escucharComandos();
			}			
		}catch (IOException e){
			e.printStackTrace(); // Puerto ocupado, no se puede iniciar server
			System.out.println("No puedo iniciar el Server en el socket:" + this.port);			
		}finally{			
				closeCrap();		
		}
	}
	
	/**
	 * Inicializa el run() del server, que esta mas abajo
	 * @throws IOException
	 */
	private void esperarConexiones() throws IOException{
		synchronized(this.monitor){		
			socket = serverSocket.accept();
			System.out.println("Conexion aceptada");
			Thread t = new Thread(this);
			t.start();
		}//end monitor		
	}
	
	/**
	 * Inicia el run() del GestorComandos
	 */
	private void escucharComandos(){
		System.out.println("SV - GC - Estoy escuchando comandos");
		Thread t = new Thread(gestorComandos);
		t.start();		
	}
	
	
	
	

	/**
	 * Thread donde está el accept()
	 * escuchando conexiones. Sin bloquear el resto del Server
	 */
	@Override
	public void run() {	
		Actor pj = gestorSesiones.initPJ(socket, dataBase, mundo);	// Recupera un PJ de la BD

		gestorComandos.agregarPjSocket(pj, socket);	// Le pasa el PJ y el Socket al gestorComandos y lo agrega a las 2 listas		
		
		//System.out.println("\n Te imprimo la lista de Sockets y PJs:");
		//gestorComandos.printList();
	}
	
	/**
	 * Cierra el SV, etc etc..
	 */
	private void closeCrap() {
		try {
			if(socket !=null){
				this.socket.close();
			}
			if(serverSocket!=null){
				this.serverSocket.close();	
			}			
		} catch (IOException e) {
			System.out.println("No pude cerrar los puertos");
			e.printStackTrace();
		}			
	}
	

}
