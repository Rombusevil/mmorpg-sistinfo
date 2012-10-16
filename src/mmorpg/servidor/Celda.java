package mmorpg.servidor;

public interface Celda {
	Celda dameCeldaArriba();
	Celda dameCeldaAbajo();
	Celda dameCeldaIz();
	Celda dameCeldaDer();
	void setCeldaArriba(Celda c);
	void setCeldaAbajo(Celda c);
	void setCeldaIz(Celda c);
	void setCeldaDer(Celda c);
	void setOcupadoPor(Dibujable d);
	Dibujable getOcupadoPor();
	void setMyPos(int x, int y);
	void imprimeMyPos();	
}
