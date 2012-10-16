package mmorpg.servidor;

public interface Mundo {
	void setEntradaMundo(Celda c);
	Mundo dimensionar(int alto, int ancho, Mundo mundo);
	Celda getEntradaMundo();
	void ponerDibujableEn(int x, int y, Dibujable dibujable, Mundo mundo);
	Celda dameCelda(int x, int y, Mundo mundo);

}
