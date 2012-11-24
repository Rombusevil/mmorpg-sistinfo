package mmorpg.servidor;

public class MovedorAbajo implements AccionEnDireccion {

	public void actuar(Actor actor){
		actor.moveteAbajo();
	}

}
