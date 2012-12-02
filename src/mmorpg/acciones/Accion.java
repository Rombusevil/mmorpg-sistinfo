package mmorpg.acciones;

import mmorpg.entes.actor.Actor;

public interface Accion {
	void actuar(Actor a);
	
	boolean getNewConnection();
	void setNewConnection(boolean b);

}
