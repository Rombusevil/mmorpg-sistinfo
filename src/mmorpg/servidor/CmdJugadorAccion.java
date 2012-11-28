package mmorpg.servidor;

import java.io.Serializable;

public class CmdJugadorAccion implements iComando, Serializable {
	private AccionEnDireccion aed;
	private Actor pj;
	
	
	public void setPj(Actor a){
		this.pj = a;
	}
	
	public Actor getPj(){
		return pj;
	}
	
	public void setAccion(AccionEnDireccion aed){
		this.aed = aed;
	}

	@Override
	public void ejecutate() {
		//TODO ver si esto está bien. Tirandome a la pileta:
		this.aed.actuar(pj);
	}

	@Override
	public void mandarComando(iComando cmd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AccionEnDireccion getAccion(){ 
		return this.aed;
	}

}
