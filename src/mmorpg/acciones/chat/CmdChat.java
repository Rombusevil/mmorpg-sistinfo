package mmorpg.acciones.chat;

import mmorpg.acciones.Accion;
import mmorpg.acciones.iComando;
import mmorpg.entes.actor.Actor;

public class CmdChat implements iComando {
	private MostrarEnChat mec; //Conoce a un mosrtar en chat 

	@Override
	public void ejecutate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPj(Actor a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAccion(Accion aed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mandarComando(iComando cmd) {
		// TODO Auto-generated method stub

	}

	@Override
	public Actor getPj() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Accion getAccion() {
		// TODO Auto-generated method stub
		return null;
	}

}
