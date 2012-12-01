package mmorpg.entes;

import mmorpg.mundo.Celda;
import mmorpg.userInterface.output.Dibujo;
import mmorpg.userInterface.output.ImpDibujo;

public interface Ente {
	void sosAtacado(int dmg, int lvlAtacante);
	
	void setDibujo(ImpDibujo dibujo);
	Dibujo getDibujo();
	
	Celda getCeldaActual();
	void setCeldaActual(Celda celda);
	
	int dameXpPorGolpearte();
}
