package mmorpg.servidor;

import java.io.Serializable;

/**
 * Esta clase se usa para que la ficha de personaje la llene con los datos
 * y el DataBaseManager los guarde en la BD
 * @author rombus
 *
 */
public class EstadoPjAGuardar implements Serializable {
	private int dex;
	private int vit;
	private int str;
	private int lvl;
	private int xp;
	private int dmgItem;
	private int armorItem;
	private double atkSpdItem;
	private String nombre;
	private String nombreItem;
	public int getDex() {
		return dex;
	}
	public void setDex(int dex) {
		this.dex = dex;
	}
	public int getVit() {
		return vit;
	}
	public void setVit(int vit) {
		this.vit = vit;
	}
	public int getStr() {
		return str;
	}
	public void setStr(int str) {
		this.str = str;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public int getXp() {
		return xp;
	}
	public void setXp(int xp) {
		this.xp = xp;
	}
	public int getDmgItem() {
		return dmgItem;
	}
	public void setDmgItem(int dmgItem) {
		this.dmgItem = dmgItem;
	}
	public int getArmorItem() {
		return armorItem;
	}
	public void setArmorItem(int armorItem) {
		this.armorItem = armorItem;
	}
	public double getAtkSpdItem() {
		return atkSpdItem;
	}
	public void setAtkSpdItem(double atkSpdItem) {
		this.atkSpdItem = atkSpdItem;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreItem() {
		return nombreItem;
	}
	public void setNombreItem(String nombreItem) {
		this.nombreItem = nombreItem;
	}

}
