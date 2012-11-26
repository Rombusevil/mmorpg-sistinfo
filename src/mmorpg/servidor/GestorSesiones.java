package mmorpg.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GestorSesiones {

	private ObjectInputStream input;
	private ObjectOutputStream output;

	
	public GestorSesiones(){
		
	}
	
	
	
	public void mandarMundoPj(Mundo mundo, Actor pj){
		
		try {
			output.writeObject(mundo);
			output.writeObject(pj);
		} catch (IOException e) {
			System.out.println("Error al enviar Mundo y Pj");
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	public Actor initPJ(Socket socketConectado, DataBaseManager db, Mundo mundo) {
		
		String usr = null;
		String pass = null;
		
		try {
			input = new ObjectInputStream(socketConectado.getInputStream());
			output = new ObjectOutputStream(socketConectado.getOutputStream());
			System.out.println("Streams del Server Configurados");
			
		} catch (IOException e) {
			System.out.println("Error al configurar los Streams");
			e.printStackTrace();
		}
		
		try{
			usr = (String) input.readObject();
			pass = (String) input.readObject();
		}catch(ClassNotFoundException e){
			System.out.println("Recibi cualquier mierda");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error IOException");
			e.printStackTrace();
		}	
		
		Actor pj = db.levantaPj(usr, pass);
		mandarMundoPj(mundo, pj);		
		
		return pj;
	}

}
