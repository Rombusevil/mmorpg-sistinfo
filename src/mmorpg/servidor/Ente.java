package mmorpg.servidor;

public interface Ente {
	void sosAtacado(int dmg, int lvlAtacante);
	
	void setDibujo(ImpDibujo dibujo);
	Dibujo getDibujo();
	
	Celda getCeldaActual();
	void setCeldaActual(Celda celda);
	
	int dameXpPorGolpearte();
}
