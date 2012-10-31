package mmorpg.servidor;

import java.util.List; //Manejo de listas

/*
 * TODO: FichaDePersonaje
 * 		 Testing de que los calculos esten bien! <-- Se necesita crear la clase Skill
 * 		 Se necesita crear la clase de los items para implementar Gear * 
 * */

public class ImpFichaDePersonaje implements FichaDePersonaje{

	/* Items Equipados del personaje -> GEAR */
	Gear gear;



	/* Atributos Primarios */
	int str; // produce armor
	int dex; // produce AtkSpd
	int vit; // produce HP
	String primaryAtt; // Atributo primario, depende de cada pj, otorga dmg

	/* Atributos Secundarios o Derivados */
	
	int armor;

	int lvl; // Nivel del personaje
	int xp; // Experiencia acumulada

	int maxHp; // HP total, el tope maximo
	int currentHp; // HP actual, que va variando

	int skillDmg; // dmg proveniente del skill utilizado
	double atkSpd; // cantidad de ataques por segundo
		
	/* OVERRIDE */
	
	@Override
	public void recibiDmg(int dmg, int attackerLvl) {
		int dmgTaken = getDamageTaken(dmg, attackerLvl);
	}

	@Override
	public int dameDmg() {
		setSkillDamage(100);  //FIXME OJO VALOR HARDCODED
		int dmg = getDmg();
		return dmg;
	}

	@Override
	public double dameAtkSpd() {
		double atkSpd = getAndSetAtkSpd();
		return atkSpd;
	}

	@Override
	public int dameCurrentHp() {
		int currentHp = getCurrentHp();
		return currentHp;
	}

	@Override
	public int dameMaxHp() {
		int maxHp = getMaxHp();
		return maxHp;
	}

	@Override
	public int dameLvl() {
		int lvl = getLvl();
		return lvl;
	}

	@Override
	public int dameXp() {
		int xp = getXp();
		return xp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * CONSTRUCTOR. Metodo que inicializa la hoja de personaje. Se debe ejecutar
	 * una vez durante la creacion del personaje. Le da al personaje un GEAR e
	 * inicializa sus atributos primarios.
	 * 
	 */
	public ImpFichaDePersonaje() {
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
	private int getPrimaryAttValue() {
		if (getPrimaryAtt() == "str") {
			return getStr();
		}
		if (getPrimaryAtt() == "dex") {
			return getDex();
		}
		else{
			return 0;
		}
	}

	/* FORMULAS */
	/*
	 * Formula Total Armor: strArmor + Armor
	 * 
	 * Se debe recalcular cada vez que se cambia de equipo
	 * O el atributo STR cambia
	 * 
	 */
	private void calcTotalArmor() {
		int totalArmor = 0;
		totalArmor += getGear().getTotalGearArmor(); // Armor del equipo.
		totalArmor += getStr(); // Sumado al armor 1:1 de la Str.

		setArmor(totalArmor);
	}

	/**
	 * Damage Reduction Formula: (TotalArmor / ( 50 * Attacker Level +
	 * TotalArmor))*100
	 * 
	 * @return el % de reduccion de dmg.
	 */
	private int getDamageReduction(int attackerLevel) {
		int dmgRed = 0;
		int numerador = getArmor();
		int denominador = 50 * attackerLevel;
		denominador += getArmor();
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
	private void setSkillDamage(int wpnDmgPercent) {
		int wpnDmg = getGear().getWpnDmg();
		setSkillDmg((wpnDmgPercent * wpnDmg) / 100);
	}

	/**
	 * DAMAGE Formula: ( WpnDmg * SkillDmg ) * ( 1 + (PrimartySt / 100) ) ANTES
	 * DE LLAMAR a getDmg() se debe llamar a setSkillDamage(int wpnDmgPercent)
	 * 
	 * @return
	 */
	private int getDmg() {
		int skillDmg = getSkillDmg();
		int wpnDmg = getGear().getWpnDmg();
		int damage = 0;
		damage = skillDmg * wpnDmg;
		damage *= (1 + (getPrimaryAttValue() / 100));

		return damage;
	}

	/**
	 * FORMULA DexAtkSpd: Dex * 0.1% FORMULA AtkSpd: WpnAtkSpd * ( 1 + DexAtkSpd
	 * )
	 * 
	 * @return
	 */
	private double getAndSetAtkSpd() {
		double wpnAtkSpd = gear.getWpnAtkSpd();
		double dexAtkSpd = (0.1 * getDex()) / 100;

		setAtkSpd(wpnAtkSpd * (1 + dexAtkSpd));
		return round((wpnAtkSpd * (1 + dexAtkSpd)), 2);
	}

	/**
	 * 
	 * Formula: Dmg - (Dmg*DmgRed%)
	 * 
	 * @param dmg
	 *            --> dmg es pasado por el jugador atacante
	 * @return el dmg real que es restado a la HP.
	 */
	private int getDamageTaken(int dmg, int attackerLvl) {
		int dmgRed = getDamageReduction(attackerLvl);
		int dmgTaken = dmg - ((dmgRed * dmg) / 100);
		int hp = getCurrentHp();
		hp -= dmgTaken;
		setCurrentHp(hp);
		return dmgTaken;
	}

	/**
	 * 
	 * Formula Hit Points: 36 + (4*LVL) + (10 * VIT)
	 */
	private void setMaxHp() {
		int maxHp;
		maxHp = 36 + (4 * getLvl() + (10 + getVit()));
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
	private double round(double numero, int decimales) {
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

	private double getAtkSpd() {
		return atkSpd;
	}

	private void setAtkSpd(double atkSpd) {
		this.atkSpd = atkSpd;
	}

	private int getLvl() {
		return lvl;
	}

	private void setLvl(int lvl) {
		this.lvl = lvl;
	}

	private int getCurrentHp() {
		return currentHp;
	}

	private void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	private int getMaxHp() {
		return maxHp;
	}

	private int getArmor() {
		return armor;
	}

	private void setArmor(int armor) {
		this.armor = armor;
	}

	private int getXp() {
		return xp;
	}

	private void setXp(int xp) {
		this.xp = xp;
	}

	
}
