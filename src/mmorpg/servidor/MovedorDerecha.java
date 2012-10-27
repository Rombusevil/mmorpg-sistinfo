package mmorpg.servidor;
// Estos moveedores nos permiten crear patrones de movimiento para los NPJ.
public class MovedorDerecha implements MovedorEnDireccion {
	
	public void mover(Actor actor){
		actor.moveteDer();
	}
}
