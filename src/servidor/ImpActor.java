package servidor;

public abstract class ImpActor implements Actor {
	private MovedorEnDireccion movedorEnDireccion;
	private Celda posicion;
	
	public void movete(){
		MovedorEnDireccion movedor;
		
		movedor = this.getMovedorEnDireccion();
		
		movedor.mover(this);
		
	}
	
		
	//G's y S's
	public Celda getPosicion() {
		return posicion;
	}

	public void setPosicion(Celda posicion) {
		this.posicion = posicion;
	}
	public MovedorEnDireccion getMovedorEnDireccion(){
		return movedorEnDireccion;
	}	
	public void setMovedorEnDireccion(MovedorEnDireccion movedorEnDireccion) {
		this.movedorEnDireccion = movedorEnDireccion;
	}
}
