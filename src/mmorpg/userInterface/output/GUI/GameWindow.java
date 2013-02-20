package mmorpg.userInterface.output.GUI;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;

import javax.swing.JFrame;

import mmorpg.common.GestorComandos;
import mmorpg.entes.actor.Actor;
import mmorpg.userInterface.input.DecodificadorTeclas;

public class GameWindow extends JFrame implements KeyListener {
	
	private DecodificadorTeclas decodificadorTeclas;
	
	public GameWindow(){
		super("MMORPG");
		init();
	}
	
	public void init(){
		// FullScreen Methods
		
		setResizable(true);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setContentPane(new LauncherPanelGUI());	//LLAMA AL LAUNCHER
        setDefaultCloseOperation(EXIT_ON_CLOSE);        
        setFocusable(true);
	}
	
	public void initClient(Actor pj, GestorComandos gc, Socket socketConectado){		
		this.decodificadorTeclas = new DecodificadorTeclas(pj, gc, socketConectado);
		addKeyListener(this);		
	}

	@Override
	public void keyPressed(KeyEvent tecla) {
		decodificadorTeclas.identificaCharComoAccion(tecla.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}


}
