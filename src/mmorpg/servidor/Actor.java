package mmorpg.servidor;

public interface Actor {
	void movete();
	void setPosicion(Celda posicion);
	
	MovedorEnDireccion getMovedorEnDireccion();
	void setMovedorEnDireccion(MovedorEnDireccion movedorEnDireccion);
	
	public void moveteDer();
	public void moveteIzq();
	public void moveteAbajo();
	public void moveteArriba();
	
	public Celda getCeldaActual();
	public void setCeldaActual(Celda celda);
	
}
