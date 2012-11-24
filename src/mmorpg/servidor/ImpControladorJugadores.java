package mmorpg.servidor;

/**
 * usar decodificadorTeclas
 * @deprecated 
 *
 */

public class ImpControladorJugadores implements ControladorJugadores{
	
	public void decodificaAccion(char comando, Actor unJugador ){
		switch(comando){
			/** Movimientos **/
			case 'w':unJugador.moveteArriba();	break;
			case 's':unJugador.moveteAbajo();	break;
			case 'a': unJugador.moveteIzq();	break;
			case 'd': unJugador.moveteDer();	break;
				
			/** Ataques **/
			case 'i': unJugador.atacaArriba(); 	break;
			case 'k': unJugador.atacaAbajo();	break;
			case 'j': unJugador.atacaIzq();		break;
			case 'l': unJugador.atacaDer();		break;
				
			default:
				System.out.println("tecla incorrecta");
				break;
		}
	}

	@Override
	public void CargarJugador(Actor unActor) {
		// TODO CargarJugador?
		//Que sería cargar jugador?
	}
}