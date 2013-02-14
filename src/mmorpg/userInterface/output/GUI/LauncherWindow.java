package mmorpg.userInterface.output.GUI;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import mmorpg.userInterface.output.GUI.utils.SpriteFactory;

public class LauncherWindow extends JFrame {
	
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
	
	SwingWorker spriteFactoryWorker = new SwingWorker() {
		protected Object doInBackground() throws Exception {
			return SpriteFactory.get();
         }
         
	 };
	
	
	

}
