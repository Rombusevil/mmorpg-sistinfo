package mmorpg.servidor;
//FIXME Habría que poner algún límite de velocidad de movimiento en los actores.

import java.io.IOException;
//FIXME Todos estos imports comentados deberían estar acá?
//import java.awt.BorderLayout;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;


public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		int alto, ancho;
		Mundo mundo;
		
		ImpControladorJugadores contJugador = new ImpControladorJugadores();		
		
		//Celda entradaMundo;
		
		/*Crear un actor, crear un dibujo y despues asignarlo al actor.*/
		ImpActor actor = new PJ();
		ImpDibujo dbjActor = new ImpDibujoChar('x');
		
		ImpActor actor2 = new PJ();
		ImpDibujo dbjActor2 = new ImpDibujoChar('y');
		
/** Imprimidores **/
		ImprimidorMundos cliMundo = new ImpImprimidorMundosCLI();	// Como usamos CLI hay que crear 
		ImpDibujoVacio vacio = new ImpDibujoCharVacio();			// un vacío CLI esto se puede hacer mejor... otro día.
		
		ImprimidorDatosPj cliDatos = new CLIDatosPj();	// Imprimidor de xp salud lvl por consola
		
/** Fin Imprimidores **/

//		List<MovedorEnDireccion> listaDeMoverdores = new ArrayList<MovedorEnDireccion>();
		
		
		/** Movedores **/
//		MovedorDerecha movedorDer = new MovedorDerecha();
//		MovedorIzquierda movedorIz = new MovedorIzquierda();
//		MovedorArriba movedorArr = new MovedorArriba();
//		MovedorAbajo movedorAbj = new MovedorAbajo();
		
/*End Declarations*/
		
		/** Creo el mundo **/
		alto = ancho = 8; // Dimensión del mundo
		mundo = new ImpMundo(alto, ancho, vacio);

		/** Asigno los dibujos a los actores **/
		actor.setDibujo(dbjActor);
		actor2.setDibujo(dbjActor2);
		
		/** Inicializo a los actores en el mundo **/
		mundo.poneActorEn(0, 1, actor); // Pone al actor en el mundo para poder moverlo
		mundo.poneActorEn(0, 0, actor2);
		cliMundo.imprimi(mundo);
		
/*End Inicializacion*/

		/*
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
		listaDeMoverdores.add(movedorDer);*/

		
		ControladorTecladoServer Teclado = new ControladorTecladoServer(actor,contJugador);
		
		/** Game Loop **/
		while(true){//una forma paisana de movimiento en "tiempo real"
			cliMundo.imprimi(mundo);
			
			for(int i=0; i< 1; i++)
				System.out.println();
			
			//Imprime las stats de tu enemigo que es el que recibe los golpes en esta prueba 
			System.out.print("Enemigo: ");
			cliDatos.imprimiTodo(actor2);
			
			
			Thread.sleep(300);
		}
		
		
		// movedorAbj.mover(actor);
		// movedorIz.mover(actor);
		// movedorAbj.mover(actor);
		// movedorIz.mover(actor);
		// movedorArr.mover(actor);
		
		

	}
}
