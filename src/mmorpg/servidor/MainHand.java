package mmorpg.servidor;

public class MainHand implements ItemEquipable {
	
	int dmg;
	int armor;
	double atkSpd;
	String nombre;

	/* IMPLEMENTACIONES DE LA INTERFAZ */
	@Override
	public int getDmg() {
		return this.getDmg();
	}

	@Override
	public int getArmor() {
		return this.getArmor();
	}

	@Override
	public double getAtkSpd() {
		return this.getAtkSpd();
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
