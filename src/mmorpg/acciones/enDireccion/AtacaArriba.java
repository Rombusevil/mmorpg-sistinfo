package mmorpg.acciones.enDireccion;

import java.io.Serializable;

import mmorpg.acciones.Accion;
import mmorpg.entes.actor.Actor;

public class AtacaArriba implements Accion, Serializable {

	private boolean newConnection;

	
	
	public boolean getNewConnection() {
		return newConnection;
	}

	public void setNewConnection(boolean newConnection) {
		this.newConnection = newConnection;
	}
	
	@Override
	public void actuar(Actor a) {
		a.atacaArriba();
	}

}
