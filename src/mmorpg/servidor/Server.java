package mmorpg.servidor;

import java.io.IOException;
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

public class Server implements Runnable {
	
	/* Sockets Related */
	private int port = 3334;				// Puerto del server
	private ServerSocket serverSocket;		// A este socket se conectan los clientes
	private Socket socket;					// Socket conectado que se envia al GestorSesiones
	private Object monitor;
	
	private Boolean isRunning = true;
	
	private GestorSesiones gestorSesiones;
	private GestorComandos gestorComandos;
	private DataBaseManager dataBase;
	
	public Server(){
		
		
		
		dataBase = new DataBaseManager("actores"); // Crea la Base de Datos
		
		gestorSesiones = new GestorSesiones();
		gestorComandos = new GestorComandos();
			
	}
	
	
	public void mainServer(){
		
		try {
			serverSocket = new ServerSocket(this.port);			
			
			while(isRunning){
				System.out.println("Esperando conexiones");
				esperarConexiones();
				
			}//end while			
		}catch (IOException e){
			e.printStackTrace(); // Puerto ocupado, no se puede iniciar server
			System.out.println("No puedo iniciar el Server en el socket:" + this.port);			
		}finally{
			closeCrap();
		}
		
	}
	
	private void esperarConexiones() throws IOException{
		//synchronized(this.monitor){		
			socket = serverSocket.accept();
			System.out.println("Conexion aceptada");
			Thread t = new Thread(this);
			t.start();
		//}//end monitor		
	}
	
	
	
	private void closeCrap() {
		try {
			this.socket.close();
			this.serverSocket.close();	
		} catch (IOException e) {
			System.out.println("No pude cerrar los puertos");
			e.printStackTrace();
		}
			
	}


	/**
	 * Thread donde está el accept()
	 * escuchando conexiones. Sin bloquear el resto del Server
	 */
	@Override
	public void run() {		
		Actor pj = gestorSesiones.initPJ(socket, dataBase);	// Recupera un PJ de la BD
		gestorComandos.agregarPjSocket(pj, socket);			// Le pasa el PJ y el Socket al gestorComandos y lo agrega al hashMap
		gestorComandos.printMap();
		
	}

}
