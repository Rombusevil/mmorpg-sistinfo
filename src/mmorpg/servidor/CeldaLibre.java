package mmorpg.servidor;

public class CeldaLibre implements Estado {

	//Este es el movedor que tiene la celda.
	public void ocupaCeldaCon(Celda celda, Actor actor) {
		Celda celdaAnterior = actor.getCeldaActual();
		
		actor.setCeldaActual(celda);
		celda.setEstado(celda.getProveedorEstados().getInaccesible());
		celda.setDibujo(actor.getDibujo());
		
		celdaAnterior.setEstado(celda.getProveedorEstados().getLibre());
		celdaAnterior.setDibujo(celda.getVacio());
	}
}
