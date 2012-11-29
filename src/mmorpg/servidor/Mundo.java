package mmorpg.servidor;

import java.util.List;

public interface Mundo{
	void setEntradaMundo(Celda c);
	void dimensionar(int alto, int ancho, ImpDibujoVacio vacio);
	Celda getEntradaMundo();
/**/	void ponerDibujableEn(int x, int y, Dibujable dibujable); //Esto tiene sentido para poner árboles, pierdas etc en el mapa

	public void poneActorEn(int x, int y, ImpActor a);
	
	
	Celda getCeldaPos(int x, int y);
	int getAlto();
	int getAncho();
	Celda[][] getMundo();
	
	public List<Actor> getPjList();
	

}
