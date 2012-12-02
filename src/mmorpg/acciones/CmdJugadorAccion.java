package mmorpg.acciones;

import java.io.Serializable;

import mmorpg.entes.actor.Actor;

public class CmdJugadorAccion implements iComando, Serializable {
	private Accion aed;
	private Actor pj;
	
	
	public void setPj(Actor a){
		this.pj = a;
	}
	
	public Actor getPj(){
		return pj;
	}
	
	public void setAccion(Accion aed){
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
	public Accion getAccion(){ 
		return this.aed;
	}

}
