package mmorpg.servidor;

import java.io.*;



public class ControladorTecladoServer {
	//uso un jugador en el constructo temporalmente porque se supone que cuando usemos socket enviara tanto la tecla como el jugador que la presiono.
	public void pedirAccion(ImpControladorJugadores controlar, Actor unJugador) throws IOException{
		System.out.println("Ingrese accion: ");
		char tecla = (char) System.in.read();
		System.out.println(tecla);
		controlar.decodificaMovimientoDeJugador(tecla , unJugador);
	}


}
