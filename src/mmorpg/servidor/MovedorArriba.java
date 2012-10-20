package mmorpg.servidor;

public class MovedorArriba implements MovedorEnDireccion {

	public void mover(Actor actor){
		Celda celdaActual, celdaDestino;
		
		celdaActual = actor.getCeldaActual();
		celdaDestino = celdaActual.dameCeldaArriba();
		
		celdaDestino.getEstado().ocupaCeldaCon(celdaDestino, actor);
	}

}
