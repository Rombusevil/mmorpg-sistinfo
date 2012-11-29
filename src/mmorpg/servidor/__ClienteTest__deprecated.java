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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
	
	private JTextArea textWindow;
	private JTextField commandLine;	
	
	private ImpMundo mundo;
	private Actor pj;
	
	private Ventana ventana;
	private GestorComandos gc;
	private Boolean isRunning = true;
	ImpImprimidorMundosCLI imprimidor;
	ImpImprimidorMundosCLIJframe impJframe;

	public __ClienteTest__deprecated(String host){	
		serverIP = host;
		gc = new GestorComandos(false); // Crea el GC en el cliente (false es para indicar que no es Server)
	}

	// Conecta al server
	public void startRunning(){
		try{

			conectarAlServer();			
			setupStreams();
			escucharComandos();
			//Mando user y pass HxC
			//FIXME leer esto de un INPUT
			Random randomGenerator = new Random();
			int randomChar= randomGenerator.nextInt(25);
			randomChar += 64;
			
			int randomChar2= randomGenerator.nextInt(25);
			randomChar2 += 64;
			
			char c = (char) randomChar ;
			char c2 = (char) randomChar2 ;
			
			String usr = ""+c;
			String pwd = "blapasswordbla"+c2;
			
			//Mando user y pass
			try{
				out.writeObject(usr);
				out.flush();
				out.writeObject(pwd);
				out.flush();				
			}catch(IOException e){
				textWindow.append("ERROR AL ENVIAR USR/PWD, COMANDO NO ENVIADO");
				e.printStackTrace();
			}			
			//FIN USR/PWD
			List<Actor> listaDePjsExistentes = new LinkedList<Actor>();
			//Recibo el PJ de la BD y el Mundo
			try {
				pj = (Actor) in.readObject();				
				mundo = (ImpMundo) in.readObject();				
				listaDePjsExistentes = (List<Actor>) in.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("Error recibiendo Mundo y Pj");
				e.printStackTrace();
			}
			//FIN RECIBIR PJ y MUNDO
			
						
			
			
			//Agrega el PJ recibido al GC para	utilizarlo en la copia local del mundo
			gc.agregarPjSocket(pj,connection);
			gc.setPjCliente(this.pj);
			System.out.println(listaDePjsExistentes);
			gc.setPjList(listaDePjsExistentes);
			
			
			
			AccionEnDireccion nuevaConexion = new MovedorAbajo(); //No importa el tipo de accion
			nuevaConexion.setNewConnection(true);  // Lo que importa es mandar un comando con el campo en true
			iComando commandoConnection = new CmdJugadorAccion();
			commandoConnection.setAccion(nuevaConexion);
			commandoConnection.setPj(pj);
			gc.mandarComando(commandoConnection, connection);
			
			//pjCollector();
			//gc.setPjList(mundo.getPjList());

			
			
			// Ventana con los Datos del PJ y el foco del teclado
			ventana = new Ventana(pj, gc, connection); 		
			
			
			
//			System.out.println(mundo);
//			System.out.println(pj);
//			System.out.println("Nivel"+pj.dameLvl()+" EXP:"+pj.dameXP());
			

			imprimidor = new ImpImprimidorMundosCLI();
			impJframe = new ImpImprimidorMundosCLIJframe();
			

			
			
			whileRunning();
		}catch(EOFException e){
			System.out.println("Cliente cerro la conexion");
		}catch(IOException e){
			System.out.println("CL - SE CHNGO TODO");
			e.printStackTrace();	// Si salta este se chingo todo
		}finally{
			cerrarConexion();
		}		
	}
	
	
	
	
	
	//Main loop
	private void whileRunning() throws IOException{	
		do{
			ventana.imprimeDatosPj();
			
			ventana.imprimiMundo(impJframe.dameMundoString(mundo));
			agregarNuevosJugadores();
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}while(isRunning);
	}
	
	/**
	 * Inicia el run() del GestorComandos
	 */
	private void escucharComandos(){
//		System.out.println("SV - GC - Estoy escuchando comandos");
		Thread t = new Thread(gc);
		t.start();		
	}	
	
	public void agregarNuevosJugadores(){
		LinkedList<Actor> newPjListAux;
		LinkedList<Actor> listAux = new LinkedList<Actor>();
		newPjListAux = (LinkedList<Actor>) gc.getNewPjList();

		Iterator<Actor> it = newPjListAux.iterator();
		while (it.hasNext()) {
			Actor pjNuevo = it.next();

			if( ! (((PJ)pjNuevo).getUsr().equals(((PJ)this.pj).getUsr())) ){
				//gc.getPjList().add(pjNuevo);
				mundo.poneActorEn(1, 1,(ImpActor) pjNuevo);
			}
			listAux.add(pjNuevo);
		}
		
		newPjListAux.removeAll(listAux);
		listAux = null;		
	}
	
	public void pjCollector(){
		for(int i=0; i < mundo.getAlto(); i++){
			for(int j=0; j < mundo.getAncho(); j++){
				try{
					Actor actorColectado =	(Actor) mundo.getCeldaPos(i, j).getEnte();					
					if( !(gc.getPjList().contains(actorColectado)) ){
						System.out.println("Pj Collector compare: "+ !(gc.getPjList().contains(actorColectado)));
						System.out.println("Collecte un Pj y lo puse en la Gc.pjList");
						this.gc.getPjList().add(actorColectado);
					}
				}catch(NullPointerException e){
					System.out.println("null pointer ex!! PJ COLLECTOR");
				}
			}
		}
	}
	
	
	// Se conecta al server
	private void conectarAlServer() throws IOException{
		connection = new Socket(InetAddress.getByName(serverIP), 3335); // IP + Puerto del server
		System.out.println("Conexion establecida con: "+ connection.getInetAddress().getHostName());
	}
	
	// Configura los Streams
	private void setupStreams() throws IOException{
		System.out.println("Cliente - Configurando Streams...");
		out = new ObjectOutputStream(connection.getOutputStream());
		System.out.println("Cliente - Outstream configurado");
		out.flush();
		in = new ObjectInputStream(connection.getInputStream());
		System.out.println("Cliente - Streams configurados");
	}	
	
	//Cierra todo el crap down
	private void cerrarConexion(){
		try{
			out.close();
			in.close();
			connection.close();			
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	
	
}
