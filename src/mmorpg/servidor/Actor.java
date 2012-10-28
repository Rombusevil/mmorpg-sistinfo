package mmorpg.servidor;

public interface Actor {
	void setPosicion(Celda posicion);
	
	void moveteDer();
	void moveteIzq();
	void moveteAbajo();
	void moveteArriba();
	
	void setDibujo(ImpDibujo dibujo);
	Dibujo getDibujo();
	
	Celda getCeldaActual();
	void setCeldaActual(Celda celda);
	
	
}
