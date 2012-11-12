package mmorpg.servidor;

/**
 * Provee una interfaz para que la FichaDePersonaje pueda comunicarse con los
 * items equipados en el personaje.
 * 
 * Cambiar items, equipar nuevos items , saber stats de los items equipados,
 * etc...
 * 
 * @author cyberpunx
 * 
 */

public interface InventarioGear {
	
	/**
	 * Le pasa un item al Gear para que lo equipe.
	 * Si ya habia un item, lo reemplaza.
	 * @param item el item a ser equipado.
	 */
	public void equipaItem(ItemEquipable item);
	
	/**
	 * 
	 * @param tipoItem el tipo de item del cual se 
	 * quiere obtener un stat, pe:
	 * 							-> MainHand
	 * 							-> Casco
	 * 							-> Armadura
	 * @param stat el stat que se quiere obtener, pe:
	 * 							-> atkSpd
	 * 							-> armor
	 * 							-> dmg
	 */
	public double dameStat(String tipoItem, String stat);
	
	
	

}
