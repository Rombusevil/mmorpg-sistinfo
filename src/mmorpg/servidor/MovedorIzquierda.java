package mmorpg.servidor;

public class MovedorIzquierda implements AccionEnDireccion {

	public void actuar(Actor actor){
		actor.moveteIzq();
	}

}
