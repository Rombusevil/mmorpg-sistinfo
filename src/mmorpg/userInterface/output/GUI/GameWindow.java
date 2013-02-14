package mmorpg.userInterface.output.GUI;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameWindow extends JFrame implements KeyListener {
	
	
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

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
