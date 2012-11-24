package mmorpg.servidor;
// Estos moveedores nos permiten crear patrones de movimiento para los NPJ.
public class MovedorDerecha implements AccionEnDireccion {
	
	public void actuar(Actor actor){
		actor.moveteDer();
	}
}
