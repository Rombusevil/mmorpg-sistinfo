package mmorpg.entes.actor;

import java.io.Serializable;

import mmorpg.items.ItemEquipable;
import mmorpg.items.Espada;
import mmorpg.server.database.EstadoPjAGuardar;

public class ImpFichaDePersonaje implements FichaDePersonaje, Serializable {

	/* Items Equipados del personaje -> GEAR */
	Slot gear;

	int str; // produce armor
	int dex; // produce AtkSpd
	int vit; // produce HP
	String primaryAtt; // Atributo primario, depende de cada pj, otorga dmg
	String nombre;	// Nombre del personaje, no es el Login
	String skin; //El skin del pj
	int armor;		// Armadura total del PJ
	int dmg;		// El DMG total, con arma incluido
	int lvl; 		// Nivel del personaje
	int xp; 		// Experiencia acumulada
	int maxHp; 		// HP total, el tope maximo
	int currentHp; 	// HP actual, que va variando
	int skill_dmg; 	// dmg proveniente del skill utilizado
	double atkSpd; 	// cantidad de ataques por segundo
	double movSpd;	// Velocidad de movimiento del PJ, fixed number.
	int xpQueDoy; 	// Experiencia que da este PJ al recibir dmg
	boolean dead;	// True-> Muerto; False-> Vivo
	int BASE_EXP = 25; 	// Experiencie System Related
	int FACTOR_EXP = 2; // Experiencie System Related
	int xpos;	//posición x al momento de la desconexión
	int ypos;	//Posición y al momento de la desconexión

	/* OVERRIDE */

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	@SuppressWarnings("unused")
	@Override
	public void recibiDmg(int dmg, int attackerLvl) {
		// getDamageTaken se encarga tambien de restarte vida.
		// y de matar al PJ si la vida llega a cero
		int dmgTaken = this.getDamageTaken(dmg, attackerLvl);
	}

	@Override
	public int dameDmg() {
		int dmg = this.getDmg();
		return dmg;
	}

	@Override
	public double dameAtkSpd() {
		double atkSpd = this.getAtkSpd();
		return atkSpd;
	}

	@Override
	public int dameCurrentHp() {
		int currentHp = this.getCurrentHp();
		return currentHp;
	}

	@Override
	public int dameMaxHp() {
		int maxHp = this.getMaxHp();
		return maxHp;
	}

	@Override
	public int dameLvl() {
		int lvl = this.getLvl();
		return lvl;
	}

	@Override
	public int dameXp() {
		int xp = this.getXp();
		return xp;
	}

	@Override
	public void equipaItem(ItemEquipable item) {
		this.getGear().reemplazaItem(item);

		// Debo calcular mi nueva cantidad de armadura y dmg
		// cada vez que me equipo algo nuevo
		this.calcTotalArmor();
		this.calcDmg();
		this.calcAtkSpd();
	}

	@Override
	public void defineNombre(String nombre) {
		this.setNombre(nombre);
	}

	@Override
	public String dameNombre() {
		return this.getNombre();
	}

	@Override
	public int dameXpPorGolpearte() {
		return this.getExpAlRecibirDmg() * this.getLvl();
	}

	@Override
	public void ganeExp(int exp) {
		int experiencia = this.getXp();
		experiencia += exp;
		this.setXp(experiencia);
		this.checkLvlUp();
	}

	@Override
	public boolean estasMuerto() {
		return isDead();
	}

	@Override
	public void morite() {
		this.matarPj();
		this.setExpAlRecibirDmg(0);
		System.out.println("Ficha PJ - isDead: "+this.dead+"¡Me mori!" );
	}

	@Override
	public void revivi() {
		this.revivePj();

	}

	@Override
	public void cargaFichaPj(String nombre, int lvl, int xp, int str, int dex, int vit, String nombreItem, int dmgItem,
			int armorItem, double atkSpdItem, int xpos, int ypos) {

		this.cargaFicha(nombre, lvl, xp, str, dex, vit, nombreItem, dmgItem, armorItem, atkSpdItem, xpos, ypos);

	}
	
	@Override
	public double dameMovSpd(){
		return getMovSpd();
	}

	/**
	 * CONSTRUCTOR. Metodo que inicializa la hoja de personaje. Se debe ejecutar
	 * una vez durante la creacion del personaje. Le da al personaje un GEAR e
	 * inicializa sus atributos primarios.
	 * 
	 */
	public ImpFichaDePersonaje() {

		this.gear = new SlotGear(); // Inicializado el Gear
		ItemEquipable armaInicial = new Espada(10, 50, 1, "Espada de Madera"); // Creo
																				 // el
																				 // arma
																				 // inicial
		this.getGear().reemplazaItem(armaInicial); // Equipo el arma inicial

		this.setLvl(1); // Empieza en nivel 1
		this.setXp(this.BASE_EXP); // Experiencia inicial
		this.setDead(false); // El PJ empieza vivo
		this.setExpAlRecibirDmg(5); // Exp base que da este Pj al recibir dmg

		// TODO Agregar Clases de PJ y pasar por
		// el tipo de clase al crear el PJ
		// Si se agregan clases de PJ
		// Los atributos se deben iniciarlizar
		// diferente para cada clase
		// Y el PrimaryAtt depende de cada clase
		this.setStr(10);
		this.setDex(8);
		this.setVit(9);
		this.setPrimaryAtt("str");
		this.calcSkillDamage(10);
		// Fin bloque HardCoded

		this.setMaxHp(); // Calculo la vida maxima
		this.setCurrentHp(this.getMaxHp()); // Lleno la vida al maximo

		this.calcDmg(); // Calculo dmg
		this.calcTotalArmor(); // Calculo Armadura
		this.calcAtkSpd(); // Calculo AtkSpd

	}

	/**
	 * Devuelve el valor del Primary Attribute.
	 * 
	 * @return
	 */
	private int getPrimaryAttValue() {
		if (this.getPrimaryAtt() == "str") {
			return this.getStr();
		}
		if (this.getPrimaryAtt() == "dex") {
			return this.getDex();
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

		totalArmor += getGear().dameArmorItem(); // Saco armor del item
		totalArmor += this.getStr(); // Sumado al armor 1:1 de la Str.

		this.setArmor(totalArmor);
	}

	/**
	 * Damage Reduction Formula: (TotalArmor / ( 50 * Attacker Level +
	 * TotalArmor))*100
	 * 
	 * @return el % de reduccion de dmg.
	 */
	private int getDamageReduction(int attackerLevel) {
		int dmgRed = 0;
		int numerador = this.getArmor();
		int denominador = 50 * attackerLevel;
		denominador += this.getArmor();
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
		int wpnDmg = (int) getGear().dameDmgItem(); // Saca el dmg del arma

		this.setSDmg((wpnDmgPercent * wpnDmg) / 100);
	}

	/**
	 * DAMAGE Formula: ( WpnDmg * SkillDmg ) * ( 1 + (PrimartySt / 100) ) ANTES
	 * DE LLAMAR a getDmg() se debe llamar a setSkillDamage(int wpnDmgPercent)
	 * 
	 * @return
	 */
	private void calcDmg() {
		int skillDamage = this.getSDmg();
		int wpnDmg = getGear().dameDmgItem();
		int damage = 0;

		damage = skillDamage * wpnDmg;
		damage *= (1 + (this.getPrimaryAttValue() / 100));
		this.setDmg(damage);
	}

	/**
	 * FORMULA DexAtkSpd: Dex * 0.1% FORMULA AtkSpd: WpnAtkSpd * ( 1 + DexAtkSpd
	 * )
	 */
	private void calcAtkSpd() {
		double atkspd;
		double wpnAtkSpd = this.getGear().dameAtkSpdItem();
		double dexAtkSpd = (0.1 * this.getDex()) / 100;

		atkspd = round((wpnAtkSpd * (1 + dexAtkSpd)), 2);
		this.setAtkSpd(atkspd);
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
		int dmgRed = this.getDamageReduction(attackerLvl);
		int dmgTaken = dmg - ((dmgRed * dmg) / 100);
		int hp = this.getCurrentHp();
		hp -= dmgTaken;
		this.setCurrentHp(hp);
		if (this.getCurrentHp() <= 0) {
			// Estas muerto
			System.out.println("FichaPJ: Mi vida es: "+this.getCurrentHp()+" por eso me mori");
			this.morite();
		}
		return dmgTaken;
	}

	/**
	 * 
	 * Formula Hit Points: 36 + (4*LVL) + (10 * VIT)
	 */
	private void setMaxHp() {
		int maxHp;
		maxHp = 36 + (4 * this.getLvl() + (10 + this.getVit()));
		this.maxHp = maxHp;
	}

	/**
	 * Mata al personaje
	 */
	private void matarPj() {
		this.setDead(true);
	}

	private void revivePj() {
		this.setDead(false);
		this.setCurrentHp(getMaxHp());
	}

	/**
	 * Se fija si el PJ debe subir de nivel
	 */
	private void checkLvlUp() {
		int xp = this.getXp();
		int nextLvl = this.getLvl() + 1;
		int nextLvlExp;

		nextLvlExp = (int) (this.BASE_EXP * (Math.pow(nextLvl, this.FACTOR_EXP)));

		// Si da True, sube de nivel
		if (xp >= nextLvlExp) {
			this.LevelUp();
		}
	}

	private void LevelUp() {
		this.setLvl(this.getLvl() + 1); // Sube de Nivel

		// Suben los Atributos con el nivel
		// PrimaryAtt +3
		// SecundaryAtt +1
		// Vitality +2
		this.setStr(getStr() + 3);
		this.setDex(getDex() + 1);
		this.setVit(getVit() + 2);

		// Se recalcula la vida y se llena
		this.setMaxHp();
		this.setCurrentHp(getMaxHp());

		// Se recalcula el Dmg, armor y atkSpd
		this.calcDmg();
		this.calcTotalArmor();
		this.calcAtkSpd();
	}

	private void cargaFicha(String nombre, int lvl, int xp, int str, int dex, int vit, String nombreItem, int dmgItem,
			int armorItem, double atkSpdItem, int xpos, int ypos) {

		this.setNombre(nombre);
		this.setLvl(lvl);
		this.setXp(xp);
		this.setStr(str);
		this.setDex(dex);
		this.setVit(vit);
		
		//this.setDead(false); // El PJ empieza vivo

		// Creo un item con los atributos que recibi
		ItemEquipable item = new Espada(dmgItem, armorItem, atkSpdItem, nombreItem);
		// Le equipo el item
		this.getGear().reemplazaItem(item);

		// Se recalcula la vida y se llena
		this.setMaxHp();
		this.setCurrentHp(getMaxHp());
		
		// Exp base que da este Pj al recibir dmg
		this.setExpAlRecibirDmg(5); 
		
		this.setPrimaryAtt("str");
		this.calcSkillDamage(10);
		
		// Por las dudas lo revivo
		this.revivePj();

		// Se recalcula el Dmg, armor y atkSpd
		this.calcDmg();
		this.calcTotalArmor();
		this.calcAtkSpd();
		//Setea pos
		this.setXpos(xpos);
		this.setYpos(ypos);
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
		return Math.round(numero * Math.pow(10, decimales)) / Math.pow(10, decimales);
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

	private Slot getGear() {
		return gear;
	}

	private void setGear(Slot gear) {
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

	private void setSDmg(int valor) {
		this.skill_dmg = valor;
	}

	private int getSDmg() {
		return this.skill_dmg;
	}

	private int getExpAlRecibirDmg() {
		return xpQueDoy;
	}

	private void setExpAlRecibirDmg(int expAlRecibirDmg) {
		this.xpQueDoy = expAlRecibirDmg;
	}

	private boolean isDead() {
		return dead;
	}

	private void setDead(boolean dead) {
		this.dead = dead;
	}

	private double getMovSpd() {
		return movSpd;
	}

	private void setMovSpd(double movSpd) {
		this.movSpd = movSpd;
	}

	//FIXME revisar este mensaje en busca de errores de noob (?)
	// recibe las coordenadas de posición del pj a la hora de desconexión
	public EstadoPjAGuardar creaEstadoPjAGuardar() {
		EstadoPjAGuardar estado = new EstadoPjAGuardar();
		
		estado.setArmorItem(this.getGear().dameArmorItem());
		estado.setAtkSpdItem(this.getGear().dameAtkSpdItem());
		estado.setDmgItem(this.getGear().dameDmgItem());
		estado.setNombreItem(this.getGear().dameItem().dameNombreItem());
		estado.setDex(getDex());
		estado.setLvl(getLvl());
		estado.setNombre(getNombre());
		estado.setStr(getStr());
		estado.setVit(getVit());
		estado.setXp(getXp());
		
		
		return estado;
	}

	@Override
	public void setSkin(String skin) {
		this.skin = skin;
		
	}

	@Override
	public String getSkin() {
		return this.skin;
	}

}
