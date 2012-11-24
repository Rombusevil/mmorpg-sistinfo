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

public class Server extends JFrame {
	
	/* GUI Related */
	private ImprimidorConexiones gui;
	
	private GestorSesiones gestorSesiones;
	
	/* Sockets Related */
	private ObjectOutputStream output; 	// En esta variable se guardan los mensajes para enviar
	private ObjectInputStream input; 	// En esta variable se reciben mensajes desde afuera
	private int port = 7879;
	private int cola = 30;
	private ServerSocket serverSocket;	// A este socket se conectan los clientes
	private Socket connection;			// Socket conectado que se envia al GestorSesiones 
	
	/* Game Related */
	private Mundo mundo;
	
	
	/* Constructor */
	public Server(){
		gestorSesiones = new GestorSesiones();		// Crea el Gestor de Sesiones
		gui = new ImpImprimidorConexionesJFrame();	// Crea la GUI
		mundo = new ImpMundo(50, 50, new ImpDibujoImagenVacia()); // TODO Arreglar imagenVacia		
		startRuning();								//Comienza a escuchar conexiones						
	}
		
	// Inicializa el server	
	public void startRuning(){		
		try{
			serverSocket = new ServerSocket(this.port, this.cola);	
			while(true){
				try{
					waitForConnections(); 	// Escucha conexiones entrantes
					setupStreams(); 		// Configura los Streams
											//TODO hacer un thread para aceptar conexiones simultaneas
					String user = gui.preguntaUser();
					String pwd = gui.preguntaPass();
					this.gestorSesiones.inicializaJugador(connection, mundo, user, pwd); // Le mando el socket conectado al GestorSesiones.
																		// GestorSesiones pregunta USER y PWD, levanta BD
																		// recupera el PJ del usuario y le manda al cliente
																		// el mundo junto con su personaje.
																		// El GestorSesiones "forwardea" el socket al GestorComandos
					//whileRunning();		  // TODO Mundo Loop
				}catch(EOFException ex){
					gui.mostrarMensaje("\n El server cerró la conexión.");
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
	 *  Espera por conexiones, y muestra los mensajes de conexiones
	 * @throws IOException
	 */
	private void waitForConnections() throws IOException{
		gui.mostrarMensaje("Esperando conexiones... \n");
		this.connection = serverSocket.accept(); // Acepta una conexion
		//TODO enviar connection a la lista de conexiones de GestorComandos (?)
		gui.mostrarMensaje("Conexion establecida con:"+connection.getInetAddress().getHostName()+"\n");
	}
	
	/**
	 * Configura los Streams de entrada y salida
	 * @throws IOException
	 */
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream()); // la conexion establecida previamente SOCKET CONECTADO -> GestorComandos
		output.flush(); // Vacia la "basura" del buffer
		
		input = new ObjectInputStream(connection.getInputStream()); // Lo mismo pero para el inputStream. Aca recibo informacion.
		gui.mostrarMensaje("\n Streams configurados \n");		
	}
	
	/**
	 * Cierra los sockets 	
	 */
	private void closeSockets(){
		gui.mostrarMensaje("Cerrando conexion... \n");		
		try{
			output.close();
			input.close();
			connection.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}		
	}
}
