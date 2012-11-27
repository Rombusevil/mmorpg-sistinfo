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
		
		
		
		ImpActor pj = (ImpActor) db.levantaPj(usr, pass);
		ImpDibujo dibujoActor = new ImpDibujoChar('X');
		
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(8);
		
		pj.setDibujo(dibujoActor);
		mundo.poneActorEn( randomInt, randomInt, (ImpActor)pj);
		
		mandarMundoPj(mundo, pj);		
		
//		try {
//			input.close();
//		} catch (IOException e) {
//			System.out.println("Server GS - Error al cerrar InputStream");
//			e.printStackTrace();
//		}
		
		return pj;
	}

}
