package mmorpg.servidor;

public class CmdJugadorAccion implements iComando {
	private AccionEnDireccion aed;
	private Actor pj;
	
	
	public void setPj(Actor a){
		this.pj = a;
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

}
