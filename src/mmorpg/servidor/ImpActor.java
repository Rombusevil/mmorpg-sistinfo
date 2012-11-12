package mmorpg.servidor;

/* El actor cuando se construye tiene que tener una posición, sino el programa crashea más adelante */

public abstract class ImpActor extends ImpEnteAtacable implements Actor{
	//TODO inicializar ficha de personaje
	//TODO que sume XP matar a alguien, 
	FichaDePersonaje fp;
	
	public void atacaDer(){
		Ente enemigo = this.getCeldaActual().dameCeldaDer().getEnte();
		int dmg = this.fp.dameDmg();
		int lvl = this.fp.dameLvl();
		
		enemigo.sosAtacado(dmg,lvl); //Ataca al ente que tiene la celda
	}
	public void atacaIzq(){
		Ente enemigo = this.getCeldaActual().dameCeldaIz().getEnte();
		int dmg = this.fp.dameDmg();
		int lvl = this.fp.dameLvl();
		
		enemigo.sosAtacado(dmg,lvl); //Ataca al ente que tiene la celda
	}
	public void atacaAbajo(){
		Ente enemigo = this.getCeldaActual().dameCeldaAbajo().getEnte();
		int dmg = this.fp.dameDmg();
		int lvl = this.fp.dameLvl();
		
		enemigo.sosAtacado(dmg,lvl); //Ataca al ente que tiene la celda
	}
	public void atacaArriba(){
		Ente enemigo = this.getCeldaActual().dameCeldaArriba().getEnte();
		int dmg = this.fp.dameDmg();
		int lvl = this.fp.dameLvl();
		
		enemigo.sosAtacado(dmg,lvl); //Ataca al ente que tiene la celda
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
	

}
