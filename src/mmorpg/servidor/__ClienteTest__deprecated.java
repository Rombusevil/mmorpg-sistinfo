package mmorpg.servidor;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class __ClienteTest__deprecated extends JFrame{
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String serverIP;
	private Socket connection;
	
	private String msg;
	
	private JTextArea textWindow;
	private JTextField commandLine;	
	

	public __ClienteTest__deprecated(String host){
		super("CLIENTE");
		
		serverIP = host;
		
		// CREA LA VENTANA
		commandLine = new JTextField();	// Linea de comandos
		commandLine.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){ //lo que pasa cuando escribis algo y apretas enter
					enviarMensaje(event.getActionCommand()); 
					commandLine.setText("");
				}
			}
		);		
		add(commandLine, BorderLayout.SOUTH); // Area de Texto
		textWindow = new JTextArea();
		add(new JScrollPane(textWindow), BorderLayout.CENTER);
		setSize(400,400);
		setVisible(true);	
		// FIN VENTANA					
	}

	// Conecta al server
	public void startRunning(){
		try{
			conectarAlServer();
			setupStreams();
			whileRunning();
		}catch(EOFException e){
			mostrarMensaje("\n Client terminated connetion."); // Termina la conexion con el server
		}catch(IOException e){
			e.printStackTrace();	// Si salta este se chingo todo
		}finally{
			cerrarConexion();
		}		
	}
	
	// Se conecta al server
	private void conectarAlServer() throws IOException{
		mostrarMensaje("Intentando conectar...\n");
		connection = new Socket(InetAddress.getByName(serverIP), 3334); // IP + Puerto del server
		mostrarMensaje("Conexion establecida con: "+ connection.getInetAddress().getHostName());
		System.out.println("Conexion establecida con: "+ connection.getInetAddress().getHostName());
	}
	
	// Configura los Streams
	private void setupStreams() throws IOException{
		out = new ObjectOutputStream(connection.getOutputStream());
		out.flush();		
		in = new ObjectInputStream(connection.getInputStream());
		mostrarMensaje("Streams configurados");
	}
	
	private void whileRunning() throws IOException{
		do{
			try{
				msg = (String) in.readObject(); // recibe comando (en este caso lee un string)
				mostrarMensaje("\n" + msg); 		// ejecuta un comando (en este caso escribe el string en la guia)
			}catch(ClassNotFoundException e){
				e.printStackTrace();
				mostrarMensaje("No conozco ese comando\n");
			}			
		}while(!msg.equals("END"));
	}
	
	private void cerrarConexion(){
		try{
			mostrarMensaje("Closing crap down");
			out.close();
			in.close();
			connection.close();			
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	// manda mensaje al server
	// esto podria ser un comando ;)
	private void enviarMensaje(String msg){
		try{
			out.writeObject("CLIENTE:" + msg );
			out.flush();
			mostrarMensaje("\n CLIENTE: " +msg);
		}catch(IOException e){
			textWindow.append("ERROR AL ENVIAR COMANDO, COMANDO NO ENVIADO");
			e.printStackTrace();
		}
	}
	
	private void mostrarMensaje(final String str){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					textWindow.append(str);
				}
			}
		);
	}
	
	
	
	
	
}
