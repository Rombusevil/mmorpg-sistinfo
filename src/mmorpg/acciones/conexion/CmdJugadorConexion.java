package mmorpg.acciones.conexion;

import java.io.Serializable;
import java.util.List;

import mmorpg.acciones.Accion;
import mmorpg.acciones.iComando;
import mmorpg.entes.actor.Actor;
import mmorpg.mundo.Mundo;

public class CmdJugadorConexion implements iComando, Serializable {
	private Actor pj;	//Creo que esto debería ser de tipo PJ
	private AccionDeConexion accion;
	
	
	
	
	
	// El método "ejecutate" de este comando
	public void ejecutarConexion(List<Actor> listaPj, Mundo mundo) {
		this.accion.actuaEnListaPj(this.pj, listaPj, mundo); 	//Lo pongo en las listas.
		this.accion.actuaEnMundo(this.pj, mundo);						//Lo pongo en el mundo
	}

	
	
	@Override
	public void setPj(Actor a) {
		this.pj = a;

	}

	@Override
	public void setAccion(Accion aed) {
		//mmm... esto huele mal. No debería estar el setAcción en la interfaz
		/*
	    _-`````-,           ,- '- .
	  .'   .- - |          | - -.  `.
	 /.'  /                     `.   \
	:/   :      _...   ..._      ``   :
	::   :     /._ .`:'_.._\.    ||   :
	::    `._ ./  ,`  :    \ . _.''   .
	`:.      /   |  -.  \-. \\_      /
	  \:._ _/  .'   .@)  \@) ` `\ ,.'
	     _/,--'       .- .\,-.`--`.
	       ,'/''     (( \ `  )    
	        /'/'  \    `-'  (      
	         '/''  `._,-----'
	          ''/'    .,---'
	           ''/'      ;:
	             ''/''  ''/
	               ''/''/''
	                 '/'/'
	                  `;		 
		 */
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
