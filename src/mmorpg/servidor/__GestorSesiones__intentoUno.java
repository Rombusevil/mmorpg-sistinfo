package mmorpg.servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class __GestorSesiones__intentoUno implements Runnable{
	private Mundo mundo;
	private GestorComandos gestorComandos;
	
	
	
	private void mandarMundo(Socket socketConectado){
		
	}
	private void mandarPj(Socket socketConectado){
		
	}	
	
	/**
	 * Recibe un pedido por parte de un cliente y establece la conexion.
	 *  
	 * @param socketConectado, verificar la validez de este param.
	 */
	public void establecerConexion(Socket socketConectado){
		Thread t = new Thread();
		Socket socket; 
		ServerSocket serverSocket;

		try{
			serverSocket = new ServerSocket(getPort());
			socket = serverSocket.accept();
			
			mandarMundo(socketConectado);
			mandarPj(socketConectado);		
		}catch(Exception ex){
			System.out.println(ex.getStackTrace());
		}finally{
			//TODO Cerrar sockets
			socket.close();
			serverSocket.close();
		}
		
	}

	/**
	 * Este metodo pide al server por una conexion.
	 * El server se encarga de establecerla.
	 */
	public void pedirConexion(){
		Socket socket; 
		ServerSocket serverSocket;
		
		socket = new Socket(getIp(),getPort());
	}
	
	public Actor recuperarPj(String nombre, String passwd){
		Actor pj;
		
		// STUFF
		
		return pj;
	}
	
	public void agregarCliente(Socket socketConectado){
		this.getGestorComandos().getColeccionClientes().add(socketConectado);
	}
	
	public String getIp(){
		//TODO leer de un conf
		return "192.168.100.1";
	}
	
	public int getPort(){
		//TODO leer de un conf
		return 3699;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	

}
