package mmorpg.servidor;

public abstract class ImpEnteAtacable implements Ente {
	private Celda posicion;
	private ImpDibujo dibujo;

	@Override
	public void sosAtacado(int dmg,int lvlAtacante) {
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

	public Celda getCeldaActual() {
		return this.posicion;
	}
	public void setCeldaActual(Celda celda){
		this.posicion = celda;
	}

	public void setPosicion(Celda posicion) {
		this.posicion = posicion;
	}

	public void setDibujo(ImpDibujo dibujo){
		this.dibujo  = dibujo;
	}
	public ImpDibujo getDibujo(){
		return this.dibujo;
	}
	

}
