package mmorpg.servidor;

import java.io.Serializable;

public class MovedorAbajo implements AccionEnDireccion,  Serializable {
	private boolean newConnection;

	
	
	public boolean getNewConnection() {
		return newConnection;
	}

	public void setNewConnection(boolean newConnection) {
		this.newConnection = newConnection;
	}

	public void actuar(Actor actor){
		actor.moveteAbajo();
	}

}
