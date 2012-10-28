package mmorpg.servidor;

public interface Ente {
	void sosAtacado(int daño);
	
	void setDibujo(ImpDibujo dibujo);
	Dibujo getDibujo();
	
	Celda getCeldaActual();
	void setCeldaActual(Celda celda);
}
