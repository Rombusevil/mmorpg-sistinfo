package mmorpg.servidor;

public interface Actor {
	void setPosicion(Celda posicion);
	
	void moveteDer();
	void moveteIzq();
	void moveteAbajo();
	void moveteArriba();
	
	void atacaDer();
	void atacaIzq();
	void atacaAbajo();
	void atacaArriba();
	
	void sosAtacado(int dmg, int lvlAtacante);
}
