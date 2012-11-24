package mmorpg.servidor;

public class AtacaIzquierda implements AccionEnDireccion {

	@Override
	public void actuar(Actor a) {
		a.atacaIzq();
	}

}
