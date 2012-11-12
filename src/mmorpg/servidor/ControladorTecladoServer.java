package mmorpg.servidor;


import java.awt.BorderLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ControladorTecladoServer extends JFrame implements KeyListener{

	
	private static final long serialVersionUID = 1L;
	Actor unActor;
	ControladorJugadores contJugador;
	JLabel j1= new JLabel("");
	JLabel j2= new JLabel("");
	
	
	/*
	por ahora estos parametros creo son correctos tambien del lado del cliente
	porque un Controlador de teclado deber�a conocer su jugador y 
	tambien conocer el "ControladorJugadores"(una sola instancia) del lado del servidor
	*/
	
	public ControladorTecladoServer(Actor unActor, ControladorJugadores contJugador){
		add(j1,BorderLayout.NORTH);
		addKeyListener(this);
		this.unActor=unActor;
		this.contJugador=contJugador;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(200,80);
		this.setVisible(true);
	}
	

	@Override
	public void keyPressed(KeyEvent tecla) {
		//FIXME les parece dejar este decodificador como un "decodificadorTeclado" en lugar de "decodificaMovimientoDeJugador"?
		//La idea es que decodifique los ataques también.
		contJugador.decodificaMovimientoDeJugador(tecla.getKeyChar(), unActor);
		j1.setText("la tecla pulsada es: "+ tecla.getKeyChar());//esto es temporal para verificar los controles
	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
	
	
	
	/*
	//uso un jugador en el constructor temporalmente porque se supone que cuando usemos socket enviara tanto la tecla como el jugador que la presiono.
	public void pedirAccion(ImpControladorJugadores controlar, Actor unJugador) throws IOException{
	System.out.println("Ingrese accion: ");
	char tecla = (char) System.in.read();
	controlar.decodificaMovimientoDeJugador(Character.toLowerCase(tecla), unJugador);
	}
	*/
