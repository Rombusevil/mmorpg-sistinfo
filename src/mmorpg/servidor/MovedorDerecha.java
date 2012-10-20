package mmorpg.servidor;

public class MovedorDerecha implements MovedorEnDireccion {
	
	public void mover(Actor actor){
		Celda celdaActual, celdaDestino;
		
		celdaActual = actor.getCeldaActual();
		celdaDestino = celdaActual.dameCeldaDer();
		
		celdaDestino.getEstado().ocupaCeldaCon(celdaDestino, actor);
	}
}
