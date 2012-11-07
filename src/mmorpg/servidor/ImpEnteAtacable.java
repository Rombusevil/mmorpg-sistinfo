package mmorpg.servidor;

public class ImpEnteAtacable implements Ente {
	private Celda posicion;
	private ImpDibujo dibujo;

	@Override
	public void sosAtacado(int dmg) {
		// TODO Auto-generated method stub
		/** Hacer el cálculo de perder vida según atributos **/

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

	public void setDibujo(ImpDibujo dibujo){
		this.dibujo  = dibujo;
	}
	public ImpDibujo getDibujo(){
		return this.dibujo;
	}
	

}
