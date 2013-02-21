package mmorpg;

import javax.swing.SwingUtilities;


import mmorpg.userInterface.output.GUI.GameWindow;

public class Main {

	
	public static void main(String[] args) {
		
		// ENTRY POINT DEL PROGRAMA
		// Llama al Launcher, el cual pregunta al usuario
		// Si quiere lanzar el Cliente o el Server.
		// Entonces se ejecuta el que corresponda.
		
		// new Launcher();   // Viejo Launcher
		
		
		// Nuevo Launcher 
		
		SwingUtilities.invokeLater(new Runnable(){  
            public void run(){  
                new GameWindow().setVisible(true);  
            }  
        });

	}

}
