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
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import mmorpg.acciones.chat.ImpMostrarEnChat;

import mmorpg.client.Client;
import mmorpg.entes.actor.ImpActor;

public class LoginPanelGUI extends JPanel {
	
	private JTextField user = new JTextField();
	private JTextField pass = new JPasswordField(10);
	private JTextField character = new JTextField("x");
	
	
	private String serverIp;
	private Integer serverPort;
	private Socket connection;
	private ImpActor pj;
	ImpMostrarEnChat mostrador;
	ChatGUI chat;
	
	private Client cliente;
	
	public LoginPanelGUI(String ip, Integer port){
		super();
		this.serverIp = ip;
		this.serverPort = port;
		init();
	}
	
	public void init(){
		JLabel title = new JLabel("LOGIN");
		JLabel labelUser = new JLabel("USR: ");
		JLabel labelPass = new JLabel("PWD: ");
		JLabel labelChar = new JLabel("Caracter: ");
		JButton conectar = new JButton("Login");
		GridBagConstraints gbcontraints;
		
		// Creo titulo
		setLayout(new GridBagLayout());
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		title.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 0;
		gbcontraints.anchor = GridBagConstraints.NORTH;
		gbcontraints.insets = new Insets(0, 0, 0, 0);
		add(title, gbcontraints);	
		
		// USER TEXT LABEL
		labelUser.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		labelUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridwidth = 200;
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 1;
		gbcontraints.anchor = GridBagConstraints.CENTER;
		gbcontraints.insets = new Insets(0, 0, 0, 250);
		gbcontraints.fill = GridBagConstraints.NONE;
		add(labelUser, gbcontraints);

		// USER TEXT AREA
		user.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridwidth = 200;
		gbcontraints.gridx = 1;
		gbcontraints.gridy = 1;
		gbcontraints.anchor = GridBagConstraints.WEST;
		gbcontraints.insets = new Insets(0, 0, 0, 0);
		gbcontraints.fill = GridBagConstraints.HORIZONTAL;
		add(user, gbcontraints);
		
		// PASS TEXT LABEL
		labelPass.setPreferredSize(new Dimension(300, 40));
		labelPass.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		labelPass.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridwidth = 200;
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 2;
		gbcontraints.anchor = GridBagConstraints.CENTER;
		gbcontraints.insets = new Insets(0, 0, 0, 0);
		gbcontraints.fill = GridBagConstraints.NONE;
		add(labelPass, gbcontraints);
		
		// PASS TEXT AREA
		pass.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridwidth = 200;
		gbcontraints.gridx = 1;
		gbcontraints.gridy = 2;		
		gbcontraints.anchor = GridBagConstraints.CENTER;
		gbcontraints.insets = new Insets(0, 0, 0, 0);
		gbcontraints.fill = GridBagConstraints.NONE;
		add(pass, gbcontraints);
		
		// PASS TEXT LABEL
		labelChar.setPreferredSize(new Dimension(300, 40));
		labelChar.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		labelChar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridwidth = 200;
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 3;
		gbcontraints.anchor = GridBagConstraints.CENTER;
		gbcontraints.insets = new Insets(0, 0, 0, 0);
		gbcontraints.fill = GridBagConstraints.NONE;
		add(labelChar, gbcontraints);
		
		// PASS TEXT AREA
		character.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridwidth = 200;
		gbcontraints.gridx = 1;
		gbcontraints.gridy = 3;		
		gbcontraints.anchor = GridBagConstraints.CENTER;
		gbcontraints.insets = new Insets(0, 0, 0, 0);
		gbcontraints.fill = GridBagConstraints.NONE;
		add(character, gbcontraints);

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
		
		conectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				SwingWorker sw = new SwingWorker() {
					
					@Override
					protected Object doInBackground() throws Exception {						
						System.out.println(serverIp);
						System.out.println(serverPort);	
						
						Client c = new Client(serverIp, serverPort, user.getText(), pass.getText(), character.getText());						
						Thread t = new Thread(c);
						t.start();

						cliente = c;
                        return c;
                    };
                    
                    @Override
                    protected void done() {
                    };                        
                };
                sw.execute();                
                // Esto es para darle tiempo al cliente de recibir
                // El PJ y el Mundo - TROFLMAO                
                while( (connection == null) || (pj == null)  ){
                	try {
                		System.out.println("Esperando 20ms");
    					Thread.sleep(20);
    					connection = cliente.getSocket();
    					pj = (ImpActor) cliente.getPj();
    				} catch (InterruptedException e1) {
    					e1.printStackTrace();
    				}
                }
                
                GameWindow gameWindow = (GameWindow) getTopLevelAncestor();
                gameWindow.getContentPane().removeAll();
                gameWindow.setLayout(new BorderLayout());
                GamePanelGUI gamePanel = new GamePanelGUI();
                gamePanel.setController(cliente.getGestorComandos());
                gamePanel.setJFrame(gameWindow);
                //gameWindow.setContentPane(gamePanel);
                gameWindow.add(gamePanel, BorderLayout.CENTER);
                chat=new ChatGUI(cliente,cliente.getGestorComandos());
                mostrador= new ImpMostrarEnChat();
                mostrador.setChat(chat);
                cliente.getGestorComandos().setMchat(mostrador);
                chat.setjFmundo(gameWindow);
                
                gameWindow.add(chat, BorderLayout.EAST);
                gameWindow.validate();
                gameWindow.initClient(cliente.getPj(), cliente.getGestorComandos(),cliente.getSocket());
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
