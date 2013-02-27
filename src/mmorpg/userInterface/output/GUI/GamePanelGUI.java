package mmorpg.userInterface.output.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mmorpg.common.GestorComandos;
import mmorpg.entes.actor.Actor;
import mmorpg.entes.actor.ImpActor;
import mmorpg.mundo.ImpMundo;
import mmorpg.server.database.EstadoPjAGuardar;
import mmorpg.userInterface.input.DecodificadorTeclas;
import mmorpg.userInterface.output.ImpImprimidorMundosCLI;
import mmorpg.userInterface.output.ImpImprimidorMundosCLIJframe;

public class GamePanelGUI extends JPanel implements Observer{
	
	private GestorComandos gc;
	private ImpImprimidorMundosCLIJframe impJframe;
	
	private JLabel j1;
	private GameWindow gameWindow;
	
	public GamePanelGUI(){
		super();
		init();
	}
	
	public void init(){
		this.setBackground(new Color(0,0,0)); // Fondo negro
		this.impJframe = new ImpImprimidorMundosCLIJframe();
		j1= new JLabel("Cargando");
		j1.setFont(new Font("Courier New", Font.PLAIN, 12));
		j1.setForeground(new Color(0,255,0)); // Verde caracter
		add(j1,BorderLayout.CENTER);
	}
	
	// Este metodo se llama cada vez que hay un cambio en el mundo
	// Aca se actuliza la pantalla.
	@Override
	public void update(Observable arg0, Object arg1) {
		SwingUtilities.invokeLater(new Runnable() {
        	public void run() {   		
        		updateLabelText();
        		j1.paintImmediately(j1.getVisibleRect());
        	}
		});
		
	}
	
	public void setController(GestorComandos c) {
    	this.gc = c;
    	this.gc.addObserver(this);   
    }
	
	public void setJFrame(GameWindow frame){
		this.gameWindow = frame;
	}
	
	public void imprimeDatosPj(){
		ImpActor pj = (ImpActor) this.gc.getPjCliente();
		EstadoPjAGuardar estado = (pj.getFichaDePersonaje().creaEstadoPjAGuardar());
		String datosPj;
				
		// Muerte del PJ, carece de elegancia, pero anda 
		if(pj.dameHP() < 0){
			this.gameWindow.getDecodificadorTeclas().identificaCharComoAccion('*');
					
			
			datosPj = "<HTML>" +
					"<br><center>You died!</center>" +
					"<br>" +
					"<br>" +
					"<br><center>GAME OVER</center>" +
					"<br>" +
					"<br>" +
					"<br>" +
					"<br>" +
					"<br>Press x to close." +
					"</HTML>";
			
			
			this.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {
				}

				public void keyReleased(KeyEvent e) {
				}

				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_X) {
						System.exit(0);
					}
				}
			});
			this.setFocusable(true);
			this.requestFocus();
			
			
		}else{
			datosPj = 
					"<HTML> <table border='1'" +
					"<tr><td color=#FF0F00>" +
					"Personaje"+ estado.getNombre() +"  " +
					"</td><td>"+
					"Hit Points: "+ pj.dameHP() +"  " +
					"</td><td>"+
					"Code Lines: "+ pj.dameXP() +"  " +
					"</td><td>"+
					"Nivel: "+ pj.dameLvl() +"  " +
					"</td><td>"+
									
					"</td></tr><td color=#FF0F00>" +
					"Stats" +		
					"</td><td>"+
					"Binary Damage: "+ estado.getStr()+"  " +
					"</td><td>"+
					"Bug Shield: "+ estado.getVit() +"  " +
					"</td><td>"+
					"True Damage:	"+ pj.dameDmg()+"  " +
					"</td><td>"+
									
					"</td></tr><td color=#FF0F00>" +
					"Item" +
					"</td><td>"+
					"Item: "+ estado.getNombreItem() 	+"  " +
					"</td><td>"+
					"Item Dmg: "+ estado.getDmgItem() 			+"  "	+
					"</td><td>"+
					"Item Armor: "+ estado.getArmorItem() 	+"  " +
					"</td><td>"+
					"Item Atk.Spd: "+ estado.getAtkSpdItem()+"  " +
					"</td></tr>"; //+
					//"</HTML>";		
		}
		
		
		
		
		j1.setText(datosPj);
	}
	
	public void imprimiMundo(String mundo){
		j1.setText(j1.getText()+mundo+"</html>"); 
	}
	
	public void updateLabelText(){
		this.imprimeDatosPj();
		this.imprimiMundo(this.impJframe.dameMundoString(this.gc.getMundo()));		
	}

	
}
