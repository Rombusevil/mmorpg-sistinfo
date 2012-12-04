package mmorpg.acciones.conexion;

import java.util.List;

import mmorpg.entes.actor.Actor;
import mmorpg.mundo.Mundo;

public class Desconexion implements AccionDeConexion {
	//Metodos para sacar el pj de la lista del mundo, y para sacarlo del mundo
	

	@Override
	public void actuaEnListaPj(Actor pj, List<Actor> listaPj, Mundo mundo) {
		//Esto funca?
		listaPj.remove(pj); 
		mundo.getPjList().remove(pj);
	}

	@Override
	public void actuaEnMundo(Actor pj, Mundo mundo) {
		pj.exitMundo();
	}
}
