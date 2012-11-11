package mmorpg.servidor;

/*
 * GEAR son los items que tiene el personaje equipado.
 * Cada vez que se equipa algo diferente, se debe mandar un mensaje al gear
 * para que se actualice.
 * 
 * El gear cuenta con 6 espacios: Casco, Pechera, Botas, Guantes, MainHand (Arma), OffHand (Escudo)
 * 
 * Por ahora solo esta implementada la MainHand (Espada)
 */
public class Gear {

	public int getTotalGearArmor() {
		// TODO Implementar getTotalGearArmor()
		return 0;
	}
//	TODO implementar MainHand (ctrl+shift+c comenta o descomenta una porcion de código seleccionada)

	public int getWpnDmg() {
		// TODO Implementar getWpnDmg()
		return 0;
	}

	public double getWpnAtkSpd() {
		// TODO Implementar getWpnAtkDmg()
		return 0;
	}

//	/* Espacios del Gear */
//
//	MainHand mainHand;
//
//	/**
//	 * CONSTRUCTOR. Inicializa los espacios sin items.
//	 * 
//	 */
//	Gear() {
//		this.mainHand = new MainHand();
//	}
//
//	/**
//	 * Metodo que calcula la suma total de la armadura del Gear
//	 * 
//	 * @return la suma de todo el armor de casco, pechera, guantes, botas y
//	 *         offhand
//	 */
//	int getTotalGearArmor() {
//		int sumaArmor = 0;
//
//		sumaArmor += getMainHand().getArmor();
//		return sumaArmor;
//	}
//
//	/**
//	 * Metodo que calcula el dmg del gear equipado (MainHand)
//	 * 
//	 * @return el dmg del arma en la MainHand
//	 */
//	int getWpnDmg() {
//		int sumaArmor = 0;
//		sumaArmor += getMainHand.getDmg();
//		return sumaArmor;
//	}
//
//	/**
//	 * Obtiene la AtkSpd del arma equipada en la MainHand
//	 * 
//	 * @return atkSpd del arma.
//	 */
//	double getWpnAtkSpd() {
//		double wpnAtkSpd = 0;
//		wpnAtkSpd += getMainHand().getAtkSpd();
//		return wpnAtkSpd;
//	}
//
//	/* GETTERS Y SETTERS */
//
//	private MainHand getMainHand() {
//		return mainHand;
//	}
//
//	private void setMainHand(MainHand mainHand) {
//		this.mainHand = mainHand;
//	}

}
