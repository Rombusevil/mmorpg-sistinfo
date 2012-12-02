package mmorpg.acciones.enDireccion;

import java.io.Serializable;

import mmorpg.acciones.Accion;
import mmorpg.entes.actor.Actor;

public class AtacaAbajo implements Accion, Serializable {
	
	private boolean newConnection;

	
		
	public boolean getNewConnection() {
		return newConnection;
	}

	public void setNewConnection(boolean newConnection) {
		this.newConnection = newConnection;
	}

	public void actuar(Actor a) {
		a.atacaAbajo();
	}

}
