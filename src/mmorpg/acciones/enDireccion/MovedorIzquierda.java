package mmorpg.acciones.enDireccion;

import java.io.Serializable;

import mmorpg.entes.actor.Actor;

public class MovedorIzquierda implements AccionEnDireccion,  Serializable {
	private boolean newConnection;

	
	
	public boolean getNewConnection() {
		return newConnection;
	}

	public void setNewConnection(boolean newConnection) {
		this.newConnection = newConnection;
	}

	public void actuar(Actor actor){
		actor.moveteIzq();
	}

}
