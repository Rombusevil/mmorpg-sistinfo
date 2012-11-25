package mmorpg.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import javax.management.monitor.Monitor;

public class GestorComandos implements Runnable{
	
	private List<Socket> connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Monitor monitor; // Para sincronizar
	

	

	@Override
	public void run() {
		// TODO ACA SE DEBE ESCRIBIR EL "MAIN LOOP" DEL GESTOR DE COMANDOS
		
		// while(true)
		// lee un comando del input <--- Si sos server , esto te llega de un cliente / Si sos cliente esto te llega del server
		// manda a ejecutar un comando <-- Si sos server, cambias el MUNDO../ Si sos cliente, actualizas TU version del mundo
		// escribe el comando en el output <--- Si sos server tenes que forwardear el comando a todo el mundo
		//											Si sos cliente tu lista de sockets es 1 (el server)
		//											Pero no se deberia forwardear este comando al server porque sino se va a ejecutar infinitamente
		//										
		
		
		// PSEUDOCODIGO DE JUAN
		/**
		 Run(){
			  while(!noPara){
			     synchronized(this monitor){  // para el "semaforo"
			            iterator it = this.getSockets iterator()  // agarra el primer socket de la lista
			            while(it.hasNext()){					// Recorre la lista de sockets con el iterator
	                    	ObjectInputStream ois = (ObjectInputStream) it.next(); 
	                      	if (ois.tienedatos() ){  // Si tiene comandos, lo ejecuta
	                        	sleep() // Sleep para no matar al procesador
	                         	Comando cmd   ois.readObject(); // Lee comando
	                         	cmd.perform(this.getMundo() );  // Ejecuta comando
	                  		}
			         	}
			      }
			  }
		 }
		 */
		
		while(true){
			synchronized(this.monitor){
				Iterator it = this.getSockets().iterator(); //Iterador de sockets
				while(it.hasNext()){
					try {
						ObjectInputStream in = (ObjectInputStream) it.next();					
					    Thread.sleep(10);
					    iComando cmd = new CmdJugadorAccion();
					    cmd = (iComando) in.readObject();
					    cmd.ejecutate();					    
					} catch(RuntimeException e) {
					    it.remove();	// Si falla algo (cliente desconectado), remuevo el cliente del iterador
					    e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();	// Si falla el thread
					} catch (ClassNotFoundException e) {
						e.printStackTrace();	// Si falla el in.readObject()
					} catch (IOException e) {
						e.printStackTrace();
					}
				}//end iterator
			}// end monitor
		}// end while(true)				
	}//end run()
	
	private List<Socket> getSockets() {
		return this.connection;
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
