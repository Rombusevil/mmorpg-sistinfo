package mmorpg.servidor;

/* El actor cuando se construye tiene que tener una posicion, sino el programa crashea mas adelante */

public abstract class ImpActor extends ImpEnteAtacable implements Actor{

	FichaDePersonaje fp;
	
	public void atacaDer(){
		Ente enemigo = this.getCeldaActual().dameCeldaDer().getEnte();
		int dmg = this.fp.dameDmg();
		int lvl = this.fp.dameLvl();
		
		//NOTA: el mundo no esta cargado con entesNoAtacables. Ahora lo cargo, tambien se podria usar try-catch
		//Catcheamos cuando ente es null y significa que no tiene a nadie adentro.
		//Usamos patron state en el diseno, pero en la implementacion realmente con un try catch ya estamos listos...
		//Gangnam style niggas _|m|

		try{
			enemigo.sosAtacado(dmg,lvl); //Ataca al ente que tiene la celda
			this.fp.ganeExp(enemigo.dameXpPorGolpearte());
		}catch (NullPointerException npe){
			//LOL
		}
		
	}
	public void atacaIzq(){
		Ente enemigo = this.getCeldaActual().dameCeldaIz().getEnte();
		int dmg = this.fp.dameDmg();
		int lvl = this.fp.dameLvl();
		
		try{
			enemigo.sosAtacado(dmg,lvl); //Ataca al ente que tiene la celda
			this.fp.ganeExp(enemigo.dameXpPorGolpearte());
		}catch (NullPointerException npe){
			//LOL
		}
	}
	public void atacaAbajo(){
		Ente enemigo = this.getCeldaActual().dameCeldaAbajo().getEnte();
		int dmg = this.fp.dameDmg();
		int lvl = this.fp.dameLvl();
		
		try{
			enemigo.sosAtacado(dmg,lvl); //Ataca al ente que tiene la celda
			this.fp.ganeExp(enemigo.dameXpPorGolpearte());
		}catch (NullPointerException npe){
			//LOL
		}
	}
	public void atacaArriba(){
		Ente enemigo = this.getCeldaActual().dameCeldaArriba().getEnte();
		int dmg = this.fp.dameDmg();
		int lvl = this.fp.dameLvl();
		
		try{
			enemigo.sosAtacado(dmg,lvl); //Ataca al ente que tiene la celdaActual
			this.fp.ganeExp(enemigo.dameXpPorGolpearte());
		}catch (NullPointerException npe){
			//LOL
		}
	}
	
	public void sosAtacado(int dmg, int lvlAtacante){
		this.fp.recibiDmg(dmg, lvlAtacante);
	}
	

	public void moveteDer(){
		Celda celdaActual, celdaDestino;
		
		celdaActual = this.getCeldaActual();
		celdaDestino = celdaActual.dameCeldaDer();
		
		celdaDestino.getEstado().ocupaCeldaCon(celdaDestino, this);
		
	}
	
	public void moveteIzq(){
		Celda celdaActual, celdaDestino;
		
		celdaActual = this.getCeldaActual();
		celdaDestino = celdaActual.dameCeldaIz();
		
		celdaDestino.getEstado().ocupaCeldaCon(celdaDestino, this);
		
	}
	
	public void moveteAbajo(){
		Celda celdaActual, celdaDestino;
		
		celdaActual = this.getCeldaActual();
		celdaDestino = celdaActual.dameCeldaAbajo();
		
		celdaDestino.getEstado().ocupaCeldaCon(celdaDestino, this);
		
	}
	public void moveteArriba(){
		Celda celdaActual, celdaDestino;
		
		celdaActual = this.getCeldaActual();
		celdaDestino = celdaActual.dameCeldaArriba();
		
		celdaDestino.getEstado().ocupaCeldaCon(celdaDestino, this);
		
	}
	
	public int dameHP(){
		return this.fp.dameCurrentHp();
	}
	public int dameXP(){
		return this.fp.dameXp();
	}
	public int dameLvl(){
		return this.fp.dameLvl();
	}
	@Override
	public int dameDmg() {
		return this.fp.dameDmg();
	}

	@Override
	public double dameAtkSpd() {
		return this.fp.dameAtkSpd();
	}
	@Override
	public int dameXpPorGolpearte() {
		return this.fp.dameXpPorGolpearte();
	}
	
	@Override
	public void cargaFichaPj(String nombre, 
			int lvl,
			int xp,
			int str, 
			int dex, 
			int vit, 
			String nombreItem,
			int dmgItem, 
			int armorItem, 
			double atkSpdItem){
		
		this.fp.cargaFichaPj(nombre, lvl, xp, str, dex, vit, nombreItem, dmgItem, armorItem, atkSpdItem);		
	}

}
