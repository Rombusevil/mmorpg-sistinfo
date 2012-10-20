package mmorpg.servidor;

/* El actor cuando se construye tiene que tener una posición, sino el programa crashea más adelante */

public abstract class ImpActor implements Actor {
	private MovedorEnDireccion movedorEnDireccion;
	private Celda posicion;
	
	public void movete(){
		MovedorEnDireccion movedor;
		
		movedor = this.getMovedorEnDireccion();
		
		movedor.mover(this);
		
	}
	
	public Celda getCeldaActual() {
		return this.posicion;
	}
	public void setCeldaActual(Celda celda){
		this.posicion = celda;
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
