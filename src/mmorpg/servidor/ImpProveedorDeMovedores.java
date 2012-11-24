package mmorpg.servidor;

public class ImpProveedorDeMovedores implements ProveedorDeEnDireccion {
	private MovedorAbajo abajo;
	private MovedorArriba arriba;
	private MovedorDerecha der;
	private MovedorIzquierda izq;
	
	public ImpProveedorDeMovedores(){
		this.abajo = new MovedorAbajo();
		this.arriba = new MovedorArriba();
		this.der = new MovedorDerecha();
		this.izq = new MovedorIzquierda();
	}
	
/* Esto va en ImpControladorJugadores */
//	public MovedorEnDireccion decodificaStringADire(String comando){
//		// TO DO
//		return dameArriba(); //esto lo puse por mientras para que no tire error.
//	}
//	
	//Devuelven direcciones 
	public MovedorAbajo dameAbajo(){
		return this.abajo;
	}
	public MovedorArriba dameArriba(){
		return this.arriba;
	}
	public MovedorIzquierda dameIzq(){
		return this.izq;
	}
	public MovedorDerecha dameDer(){
		return this.der;
	}
	
	
	
	
	/*
	
	public MovedorEnDireccion decodificaStringADire(String comando);
	 */
	
}
