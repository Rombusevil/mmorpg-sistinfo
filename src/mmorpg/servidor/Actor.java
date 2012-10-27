package mmorpg.servidor;

public interface Actor {
	void movete();
	void setPosicion(Celda posicion);
	
	MovedorEnDireccion getMovedorEnDireccion();
	void setMovedorEnDireccion(MovedorEnDireccion movedorEnDireccion);
	
	void moveteDer();
	void moveteIzq();
	void moveteAbajo();
	void moveteArriba();
	
	void setDibujo(ImpDibujo dibujo);
	Dibujo getDibujo();
	
	Celda getCeldaActual();
	void setCeldaActual(Celda celda);
	
	
}
