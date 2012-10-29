package mmorpg.servidor;

/*
 * GEAR son los items que tiene el personaje equipado.
 * Cada vez que se equipa algo diferente, se debe mandar un mensaje al gear
 * para que se actualice.
 * 
 * El gear cuenta con 6 espacios: Casco, Pechera, Botas, Guantes, MainHand (Arma), OffHand (Escudo)
 */
public class Gear {

	/* Espacios del Gear */
	Casco casco;
	Pechera pechera;
	Botas botas;
	Guantes guantes;
	MainHand mainHand;
	OffHand offHand;

	/**
	 * CONSTRUCTOR. Inicializa los espacios sin items.
	 * 
	 */
	Gear() {
		this.casco = new Casco();
		this.pechera = new Pechera();
		this.botas = new Botas();
		this.guantes = new Guantes();
		this.mainHand = new MainHand();
		this.offHand = new OffHand();
	}

	/*
	 * Atributos y calculos derivados del equipo como la Armadura sumada de
	 * todos los items y el dmg del arma.
	 */

	/**
	 * Metodo que calcula la suma total de la armadura del Gear
	 * 
	 * @return la suma de todo el armor de casco, pechera, guantes, botas y
	 *         offhand
	 */
	int getTotalGearArmor() {
		int sumaArmor = 0;
		sumaArmor += getCasco().getArmor();
		sumaArmor += getPechera().getArmor();
		sumaArmor += getGuantes().getArmor();
		sumaArmor += getBotas().getArmor();
		sumaArmor += getOffHand().getArmor();
		return sumaArmor;
	}

	/**
	 * Metodo que calcula el dmg del gear equipado (MainHand)
	 * 
	 * @return el dmg del arma en la MainHand
	 */
	int getWpnDmg() {
		int sumaArmor = 0;
		sumaArmor += getMainHand.getDmg();
		return sumaArmor;
	}

	/**
	 * Obtiene la AtkSpd del arma equipada en la MainHand
	 * 
	 * @return atkSpd del arma.
	 */
	double getWpnAtkSpd() {
		double wpnAtkSpd = 0;
		wpnAtkSpd += getMainHand().getAtkSpd();
		return wpnAtkSpd;
	}

	/* GETTERS Y SETTERS */
	private Casco getCasco() {
		return casco;
	}

	private void setCasco(Casco casco) {
		this.casco = casco;
	}

	private Pechera getPechera() {
		return pechera;
	}

	private void setPechera(Pechera pechera) {
		this.pechera = pechera;
	}

	private Botas getBotas() {
		return botas;
	}

	private void setBotas(Botas botas) {
		this.botas = botas;
	}

	private Guantes getGuantes() {
		return guantes;
	}

	private void setGuantes(Guantes guantes) {
		this.guantes = guantes;
	}

	private MainHand getMainHand() {
		return mainHand;
	}

	private void setMainHand(MainHand mainHand) {
		this.mainHand = mainHand;
	}

	private OffHand getOffHand() {
		return offHand;
	}

	private void setOffHand(OffHand offHand) {
		this.offHand = offHand;
	}

}
