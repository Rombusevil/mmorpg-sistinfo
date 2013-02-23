package mmorpg.userInterface.output.GUI;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import mmorpg.server.Server;
import mmorpg.client.*;

public class ConnectionPanelGUI extends JPanel {
	
	
	private JTextField iptextarea;
	private JTextField puerto;
       
    
	
	public ConnectionPanelGUI(){
		super();
		init();
	}
	
	public void init(){
		JLabel title = new JLabel("CONECTAR A UN MUNDO");
		JLabel labelip = new JLabel("Dirección IP: ");		
		JButton conectar = new JButton("Conectar");
		GridBagConstraints gbcontraints;

		// Crea pantalla con dos Botones (Cliente - Servidor)
		setLayout(new GridBagLayout());
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		title.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 0;
		gbcontraints.anchor = GridBagConstraints.NORTH;
		gbcontraints.weighty = 1.0;
		gbcontraints.insets = new Insets(0, 0, 0, 0);
		add(title, gbcontraints);

		// IMAGEN DEL LAUNCHER
		ImageIcon image = new ImageIcon("src/images/image.jpg");
		JLabel imageLabel = new JLabel("", (Icon) image, JLabel.CENTER);
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 0;
		gbcontraints.anchor = GridBagConstraints.CENTER;
		gbcontraints.weighty = 2.0;
		gbcontraints.insets = new Insets(50, 0, 0, 0);
		add(imageLabel, gbcontraints);

		// IP TEXT LABEL
		labelip.setPreferredSize(new Dimension(300, 40));
		labelip.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 4;
		gbcontraints.anchor = GridBagConstraints.SOUTHWEST;
		gbcontraints.weighty = 5.0;
		gbcontraints.insets = new Insets(0, 30, 10, 0);
		add(labelip, gbcontraints);

		// IP TEXT AREA
		try {
			 
	        Properties config = new Properties();
	        config.load(new FileInputStream("config.properties"));
	        iptextarea=new JTextField(config.getProperty("serverIp"));
	        
	    } catch (IOException ex) {
	        System.out.println("Error al leer ip del server en config.properties"+ex);
	    }
		
		iptextarea.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		iptextarea.setPreferredSize(new Dimension(250, 40));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 4;
		gbcontraints.anchor = GridBagConstraints.SOUTH;
		gbcontraints.insets = new Insets(0, 0, 10, 130);
		add(iptextarea, gbcontraints);
		
		// puerto TEXT AREA
		try {
			 
	        Properties config = new Properties();
	        config.load(new FileInputStream("config.properties"));
	        puerto=new JTextField(config.getProperty("serverPort"));
	        
	    } catch (IOException ex) {
	        System.out.println("Error al leer puerto del cliente en config.properties"+ex);
	    }
		puerto.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		puerto.setPreferredSize(new Dimension(70, 40));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 4;
		gbcontraints.fill = GridBagConstraints.NONE;
		gbcontraints.anchor = GridBagConstraints.SOUTHEAST;
		gbcontraints.insets = new Insets(0, 200, 10, 250);
		add(puerto, gbcontraints);

		// CONECTAR
		conectar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		conectar.setPreferredSize(new Dimension(200, 40));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 4;
		gbcontraints.anchor = GridBagConstraints.SOUTHEAST;
		gbcontraints.weightx = 3.0;
		gbcontraints.weighty = 4.0;
		gbcontraints.insets = new Insets(10, 60, 10, 10);
		
		// Acciones del botón conectar
//		conectar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				SwingWorker sw = new SwingWorker() {
//					protected Object doInBackground() throws Exception {
//						System.out.println("Connect to " + iptextarea.getText() + ":" + puerto.getText());
//						Client c = new Client(iptextarea.getText(), Integer.valueOf(puerto.getText()));						
//						Thread t = new Thread(c);
//    					t.start();    					
//    					JFrame frame = (JFrame) getTopLevelAncestor();
//    					frame.dispose();    					
//                        return c;
//                    };
//                    protected void done() {
//
//                        };
//                        
//                };
//                sw.execute();
//            }
//    });		
		
		conectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame) getTopLevelAncestor();
				frame.getContentPane().removeAll();
				frame.invalidate();
				frame.setContentPane(new LoginPanelGUI(iptextarea.getText(), Integer.valueOf(puerto.getText())));
				frame.validate();
			}
		});
		
		conectar.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.exit(0);
				}
			}
		});
		add(conectar, gbcontraints);
	}

}
