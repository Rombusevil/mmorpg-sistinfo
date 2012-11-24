package mmorpg.servidor;

public class AtacaArriba implements AccionEnDireccion {

	@Override
	public void actuar(Actor a) {
		a.atacaArriba();
	}

}
