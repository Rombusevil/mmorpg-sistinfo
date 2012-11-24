package mmorpg.servidor;

//La idea es apretar una tecla y no hablar con el jugador, sino crear un paquete ejecutable
//con el actor y la acción para mandarseló al gestor de comandos.

public class DecodificadorTeclas {
	private Actor pj;
	private ProveedorDeEnDireccion pMovedores;
	private ProveedorDeEnDireccion pAtaques;
	
	//FIXME el constructor de esta clase es el encargado de instanciar los proveedores o se los pasan de arriba?
	//En el cliente, tiene mucho sentido que los proveedores se instancien acá.
	//Pero el servidor también tiene que tener los proveedores instanciados y no suena lógico que el 
	//servidor tenga un decodificadorTeclas.
	//Podemos hacer que la clase server (que tiene el mundo) tenga todos los proveedores. Eso suena lógico.
	
	/**
	 * 
	 * @param pj hay que pasarle el pj, porque esta clase está enfocada para el uso de los clientes
	 * y cada cliente es necesariamente un solo pj a la vez.
	 */
	
	public DecodificadorTeclas(Actor pj){
		this.pMovedores = new ImpProveedorDeMovedores();
		this.pAtaques   = new ImpProveedorAtacadores();
		this.pj = pj;
	}
	
	
	private AccionEnDireccion decodificaCharMovedor(char c){		
		switch(c){
			/** Movimientos **/
			case 'w':
				return pMovedores.dameArriba(); 
			case 's': 
				return pMovedores.dameAbajo(); 
			case 'a': 
				return pMovedores.dameIzq(); 
			case 'd': 
				return pMovedores.dameDer();
			default:
				return null;
		}
		
	}
	
	private AccionEnDireccion decodificaCharAtaque(char c){
		switch(c){
			/** Ataques **/
			case 'i': 
				return pAtaques.dameArriba();
			case 'k': 
				return pAtaques.dameAbajo(); 
			case 'j': 
				return pAtaques.dameIzq(); 
			case 'l': 
				return pMovedores.dameDer();
			default:
				return null;
		}
		
	}
	
	/**
	 * Esta func recibe un char que simboliza la tecla presionada y armar un comando
	 * con el pj del cliente y la acción que quiere relizar. La acción se obtiene
	 * parseando el caracter recibido a una acción.
	 * 
	 * @param c un caracter tomado del teclado.
	 */
	public void identificaCharComoAccion(char c){
		iComando cmd;
		
		switch(c){
			/** Movimientos **/
			case 'w': 
			case 's': 
			case 'a': 
			case 'd': 
					cmd = new CmdJugadorAccion();
					cmd.setPj(this.pj);
					cmd.setAccion(decodificaCharMovedor(c));
					//FIXME ahora habría que mandarle cmd al gestor de comandos.
				break;
				
			/** Ataques **/
			case 'i': 
			case 'k': 
			case 'j': 
			case 'l': 
					cmd = new CmdJugadorAccion();
					cmd.setPj(this.pj);
					cmd.setAccion(decodificaCharAtaque(c));
					//FIXME ahora habría que mandarle cmd al gestor de comandos.					
				break;
		}
	}
}
