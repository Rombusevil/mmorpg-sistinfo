package mmorpg.servidor;

public class ImpCelda implements Celda {
	private Dibujable ocupadoPor;
	private Celda celdaArriba;
	private Celda celdaAbajo;
	private Celda celdaIz;
	private Celda celdaDer;
	private int posX;
	private int posY;
	
	

	public void setOcupadoPor(Dibujable d){
		ocupadoPor = d;
	}
	public Dibujable getOcupadoPor(){
		return ocupadoPor;
	}

	@Override
	public Celda dameCeldaArriba() {
		return celdaArriba;
	}

	@Override
	public Celda dameCeldaAbajo() {
		return celdaAbajo;
	}

	@Override
	public Celda dameCeldaIz() {
		return celdaIz;
	}

	@Override
	public Celda dameCeldaDer() {
		return celdaDer;
	}

	@Override
	public void setCeldaArriba(Celda c) {
		this.celdaArriba = c;
	}

	@Override
	public void setCeldaAbajo(Celda c) {
		celdaAbajo = c;
		
	}

	@Override
	public void setCeldaIz(Celda c) {
		celdaIz = c;
	}

	@Override
	public void setCeldaDer(Celda c) {
		celdaDer = c;
		
	}
	@Override
	public void setMyPos(int x, int y) {
		this.posX = x;
		this.posY = y;
		
	}
	@Override
	public void imprimeMyPos() {
		System.out.print("["+this.posX+","+this.posY+"]");
	}


}
