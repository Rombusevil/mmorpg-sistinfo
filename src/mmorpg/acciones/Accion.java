package mmorpg.acciones;

import mmorpg.entes.actor.Actor;

public interface Accion {
	void actuar(Actor a);
	
	
	
	
	//Esto hay que borrarlo de acá y hacer el comando conexión
	/** @deprecated	 */
	boolean getNewConnection();
	/** @deprecated	 */
	void setNewConnection(boolean b);
}
