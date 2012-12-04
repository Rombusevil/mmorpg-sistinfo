package mmorpg.acciones.conexion;

import java.util.List;

import mmorpg.acciones.Accion;
import mmorpg.acciones.iComando;
import mmorpg.entes.actor.Actor;
import mmorpg.mundo.Mundo;

public class CmdJugadorConexion implements iComando {
	private Actor pj;	//Creo que esto debería ser de tipo PJ
	private AccionDeConexion accion;
	
	
	
	
	
	// El método "ejecutate" de este comando
	public void ejecutarConexion(Actor pj, List<Actor> listaPj, Mundo mundo) {
		this.accion.actuaEnListaPj(pj, listaPj, mundo); 	//Lo pongo en las listas.
		this.accion.actuaEnMundo(pj, mundo);						//Lo pongo en el mundo
	}

	
	
	@Override
	public void setPj(Actor a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAccion(Accion aed) {
		//mmm... esto huele mal. No debería estar el setAcción en la interfaz
	}

	@Override
	public void mandarComando(iComando cmd) {
		// TODO Auto-generated method stub
	}

	@Override
	public Actor getPj() {
		return pj;
	}

	@Override
	public Accion getAccion() {
		//Esto huele mal también, no debería estar esto en la interfaz
		return null;
	}

	
	
	public void setAccion(AccionDeConexion accion) {
		this.accion = accion;
	}

	public void ejecutarEnDireccion() {
		//No implementa accionesEnDirección.
	}

}
