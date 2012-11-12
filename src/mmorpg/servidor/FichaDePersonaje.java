package mmorpg.servidor;

public interface FichaDePersonaje {

	/**
	 * Le indica a la FichaDePersonaje que fue atacado y recibio dmg, la ficha
	 * sabe hacer los calculos y restar la vida correspondiente.
	 * 
	 * @param dmg
	 *            recibido en un ataque.
	 * @param attackerLvl
	 *            nivel del actor atacante.
	 */
	void recibiDmg(int dmg, int attackerLvl);

	/**
	 * Le da al actor su total de dmg al atacar. Para poder golpear a otro actor
	 * 
	 * @return total de dmg que hace el Pj.
	 */
	int dameDmg();

	/**
	 * El Attack Speed del personaje. Es un punto flotante con 1 decimal, que
	 * representa los ataques por segundo. p.e: 1,8 ataques por segundo.
	 * 
	 * @return el AtkSpd.
	 */
	double dameAtkSpd();

	/**
	 * Le pide a la HojaDePersonaje el HP actual del pj
	 * 
	 * @return currentHP
	 */
	int dameCurrentHp();

	/**
	 * Le pide a la Hoja de Personaje el HP maximo.
	 * 
	 * @return MaxHp
	 */
	int dameMaxHp();

	/**
	 * Le pide a la HojaDePersonaje el nivel del PJ. Sirve para pasarselo junto
	 * con el DMG al realizar un ataque.
	 * 
	 * @return nivel de experiencia de un PJ.
	 */
	int dameLvl();

	/**
	 * El total de experiencia. La cantidad de xp determina el Lvl del pj.
	 * 
	 * @return total de Xp.
	 */
	int dameXp();
	
	/**
	 * Equipa un item
	 * @param item el item a equipar.
	 */
	void equipaItem(ItemEquipable item);
	
	
	// Si no tenía estos métodos acá no los podía usar invocando a algo de tipo ficha de personaje como está puesto en el diag
	public int getStr();
	public void setStr(int str);
	public int getDex();
	public void setDex(int dex);
	public int getVit();
	public void setVit(int vit);
	public String getPrimaryAtt();
	public void setPrimaryAtt(String primaryAtt);
	public InventarioGear getGear();
	public void setGear(InventarioGear gear);
	public int getSkillDmg();
	public void setSkillDmg(int skillDmg);
	public double getAtkSpd();
	public void setAtkSpd(double atkSpd);
	public int getLvl();
	public void setLvl(int lvl);
	public int getCurrentHp();
	public void setCurrentHp(int currentHp);
	public int getMaxHp();
	public int getArmor();
	public void setArmor(int armor);
	public int getXp();
	public void setXp(int xp);
	public void setDmg(int dmg);
	public int getDmg();
	
}
