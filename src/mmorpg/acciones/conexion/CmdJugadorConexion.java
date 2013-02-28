package mmorpg.acciones.conexion;

import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import mmorpg.acciones.Accion;
import mmorpg.acciones.iComando;
import mmorpg.acciones.chat.MostrarEnChat;
import mmorpg.entes.actor.Actor;
import mmorpg.mundo.Mundo;
import mmorpg.server.database.DataBaseManager;

public class CmdJugadorConexion implements iComando, Serializable {
	private Actor pj;	//Creo que esto debería ser de tipo PJ
	private AccionDeConexion accion;
	
	
	
	
	
	// El método "ejecutate" de este comando
	public void ejecutarConexion(List<Actor> newPjList, List<Actor> killPjList, Mundo mundo, Map.Entry<Socket,Boolean> socket, DataBaseManager db) {
		this.accion.actuaEnListaPj(this.pj, newPjList, killPjList, mundo, socket, db); 		// Actuo en las listas.
		this.accion.actuaEnMundo(this.pj, mundo);						// Actuo en el mundo.
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



	
	public void setMensaje(String m) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ejecutarChat(MostrarEnChat mostrarEnChat) {
		// TODO Auto-generated method stub
		
	}

}
