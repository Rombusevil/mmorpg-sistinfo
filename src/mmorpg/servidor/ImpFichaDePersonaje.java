package mmorpg.servidor;
/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! **/
//FIXME le mandaste private a casi todos los métodos. Puse publics los getters y setters que se que tienen que ser public
// y los estoy usando ahora fijate de verlo y ponerle public a los que sean public B-)
//Edit: Le mandé public a todo (supongo que así debe ser), si no está bien cabia los que tengan que ser private.

//Esto parece que no se usa.
//import java.util.List; //Manejo de listas

public class ImpFichaDePersonaje implements FichaDePersonaje {

	/* Items Equipados del personaje -> GEAR */
	InventarioGear gear;

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
	
	int dmg;

	/* OVERRIDE */

	@SuppressWarnings("unused")
	@Override
	public void recibiDmg(int dmg, int attackerLvl) {
		// getDamageTaken se encarga tambien de restarte vida.
		int dmgTaken = getDamageTaken(dmg, attackerLvl);
	}

	@Override
	public int dameDmg() {
		int dmg = getDmg();
		return dmg;
	}

	@Override
	public double dameAtkSpd() {
		double atkSpd = getAtkSpd();
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

	@Override
	public void equipaItem(ItemEquipable item) {
		getGear().equipaItem(item);

		// Debo calcular mi nueva cantidad de armadura y dmg
		// cada vez que me equipo algo nuevo
		calcTotalArmor();
		calcDmg();
		calcAtkSpd();
	}

	/**
	 * CONSTRUCTOR. Metodo que inicializa la hoja de personaje. Se debe ejecutar
	 * una vez durante la creacion del personaje. Le da al personaje un GEAR e
	 * inicializa sus atributos primarios.
	 * 
	 */
	public ImpFichaDePersonaje() {
		// Inicializao el Gear
		this.gear = new ImpInventarioGear(); 

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
	public int getPrimaryAttValue() {
		if (getPrimaryAtt() == "str") {
			return getStr();
		}
		if (getPrimaryAtt() == "dex") {
			return getDex();
		} else {
			return 0;
		}
	}

	/* FORMULAS */
	/*
	 * Formula Total Armor: strArmor + Armor
	 * 
	 * Se debe recalcular cada vez que se cambia de equipo O el atributo STR
	 * cambia
	 */
	public void calcTotalArmor() {
		int totalArmor = 0;
		totalArmor += getGear().dameStat("MainHand", "Armor");
		// totalArmor += getGear().dameStat("Casco", "Armor");
		// totalArmor += getGear().dameStat("Armadura", "Armor");
		totalArmor += getStr(); // Sumado al armor 1:1 de la Str.

		setArmor(totalArmor);
	}

	/**
	 * Damage Reduction Formula: (TotalArmor / ( 50 * Attacker Level +
	 * TotalArmor))*100
	 * 
	 * @return el % de reduccion de dmg.
	 */
	public int getDamageReduction(int attackerLevel) {
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
	public void setSkillDamage(int wpnDmgPercent) {
		int wpnDmg = (int) getGear().dameStat("MainHand", "Dmg"); //Saca el dmg del arma
		setSkillDmg((wpnDmgPercent * wpnDmg) / 100);
	}

	/**
	 * DAMAGE Formula: ( WpnDmg * SkillDmg ) * ( 1 + (PrimartySt / 100) ) ANTES
	 * DE LLAMAR a getDmg() se debe llamar a setSkillDamage(int wpnDmgPercent)
	 * 
	 * @return
	 */
	public void calcDmg() {
		setSkillDamage(100); // FIXME OJO, VALOR HARDCODED
		
		int skillDmg = getSkillDmg();
		int wpnDmg = (int) getGear().dameStat("MainHand", "Dmg"); //Saca el dmg del arma
		int damage = 0;
		damage = skillDmg * wpnDmg;
		damage *= (1 + (getPrimaryAttValue() / 100));

		setDmg(damage);
	}

	/**
	 * FORMULA DexAtkSpd: Dex * 0.1% FORMULA AtkSpd: WpnAtkSpd * ( 1 + DexAtkSpd
	 * )
	 */
	public void calcAtkSpd() {
		double atkspd;
		double wpnAtkSpd = getGear().dameStat("MainHand", "AtkSpd");
		double dexAtkSpd = (0.1 * getDex()) / 100;

		atkspd = round((wpnAtkSpd * (1 + dexAtkSpd)), 2);
		setAtkSpd(atkspd);
	}

	/**
	 * 
	 * Formula: Dmg - (Dmg*DmgRed%)
	 * 
	 * Este metodo tambien se encarga de restar la vida al personaje.
	 * 
	 * @param dmg
	 *            --> dmg es pasado por el jugador atacante
	 * @return el dmg real que es restado a la HP.
	 */
	public int getDamageTaken(int dmg, int attackerLvl) {
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
	public void setMaxHp() {
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
	public double round(double numero, int decimales) {
		return Math.round(numero * Math.pow(10, decimales))
				/ Math.pow(10, decimales);
	}

	/* GETTERS Y SETTERS */
	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

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

	public String getPrimaryAtt() {
		return primaryAtt;
	}

	public void setPrimaryAtt(String primaryAtt) {
		this.primaryAtt = primaryAtt;
	}

	public InventarioGear getGear() {
		return gear;
	}

	public void setGear(InventarioGear gear) {
		this.gear = gear;
	}

	public int getSkillDmg() {
		return skillDmg;
	}

	public void setSkillDmg(int skillDmg) {
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

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	
	public int getDmg(){
		return dmg;
	}

}
