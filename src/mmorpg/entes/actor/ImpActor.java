package mmorpg.entes.actor;

import java.io.Serializable;

import mmorpg.entes.Ente;
import mmorpg.entes.ImpEnteAtacable;
import mmorpg.mundo.Celda;

/* El actor cuando se construye tiene que tener una posicion, sino el programa crashea mas adelante */

public abstract class ImpActor extends ImpEnteAtacable implements Actor, Serializable{

	private FichaDePersonaje fp;
	
	public void atacaDer(){
		Ente enemigo = this.getCeldaActual().dameCeldaDer().getEnte();
		int dmg = this.getFichaDePersonaje().dameDmg();
		int lvl = this.getFichaDePersonaje().dameLvl();
		
		//NOTA: el mundo no esta cargado con entesNoAtacables. Ahora lo cargo, tambien se podria usar try-catch
		//Catcheamos cuando ente es null y significa que no tiene a nadie adentro.
		//Usamos patron state en el diseno, pero en la implementacion realmente con un try catch ya estamos listos...
		//Gangnam style niggas _|m|

		try{
			enemigo.sosAtacado(dmg,lvl); //Ataca al ente que tiene la celda
			this.getFichaDePersonaje().ganeExp(enemigo.dameXpPorGolpearte());
		}catch (NullPointerException npe){
			//LOL
		}
		
	}
	public void atacaIzq(){
		Ente enemigo = this.getCeldaActual().dameCeldaIz().getEnte();
		int dmg = this.getFichaDePersonaje().dameDmg();
		int lvl = this.getFichaDePersonaje().dameLvl();
		
		try{
			enemigo.sosAtacado(dmg,lvl); //Ataca al ente que tiene la celda
			this.getFichaDePersonaje().ganeExp(enemigo.dameXpPorGolpearte());
		}catch (NullPointerException npe){
			//LOL
		}
	}
	public void atacaAbajo(){
		Ente enemigo = this.getCeldaActual().dameCeldaAbajo().getEnte();
		int dmg = this.getFichaDePersonaje().dameDmg();
		int lvl = this.getFichaDePersonaje().dameLvl();
		
		try{
			enemigo.sosAtacado(dmg,lvl); //Ataca al ente que tiene la celda
			this.getFichaDePersonaje().ganeExp(enemigo.dameXpPorGolpearte());
		}catch (NullPointerException npe){
			//LOL
		}
	}
	public void atacaArriba(){
		Ente enemigo = this.getCeldaActual().dameCeldaArriba().getEnte();
		int dmg = this.getFichaDePersonaje().dameDmg();
		int lvl = this.getFichaDePersonaje().dameLvl();
		
		try{
			enemigo.sosAtacado(dmg,lvl); //Ataca al ente que tiene la celdaActual
			this.getFichaDePersonaje().ganeExp(enemigo.dameXpPorGolpearte());
		}catch (NullPointerException npe){
			//LOL
		}
	}
	
	public void sosAtacado(int dmg, int lvlAtacante){
		this.getFichaDePersonaje().recibiDmg(dmg, lvlAtacante);
	}
	
	//El pj se sale del mundo
	public void exitMundo(){
		Celda ca = this.getCeldaActual();
		
		ca.setEnte(ca.getEnteNoAtacable());	//Le pongo un ente no atacable a la celda
		ca.setEstado(ca.getEstadoLibre()); 	//Libero la celda
		
		/** @deprecated */
		this.getCeldaActual().setDibujo(this.getCeldaActual().getVacio()); //Le limpio el dibujo.
		//Fin deprecated
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
		
		this.getFichaDePersonaje().cargaFichaPj(nombre, lvl, xp, str, dex, vit, nombreItem, dmgItem, armorItem, atkSpdItem);		
	}
	
	@Override public double dameMovSpd(){
		return this.getFichaDePersonaje().dameMovSpd();
	}
	public FichaDePersonaje getFichaDePersonaje() {
		return fp;
	}
	public void setFichaDePersonaje(FichaDePersonaje fp) {
		this.fp = fp;
	}
	public int dameHP(){
		return this.getFichaDePersonaje().dameCurrentHp();
	}
	public int dameXP(){
		return this.getFichaDePersonaje().dameXp();
	}
	public int dameLvl(){
		return this.getFichaDePersonaje().dameLvl();
	}
	@Override
	public int dameDmg() {
		return this.getFichaDePersonaje().dameDmg();
	}

	@Override
	public double dameAtkSpd() {
		return this.getFichaDePersonaje().dameAtkSpd();
	}
	@Override
	public int dameXpPorGolpearte() {
		return this.getFichaDePersonaje().dameXpPorGolpearte();
	}
	
	@Override
	public boolean estasMuerto(){
		return this.getFichaDePersonaje().estasMuerto();
	}
	
	@Override
	public void morite(){
		this.getFichaDePersonaje().morite();
		System.out.println("ImpActor - morite!" );
	}
	
	@Override
	public void revivi(){
		this.getFichaDePersonaje().revivi();
	}

}
