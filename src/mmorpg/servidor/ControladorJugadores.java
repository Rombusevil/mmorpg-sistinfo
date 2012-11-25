package mmorpg.servidor;

//FIXME Ahora no usamos más un controladorJugadores. Hacemos todo por objetos comando ejecutables.

public interface ControladorJugadores {
	

	public void CargarJugador(Actor unActor);

	//Recibe un char y lo decodifica en un movimiento o ataque
	public void decodificaAccion(char comando, Actor unJugador );
	
		
	

}
