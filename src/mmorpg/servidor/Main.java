package mmorpg.servidor;

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
		
		ImpControladorJugadores contJugador=new ImpControladorJugadores();
		 //temporalmente le pasamos un actor para que ande.
		
		
		//Celda entradaMundo;
		
		/*Crear un actor, crear un dibujo y despues asignarlo al actor.*/
		ImpActor actor = new PJ();
		ImpDibujo dbjActor = new ImpDibujoChar('x');
		
		
		ImpActor actor2 = new PJ();
		ImpDibujo dbjActor2 = new ImpDibujoChar('y');
		
		ImpActor actor3 = new PJ();
		ImpDibujo dbjActor3 = new ImpDibujoChar('z');
		
		
		ImprimidorMundos cli = new ImpImprimidorMundosCLI();	// Como usamos CLI hay que crear 
		ImpDibujoVacio vacio = new ImpDibujoCharVacio();		// un vacío CLI esto se puede hacer mejor... otro día.
		
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
		actor3.setDibujo(dbjActor3);
		
		
		
		
		/** Inicializo a los actores en el mundo **/
		mundo.poneActorEn(0, 1, actor); // Pone al actor en el mundo para poder moverlo
		mundo.poneActorEn(0, 0, actor2);
		mundo.poneActorEn(3, 6, actor3);
		cli.imprimi(mundo);
/*End Inicializacion*/


//		listaDeMoverdores.add(movedorDer);
//		listaDeMoverdores.add(movedorAbj);
//		listaDeMoverdores.add(movedorIz);
//		listaDeMoverdores.add(movedorAbj);
//		listaDeMoverdores.add(movedorIz);
//		listaDeMoverdores.add(movedorArr);
//		listaDeMoverdores.add(movedorArr);
//		listaDeMoverdores.add(movedorArr);
//		listaDeMoverdores.add(movedorArr);
//		listaDeMoverdores.add(movedorDer);
//		listaDeMoverdores.add(movedorDer);
//		listaDeMoverdores.add(movedorDer);
//		listaDeMoverdores.add(movedorDer);
//		listaDeMoverdores.add(movedorDer);
//		listaDeMoverdores.add(movedorDer);
//		listaDeMoverdores.add(movedorDer);
//		


		
		ControladorTecladoServer Teclado =new ControladorTecladoServer(actor,contJugador);
			while(true){//una forma paisana de movimiento en "tiempo real"
			cli.imprimi(mundo);
			Thread.sleep(300);
		}
		
		
		// movedorAbj.mover(actor);
		// movedorIz.mover(actor);
		// movedorAbj.mover(actor);
		// movedorIz.mover(actor);
		// movedorArr.mover(actor);
		
		

	}
}
