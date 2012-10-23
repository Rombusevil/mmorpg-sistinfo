package mmorpg.servidor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Los objetos creados en los métodos son locales a los métodos? no creo,
 * están en el heap
 **/

public class Main {
	public static void main(String[] args) {
		int alto, ancho;
		Mundo mundo;
		Celda entradaMundo;
		Actor actor = new PJ();
		Actor actor2 = new PJ();
		List<MovedorEnDireccion> listaDeMoverdores = new ArrayList<MovedorEnDireccion>();

		/** Movedores **/
		MovedorDerecha movedorDer = new MovedorDerecha();
		MovedorIzquierda movedorIz = new MovedorIzquierda();
		MovedorArriba movedorArr = new MovedorArriba();
		MovedorAbajo movedorAbj = new MovedorAbajo();

		/** Creo el mundo **/
		alto = ancho = 3; // Dimensión del mundo
		mundo = new ImpMundo(alto, ancho);
		entradaMundo = mundo.getEntradaMundo();

		/** Inicializo a los actores **/
		mundo.poneActorEn(0, 1, actor); // Pone al actor en el mundo para poder
										// moverlo
		mundo.poneActorEn(0, 0, actor2);

		mundo.muestraMundo(alto, ancho, entradaMundo);

		listaDeMoverdores.add(movedorDer);
		listaDeMoverdores.add(movedorAbj);
		listaDeMoverdores.add(movedorIz);
		listaDeMoverdores.add(movedorAbj);
		listaDeMoverdores.add(movedorIz);
		listaDeMoverdores.add(movedorArr);
		listaDeMoverdores.add(movedorArr);
		listaDeMoverdores.add(movedorArr);
		listaDeMoverdores.add(movedorArr);
		listaDeMoverdores.add(movedorDer);
		listaDeMoverdores.add(movedorDer);
		listaDeMoverdores.add(movedorDer);
		listaDeMoverdores.add(movedorDer);
		listaDeMoverdores.add(movedorDer);
		listaDeMoverdores.add(movedorDer);
		listaDeMoverdores.add(movedorDer);

		Iterator<MovedorEnDireccion> iterator = listaDeMoverdores.iterator();
		while (iterator.hasNext()) {

			MovedorEnDireccion cadaMovedor = (MovedorEnDireccion) iterator.next();
			cadaMovedor.mover(actor);
			mundo.muestraMundo(alto, ancho, entradaMundo);
		}

		// movedorDer.mover(actor);
		// movedorAbj.mover(actor);
		// movedorIz.mover(actor);
		// movedorAbj.mover(actor);
		// movedorIz.mover(actor);
		// movedorArr.mover(actor);


	}
}
