package mmorpg.servidor;

import java.io.Serializable;

public class AtacaDerecha implements AccionEnDireccion, Serializable {

	@Override
	public void actuar(Actor a) {
		a.atacaDer();

	}

}
