package mmorpg.servidor;

public class CeldaLibre implements Estado {
	
//	//Se devuelve a ella mísma así el pj que movió dejá "libre" la celda en la que estaba
//	public void ocupaCeldaConDibujable(Celda celdaDestino, Actor actor){
//		Celda celdaActual = actor.getCeldaActual();
//		
//		actor.setCeldaActual(celdaDestino);
//		celdaDestino.setOcupadoPor(); //CELDA INACCESIBLE
//		
//		celdaActual.setOcupadoPor(this); //La celda donde estaba el pj antes, ahora está ocupada por celda libre
//	}

	public void ocupaCeldaCon(Celda celda, Actor actor) {
		Celda celdaAnterior = actor.getCeldaActual();
		
		actor.setCeldaActual(celda);
		celda.setEstado(/*hablando mal y pronto:*/ new CeldaInaccesible());
		
		 //Pongo en libre la celda donde estaba el actor antes
		/*** Acá crashea si el actor no tiene una celda actual, osea este método sirve para mover al actor
		 * no para posicionarlo por primera vez.
		 */
		/** arreglando este problema mal y pronto: */
		if(celdaAnterior == null){
			celdaAnterior = new ImpCelda(); //Que cabeceada punga!!
		}
		
		celdaAnterior.setEstado(/*hablando mal y pronto otra vez:*/ new CeldaLibre());
	}
	
}
