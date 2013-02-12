package mmorpg.acciones.conexion;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmorpg.entes.actor.Actor;
import mmorpg.mundo.Mundo;

public class Desconexion implements AccionDeConexion, Serializable {
	//Metodos para sacar el pj de la lista del mundo, y para sacarlo del mundo
	

	@Override
	public void actuaEnListaPj(Actor pj, List<Actor> newListaPj, List<Actor> killListaPj, Mundo mundo, Map.Entry<Socket,Boolean> socket, Map.Entry<Socket,Boolean> socketList) {
		//Esto funca?
		killListaPj.add(pj); 
		mundo.getPjList().remove(pj);
		
		socket.setValue(false); //Marco la socketlist para que no se use el socket.
		//Cierro el socket.
		try {
			socket.getKey().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		socketList.remove(socket);
//		try {
//			socket.close();
//		} catch (IOException e) {
//			System.out.println("No le gusto cerrar el socket");
//			e.printStackTrace();
//		}
	}

	@Override
	public void actuaEnMundo(Actor pj, Mundo mundo) {
		pj.exitMundo();
	}
}
