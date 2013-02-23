package mmorpg.userInterface.output.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mmorpg.server.Server;
import mmorpg.userInterface.output.GUI.utils.BufferedImageLoader;
import mmorpg.userInterface.output.GUI.utils.Sprite;
import mmorpg.userInterface.output.GUI.utils.SpriteFactory;

public class LauncherPanelGUI extends JPanel {

	public LauncherPanelGUI() {
		super();
		init();
	}

	public void init() {
		JLabel title = new JLabel("M M O R P G");
		JButton clientLaunch = new JButton("Entrar al mundo (Cliente)");
		JButton netgame = new JButton("Crear un mundo (Servidor)");
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
		ImageIcon image = new ImageIcon("src/images/image.jpg");
		JLabel imageLabel = new JLabel("", (Icon) image, JLabel.CENTER);
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 0;
		gbcontraints.anchor = GridBagConstraints.CENTER;
		gbcontraints.weighty = 2.0;
		gbcontraints.insets = new Insets(50, 0, 0, 0);
		add(imageLabel, gbcontraints);

		// Acciones del Boton Cliente (LLAMA AL CLIENTE)
		clientLaunch.setPreferredSize(new Dimension(300, 40));
		clientLaunch.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 4;
		gbcontraints.anchor = GridBagConstraints.SOUTHWEST;
		gbcontraints.weighty = 5.0;
		gbcontraints.insets = new Insets(0, 30, 10, 0);
		clientLaunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame) getTopLevelAncestor();
				frame.getContentPane().removeAll();
				frame.invalidate();
				frame.setContentPane(new ConnectionPanelGUI());
				frame.validate();
			}
		});
		add(clientLaunch, gbcontraints);

		// Acciones del Boton Servidor (LLAMA AL SERVIDOR)
		netgame.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		netgame.setPreferredSize(new Dimension(300, 40));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 4;
		gbcontraints.anchor = GridBagConstraints.SOUTHEAST;
		gbcontraints.insets = new Insets(0, 0, 10, 130);
		netgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame) getTopLevelAncestor();
				frame.getContentPane().removeAll();
				frame.invalidate();
				frame.dispose();
				// Llama el Main del Server y empieza a escuchar conexiones
				Server server = new Server();
				server.mainServer();
				//
				// frame.validate();
			}
		});
		add(netgame, gbcontraints);

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
