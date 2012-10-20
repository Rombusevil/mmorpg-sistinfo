package mmorpg.servidor;

public interface Actor {
	void movete();
	void setPosicion(Celda posicion);
	
	MovedorEnDireccion getMovedorEnDireccion();
	void setMovedorEnDireccion(MovedorEnDireccion movedorEnDireccion);
	
	public Celda getCeldaActual();
	public void setCeldaActual(Celda celda);
	
}
