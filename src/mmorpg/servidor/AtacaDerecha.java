package mmorpg.servidor;

import java.io.Serializable;

public class AtacaDerecha implements AccionEnDireccion, Serializable {
	
	private boolean newConnection;

	
	
	public boolean getNewConnection() {
		return newConnection;
	}

	public void setNewConnection(boolean newConnection) {
		this.newConnection = newConnection;
	}

	@Override
	public void actuar(Actor a) {
		a.atacaDer();

	}

}
