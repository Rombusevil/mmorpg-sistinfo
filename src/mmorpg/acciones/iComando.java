package mmorpg.acciones;

import mmorpg.entes.actor.Actor;

public interface iComando {
	void ejecutate();
	//Estos dos métodos capaz no deberían estar acá, pero bueno... va a andar.
	void setPj(Actor a);
	void setAccion(Accion aed);
	void mandarComando(iComando cmd);
	Actor getPj();
	Accion getAccion();
}
