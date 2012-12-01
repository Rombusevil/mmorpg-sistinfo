package mmorpg.entes;

import java.io.Serializable;

import mmorpg.mundo.Celda;
import mmorpg.userInterface.output.Dibujo;
import mmorpg.userInterface.output.ImpDibujo;

public class ImpEnteNoAtacable implements Ente,  Serializable {

	@Override
	public void sosAtacado(int dmg, int lvlAtacante) {
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
	public void setDibujo(ImpDibujo dibujo) {
		// TODO Auto-generated method stub

	}
	@Override
	public int dameXpPorGolpearte() {
		return 0;
	}

	@Override
	public Dibujo getDibujo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Celda getCeldaActual() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCeldaActual(Celda celda) {
		// TODO Auto-generated method stub

	}

}
