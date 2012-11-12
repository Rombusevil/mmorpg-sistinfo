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
}
