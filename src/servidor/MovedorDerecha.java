package servidor;

public class MovedorDerecha implements MovedorEnDireccion {
	
	public void mover(Actor a){
		Celda celdaActual, celdaDestino;
		
		celdaActual = a.getPosicion();
		celdaDestino = celdaActual.dameCeldaDer();
		a.setPosicion(celdaDestino);
	}
}
