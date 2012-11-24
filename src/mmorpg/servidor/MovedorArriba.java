package mmorpg.servidor;

public class MovedorArriba implements AccionEnDireccion {

	public void actuar(Actor actor){
		actor.moveteArriba();
	}

}
