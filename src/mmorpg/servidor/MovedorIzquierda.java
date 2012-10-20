package mmorpg.servidor;

public class MovedorIzquierda implements MovedorEnDireccion {

	public void mover(Actor actor){
		Celda celdaActual, celdaDestino;
		
		celdaActual = actor.getCeldaActual();
		celdaDestino = celdaActual.dameCeldaIz();
		
		celdaDestino.getEstado().ocupaCeldaCon(celdaDestino, actor);
	}

}
