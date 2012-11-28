package mmorpg.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sun.corba.se.spi.activation.InvalidORBidHolder;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class Server implements Runnable, Serializable {
	
	/* Sockets Related */
	private int port = 3335;				// Puerto del server
	private ServerSocket serverSocket;		// A este socket se conectan los clientes
	private Socket socket;					// Socket conectado que se envia al GestorSesiones
	
	//Este monitor donde se usa?
	private Object monitor;
	
	private Boolean isRunning = true;
	
	private GestorSesiones gestorSesiones;
	private GestorComandos gestorComandos;
	private DataBaseManager dataBase;
	
	private Mundo mundo;
	
	public Server(){
		
		
		monitor = new Object();
		dataBase = new DataBaseManager("actores"); // Crea la Base de Datos
		
		mundo = new ImpMundo(8, 8, new ImpDibujoCharVacio());
		
		gestorSesiones = new GestorSesiones();
		gestorComandos = new GestorComandos();
			
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
		
		System.out.println("\n Te imprimo la lista de Sockets y PJs:");
		gestorComandos.printList();
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
