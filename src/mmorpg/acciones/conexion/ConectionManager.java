package mmorpg.acciones.conexion;

import mmorpg.acciones.Accion;
import mmorpg.entes.actor.Actor;
import mmorpg.mundo.Mundo;

public abstract class ConectionManager implements Accion {
	private Mundo mundo;
	private Actor pj;

	@Override
	public void actuar(Actor a) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getNewConnection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setNewConnection(boolean b) {
		// TODO Auto-generated method stub

	}

}
