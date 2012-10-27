package mmorpg.servidor;

/* El actor cuando se construye tiene que tener una posición, sino el programa crashea más adelante */

public abstract class ImpActor implements Actor {
	private MovedorEnDireccion movedorEnDireccion;
	private Celda posicion;
	private ImpDibujo dibujo;
	

	public void movete(){
		MovedorEnDireccion movedor;
		
		movedor = this.getMovedorEnDireccion();
		
		movedor.mover(this);
		
	}
	
	public void moveteDer(){
		Celda celdaActual, celdaDestino;
		
		celdaActual = this.getCeldaActual();
		celdaDestino = celdaActual.dameCeldaDer();
		
		celdaDestino.getEstado().ocupaCeldaCon(celdaDestino, this);
		
	}
	
	public void moveteIzq(){
		Celda celdaActual, celdaDestino;
		
		celdaActual = this.getCeldaActual();
		celdaDestino = celdaActual.dameCeldaIz();
		
		celdaDestino.getEstado().ocupaCeldaCon(celdaDestino, this);
		
	}
	
	public void moveteAbajo(){
		Celda celdaActual, celdaDestino;
		
		celdaActual = this.getCeldaActual();
		celdaDestino = celdaActual.dameCeldaAbajo();
		
		celdaDestino.getEstado().ocupaCeldaCon(celdaDestino, this);
		
	}
	public void moveteArriba(){
		Celda celdaActual, celdaDestino;
		
		celdaActual = this.getCeldaActual();
		celdaDestino = celdaActual.dameCeldaArriba();
		
		celdaDestino.getEstado().ocupaCeldaCon(celdaDestino, this);
		
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
	public void setDibujo(ImpDibujo dibujo){
		this.dibujo  = dibujo;
	}
	public ImpDibujo getDibujo(){
		return this.dibujo;
	}
}
