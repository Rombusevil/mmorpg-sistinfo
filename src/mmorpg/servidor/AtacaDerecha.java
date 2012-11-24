package mmorpg.servidor;

public class AtacaDerecha implements AccionEnDireccion {

	@Override
	public void actuar(Actor a) {
		a.atacaDer();

	}

}
