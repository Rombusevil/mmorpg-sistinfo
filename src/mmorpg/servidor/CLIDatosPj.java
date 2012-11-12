package mmorpg.servidor;

/**
 * Imprime el nivel de exp. de salud y el Level por consola
 * 
 * @author rombus
 *
 */

public class CLIDatosPj implements ImprimidorDatosPj {

	@Override
	public void imprimiXP(Actor actor) {
		int xp = actor.dameXP();
		System.out.print("XP: "+xp);
	}

	@Override
	public void imprimiHP(Actor actor) {
		int hp = actor.dameHP();
		System.out.print("HP: "+hp);
	}

	@Override
	public void imprimiGear(Actor actor) {
		//TODO imprime gear
	}

	@Override
	public void imprimiLvl(Actor actor) {
		int lvl = actor.dameLvl();
		
		System.out.print("Lvl: "+lvl);
	}

	@Override
	public void imprimiTodo(Actor actor) {
		this.imprimiHP(actor);	System.out.print(" ");
		this.imprimiLvl(actor); System.out.print(" ");
		this.imprimiXP(actor); 	System.out.print(" ");
		this.imprimiGear(actor);System.out.println();
	}

}
