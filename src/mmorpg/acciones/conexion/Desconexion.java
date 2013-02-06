package mmorpg.acciones.conexion;

import java.io.Serializable;
import java.util.List;

import mmorpg.entes.actor.Actor;
import mmorpg.mundo.Mundo;

public class Desconexion implements AccionDeConexion, Serializable {
	//Metodos para sacar el pj de la lista del mundo, y para sacarlo del mundo
	

	@Override
	public void actuaEnListaPj(Actor pj, List<Actor> newListaPj, List<Actor> killListaPj, Mundo mundo) {
		//Esto funca?
		killListaPj.add(pj); 
		mundo.getPjList().remove(pj);
	}

	@Override
	public void actuaEnMundo(Actor pj, Mundo mundo) {
		pj.exitMundo();
	}
}