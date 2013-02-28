package mmorpg.userInterface.output.GUI;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

import mmorpg.client.Client;
import mmorpg.common.GestorComandos;
import mmorpg.entes.actor.Actor;
import mmorpg.entes.actor.ImpActor;
import mmorpg.entes.actor.PJ;
import net.miginfocom.swing.MigLayout;
import mmorpg.acciones.chat.CmdChat;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;


public class ChatGUI extends JPanel {
    private GestorComandos gc;
    private CmdChat cmd;
    private Client c;
    private JFrame jFmundo;
    private JScrollPane scrollPane;
    private JLabel textSala;
    private JTextField textChat;
   
   
  
    public ChatGUI(Client cliente, GestorComandos gestorComandos) {
    	setBackground(Color.BLACK);
    	setBorder(UIManager.getBorder("TextArea.border"));
    	this.setFocusable(false);
    	this.c=cliente;
        setLayout(new MigLayout("", "[-4.00px][200.00px,grow][3.00]", "[][20px][135.00,grow]"));
        gc=gestorComandos;
          JLabel lblChat = new JLabel("CHAT:");
          lblChat.setForeground(Color.RED);
          add(lblChat, "cell 1 0");
          
          textChat = new JTextField();
          textChat.setForeground(Color.BLACK);
          textChat.setBackground(Color.GREEN);
          add(textChat, "cell 1 1,growx");
          textChat.setColumns(10);
          textChat.addKeyListener(new KeyListener() {
  			public void keyTyped(KeyEvent e) {
  			}

  			public void keyReleased(KeyEvent e) {
  			}

  			public void keyPressed(KeyEvent e) {
  				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
  					cmd=new CmdChat();
  	            	cmd.setPj(gc.getPjCliente());
  	              	cmd.setMensaje( textChat.getText());
  	                gc.mandarComando(cmd, c.getSocket());
  	                getjFmundo().requestFocus();
  	                textChat.setText(null);
  				}
  			}
  		});
         
          textSala =  new JLabel();
          textSala.setForeground(Color.BLACK);
          textSala.setBackground(Color.GREEN);
          textSala.setVerticalAlignment(SwingConstants.TOP);
          scrollPane = new JScrollPane(textSala, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
          add(scrollPane, "cell 1 2,grow");
          scrollPane.setViewportView(textSala);
          
         
          
         

    }
    public void mostrarEnChat(String pj,String mensaje){ 
         if(!(pj==((PJ)(gc.getPjCliente())).getUsr()))
            textSala.setText("<html>"+textSala.getText()+pj+ ":" +mensaje+"<P>");      
         
    }
    
	public JFrame getjFmundo() {
		return jFmundo;
	}
	public void setjFmundo(JFrame jFmundo) {
		this.jFmundo = jFmundo;
	}
	
    
}