package mmorpg.userInterface.output.GUI;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import mmorpg.common.GestorComandos;
import mmorpg.entes.actor.Actor;
import mmorpg.userInterface.input.DecodificadorTeclas;
import mmorpg.userInterface.output.GUI.utils.SpriteFactory;

public class GameWindow extends JFrame implements KeyListener  {
	
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
	}
	
	public void initClient(Actor pj, GestorComandos gc, Socket socketConectado){		
		this.decodificadorTeclas = new DecodificadorTeclas(pj, gc, socketConectado);
		addKeyListener(this);
		
		setSize(gc.getMundo().getAncho()*100,gc.getMundo().getAlto()*22); // Ancho dinamico segun ancho del mundo
		setFocusable(true);
		
		this.toFront();
		this.setState(this.NORMAL);
		this.requestFocus();
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				decodificadorTeclas.identificaCharComoAccion('*');
				System.exit(0);
			}
		});
	}
	
	

	@Override
	public void keyPressed(KeyEvent tecla) {
		
	}

	@Override
	public void keyReleased(KeyEvent tecla) {
		decodificadorTeclas.identificaCharComoAccion(tecla.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	public DecodificadorTeclas getDecodificadorTeclas(){
		return this.decodificadorTeclas;
	}
}
