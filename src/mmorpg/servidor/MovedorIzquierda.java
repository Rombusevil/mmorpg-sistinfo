package mmorpg.servidor;

public class MovedorIzquierda implements MovedorEnDireccion {

	public void mover(Actor actor){
		actor.moveteIzq();
	}

}
