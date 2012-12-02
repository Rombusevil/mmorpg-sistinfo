package mmorpg.items;

import mmorpg.entes.Ente;
import mmorpg.mundo.Celda;
import mmorpg.userInterface.output.Dibujo;
import mmorpg.userInterface.output.ImpDibujo;

public abstract class Item implements ItemEquipable, Ente {

	@Override
	public void sosAtacado(int dmg, int lvlAtacante) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDibujo(ImpDibujo dibujo) {
		// TODO Auto-generated method stub

	}

	@Override
	public Dibujo getDibujo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Celda getCeldaActual() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCeldaActual(Celda celda) {
		// TODO Auto-generated method stub

	}

	@Override
	public int dameXpPorGolpearte() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDmg() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getArmor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAtkSpd() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String dameNombreItem() {
		// TODO Auto-generated method stub
		return null;
	}

}
