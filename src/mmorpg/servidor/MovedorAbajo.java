package mmorpg.servidor;

public class MovedorAbajo implements MovedorEnDireccion {

	public void mover(Actor actor){
		actor.moveteAbajo();
	}

}
