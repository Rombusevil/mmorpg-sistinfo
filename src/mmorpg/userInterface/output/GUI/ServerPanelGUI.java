package mmorpg.userInterface.output.GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import mmorpg.server.Server;

public class ServerPanelGUI extends JPanel {
	
	private JButton startServer = new JButton("Start Server");
	private JButton stopServer = new JButton("Stop Server");
	private Server server;
	
	public ServerPanelGUI(){
		super();
		init();
	}
	
	public void init(){
		JLabel title = new JLabel("SERVER");
		
		JButton exit = new JButton("Salir");
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
		ImageIcon image = new ImageIcon("src/images/Launcher.jpg");
		JLabel imageLabel = new JLabel("", (Icon) image, JLabel.CENTER);
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 0;
		gbcontraints.anchor = GridBagConstraints.CENTER;
		gbcontraints.insets = new Insets(0, 0, 0, 0);
		add(imageLabel, gbcontraints);

		// Acciones del Boton StartServer
		startServer.setPreferredSize(new Dimension(300, 40));
		startServer.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 4;
		gbcontraints.anchor = GridBagConstraints.SOUTHWEST;
		gbcontraints.weighty = 5.0;
		gbcontraints.insets = new Insets(0, 30, 10, 0);
		startServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker sw = new SwingWorker() {
					
					@Override
					protected Object doInBackground() throws Exception {
						
						server = new Server();
						server.mainServer();
						
						return null;
					}
					
					@Override
	                protected void done() {
						startServer.setFocusPainted(false);
	                };   
				};
				sw.execute();  
			}
		});
		add(startServer, gbcontraints);
		
		// Acciones del Boton Salir
		exit.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		exit.setPreferredSize(new Dimension(100, 40));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 4;
		gbcontraints.anchor = GridBagConstraints.SOUTHEAST;
		gbcontraints.weightx = 3.0;
		gbcontraints.weighty = 4.0;
		gbcontraints.insets = new Insets(10, 10, 10, 10);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		exit.addKeyListener(new KeyListener() {
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
		add(exit, gbcontraints);
		
		
		
	}

}
