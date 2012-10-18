package mmorpg.servidor;

/** Los objetos creados en los métodos son locales a los métodos? no creo, están en el heap **/

public class Main {	
	public static void main(String[] args) {
		int alto, ancho;
		Mundo mundo = new MundoElfico();
		Celda celdaActual;
		
		alto = ancho = 3;
		mundo.dimensionar(alto, ancho, mundo);
		celdaActual = mundo.getEntradaMundo();
		
		
		// Imprime matriz, no ser�a mejor dejar el imprime Matriz como un metodo de ImpMundo y llamarlo en un bucle dentro del main??
		
		//muestraMundo(alto,ancho,celdaActual)
		mundo.muestraMundo(alto, ancho, celdaActual);
		//ac� estaba el for antes.
	
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
