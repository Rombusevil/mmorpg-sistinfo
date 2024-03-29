package mmorpg.acciones;

import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.Map.Entry;

import mmorpg.acciones.chat.MostrarEnChat;
import mmorpg.acciones.conexion.AccionDeConexion;
import mmorpg.entes.actor.Actor;
import mmorpg.mundo.Mundo;
import mmorpg.server.database.DataBaseManager;

public class CmdJugadorAccion implements iComando, Serializable {
	private Accion aed;
	private Actor pj;
	
	
	public void setPj(Actor a){
		this.pj = a;
	}
	
	public Actor getPj(){
		return pj;
	}
	
	public void setAccion(Accion aed){
		this.aed = aed;
	}
	
	@Override
	public void setAccion(AccionDeConexion aed) {
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
	public void ejecutarEnDireccion() {
		this.aed.actuar(pj);
	}

	@Override
	public void mandarComando(iComando cmd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Accion getAccion(){ 
		return this.aed;
	}

	@Override
	public void ejecutarConexion(List<Actor> newPjList, List<Actor> killPjList, Mundo mundo, Entry<Socket, Boolean> socket, DataBaseManager db) {
		// No lo implementa
		
	}

	public void setMensaje(String m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ejecutarChat(MostrarEnChat mostrarEnChat) {
		// TODO Auto-generated method stub
		
	}

	

}
