package mmorpg.servidor;

import java.io.Serializable;

public class ImpProveedorDeMovedores implements ProveedorDeEnDireccion,  Serializable {
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
	
}
