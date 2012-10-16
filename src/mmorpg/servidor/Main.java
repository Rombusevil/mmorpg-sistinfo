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
		
		
		// Imprime matriz		
		for(int i=0; i<alto; i++){
			for(int j=0; j<ancho; j++){
				celdaActual.imprimeMyPos();
				System.out.print(celdaActual.getOcupadoPor());
				
				if(j < ancho -1)
					celdaActual = celdaActual.dameCeldaDer();
			}
			
			//Voy una más abajo si no estoy en el límite
			if(i<alto-1)
				celdaActual = celdaActual.dameCeldaAbajo();
			
			System.out.println();
			
			//Voy todo a la iz para empezar a imprimir de iz a der
			for(int j=0; j<ancho-1; j++){				
				celdaActual = celdaActual.dameCeldaIz();
			}
		}
	}
	
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
