package mmorpg.acciones.enDireccion;

import mmorpg.entes.actor.Actor;

public interface AccionEnDireccion {
	void actuar(Actor a);
	
	boolean getNewConnection();
	void setNewConnection(boolean b);

}
