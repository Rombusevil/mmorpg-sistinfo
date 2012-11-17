package mmorpg.servidor;

public class ImpFichaDePersonaje implements FichaDePersonaje {

	/* Items Equipados del personaje -> GEAR */
	InventarioGear gear;

	/* Atributos Primarios */
	int str; // produce armor
	int dex; // produce AtkSpd
	int vit; // produce HP
	String primaryAtt; // Atributo primario, depende de cada pj, otorga dmg
	
	String nombre; // Nombre del personaje, no es el Login

	/* Atributos Secundarios o Derivados */

	int armor;

	int lvl; // Nivel del personaje
	int xp; // Experiencia acumulada

	int maxHp; // HP total, el tope maximo
	int currentHp; // HP actual, que va variando

	int sDmg; // dmg proveniente del skill utilizado
	double atkSpd; // cantidad de ataques por segundo
	
	int expAlRecibirDmg; //Experiencia que da este PJ al recibir dmg

	int dmg;
	
	
	//TODO Item Hardcoded
	int _MainHandDmg = 10;   //Dmg de la espada Hardcodeada
	int _MainHandArmor = 50; //Armor de la Espada HxC

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

	@Override
	public void defineNombre(String nombre) {
		setNombre(nombre);		
	}

	@Override
	public String dameNombre() {
		return getNombre();
	}
	
	@Override
	public int dameXpPorGolpearte(){
		return getExpAlRecibirDmg() * getLvl();
	}
	@Override
	public void ganeExp(int exp){
		int experiencia = getXp();
		experiencia += exp;
		setXp(experiencia);
	}

	/**
	 * CONSTRUCTOR. Metodo que inicializa la hoja de personaje. Se debe ejecutar
	 * una vez durante la creacion del personaje. Le da al personaje un GEAR e
	 * inicializa sus atributos primarios.
	 * 
	 */
	public ImpFichaDePersonaje() {
		// Inicializado el Gear
		this.gear = new ImpInventarioGear();
		setLvl(1); 
		// TODO Agregar Clases de PJ y pasar por
		// el tipo de clase al crear el PJ
		// Si se agregan clases de PJ
		// Los atributos se deben iniciarlizar
		// diferente para cada clase
		// Y el PrimaryAtt depende de cada clase
		setStr(10);
		setDex(8);
		setVit(9);
		setPrimaryAtt("str");
		calcSkillDamage(10);
		// Fin bloque HardCoded

		setMaxHp();
		setCurrentHp(getMaxHp());

		calcDmg();
		calcTotalArmor();
		calcAtkSpd();
		
			

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
	private void calcTotalArmor() {
		int totalArmor = 0;
		/*
		totalArmor += getGear().dameStat("MainHand", "Armor"); //TODO ARMADURA HxC
		 */
		totalArmor = this._MainHandArmor; //TODO valor HxC
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
	private void calcSkillDamage(int wpnDmgPercent) {
		
		/*
		int wpnDmg = (int) getGear().dameStat("MainHand", "Dmg"); // Saca el dmg del arma
		*/
		int wpnDmg = this._MainHandDmg; //TODO Valor HxC
		
		setSDmg((wpnDmgPercent * wpnDmg) / 100);
	}

	/**
	 * DAMAGE Formula: ( WpnDmg * SkillDmg ) * ( 1 + (PrimartySt / 100) ) ANTES
	 * DE LLAMAR a getDmg() se debe llamar a setSkillDamage(int wpnDmgPercent)
	 * 
	 * @return
	 */
	private void calcDmg() {
		
		int skillDamage =  getSDmg();
		
		/* TODO Esto va cuando implementemos el Gear y los Items
		int wpnDmg = (int) getGear().dameStat("MainHand", "Dmg"); // Saca el dmg del arma
		*/
		
		int wpnDmg = this._MainHandDmg; //TODO Valor HxC
		
		int damage = 0;
		damage = skillDamage * wpnDmg;
		damage *= (1 + (getPrimaryAttValue() / 100));

		setDmg(damage);
	}

	/**
	 * FORMULA DexAtkSpd: Dex * 0.1% FORMULA AtkSpd: WpnAtkSpd * ( 1 + DexAtkSpd
	 * )
	 */
	private void calcAtkSpd() {
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

	private InventarioGear getGear() {
		return gear;
	}

	private void setGear(InventarioGear gear) {
		this.gear = gear;
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

	private void setDmg(int dmg) {
		this.dmg = dmg;
	}

	private int getDmg() {
		return dmg;
	}

	private String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	private void setSDmg(int valor){
		this.sDmg = valor;
	}
	
	private int getSDmg(){
		return this.sDmg;
	}

	private int getExpAlRecibirDmg() {
		return expAlRecibirDmg;
	}

	private void setExpAlRecibirDmg(int expAlRecibirDmg) {
		this.expAlRecibirDmg = expAlRecibirDmg;
	}
}
