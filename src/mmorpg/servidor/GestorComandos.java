package mmorpg.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import javax.management.monitor.Monitor;

public class GestorComandos implements Runnable{
	
	private List <Socket> connection;
	private List <ObjectOutputStream> output;
	private List <ObjectInputStream> input;
	private Object monitor; // Para sincronizar el inputStream
	private Object monitorOut; // Para sincronizar el outputStream
	

	

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
					    // if si es server
					    //	forwardearComando(cmd);
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
	
	
	
	/**
	 * Metodo que recibe un CMD, lo pone en el ObjectOutputStream
	 * y lo envia al socket correspondiente.
	 * @param cmd
	 */
	public void mandarComando(iComando cmd){
		
	}
	
	
	/**
	 * Agrega un InputStream a la lista
	 * @param in
	 */
	public void addInput(ObjectInputStream in){
		monitor = new Object();
		synchronized (this.monitor){
			this.getInput().add(in);
		}
	}
	
	/**
	 * Agrega un OutputStream a la lista
	 * @param out
	 */
	public void addOutput(ObjectOutputStream out){
		monitorOut = new Object();
		synchronized (this.monitorOut){
			this.getOutput().add(out);
		}
	}
	
	private List <ObjectOutputStream> getOutput() {
		return output;
	}
	

	private List <ObjectInputStream> getInput() {
		return input;
	}
	
	private List <Socket> getSockets() {
		return this.connection;
	}


	/**
	 * closes() la lista de OutputStream
	 */
	public void closeOutput() {
		try{
			Iterator it = this.getOutput().iterator();
			while(it.hasNext()){
				ObjectOutputStream out = (ObjectOutputStream) it.next();
				out.close();
			}			
		}catch(IOException ex){
			System.out.println("Error al cerrar GestorComandos->OutputStreams");
			ex.printStackTrace();
		}		
	}

	/**
	 * closes() la lista de InputStrams
	 */
	public void closeInput() {
		try{
			Iterator it = this.getInput().iterator();
			while(it.hasNext()){
				ObjectInputStream in = (ObjectInputStream) it.next();
				in.close();
			}			
		}catch(IOException ex){
			System.out.println("Error al cerrar GestorComandos->InputStreams");
			ex.printStackTrace();
		}		
	}
	
	public void closeSockets() {
		try{
			Iterator it = this.getSockets().iterator();
			while(it.hasNext()){
				Socket skt = (Socket) it.next();
				skt.close();
			}			
		}catch(IOException ex){
			System.out.println("Error al cerrar GestorComandos->Sockets");
			ex.printStackTrace();
		}		
	}

	
	

}
