package mmorpg.servidor;

import java.io.Serializable;

public class AtacaIzquierda implements AccionEnDireccion, Serializable {

	@Override
	public void actuar(Actor a) {
		a.atacaIzq();
	}

}
