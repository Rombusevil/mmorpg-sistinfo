package mmorpg.servidor;


public class ImpControladorJugadores implements ControladorJugadores{
	ImpProveedorDeMovedores movimientos=new ImpProveedorDeMovedores();
	
	public void decodificaMovimientoDeJugador(char comando, Actor unJugador ){
		switch(comando){
			case 'a': movimientos.dameIzq().mover(unJugador);
					break;
			case 'd': movimientos.dameDer().mover(unJugador);
					break;
			case 's': movimientos.dameAbajo().mover(unJugador);
					break;
			case 'w': movimientos.dameArriba().mover(unJugador);
					break;
			default:
				System.out.println("tecla incorrecta");
				break;
		}
	}

	@Override
	public void CargarJugador(Actor unActor) {
		// TODO CargarJugador?
	}
}