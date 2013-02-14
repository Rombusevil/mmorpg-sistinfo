package mmorpg.acciones.conexion;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmorpg.entes.actor.Actor;
import mmorpg.entes.actor.ImpActor;
import mmorpg.entes.actor.PJ;
import mmorpg.mundo.Mundo;
import mmorpg.server.database.DataBaseManager;
import mmorpg.server.database.EstadoPjAGuardar;

public class Desconexion implements AccionDeConexion, Serializable {
	//Metodos para sacar el pj de la lista del mundo, y para sacarlo del mundo
	

	@Override
	public void actuaEnListaPj(Actor pj, List<Actor> newListaPj, List<Actor> killListaPj, Mundo mundo, Map.Entry<Socket,Boolean> socket, DataBaseManager db) {
		try {
			db.guardaPj((PJ) pj);
		} catch (NullPointerException e) {
			// Un cliente está mandando null pq no tiene bd. ;)
		}
		
		
		//Esto funca?		
		killListaPj.add(pj);
						
		mundo.getPjList().remove(pj);
		
		socket.setValue(false); //Marco la socketlist para que no se use el socket.
		
		//Cierro el socket.
//		try {
//			socket.getKey().close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void actuaEnMundo(Actor pj, Mundo mundo) {
		pj.exitMundo();
	}

}
