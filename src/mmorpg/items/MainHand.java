package mmorpg.items;

import java.io.Serializable;

public class MainHand implements ItemEquipable, Serializable {
	
	int dmg;
	int armor;
	double atkSpd;
	String nombre;

	/* IMPLEMENTACIONES DE LA INTERFAZ */
	@Override
	public int getDmg() {
		return this.dmg;
	}

	@Override
	public int getArmor() {
		return this.armor;
	}

	@Override
	public double getAtkSpd() {
		return this.atkSpd;
	}
	
	@Override
	public String dameNombreItem(){
		return this.getNombre();
	}
	
	/* CONSTRUCTOR */
	
	public MainHand(int dmg, int amor, double aktSpd, String nombre){
		this.setArmor(amor);
		this.setAtkSpd(aktSpd);
		this.setDmg(dmg);
		this.setNombre(nombre);
	}

	
	/* GETTERS Y SETTERS */

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

	private void setAtkSpd(double atkSpd) {
		this.atkSpd = atkSpd;
	}

}
