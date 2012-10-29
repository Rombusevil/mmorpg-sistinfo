package mmorpg.servidor;

import java.util.List; //Manejo de listas

/*
 * TODO: Todo lo referente al AtkSpd.
 * 		 DamageTaken
 * 		 Todo lo referente al HP
 * 		 Testing de que los calculos esten bien! <-- Se necesita crear la clase Skill
 * 		 Se necesita crear la clase de los items para implementar Gear
 *   
 * 
 * */

public class FichaDePersonaje {

	/* Items Equipados del personaje -> GEAR */
	Gear gear;

	/* Lista de Skills del Personaje */
	List<Skill> skillList;

	/* Atributos Primarios */
	int str; // produce armor
	int dex; // produce AtkSpd
	int vit; // produce HP
	String primaryAtt; // Atributo primario, depende de cada pj, otorga dmg

	/* Atributos Secundarios o Derivados */

	int lvl; // Nivel del personaje
	int xp; // Experiencia acumulada

	int maxHp; // HP total, el tope maximo
	int currentHp; // HP actual, que va variando

	int skillDmg; // dmg proveniente del skill utilizado
	double atkSpd; //cantidad de ataques por segundo

	/**
	 * CONSTRUCTOR. Metodo que inicializa la hoja de personaje. Se debe ejecutar
	 * una vez durante la creacion del personaje. Le da al personaje un GEAR e
	 * inicializa sus atributos primarios.
	 * 
	 */
	public FichaDePersonaje() {
		// Inicializao el Gear
		this.gear = new Gear();

		// Si se agregan clases de PJ
		// Los atributos se deben iniciarlizar
		// diferente para cada clase
		// Y el PrimaryAtt depende de cada clase
		setStr(10);
		setDex(8);
		setVit(9);
		setPrimaryAtt("str");
		
		setMaxHp();
		setCurrentHp(getMaxHp());
		
	}

	/**
	 * Devuelve el valor del Primary Attribute.
	 * 
	 * @return
	 */
	int getPrimaryAttValue() {
		if (getPrimaryAtt() == "str") {
			return getStr();
		}
		if (getPrimaryAtt() == "dex") {
			return getDex();
		}
	}

	/* FORMULAS */
	/*
	 * Formula Total Armor: strArmor + Armor
	 * 
	 */
	public int getTotalArmor() {
		int totalArmor = 0;
		totalArmor += getGear().getTotalGearArmor(); // Armor del equipo.
		totalArmor += getStr(); // Sumado al armor 1:1 de la Str.

		return totalArmor;
	}

	/**
	 * Damage Reduction Formula: (TotalArmor / ( 50 * Attacker Level +
	 * TotalArmor))*100
	 * 
	 * @return el % de reduccion de dmg.
	 */
	public int getDamageReduction(int attackerLevel) {
		int dmgRed = 0;
		int numerador = getTotalArmor();
		int denominador = 50 * attackerLevel;
		denominador += getTotalArmor();
		denominador *= 100;
		dmgRed = numerador / denominador;

		return dmgRed;
	}

	/**
	 * SkillDamage Formula: WpnDmg*x% (donde x es el porcentaje de dmg del arma
	 * que hace el skill... pe: 110%, 150%, 200%, etc..)
	 * 
	 * @param wpnDmgPercent
	 *            porcentaje (en base al wpnDmg) de dmg del skill
	 */
	public void setSkillDamage(int wpnDmgPercent) {
		int wpnDmg = getGear().getWpnDmg();
		setSkillDmg((wpnDmgPercent * wpnDmg) / 100);
	}

	/**
	 * DAMAGE Formula: ( WpnDmg * SkillDmg ) * ( 1 + (PrimartySt / 100) )
	 * ANTES DE LLAMAR a getDmg() se debe llamar a setSkillDamage(int wpnDmgPercent)
	 * 
	 * @return
	 */
	public int getDmg() {
		int skillDmg = getSkillDmg();
		int wpnDmg = getGear().getWpnDmg();
		int damage = 0;
		damage = skillDmg * wpnDmg;
		damage *= (1 + (getPrimaryAttValue() / 100));

		return damage;
	}
	
	/**
	 * FORMULA DexAtkSpd: Dex * 0.1%
	 * FORMULA AtkSpd:  WpnAtkSpd * ( 1 + DexAtkSpd )
	 * @return
	 */
	public double getAndSetAtkSpd(){
		double wpnAtkSpd = gear.getWpnAtkSpd();
		double dexAtkSpd = ( 0.1 * getDex() ) / 100 ; 
		
		setAtkSpd(wpnAtkSpd * (1 + dexAtkSpd));
		return round( (wpnAtkSpd * (1 + dexAtkSpd)), 2);		
	}
	
	/**
	 * 
	 * Formula: Dmg - (Dmg*DmgRed%)
	 * @param dmg --> dmg es pasado por el jugador atacante
	 * @return el dmg real que es restado a la HP.
	 */
	public int getDamageTaken(int dmg, int attackerLvl){
		int dmgRed = getDamageReduction(attackerLvl);
		int dmgTaken = dmg - ( (dmgRed * dmg) / 100);
		int hp = getCurrentHp();
		hp -= dmgTaken;
		setCurrentHp(hp);
	}
	
	/**
	 * 
	 * Formula Hit Points: 36 + (4*LVL) + (10 * VIT)
	 */
	public void setMaxHp(){
		int maxHp;
		maxHp = 36 + (4 * getLvl() + (10 + getVit()) );
		this.maxHp = maxHp;
	}

	/* UTILS */

	/**
	 * Redondea un numero del tipo double, acortando los decimales
	 * 
	 * @param numero
	 *            que se quiere redondear
	 * @param decimales
	 *            cantidad de decimales
	 * @return el numero ya redondeado.
	 */
	public double round(double numero, int decimales) {
		return Math.round(numero * Math.pow(10, decimales))
				/ Math.pow(10, decimales);
	}

	/* GETTERS Y SETTERS */
	private int getStr() {
		return str;
	}

	private void setStr(int str) {
		this.str = str;
	}

	private int getDex() {
		return dex;
	}

	private void setDex(int dex) {
		this.dex = dex;
	}

	private int getVit() {
		return vit;
	}

	private void setVit(int vit) {
		this.vit = vit;
	}

	private String getPrimaryAtt() {
		return primaryAtt;
	}

	private void setPrimaryAtt(String primaryAtt) {
		this.primaryAtt = primaryAtt;
	}

	private Gear getGear() {
		return gear;
	}

	private void setGear(Gear gear) {
		this.gear = gear;
	}

	private int getSkillDmg() {
		return skillDmg;
	}

	private void setSkillDmg(int skillDmg) {
		this.skillDmg = skillDmg;
	}

	public double getAtkSpd() {
		return atkSpd;
	}

	public void setAtkSpd(double atkSpd) {
		this.atkSpd = atkSpd;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public int getMaxHp() {
		return maxHp;
	}
}
