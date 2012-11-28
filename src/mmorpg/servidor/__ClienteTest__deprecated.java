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
import java.util.Random;

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
	
	private ImpMundo mundo;
	private Actor pj;
	
	private Ventana ventana;
	private GestorComandos gc;
	private Boolean isRunning = true;
	ImpImprimidorMundosCLI imprimidor;

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
		setVisible(false);	
		// FIN VENTANA			
		
		gc = new GestorComandos();
	}

	// Conecta al server
	public void startRunning(){
		try{
			conectarAlServer();
			setupStreams();
			
			//Mando user y pass HxC
			//FIXME leer esto de un INPUT
			Random randomGenerator = new Random();
			int randomChar= randomGenerator.nextInt(25);
			randomChar += 64;
			
			int randomChar2= randomGenerator.nextInt(25);
			randomChar2 += 64;
			
			char c = (char) randomChar ;
			char c2 = (char) randomChar2 ;
			
			String usr = "Paez"+c+c2;
			String pwd = "FitoPae"+c2;
			
			
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
				pj = (Actor) in.readObject();				
				mundo = (ImpMundo) in.readObject();				
			} catch (ClassNotFoundException e) {
				System.out.println("Error recibiendo Mundo y Pj");
				e.printStackTrace();
			}
			
			
			gc.setSocket(connection, in, out);
			
			ventana = new Ventana(pj, gc, connection);
			
			
			System.out.println(mundo);
			System.out.println(pj);
			System.out.println("Nivel"+pj.dameLvl()+" EXP:"+pj.dameXP());
			
			imprimidor = new ImpImprimidorMundosCLI();
			
			
			
			
			whileRunning();
		}catch(EOFException e){
			mostrarMensaje("\n Client terminated connetion."); // Termina la conexion con el server
		}catch(IOException e){
			e.printStackTrace();	// Si salta este se chingo todo
		}finally{
			cerrarConexion();
		}		
	}
	
	
	
	
	
	//Main loop
	private void whileRunning() throws IOException{
		imprimidor.imprimi(mundo);
		do{
			
			ventana.imprimeDatosPj();
			
			//ClearScreen
			//Runtime.getRuntime().exec("cls");
			//Runtime.getRuntime().exec("clear");
			
		}while(isRunning);
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
		System.out.println("Cliente - Configurando Streams...");
		out = new ObjectOutputStream(connection.getOutputStream());
		System.out.println("Cliente - Outstream configurado");
		//out.flush();
		in = new ObjectInputStream(connection.getInputStream());
		System.out.println("Cliente - Streams configurados");
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
