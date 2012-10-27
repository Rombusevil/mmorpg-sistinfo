package mmorpg.servidor;

public class MovedorArriba implements MovedorEnDireccion {

	public void mover(Actor actor){
		actor.moveteArriba();
	}

}
