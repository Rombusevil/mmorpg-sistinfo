package mmorpg.entes.actor;

import mmorpg.mundo.Celda;

public interface Actor {
	void setPosicion(Celda posicion);
	void exitMundo();
	
	void moveteDer();
	void moveteIzq();
	void moveteAbajo();
	void moveteArriba();
	
	void atacaDer();
	void atacaIzq();
	void atacaAbajo();
	void atacaArriba();
	
	void sosAtacado(int dmg, int lvlAtacante);
	int dameHP();
	int dameXP();
	int dameLvl();
	int dameDmg();
	double dameAtkSpd();
	int dameXpPorGolpearte();
	
	void cargaFichaPj(String nombre, 
			int lvl,
			int xp,
			int str, 
			int dex, 
			int vit, 
			String nombreItem,
			int dmgItem, 
			int armorItem, 
			double atkSpdItem, int xpos, int ypos);
	
	public double dameMovSpd();
	
	public boolean estasMuerto();
	public void morite();
	public void revivi();
	public int getYpos();
	public int getXpos();
}
