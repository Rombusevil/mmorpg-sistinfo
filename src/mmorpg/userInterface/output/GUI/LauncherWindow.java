package mmorpg.userInterface.output.GUI;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import mmorpg.common.GestorComandos;
import mmorpg.entes.actor.Actor;
import mmorpg.userInterface.input.DecodificadorTeclas;
import mmorpg.userInterface.output.GUI.utils.SpriteFactory;

public class LauncherWindow extends JFrame implements KeyListener  {
	
	private DecodificadorTeclas decodificadorTeclas;
	
	public LauncherWindow(){
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
        
        // Construimos el repositorio de piezas
        spriteFactoryWorker.execute();
	}
	
	public void initClient(Actor pj, GestorComandos gc, Socket socketConectado){		
		this.decodificadorTeclas = new DecodificadorTeclas(pj, gc, socketConectado);
		addKeyListener(this);	
		setSize(800,300);
		setFocusable(true);
	}
	
	SwingWorker spriteFactoryWorker = new SwingWorker() {
		protected Object doInBackground() throws Exception {
			return SpriteFactory.get();
         }
         
	 };

	@Override
	public void keyPressed(KeyEvent tecla) {
		decodificadorTeclas.identificaCharComoAccion(tecla.getKeyChar());
		
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
