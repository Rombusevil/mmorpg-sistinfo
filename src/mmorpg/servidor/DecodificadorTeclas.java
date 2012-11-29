package mmorpg.servidor;

import java.io.Serializable;
import java.net.Socket;

import sun.misc.GC;

//La idea es apretar una tecla y no hablar con el jugador, sino crear un paquete ejecutable
//con el actor y la acción para mandarseló al gestor de comandos.

public class DecodificadorTeclas implements Serializable {
	private Actor pj;
	private ProveedorDeEnDireccion pMovedores;
	private ProveedorDeEnDireccion pAtaques;
	private GestorComandos gestorComandos;
	private Socket socketConectado;
	

	//Si sos server, en la clase sercer tenés todos los proveedores, si sos cliente, los proveedores están acá.
	/**
	 * 
	 * @param pj hay que pasarle el pj, porque esta clase está enfocada para el uso de los clientes
	 * y cada cliente es necesariamente un solo pj a la vez. Así como también, cada cliente tiene un solo
	 * gestor comandos.
	 * 
	 */
	
	public DecodificadorTeclas(Actor pj, GestorComandos gc, Socket socket){
		this.socketConectado = socket;
		this.pMovedores = new ImpProveedorDeMovedores();
		this.pAtaques   = new ImpProveedorAtacadores();
		this.pj = pj;
		this.gestorComandos = gc;
		
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
					AccionEnDireccion aed = decodificaCharMovedor(c);
					aed.setNewConnection(false);
					cmd.setAccion(aed);
					//Mando el comando al gestor para que haga todo lo que 
					gestorComandos.mandarComando(cmd, this.socketConectado);

				break;
				
			/** Ataques **/
			case 'i': 
			case 'k': 
			case 'j': 
			case 'l': 
					cmd = new CmdJugadorAccion();
					cmd.setPj(this.pj);
					AccionEnDireccion aed2 = decodificaCharAtaque(c);
					aed2.setNewConnection(false);
					cmd.setAccion(aed2);
					//Mando el comando al gestor para que haga todo lo que 
					gestorComandos.mandarComando(cmd, this.socketConectado);			
				break;
		}
	}
}
