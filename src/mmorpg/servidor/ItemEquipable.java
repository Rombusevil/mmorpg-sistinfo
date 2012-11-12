package mmorpg.servidor;

/**
 * Estos son los Tipos de items Equipables:
 * 
 * ->MainHand  <--- Unico implementado
 * ->OffHand
 * ->Casco
 * ->Armadura
 * ->Guantes
 * ->Botas
 * 
 * Estos son los Stats de los Items:
 * 
 * ->Dmg
 * ->AtkSpd
 * ->Armor
 * 
 * @author cyberpunx
 *
 */

public interface ItemEquipable {

	/**
	 * Obtiene el dmg de un item
	 * 
	 * @return el dmg
	 */
	public int getDmg();

	/**
	 * Obtiene el stat Armor de un item
	 * 
	 * @return el armor
	 */
	public int getArmor();

	/**
	 * Obitene el stat Attack Speed de un item
	 * 
	 * @return el Attack Speed
	 */
	public int getAtkSpd();

}
