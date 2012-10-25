package mmorpg.servidor;

public interface ControladorJugadores {
	
	public void CargarJugador(Actor unActor);

	/*Decodifica un jugador de su mapa, despues llama a su ProvedorDeMovedores llamando al metodo: "decodificaStringAdire()"  con el cual este la desifrara y le dar·
	el movedor que corresponda, luego el ImpControldeJugadores le enviar¡ a ese movedor el actor parar que se mueva.*/
	public void decodificaIdPj(String mensaje);
	

}
