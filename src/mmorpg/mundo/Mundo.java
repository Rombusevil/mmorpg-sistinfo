package mmorpg.mundo;

import java.util.List;

import mmorpg.entes.actor.Actor;
import mmorpg.entes.actor.ImpActor;
import mmorpg.userInterface.output.ImpDibujoVacio;

public interface Mundo{
	void setEntradaMundo(Celda c);
	void dimensionar(int alto, int ancho, ImpDibujoVacio vacio);
	Celda getEntradaMundo();
	//void ponerDibujableEn(int x, int y, Dibujable dibujable); //Esto tiene sentido para poner árboles, pierdas etc en el mapa

	public void poneActorEn(int x, int y, ImpActor a);
	
	
	Celda getCeldaPos(int x, int y);
	int getAlto();
	int getAncho();
	Celda[][] getMundo();
	
	
	public List<Actor> getPjList();	
	public void borraPjListaMundo(Actor pj);
	void agregaPjListaMundo(Actor pj);
	
	// Seek & Destroy
	public void buscaYDestruyeMuertos();
	
	
	
	

}
