package mmorpg.servidor;

public interface Actor {
	void movete();
	Celda getPosicion();	
	void setPosicion(Celda posicion);
	
	MovedorEnDireccion getMovedorEnDireccion();
	void setMovedorEnDireccion(MovedorEnDireccion movedorEnDireccion);
	
}
