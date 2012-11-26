package mmorpg.servidor;

import java.io.Serializable;

public class MovedorIzquierda implements AccionEnDireccion,  Serializable {

	public void actuar(Actor actor){
		actor.moveteIzq();
	}

}
