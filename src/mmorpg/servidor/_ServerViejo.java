package mmorpg.servidor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

public class _ServerViejo extends JFrame{
	
	/* GUI Related */
	private ImprimidorConexiones gui;
	
	private GestorSesiones gestorSesiones;
	private GestorComandos gestorComandos;
	
	/* Sockets Related */
	private ObjectInputStream in;  	// InputStream, se usa para recibir user y pass
	private ObjectOutputStream out; // OutputStream
	private int port = 3333;
	private int cola = 30;
	private ServerSocket serverSocket;	// A este socket se conectan los clientes
	private Socket connection;			// Socket conectado que se envia al GestorSesiones 
	
	/* Game Related */
	private Mundo mundo;

	/* User y Pass */
	private String user;
	private String pwd;
	
	
	/* Constructor */
	public _ServerViejo(){
		gestorSesiones = new GestorSesiones();		// Crea el Gestor de Sesiones
		gestorComandos = new GestorComandos();		// Crea el Gestor de COmandos
		gui = new ImpImprimidorConexionesJFrame();	// Crea la GUI
		mundo = new ImpMundo(50, 50, new ImpDibujoImagenVacia()); // TODO Arreglar imagenVacia		
										//Comienza a escuchar conexiones		
		
	}
			
	/**
	 * Inicializa el server
	 */
	public void startRuning(){		
		try{
			serverSocket = new ServerSocket(this.port, this.cola);	
			while(true){
				try{
					waitForConnectionsAndSetupStreams(); 	// Escucha conexiones entrantes
															//TODO hacer un thread para aceptar conexiones simultaneas ?
										
					this.gestorSesiones.inicializaJugador(connection, mundo, user, pwd); // Le mando el socket conectado al GestorSesiones.
																		// GestorSesiones pregunta USER y PWD, levanta BD
																		// recupera el PJ del usuario y le manda al cliente
																		// el mundo junto con su personaje.
																		// El GestorSesiones "forwardea" el socket al GestorComandos
					//whileRunning();		  // TODO Mundo Loop
				}catch(EOFException ex){
					//gui.mostrarMensaje("\n El server cerró la conexión.");
					System.out.println("El server cerró la conexión");
					ex.printStackTrace();
				}finally{
					closeSockets();
				}				
			}			
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	
	/**
	 *  Espera por conexiones, y muestra los mensajes de conexiones. Manda las conexiones a la lista del GestorComandos
	 * @throws IOException
	 */
	private void waitForConnectionsAndSetupStreams() throws IOException{
		//gui.mostrarMensaje("Esperando conexiones... \n");
		System.out.println("Esperando conexiones");
		
		this.connection = serverSocket.accept(); // Acepta una conexion
		System.out.println("Conexion aceptada");
		// Conexion aceptada. Ahora pregunta User y Pass
		
		this.in = new ObjectInputStream(connection.getInputStream());
		this.out = new ObjectOutputStream(connection.getOutputStream());
		
		// Lee usuario y password que viene del cliente
//		try {			
//			user = (String)in.readObject();
//            System.out.println("Username received: " + user);
//            pwd = (String) in.readObject();
//            System.out.println("Password received: " + pwd);
//
//		} catch (ClassNotFoundException e) {			
//			e.printStackTrace();
//		}


		this.gestorComandos.addInput(new ObjectInputStream( connection.getInputStream())); // Agrega el inputStream a la lista del GestorComandos
		this.gestorComandos.addOutput(new ObjectOutputStream ( connection.getOutputStream())); // Agrega el outputStream a la lista del GestorComandos		
		
		//gui.mostrarMensaje("\n Streams configurados \n");	
		System.out.println("Streams Configurados");
		//TODO enviar connection a la lista de conexiones de GestorComandos (?)
		//gui.mostrarMensaje("Conexion establecida con:"+connection.getInetAddress().getHostName()+"\n");
		System.out.println("Conexion establecida con:"+connection.getInetAddress().getHostName()+"\n");
	}
	
	/**
	 * Cierra los sockets 	
	 */
	private void closeSockets(){
		
		//gui.mostrarMensaje("Cerrando conexion... \n");	
		System.out.println("Cerrando conexion...");
		try{
			gestorComandos.closeOutput();
			gestorComandos.closeInput();
			gestorComandos.closeSockets();
			connection.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}		
	}
}
