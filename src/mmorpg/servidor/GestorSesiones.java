package mmorpg.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class GestorSesiones {

	private ObjectInputStream input;
	private ObjectOutputStream output;

	
	public GestorSesiones(){
		
	}
	
	public void mandarMundoPj(Mundo mundo, Actor pj){		
		
		try {
			output.writeObject(pj);
			output.flush();
		} catch (IOException e) {
			System.out.println("Error al enviar Pj");
			e.printStackTrace();
		}
		
		try {
			output.writeObject(mundo);
			output.flush();
		} catch (IOException e) {
			System.out.println("Error al enviar Mundo");
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
		Random randomGenerator = new Random();
		
		
		ImpActor pj = (ImpActor) db.levantaPj(usr, pass);
		
		int randomChar= randomGenerator.nextInt(25);
		randomChar += 64;
		char c = (char) randomChar;
		
		ImpDibujo dibujoActor = new ImpDibujoChar(c);
		
		
		int randomInt = randomGenerator.nextInt(8);
		
		pj.setDibujo(dibujoActor);
		mundo.poneActorEn( randomInt, randomInt, (ImpActor)pj);
		
		mandarMundoPj(mundo, pj);	
		return pj;
	}

}
