package mmorpg.servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {
	
	private DecodificadorTeclas decoderTeclas;
	
	private ObjectOutputStream output; 	// En esta variable se guardan los mensajes para enviar
	private ObjectInputStream input; 	// En esta variable se reciben mensajes desde afuera
	
	public Client(){
		
		
	}
}
