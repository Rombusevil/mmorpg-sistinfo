package mmorpg.servidor;

public class ImpInventarioGear implements InventarioGear {

	/**
	 * ATRIBUTOS
	 */
	MainHand mainHand; // <-- Unico Slot por ahora, solo pueden almacenarse
						// espadas u otras armas de una mano.

	/**
	 * IMPLEMENTACIONES DE INTERFACE
	 */
	@Override
	public double dameStat(String tipoItem, String stat) {
		// FIXME Hardcoded, porque tenemos solo un tipo de item
		// Cuando se haga bien, habria que buscar en la Lista.
		// tipoItem se utilizaria como clave para buscar en dicha Lista.
		return 0;
	}

	@Override
	public void equipaItem(ItemEquipable item) {
		

	}

	/**
	 * METODOS INTERNOS
	 */
	// Constructor
	public ImpInventarioGear() {
		this.mainHand = new MainHand();
	}

	/**
	 * GETTERS Y SETTERS
	 */
	private MainHand getMainHand() {
		return mainHand;
	}

	private void setMainHand(MainHand mainHand) {
		this.mainHand = mainHand;
	}
}
