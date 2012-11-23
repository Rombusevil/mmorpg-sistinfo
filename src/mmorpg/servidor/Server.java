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
	private JTextField commandLine;	 //Linea para escribir comandos
	private JTextArea commandWindow; //Ventana con el historial de comandos y mensajes
	
	/* Sockets Related */
	private ObjectOutputStream output; 	// En esta variable se guardan los mensajes para enviar
	private ObjectInputStream input; 	// En esta variable se reciben mensajes desde afuera
	private ServerSocket serverSocket;	// A este socket se conectan los clientes
	private Socket connection;			// 
	
	
	/* Constructor */
	public Server(){
			super("SERVER WINDOW"); 		// Barra de titulo
			this.commandLine = new JTextField(); // Para escribir comandos
			commandLine.setEditable(false); // Bloquea la escritura
			commandLine.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent event){ //Este metodo se llama cuando se escribe algo en commandLine y apretas enter
							mostrarMensaje(event.getActionCommand());	// event.getActionCommand() tiene el texto de la caja de texto de commandLine
							commandLine.setText("");					// Despues de enviar un comando, se borra y queda en blanco.
							
						}
					}
			);
			add(commandLine, BorderLayout.NORTH); 	// Agrega la commandLine a la GUI
			commandWindow = new JTextArea();
			add(new JScrollPane(commandWindow));	// Agrega un scoll a la ventana
			setSize(300, 400);						// El tamaño de la ventana
			setVisible(true);			
	}
	
	
	// Inicializa el server
	
	public void startRuning(){		
		try{
			serverSocket = new ServerSocket(6789, 100); //6789 -> Puerto; 100 -> Limite de clientes en la cola para conectar
			
			// Main Loop
			while(true){
				try{
					waitForConnections(); // Escucha conexiones entrantes
					setupStreams();		 // TODO Configura los Streams
					whileRunning();		 // TODO Mundo Loop
				}catch(EOFException ex){
					mostrarMensaje("\n El server cerró la conexión.");
					ex.printStackTrace();
				}finally{
					closeSockets(); // TODO Cierra todos los sockets
				}
				
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	
	// Espera por conexiones, y muestra los mensajes de conexiones
	private void waitForConnections() throws IOException{
		mostrarMensaje("Esperando conexiones... \n");
		
		this.connection = serverSocket.accept(); // Acepta una conexion
		//TODO enviar connection a la lista de conexiones de GestorComandos (?)
		
		mostrarMensaje("Conexion establecida con:"+connection.getInetAddress().getHostName()+"\n");
	}
	
	
	// Configura el stream para enviar y recibir mensajes
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream()); // la conexion establecida previamente SOCKET CONECTADO -> GestorComandos
		output.flush(); // Vacia la "basura" del buffer
		
		input = new ObjectInputStream(connection.getInputStream()); // Lo mismo pero para el inputStream. Aca recibo informacion.
		mostrarMensaje("\n Streams configurados \n");		
	}
	
	
	// Durante la conexion establecida.. se envian y reciben mensajes
	private void whileRunning() throws IOException{
		String msg = "Ahora estas conectado.";
		mostrarMensaje(msg);
		
		do{
			try{
				msg = (String) input.readObject(); //Leo el input y lo casteo a String ---> Castear a "Comando" o a "Ejecutable" para el GestorComandos
				mostrarMensaje("\n"+msg);
			}catch(ClassNotFoundException ex){
				mostrarMensaje("\n Comando incorrecto! Mensaje no entendido \n");
			}
		}while(true); // Mientras stop == false
	}
	
	
	// Cierra los sockets 
	
	private void closeSockets(){
		mostrarMensaje("Cerrando conexion... \n");
		
		try{
			output.close();
			input.close();
			connection.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	
	// muestra un mensaje en la consola
	private void enviarMensaje(String str){
		try{
			output.writeObject("SERVER: " + str);
			output.flush();
			mostrarMensaje("SERVER: " + str);
		}catch(IOException ex){
			commandWindow.append("\n ERROR \n");
		}
	}
	
	// Actualiza la ventana de la consola
	private void mostrarMensaje(final String text){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						commandWindow.append(text);
					}
				}
		);
	}

}
