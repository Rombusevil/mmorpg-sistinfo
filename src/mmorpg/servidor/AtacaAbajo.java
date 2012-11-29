package mmorpg.servidor;

import java.io.Serializable;

public class AtacaAbajo implements AccionEnDireccion, Serializable {
	
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
