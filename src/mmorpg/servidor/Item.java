package mmorpg.servidor;

/**
 * Esta interface engloba a todos los items.
 * Luego hay ItemsEquipable e ItemsNoEquipables.
 * 
 * Estos son los Tipos de items Equipables:
 * 
 * ->MainHand  <--- Unico implementado
 * ->OffHand
 * ->Casco
 * ->Armadura
 * ->Guantes
 * ->Botas
 *
 * @author cyberpunx
 *
 */

public interface Item {

	public String getTipoItem();
}
