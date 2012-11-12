package mmorpg.servidor;

/**
 * Esta clase implementa los items de TIPO MAIN HAND, es decir espadas, hachas,
 * garrotes, etc
 * 
 * Por lo general en la MainHand se equipan armas de una mano.
 * 
 * @author cyberpunx
 * 
 */

public class MainHand implements ItemEquipable {

	/**
	 * Atributos
	 */
	
	String tipoItem = "MainHand"; //El tipo del item
	
	int dmg; // dmg del arma
	int armor; // cantidad de armadura que suma el arma
	float atkSpd; // cantidad de ataques por segundos
	
	String nombre; //Nombre del item

	/**
	 * IMPLEMENTACIONES DE LA INTERFAZ
	 */
	@Override
	public int getDmg() {
		return this.dmg;
	}

	@Override
	public int getArmor() {
		return this.armor;
	}

	@Override
	//FIXME el metodo devuelve int pero atkSpd es float.
	public int getAtkSpd() {
		return getAtkSpd();
	}



	

	/**
	 * GETTERS Y SETTERS
	 */
	
	private String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private void setDmg(int dmg) {
		this.dmg = dmg;
	}

	private void setArmor(int armor) {
		this.armor = armor;
	}

	private void setAtkSpd(float atkSpd) {
		this.atkSpd = atkSpd;
	}

}
