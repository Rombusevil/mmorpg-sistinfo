package mmorpg.servidor;

public class SlotGear implements Slot {
	
	
	ItemEquipable item;

	@Override
	public void borraItem() {
		this.setItem(null);
	}

	@Override
	public ItemEquipable dameItem() {
		return this.getItem();
	}

	@Override
	public void reemplazaItem(ItemEquipable item) {
		this.setItem(item);
	}

	@Override
	public int dameDmgItem() {
		return this.getItem().getDmg();
	}

	@Override
	public int dameArmorItem() {
		return this.getItem().getArmor();
	}

	@Override
	public double dameAtkSpdItem() {
		return this.getItem().getAtkSpd();
	}

	private ItemEquipable getItem() {
		return item;
	}

	private void setItem(ItemEquipable item) {
		this.item = item;
	}

}
