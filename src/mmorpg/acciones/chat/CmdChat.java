package mmorpg.acciones.chat;

import java.util.List;

import mmorpg.acciones.Accion;
import mmorpg.acciones.iComando;
import mmorpg.acciones.conexion.AccionDeConexion;
import mmorpg.entes.actor.Actor;
import mmorpg.mundo.Mundo;

public class CmdChat implements iComando {
	private MostrarEnChat mec; //Conoce a un mosrtar en chat 

	@Override
	public void ejecutarEnDireccion() {
		// No lo implementa.

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

	@Override
	public void ejecutarConexion(List<Actor> newPjList, List<Actor> killPjList, Mundo mundo) {
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

}
