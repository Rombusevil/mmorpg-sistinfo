package mmorpg.servidor;

import java.io.Serializable;

public class ImpMundo implements Mundo, Serializable {
	private Celda entradaMundo;
	private Celda[][] mundo;
	private int alto, ancho;
	


	//Constructor, dimensiona el mundo.
	/**
	 * 
	 * @param alto
	 * @param ancho
	 * @param vacio
	 */
	public ImpMundo(int alto, int ancho, ImpDibujoVacio vacio){
		this.mundo = new ImpCelda[alto][ancho];
		this.dimensionar(alto,ancho, vacio); // Enlaza las celdas, las pone a todas como libres, pone la imagen de vacio, etc
		this.entradaMundo = mundo[0][0];
		this.alto = alto;
		this.ancho = ancho;		
	}

	//Crea la matriz, la llena con celdas de contenido libre y la rodea con una celda de contenido inaccesible.
	public void dimensionar(int alto, int ancho, ImpDibujoVacio vacio){
		Celda[][] mundoMatrix = this.getMundo();
		Celda celdaOcupada = new ImpCelda();
		ProveedorEstados proveedorEstados = new ImpProveedorEstados();
		//ImpEnteNoAtacable enteNoAtacable = new ImpEnteNoAtacable();
		
		Estado inaccesible = proveedorEstados.getInaccesible(); 
		Estado libre = proveedorEstados.getLibre();
		
		celdaOcupada.setEstado(inaccesible);
		
		
		
		//Cargo la matriz con celdas
		for(int i=0; i<alto; i++)
			for(int j=0; j<ancho; j++){
				mundoMatrix[i][j] = new ImpCelda();
				mundoMatrix[i][j].setMyPos(i, j);
				mundoMatrix[i][j].setEstado(libre);
				mundoMatrix[i][j].setProveedorEstados(proveedorEstados); //A todas las celdas de este mundo les pongo el mismo proveedor de estados
				mundoMatrix[i][j].setDibujo(vacio);	// Le pongo a todas las celdas el dibujo de vacío.
				mundoMatrix[i][j].setVacio(vacio); // Le pongo a todas las celdas cual es el dibujo de vacío que van a usar
				//mundoMatrix[i][j].setEnteNoAtacable(enteNoAtacable);	// Le pongo a todas las celdas cual es su ente no atacable
				//mundoMatrix[i][j].setEnte(enteNoAtacable);	// Le pongo a todas las celdas libres, el ente no atacable
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
	
	// Esto se usa para incializar un actor en el mapa, para mover se usan los estados de celda
	public void poneActorEn(int x, int y, ImpActor actor){
		Celda celdaDestino = this.getCeldaPos(x,y);

		celdaDestino.setEstado(celdaDestino.getEstadoInaccesible()); //Pongo como inaccesible la celda donde quiero ir.
		celdaDestino.setDibujo(actor.getDibujo()); 	// Le pongo a la celda el dibujo del actor que se va a mover ahí.
		celdaDestino.setEnte(actor);				// Le pongo a la celda el actor
		actor.setCeldaActual(celdaDestino);			// Le digo al actor que se movió de celda.
		
	}
	public void ponerDibujableEn(int x, int y, Dibujable dibujable) {
		/**/
	}
	
	public Celda dameCelda(int x, int y, Mundo mundo){		
		return entradaMundo;
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
	
	public int getAlto(){
		return this.alto;
	}
	
	public int getAncho(){
		return this.ancho;
	}	

}
