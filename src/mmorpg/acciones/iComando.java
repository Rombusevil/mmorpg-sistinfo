package mmorpg.acciones;

import java.net.Socket;
import java.util.List;
import java.util.Map.Entry;

import mmorpg.acciones.conexion.AccionDeConexion;
import mmorpg.entes.actor.Actor;
import mmorpg.mundo.Mundo;
import mmorpg.server.database.DataBaseManager;

/**
 * Los comandos deben ejecutar siempre
 * los 3 mensajes (hasta la fecha)
 * ejecutarEnDireccion();
 * ejecutarConexio(param);
 * mostarEnChat(param);
 */
public interface iComando {
	void ejecutarEnDireccion();
	void ejecutarConexion(List<Actor> newPjList, List<Actor> killPjList, Mundo mundo, Entry<Socket, Boolean> socket, DataBaseManager db);
	//void mostrarEnChat(Ventana v); //Esto lo dejo comentado porque hay que resolver la GUI antes, capaz podríamos poner una interfaz y listo.
	
	
	
	//Estos dos métodos capaz no deberían estar acá, pero bueno... va a andar.
	void setPj(Actor a);
	void setAccion(Accion aed);
	void setAccion(AccionDeConexion aed);
	void mandarComando(iComando cmd); //FIXME validar validez; Creo que no estamos usando más esto
	Actor getPj();
	Accion getAccion();
}
