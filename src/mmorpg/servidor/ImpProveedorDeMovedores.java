package mmorpg.servidor;

public class ImpProveedorDeMovedores implements ProveedorDeMovedores {
	private MovedorEnDireccion abajo;
	private MovedorEnDireccion arriba;
	private MovedorEnDireccion der;
	private MovedorEnDireccion izq;
	
	public ImpProveedorDeMovedores(){
		this.abajo = new MovedorAbajo();
		this.arriba = new MovedorArriba();
		this.der = new MovedorDerecha();
		this.izq = new MovedorIzquierda();
		
	}
	
	public MovedorEnDireccion decodificaStringADire(String comando){
		return dameArriba(); //esto lo puse por mientras para que no tire error.
	}
	
	//Devuelven direcciones 
	public MovedorEnDireccion dameAbajo(){
		return getAbajo();
	}
	public MovedorEnDireccion dameArriba(){
		return getArriba();
	}
	public MovedorEnDireccion dameIzq(){
		return getIzq();
	}
	public MovedorEnDireccion dameDer(){
		return getDer();
	}
	
	
	//Seters y geters
	public MovedorEnDireccion getAbajo() {
		return abajo;
	}
	public void setAbajo(MovedorAbajo abajo) {
		this.abajo = abajo;
	}
	public MovedorEnDireccion getArriba() {
		return arriba;
	}
	public void setArriba(MovedorArriba arriba) {
		this.arriba = arriba;
	}
	public MovedorEnDireccion getDer() {
		return der;
	}
	public void setDer(MovedorDerecha der) {
		this.der = der;
	}
	public MovedorEnDireccion getIzq() {
		return izq;
	}
	public void setIzq(MovedorIzquierda izq) {
		this.izq = izq;
	}
	
	
	/*
	
	public MovedorEnDireccion decodificaStringADire(String comando);
	 */
	
}
