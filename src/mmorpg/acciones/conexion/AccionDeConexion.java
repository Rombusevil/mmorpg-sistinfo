package mmorpg.acciones.conexion;

import java.net.Socket;
import java.util.List;
import java.util.Map.Entry;

import mmorpg.entes.actor.Actor;
import mmorpg.mundo.Mundo;
import mmorpg.server.database.DataBaseManager;

public interface AccionDeConexion {
	void actuaEnListaPj(Actor pj, List<Actor> listaPj, List<Actor> killPjList, Mundo mundo, Entry<Socket, Boolean> socket, DataBaseManager db);//Este método pone/saca al pj en la lista de pjs. 
																	//Le paso mundo para que lo ponga/saque de la lista de pj's del mundo
	
	void actuaEnMundo(Actor pj, Mundo mundo); 						//Este método pone/saca al pj en el mundo
}
