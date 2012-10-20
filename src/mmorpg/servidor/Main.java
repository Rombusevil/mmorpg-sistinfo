package mmorpg.servidor;

/** Los objetos creados en los métodos son locales a los métodos? no creo, están en el heap **/

public class Main {	
	public static void main(String[] args) {
		int alto, ancho;
		Mundo mundo;
		Celda entradaMundo;
		Actor actor = new PJ();
		MovedorDerecha movedorDer = new MovedorDerecha();
			
			
		
		alto = ancho = 3;	//Dimensión del mundo
		mundo = new ImpMundo(alto,ancho);
		entradaMundo = mundo.getEntradaMundo();
		
		mundo.poneActorEn(0, 1, actor);		
		
		mundo.muestraMundo(alto, ancho, entradaMundo);
		
		movedorDer.mover(actor);
		
		
		mundo.muestraMundo(alto, ancho, entradaMundo);
		

		
		
		
		
	
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
