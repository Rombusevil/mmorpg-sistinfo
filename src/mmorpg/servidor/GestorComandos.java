package mmorpg.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GestorComandos implements Runnable{
	
	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	

	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
	
	// Configura el stream para enviar y recibir mensajes
		private void setupStreams() throws IOException{
			output = new ObjectOutputStream(connection.getOutputStream()); // la conexion establecida previamente SOCKET CONECTADO -> GestorComandos
			output.flush(); // Vacia la "basura" del buffer
			
			input = new ObjectInputStream(connection.getInputStream()); // Lo mismo pero para el inputStream. Aca recibo informacion.
			//gui.mostrarMensaje("\n Streams configurados \n");		
		}
		
		
		// Durante la conexion establecida.. se envian y reciben mensajes
		private void whileRunning() throws IOException{
			String msg = "Ahora estas conectado.";
			//gui.mostrarMensaje(msg);
			
			do{
				try{
					msg = (String) input.readObject(); //Leo el input y lo casteo a String ---> Castear a "Comando" o a "Ejecutable" para el GestorComandos
					//gui.mostrarMensaje("\n"+msg);
				}catch(ClassNotFoundException ex){
					//gui.mostrarMensaje("\n Comando incorrecto! Mensaje no entendido \n");
				}
			}while(true); // Mientras stop == false
		}
}
