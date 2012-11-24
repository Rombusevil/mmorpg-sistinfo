package mmorpg.servidor;

public class ImpProveedorAtacadores implements ProveedorDeEnDireccion {
	private AtacaAbajo abajo;
	private AtacaArriba arriba;
	private AtacaDerecha der;
	private AtacaIzquierda izq;
	
	public ImpProveedorAtacadores(){
		this.abajo 	= new AtacaAbajo();
		this.arriba = new AtacaArriba();
		this.der 	= new AtacaDerecha();
		this.izq 	= new AtacaIzquierda();
	}

	//Devuelven direcciones 
	public AtacaAbajo dameAbajo(){
		return this.abajo;
	}
	public AtacaArriba dameArriba(){
		return this.arriba;
	}
	public AtacaIzquierda dameIzq(){
		return this.izq;
	}
	public AtacaDerecha dameDer(){
		return this.der;
	}
	
}



