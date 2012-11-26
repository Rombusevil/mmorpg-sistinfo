package mmorpg.servidor;

import java.io.Serializable;

public class MovedorArriba implements AccionEnDireccion,  Serializable {

	public void actuar(Actor actor){
		actor.moveteArriba();
	}

}
