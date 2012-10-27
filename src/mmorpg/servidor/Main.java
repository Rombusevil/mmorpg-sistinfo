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
//		Celda entradaMundo;
		
		/*Crear un actor, crear un dibujo y despues asignarlo al actor.*/
		Actor actor = new PJ();
		ImpDibujo dbjActor = new ImpDibujoChar('x');
		
		Actor actor2 = new PJ();
		ImpDibujo dbjActor2 = new ImpDibujoChar('y');
		
		Actor actor3 = new PJ();
		ImpDibujo dbjActor3 = new ImpDibujoChar('g');
		
		
		ImprimidorMundos cli = new ImpImprimidorMundosCLI();	// Como usamos CLI hay que crear 
		ImpDibujoVacio vacio = new ImpDibujoCharVacio();		// un vacío CLI esto se puede hacer mejor... otro día.
		
		List<MovedorEnDireccion> listaDeMoverdores = new ArrayList<MovedorEnDireccion>();
		

		/** Movedores **/
		MovedorDerecha movedorDer = new MovedorDerecha();
		MovedorIzquierda movedorIz = new MovedorIzquierda();
		MovedorArriba movedorArr = new MovedorArriba();
		MovedorAbajo movedorAbj = new MovedorAbajo();
		
/*End Declarations*/
		
		/** Creo el mundo **/
		alto = ancho = 8; // Dimensión del mundo
		mundo = new ImpMundo(alto, ancho, vacio);
//		entradaMundo = mundo.getEntradaMundo();

		/** Asigno los dibujos a los actores **/
		actor.setDibujo(dbjActor);
		actor2.setDibujo(dbjActor2);
		actor3.setDibujo(dbjActor3);
		
		/** Inicializo a los actores en el mundo **/
		mundo.poneActorEn(0, 1, actor); // Pone al actor en el mundo para poder moverlo
		mundo.poneActorEn(0, 0, actor2);
		mundo.poneActorEn(3, 6, actor3);
		
/*End Inicializacion*/


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
			/* Refresca mundo*/
			for (int i=0; i< 50; i++)
				System.out.println();			
			cli.imprimi(mundo);
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException ie) {
			}
			
			MovedorEnDireccion cadaMovedor = (MovedorEnDireccion) iterator.next();
			cadaMovedor.mover(actor);
			
			movedorDer.mover(actor2);			
			movedorArr.mover(actor3);
			

			
		}
		
		
		// movedorAbj.mover(actor);
		// movedorIz.mover(actor);
		// movedorAbj.mover(actor);
		// movedorIz.mover(actor);
		// movedorArr.mover(actor);


	}
}
