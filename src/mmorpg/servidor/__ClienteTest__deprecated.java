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
	
	private Mundo mundo;
	private Actor pj;
	

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
			
			//Mando user y pass HxC
			//FIXME leer esto de un INPUT
			String usr = "Fito11";
			String pwd = "FitoPae11";
			
			
			try{
				out.writeObject(usr);
				out.flush();
				out.writeObject(pwd);
				out.flush();				
			}catch(IOException e){
				textWindow.append("ERROR AL ENVIAR USR/PWD, COMANDO NO ENVIADO");
				e.printStackTrace();
			}
			
			try {
				mundo = (Mundo) in.readObject();
				pj = (Actor) in.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("Error recibiendo Mundo y Pj");
				e.printStackTrace();
			}
			
			System.out.println(mundo);
			System.out.println(pj);
			System.out.println("Nivel"+pj.dameLvl()+" EXP:"+pj.dameXP() );
			
			
			
			
			
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
		connection = new Socket(InetAddress.getByName(serverIP), 3335); // IP + Puerto del server
		mostrarMensaje("Conexion establecida con: "+ connection.getInetAddress().getHostName());
		System.out.println("Conexion establecida con: "+ connection.getInetAddress().getHostName());
	}
	
	// Configura los Streams
	private void setupStreams() throws IOException{
		System.out.println("Configurando Streams...");
		out = new ObjectOutputStream(connection.getOutputStream());
		out.flush();
		System.out.println("OutStream config");
		in = new ObjectInputStream(connection.getInputStream());
		System.out.println("InStream config");
		mostrarMensaje("Streams configurados");
		System.out.println("STREAMS CONFIG");
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
