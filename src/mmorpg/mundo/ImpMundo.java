package mmorpg.mundo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import mmorpg.entes.actor.Actor;
import mmorpg.entes.actor.ImpActor;
import mmorpg.userInterface.output.ImpDibujoVacio;

public class ImpMundo implements Mundo, Serializable {
	private Celda entradaMundo;
	private Celda[][] mundo;
	private int alto, ancho;
	
	private List<Actor> pjList; // Son todos los PJs que estan en el mundo
	


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
		pjList = new LinkedList<Actor>();
	}

	//Crea la matriz, la llena con celdas de contenido libre y la rodea con una celda de contenido inaccesible.
	public void dimensionar(int alto, int ancho, ImpDibujoVacio vacio){
		Celda[][] mundoMatrix = this.getMundo();
		Celda celdaOcupada = new ImpCelda();
		ProveedorEstadosCelda proveedorEstados = new ImpProveedorEstados();
		//ImpEnteNoAtacable enteNoAtacable = new ImpEnteNoAtacable();
		
		OcupadorCeldaSegunEstado inaccesible = proveedorEstados.getInaccesible(); 
		OcupadorCeldaSegunEstado libre = proveedorEstados.getLibre();
		
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
		Celda celdaDestino;
		int col,fil,aux,extremo1X,extremo1Y,extremo2X,extremo2Y,extremo3X,extremo3Y,extremo4X,extremo4Y;
		boolean salir=false;
		
		celdaDestino=mundo[x][y];
		this.agregaPjListaMundo(actor);
		col=y;
		fil=x;
		aux=1;
		
		System.out.println("ubicando actor");
		
		while(!salir){
			if(mundo[fil][col].getEstado().getClass().getName() == "mmorpg.mundo.CeldaLibre"){
				celdaDestino= mundo[fil][col];
				salir=true;
			}
			
			//acomodo extremos para el recorrido
			extremo1X=fil-aux;
			extremo1Y=col+aux;
			extremo2X=fil-aux;
			extremo2Y=col-aux;
			extremo3X=fil+aux;
			extremo3Y=col-aux;
			extremo4X=fil+aux;
			extremo4Y=col+aux;
			//-----------------------------------
			
			//compruebo los margenes
			if(extremo1Y>ancho)
				extremo1Y=ancho;
			if(extremo1X<0)
				extremo1X=0;
			
			if(extremo2Y<0)
				extremo2Y=0;
			if(extremo2X<0)
				extremo2X=0;
			
			if(extremo3Y<0)
				extremo3Y=0;
			if(extremo3X>alto)
				extremo1X=alto;
			
			if(extremo4Y>ancho)
				extremo1Y=ancho;
			if(extremo4X>alto)
				extremo4X=alto;
			
			//----------------------------------------------
			if(!salir)
				for(y=extremo1Y;y>=extremo2Y;y--){
					if(mundo[extremo1X][y].getEstado().getClass().getName() == "mmorpg.mundo.CeldaLibre"){
						celdaDestino= mundo[extremo1X][y];
						
						salir=true;
						extremo2Y=50;
					}

					
				}
			if(!salir)
				for(x=extremo2X;x<=extremo3X;x++){
					if(mundo[x][extremo2Y].getEstado().getClass().getName() == "mmorpg.mundo.CeldaLibre"){
						celdaDestino= mundo[x][extremo2Y];
						
						salir=true;
						extremo3X=-50;
					}
				}
			if(!salir){
				x--;
				for(y=extremo3Y;y<=extremo4Y;y++){
					if(mundo[extremo4X][y].getEstado().getClass().getName() == "mmorpg.mundo.CeldaLibre"){
						celdaDestino= mundo[extremo4X][y];
						salir=true;
						extremo4Y=-50;
					}
				}
			}
			if(!salir)
				for(x=extremo4X;x>=extremo1X;x--){
					if(mundo[x][extremo1Y].getEstado().getClass().getName() == "mmorpg.mundo.CeldaLibre"){
						celdaDestino= mundo[x][extremo1Y];
						salir=true;
						extremo1X=50;
					}
				}	
			aux++;				
		}
		celdaDestino.setEstado(celdaDestino.getEstadoInaccesible()); //Pongo como inaccesible la celda donde quiero ir.
		celdaDestino.setDibujo(actor.getDibujo()); 	// Le pongo a la celda el dibujo del actor que se va a mover ah�.
		celdaDestino.setEnte(actor);
		actor.setCeldaActual(celdaDestino);
	}
//	public void ponerDibujableEn(int x, int y, Dibujable dibujable) {
//		/**/
//	}
	
	
	/*
	 * Este metodo recorre la lista de PJs y pregunta cual esta muerto. (Los Pjs se saben matar).
	 * Cuando encuentra un muerto le manda un mensaje a ese PJ para que desactive su keyListener (provisional).
	 * Podria agregarlo en la lista de PJs para desconectar.. o respawnearlo en algun lugar. Queda por definir.
	 */
	@Override
	public void buscaYDestruyeMuertos(List<Actor> killListaPj){
		
		Iterator<Actor> pjIt = this.pjList.iterator();
		
		while(pjIt.hasNext()) { 
			Actor actorAux = (Actor) pjIt.next();
			
			// Si esta muerto lo remuevo de la lista
			if (actorAux.estasMuerto()) {
				System.out.println("Mundo - encontre un muerto!");
				this.borraPjListaMundo(actorAux, killListaPj);
				actorAux.exitMundo();
			}			
		}
		
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

	@Override
	public List<Actor> getPjList() {
		return this.pjList;
	}

	@Override
	public void borraPjListaMundo(Actor pj, List<Actor> killListaPj) {
		System.out.println("Mundo - Estoy borrando un pj de ListaMundo");
		killListaPj.add(pj);
		this.getPjList().remove(pj);		
	}

	@Override
	public void agregaPjListaMundo(Actor pj) {
		System.out.println("Mundo - Estoy agregando un pj a ListaMundo");
		this.getPjList().add(pj);		
	}

}
