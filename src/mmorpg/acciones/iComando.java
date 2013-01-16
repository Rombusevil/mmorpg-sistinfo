package mmorpg.acciones;

import java.util.List;

import mmorpg.acciones.conexion.AccionDeConexion;
import mmorpg.entes.actor.Actor;
import mmorpg.mundo.Mundo;

/**
 * Los comandos deben ejecutar siempre
 * los 3 mensajes (hasta la fecha)
 * ejecutarEnDireccion();
 * ejecutarConexio(param);
 * mostarEnChat(param);
 */
public interface iComando {
	void ejecutarEnDireccion();
	void ejecutarConexion(List<Actor> listaPj, Mundo mundo);
	//void mostrarEnChat(Ventana v); //Esto lo dejo comentado porque hay que resolver la GUI antes, capaz podríamos poner una interfaz y listo.
	
	
	
	//Estos dos métodos capaz no deberían estar acá, pero bueno... va a andar.
	void setPj(Actor a);
	void setAccion(Accion aed);
	void setAccion(AccionDeConexion aed);
	void mandarComando(iComando cmd); //FIXME validar validez
	Actor getPj();
	Accion getAccion();
}
