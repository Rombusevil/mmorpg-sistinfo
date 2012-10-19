package mmorpg.servidor;

public interface Mundo {
	void muestraMundo(int alto, int ancho, Celda celdaActual);
	void setEntradaMundo(Celda c);
	void dimensionar(int alto, int ancho);
	Celda getEntradaMundo();
	void ponerDibujableEn(int x, int y, Dibujable dibujable, Mundo mundo);
	Celda dameCelda(int x, int y, Mundo mundo);

}
