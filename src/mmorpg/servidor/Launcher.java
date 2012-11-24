package mmorpg.servidor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Launcher {
   
	
	private static final ActionListener Event = null;
    
	JComboBox combo1;	// Combobox con Cliente y Server
    Button boton;		// Boton OK
    Button botonExit;	// Boton para salir    
    String opcionElegida;

    public Launcher() {
        String opciones[] = { "Server", "Cliente"};
        
        JFrame frame = new JFrame("LAUNCHER");	// Crea Frame, titulo ventana
        JPanel panel = new JPanel();        	//
        combo1 = new JComboBox(opciones);		// Crea un combo con las opciones
        combo1.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));	// Elijo Font
        boton = new Button( "Launch"); 			// Texto del boton
        botonExit = new Button( "Exit"); 		// Texto del boton
        boton.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));	// Elijo Font
        botonExit.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));	// Elijo Font
        
        //Agrego todo al panel
        panel.add(combo1);
        panel.add(boton);
        panel.add(botonExit);
        frame.add(panel);

        //Acciones del Boton "LAUNCH"
        boton.addActionListener(new ActionListener() {
            private Server server;

			public void actionPerformed(ActionEvent e) {
            		opcionElegida = (String) combo1.getSelectedItem();                  
                    /* LLAMA AL SERVER O AL CLIENTE SEGUN CORRESPONDA */                     
                    if (opcionElegida.equals("Server")){
                    	System.out.println("ELIGIO SV");                    	
                    	this.server = new Server(); 
                    }
                    if (opcionElegida.equals("Cliente")){
                    	System.out.println("ELIGIO CL");                    	
                    	//this.client = new Client();
                    }                   
            }
        });
        
        //Acciones del boton "EXIT"
        botonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		System.exit(0);
            }
        });
        
        combo1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {

            }
        });
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}