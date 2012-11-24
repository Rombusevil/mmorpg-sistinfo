package mmorpg.servidor;

public class AtacaAbajo implements AccionEnDireccion {

	@Override
	public void actuar(Actor a) {
		a.atacaAbajo();
	}

}
