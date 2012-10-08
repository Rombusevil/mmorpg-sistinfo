package servidor;

/** Los objetos creados en los métodos son locales a los métodos? no creo están en el heap **/

public class Main {	
	public static void main(String[] args) {
		int alto, ancho;
		Mundo mundo;
		Celda celdaActual;
		
		alto = ancho = 3;
		mundo = generaMundo(alto,ancho);
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
	
	
	public static Mundo generaMundo(int alto, int ancho){
		Mundo mundo = new MundoElfico();
		Celda[][] mundoMatrix = new ImpCelda[alto][ancho];
		Celda celdaOcupada = new ImpCelda();
		Dibujable oc = new OcupaCelda();
		Dibujable libre = new CeldaLibre();
		
		celdaOcupada.setOcupadoPor(oc);
		
		
		//Cargo la matriz con celdas
		for(int i=0; i<alto; i++)
			for(int j=0; j<ancho; j++){
				mundoMatrix[i][j] = new ImpCelda();
				mundoMatrix[i][j].setMyPos(i, j);
				mundoMatrix[i][j].setOcupadoPor(libre);
			}
		
		//Enlazo las celdas de la matriz
		for(int i=0; i<alto; i++)
			for(int j=0; j<ancho; j++){
				if(i==0)
					mundoMatrix[i][j].setCeldaArriba(celdaOcupada);
				else 
					mundoMatrix[i][j].setCeldaArriba(mundoMatrix[i-1][j]);
				
				if(i==alto-1)
					mundoMatrix[i][j].setCeldaAbajo(celdaOcupada);
				else
					mundoMatrix[i][j].setCeldaAbajo(mundoMatrix[i+1][j]);
					
				if(j==0)
					mundoMatrix[i][j].setCeldaIz(celdaOcupada);
				else
					mundoMatrix[i][j].setCeldaIz(mundoMatrix[i][j-1]);
				
				if(j==ancho-1)
					mundoMatrix[i][j].setCeldaDer(celdaOcupada);
				else
					mundoMatrix[i][j].setCeldaDer(mundoMatrix[i][j+1]);
			}
		
		mundo.setEntradaMundo(mundoMatrix[0][0]);
		
		return mundo;
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
