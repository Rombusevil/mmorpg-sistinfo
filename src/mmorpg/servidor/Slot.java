package mmorpg.servidor;

public interface Slot {
	
	public ItemEquipable dameItem();
	
	public void borraItem();
	
	public void reemplazaItem(ItemEquipable item);
	
	public int dameDmgItem();
	
	public int dameArmorItem();
	
	public double dameAtkSpdItem();

}
