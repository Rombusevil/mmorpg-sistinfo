package mmorpg.servidor;

public class CeldaLibre implements Estado {

	//Este es el movedor que tiene la celda.
	public void ocupaCeldaCon(Celda celda, Actor actor) {
		Celda celdaAnterior = actor.getCeldaActual();
		
		actor.setCeldaActual(celda);
		celda.setEstado(celda.getMundo().getProvedorEstados().getInaccesible());
		celdaAnterior.setEstado(celda.getMundo().getProvedorEstados().getLibre());
	}
	
	public void imprimite(){
		System.out.print("[ ]");
	}
	
}
