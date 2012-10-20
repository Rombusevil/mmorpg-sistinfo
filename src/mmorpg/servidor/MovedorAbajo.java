package mmorpg.servidor;

public class MovedorAbajo implements MovedorEnDireccion {

	public void mover(Actor actor){
		Celda celdaActual, celdaDestino;
		
		celdaActual = actor.getCeldaActual();
		celdaDestino = celdaActual.dameCeldaAbajo();
		
		celdaDestino.getEstado().ocupaCeldaCon(celdaDestino, actor);
	}

}
