package mmorpg.servidor;

import java.io.Serializable;

public class AtacaAbajo implements AccionEnDireccion, Serializable {

	public void actuar(Actor a) {
		a.atacaAbajo();
	}

}
