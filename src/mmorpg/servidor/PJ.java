package mmorpg.servidor;

public class PJ extends ImpActor {
	public PJ(){
		//fp está en su superclase
		this.fp = new ImpFichaDePersonaje();
		
	/**
	 	Cargo la FP hardcoded
 		//FIXME la ficha de personaje la tiene que levantar de la BD
	 */
		fp.setDex(12);
		fp.setStr(10);
		fp.setArmor(25);
		fp.setAtkSpd(3);
		fp.setCurrentHp(100);
		fp.setDmg(20);
		fp.setLvl(1);
		fp.setXp(0);
	}

}
