package mmorpg.servidor;

import java.io.Serializable;

public class AtacaArriba implements AccionEnDireccion, Serializable {

	@Override
	public void actuar(Actor a) {
		a.atacaArriba();
	}

}
