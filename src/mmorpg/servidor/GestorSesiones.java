package mmorpg.servidor;

import java.io.ObjectInputStream;
import java.net.Socket;

public class GestorSesiones {

	private ObjectInputStream input;

	
	public GestorSesiones(){
		
	}
	
	
	public Actor initPJ(Socket socketConectado, DataBaseManager db) {
		
		// FIXME User y Pass estan HxC
		String usr = "Pepe";
		String pass = "PepeNumbero";		
		
		return db.levantaPj(usr, pass);
	}

}
