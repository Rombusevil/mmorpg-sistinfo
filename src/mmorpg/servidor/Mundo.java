package mmorpg.servidor;

public interface Mundo {
	void muestraMundo(int alto, int ancho, Celda celdaActual);
	void setEntradaMundo(Celda c);
	void dimensionar(int alto, int ancho, ImpDibujoVacio vacio);
	Celda getEntradaMundo();
/**/	void ponerDibujableEn(int x, int y, Dibujable dibujable); //Esto tiene sentido para poner árboles, pierdas etc en el mapa
	void poneActorEn(int x, int y, Actor a);
	Celda getCeldaPos(int x, int y);
	int getAlto();
	int getAncho();
	Celda[][] getMundo();

}
