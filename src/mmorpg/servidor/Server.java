package mmorpg.servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	/* Sockets Related */
	private int port = 3333;			// Puerto del server
	private ServerSocket serverSocket;	// A este socket se conectan los clientes
	private Socket socket;				// Socket conectado que se envia al GestorSesiones
	
	
	public Server(){
		
	}
	
	/**
	 * Thread donde está el accept()
	 * escuchando conexiones. Sin bloquear el resto del Server
	 */
	@Override
	public void run() {		

	}

}
