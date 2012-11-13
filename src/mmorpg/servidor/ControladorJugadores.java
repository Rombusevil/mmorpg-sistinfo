package mmorpg.servidor;

public interface ControladorJugadores {
	

	public void CargarJugador(Actor unActor);

	//Recibe un char y lo decodifica en un movimiento o ataque
	public void decodificaAccion(char comando, Actor unJugador );
		
	

}
