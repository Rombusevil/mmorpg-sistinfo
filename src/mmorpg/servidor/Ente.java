package mmorpg.servidor;

public interface Ente {
	void sosAtacado(int dmg);
	
	void setDibujo(ImpDibujo dibujo);
	Dibujo getDibujo();
	
	Celda getCeldaActual();
	void setCeldaActual(Celda celda);
}
