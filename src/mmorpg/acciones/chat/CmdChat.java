package mmorpg.acciones.chat;

import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.Map.Entry;

import mmorpg.acciones.Accion;
import mmorpg.acciones.iComando;
import mmorpg.acciones.conexion.AccionDeConexion;
import mmorpg.entes.actor.Actor;
import mmorpg.entes.actor.PJ;
import mmorpg.mundo.Mundo;
import mmorpg.server.database.DataBaseManager;

public class CmdChat implements iComando,Serializable {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private MostrarEnChat mec; //Conoce a un mosrtar en chat 
	private Actor pj;
	private String mensaje;
	
	
	
	@Override
	public void ejecutarEnDireccion() {
		// No lo implementa.

	}

	@Override
	public void setPj(Actor a) {
		this.pj=a;

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
		return this.pj;
	}

	@Override
	public Accion getAccion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ejecutarConexion(List<Actor> newPjList, List<Actor> killPjList, Mundo mundo, Entry<Socket, Boolean> socket, DataBaseManager db) {
		// No lo implementa
		
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
	public void ejecutarChat(MostrarEnChat mec1){
			mec1.actuar(((PJ)(this.pj)).getUsr(), this.mensaje);
		
	}
	public void setMensaje(String m){
		this.mensaje=m;
	}

	public MostrarEnChat getMec() {
		return mec;
	}

	public void setMec(ImpMostrarEnChat mec) {
		this.mec = mec;
	}

}
