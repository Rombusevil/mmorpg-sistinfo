package mmorpg.acciones.conexion;

import java.io.Serializable;
import java.util.List;

import mmorpg.entes.actor.Actor;
import mmorpg.entes.actor.ImpActor;
import mmorpg.mundo.Mundo;

public class Conexion implements AccionDeConexion, Serializable {
	//Metodos para poner el pj en la lista del mundo, y para ponerlo en algún lugar del mundo
	


	@Override
	public void actuaEnListaPj(Actor pj, List<Actor> newListaPj, List<Actor> killListaPj, Mundo mundo) {
		try{
			newListaPj.add(pj);
			mundo.getPjList().add(pj);
		}catch(Exception e){
			System.out.println("Server - recibi NULL en newListaPj (OK)");
			e.printStackTrace();
		}
		
	}

	@Override
	public void actuaEnMundo(Actor pj, Mundo mundo) {
		//FIXME definir algorítmo de posicionamiento o celda respawn.
		//Por el momento lo tiro en 1,1
		int x, y;
		x = y = 1;
		
		mundo.poneActorEn(x, y, (ImpActor)pj);
	}	
}