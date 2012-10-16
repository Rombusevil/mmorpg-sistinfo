package mmorpg.servidor;

public abstract class ImpMundo implements Mundo {
	private Celda entradaMundo;
	
	public Celda getEntradaMundo(){
		
		return entradaMundo;
	}
	
	public void setEntradaMundo(Celda c){
		entradaMundo = c;
	}
	
	// Recive el mundo por parámetro así cada implementación de esta clase abs. puede crearse igual
	// 
	public Mundo dimensionar(int alto, int ancho, Mundo mundo){
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
	
	@Override
	public void ponerDibujableEn(int x, int y, Dibujable dibujable, Mundo mundo) {
		
	}
	
	public Celda dameCelda(int x, int y, Mundo mundo){
		
		return entradaMundo;
	}

}
