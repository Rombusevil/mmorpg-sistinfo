package mmorpg.servidor;

/**
 * Implementacion de la interface Slot, con comportamientos unicos del Gear. El
 * Gear es el conjunto de Slots donde se almacenan los items Equipados.
 * 
 * Los items equipados, aportan estadisticas a la FichaDePersonaje.
 * 
 * Un SlotGear solo sabe almacenar un solo tipo de item.
 * 
 * @author cyberpunx
 * 
 */

public class SlotGear implements Slot {

	/**
	 * ATRIBUTOS
	 */

	String tipoItem; // El tipo de item que este Slot sabe almacenar
	ItemEquipable item; // El item que este slot almacena, si es null es que no
						// almacena nada

	/**
	 * IMPLEMENTACIONES DE LA INTERFAZ
	 * 
	 */
	@Override
	public Item dameItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borraItem() {
		// TODO Auto-generated method stub

	}

	@Override
	public void recibirItem(ItemEquipable item) {
		// TODO Auto-generated method stub

	}

	/**
	 * METODOS
	 */
	public void reemplazaItem(ItemEquipable item) {
		// TODO
	}

	double getStat(String stat) {
		if (stat.equals("Dmg")) {
			return getItem().getDmg();
		}
		if (stat.equals("AtkSpd")) {
			return getItem().getAtkSpd();
		}
		if (stat.equals("Armor")) {
			return getItem().getArmor();
		} else {
			return 0;
		}

	}

	/**
	 * GETTERS Y SETTERS
	 */
	private String getTipoItem() {
		return tipoItem;
	}

	private void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	private ItemEquipable getItem() {
		return item;
	}

	private void setItem(ItemEquipable item) {
		this.item = item;
	}

}
