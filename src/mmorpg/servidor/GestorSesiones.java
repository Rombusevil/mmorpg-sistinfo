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
		ImpActor pj = new PJ();
		// <Recupera PJ BD con Nombre y Password>
		
		// <Parsea los datos en las variables de abajo>
		
		// Datos para cargar la ficha de PJ, los dejo HxC
		// Pero habria que parsear lo que devuelve la BD
		// y meter cada valor en cada variable
		
		String nombrePj = "Prumpi_PieDeHierro";
		int lvl = 1;
		int xp = 30;
		int str = 10;
		int dex = 8;
		int vit = 9;
		String nombreItem = "Porra";
		int dmgItem = 12;
		int armorItem = 20;
		double atkSpdItem = 1;
		
		pj.cargaFichaPj(nombrePj, lvl, xp, str, dex, vit, nombreItem, dmgItem, armorItem, atkSpdItem);
		
		return pj;
	}

		 
}
