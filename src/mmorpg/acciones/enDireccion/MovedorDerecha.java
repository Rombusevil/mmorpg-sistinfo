package mmorpg.acciones.enDireccion;

import java.io.Serializable;

import mmorpg.acciones.Accion;
import mmorpg.entes.actor.Actor;

// Estos moveedores nos permiten crear patrones de movimiento para los NPJ.
public class MovedorDerecha implements Accion,  Serializable {
	private boolean newConnection;

	
	
	public boolean getNewConnection() {
		return newConnection;
	}

	public void setNewConnection(boolean newConnection) {
		this.newConnection = newConnection;
	}
	
	public void actuar(Actor actor){
		actor.moveteDer();
	}
}
