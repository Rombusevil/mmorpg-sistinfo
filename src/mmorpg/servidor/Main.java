package mmorpg.servidor;

/** Los objetos creados en los métodos son locales a los métodos? no creo, están en el heap **/

public class Main {	
	public static void main(String[] args) {
		int alto, ancho;
		Mundo mundo;
		Celda celdaActual;
		
		alto = ancho = 3;	//Dimensión del mundo
		mundo = new ImpMundo(alto,ancho);
		
		celdaActual = mundo.getEntradaMundo();
		
		mundo.muestraMundo(alto, ancho, celdaActual);

		
		
		
		
	
	/** Métodos para lista cuadruple enlazada **/
	/*
	public Celda recorreDerechaHastaOcupado(Celda c){
		if (c.dameCeldaDer() == null) //llegó al final
			return c;
		
		return recorreDerechaHastaOcupado(c.dameCeldaDer());
	}
	public Celda recorreAbajoHastaOcupado(Celda c){
		if (c.dameCeldaAbajo() == null) //llegó al final
			return c;
		
		return recorreDerechaHastaOcupado(c.dameCeldaAbajo());
	}
	*/
	}
}
