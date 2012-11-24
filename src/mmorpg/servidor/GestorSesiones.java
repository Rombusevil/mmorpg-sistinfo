package mmorpg.servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GestorSesiones {
	
	private ObjectOutputStream output; 	// En esta variable se guardan los mensajes para enviar
	private ObjectInputStream input; 	// En esta variable se reciben mensajes desde afuera
	
	public void inicializaJugador(Socket socket, Mundo mundo, String user, String pwd){
				
		// Pregunta USER y PWD
		// Levantar PJ de la BD
		// Levanta mundo
		// Crea instancia PJ con los parametros recuperados de la BD
		// TODO agregar metodos recuperarPJ en Actor y FichaDePersonaje, que permiten crear un PJ con los atributos que se deseen
		// Manda PJ y Mundo al cliente
		
		
		// Forwardea el socket al GestorComandos, junto con el PJ
		// para que el GestorComandos cree un mapa con PJ y Socket de cada cliente
	}
	
	
	
	
	
	
	
	
	private Actor recuperarPj(String nombre, String password){
		Actor pj = null;
		// Recupera PJ BD con Nombre y Password
		return pj;
	}

		 
}
