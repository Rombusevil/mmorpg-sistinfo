package mmorpg.servidor;

public class ImpMundo implements Mundo {
	private Celda entradaMundo;
	private Celda[][] mundo;
	private ProveedorEstados provedoEstados;
	


	//Constructor, dimensiona el mundo.
	public ImpMundo(int alto, int ancho){
		this.mundo = new ImpCelda[alto][ancho];
		this.provedoEstados = new ImpProveedorEstados();
		
		this.dimensionar(alto,ancho);		
		this.entradaMundo = mundo[0][0];
		
	}

	//Crea la matriz, la llena con celdas de contenido libre y la rodea con una celda de contenido inaccesible.
	public void dimensionar(int alto, int ancho){
		Celda[][] mundoMatrix = this.getMundo();
		Celda celdaOcupada = new ImpCelda(this);
		
		Estado inaccesible = this.getProvedorEstados().getInaccesible(); 
		Estado libre = this.getProvedorEstados().getLibre();
		
		celdaOcupada.setEstado(inaccesible);
		
		
		//Cargo la matriz con celdas
		for(int i=0; i<alto; i++)
			for(int j=0; j<ancho; j++){
				mundoMatrix[i][j] = new ImpCelda(this);
				mundoMatrix[i][j].setMyPos(i, j);
				mundoMatrix[i][j].setEstado(libre);
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
	}
	
	public void poneActorEn(int x, int y, Actor actor){
		Celda celdaDestino = this.getCeldaPos(x,y);
		
		celdaDestino.setEstado(celdaDestino.getEstadoInaccesible());
		actor.setCeldaActual(celdaDestino);
		
	}
	public void ponerDibujableEn(int x, int y, Dibujable dibujable) {
		/**/
	}
	
	public Celda dameCelda(int x, int y, Mundo mundo){		
		return entradaMundo;
	}
	
	
	public void muestraMundo(int alto, int ancho, Celda celdaActual){
		
		for(int i=0; i<alto; i++){
			for(int j=0; j<ancho; j++){
				celdaActual.imprimeMyPos();
				
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
		System.out.println();
	}

	public Celda getEntradaMundo(){
		
		return entradaMundo;
	}
	
	public void setEntradaMundo(Celda c){
		entradaMundo = c;
	}

	public Celda[][] getMundo() {
		return mundo;
	}

	public Celda getCeldaPos(int x, int y) {
		return this.getMundo()[x][y];
	}
	
	
	public ProveedorEstados getProvedorEstados() {
		return provedoEstados;
	}

	public void setProvedoEstados(ProveedorEstados provedoEstados) {
		this.provedoEstados = provedoEstados;
	}
}
