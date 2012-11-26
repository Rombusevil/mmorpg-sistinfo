package mmorpg.servidor;

import java.io.Serializable;

// Estos moveedores nos permiten crear patrones de movimiento para los NPJ.
public class MovedorDerecha implements AccionEnDireccion,  Serializable {
	
	public void actuar(Actor actor){
		actor.moveteDer();
	}
}
