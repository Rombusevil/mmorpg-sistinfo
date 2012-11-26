package mmorpg.servidor;

import java.io.Serializable;

public class MovedorAbajo implements AccionEnDireccion,  Serializable {

	public void actuar(Actor actor){
		actor.moveteAbajo();
	}

}
