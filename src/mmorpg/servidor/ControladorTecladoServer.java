package mmorpg.servidor;

import java.io.*;
import java.awt.event.KeyListener; // Habría que usar un key listener para no tener que apretar ENTER.



public class ControladorTecladoServer {
	//uso un jugador en el constructor temporalmente porque se supone que cuando usemos socket enviara tanto la tecla como el jugador que la presiono.
	public void pedirAccion(ImpControladorJugadores controlar, Actor unJugador) throws IOException{
//		System.out.println("Ingrese accion: ");
		char tecla = (char) System.in.read();
		controlar.decodificaMovimientoDeJugador(Character.toLowerCase(tecla), unJugador);
	}
}
