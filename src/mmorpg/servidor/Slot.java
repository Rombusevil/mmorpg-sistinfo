package mmorpg.servidor;

/**
 * Interface de los Slots. Un Slot es UN UNICO espacio para almacenar un item.
 * 
 * Un inventario deberia estar conformador por un conjunto de Slots
 * 
 * Un Slot sabe que item tiene adentro suyo. Un Slot sabe cambiar su item
 * almacenado por otro. Un Slot sabe recibir un item para almacenar. Un Slot
 * sabe "entregar" su item almacenado. Un Slot sabe borrar el item almacenado.
 * 
 * @author cyberpunx
 * 
 */

public interface Slot {

	/**
	 * Devuelve el item que tiene almacenado.
	 * @return el item que tiene almacenado.
	 */
	public Item dameItem();

	/**
	 * Borra el item que tiene almacenado.
	 */
	public void borraItem();
	
	/**
	 * Recibe un item y lo almacena.
	 * @param item un item a almacenar.
	 */
	public void recibirItem(ItemEquipable item);

}
