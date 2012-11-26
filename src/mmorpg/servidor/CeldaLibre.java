package mmorpg.servidor;

import java.io.Serializable;

public class CeldaLibre implements Estado, Serializable {

	//Este es el movedor que tiene la celda.
	public void ocupaCeldaCon(Celda celda, ImpActor actor) {
		Celda celdaAnterior = actor.getCeldaActual();
		Ente enteNoAtacable = celda.getEnteNoAtacable();
		
		actor.setCeldaActual(celda);	// Cambio la celda del actor
		celda.setEstado(celda.getProveedorEstados().getInaccesible()); // Pongo la celda como inaccesible
		celda.setDibujo(actor.getDibujo());	// Pongo el dibujo de la celda como el del actor que tiene adentro
		celda.setEnte(actor);	// Pongo el actor en celda
		
		celdaAnterior.setEstado(celda.getProveedorEstados().getLibre());	// Libero la celda anterior
		celdaAnterior.setDibujo(celda.getVacio());	// Pongo el dibujo vacío de la celda anterior
		celdaAnterior.setEnte(enteNoAtacable);// Pongo la celda vieja con un EnteNoAtacable
	}
}
