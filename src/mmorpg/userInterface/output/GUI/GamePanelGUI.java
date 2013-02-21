package mmorpg.userInterface.output.GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

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
	private LauncherWindow gameWindow;
	
	public GamePanelGUI(){
		super();
		init();
	}
	
	public void init(){
		this.impJframe = new ImpImprimidorMundosCLIJframe();
		j1= new JLabel("HOLA");
		j1.setFont(new Font("Courier New", Font.PLAIN, 12));
		add(j1,BorderLayout.NORTH);
		
		System.out.println("--BUSCANDO JFRAME---");
		System.out.println(SwingUtilities.getWindowAncestor(this));
		LauncherWindow topFrame = (LauncherWindow) SwingUtilities.getRoot(this);
	}
	
	// Este metodo se llama cada vez que hay un cambio en el mundo
	// Aca se actuliza la pantalla.
	@Override
	public void update(Observable arg0, Object arg1) {
		SwingUtilities.invokeLater(new Runnable() {
        	public void run() {
        		System.out.println("ENTRE A IMPRIMIR");        		
        		updateLabelText();
        		j1.paintImmediately(j1.getVisibleRect());
				//gameWindow.validate();
        	}
		});
		
	}
	
	public void setController(GestorComandos c) {
    	this.gc = c;
    	this.gc.addObserver(this);   
    }
	
	public void setJFrame(LauncherWindow frame){
		this.gameWindow = frame;
	}
	
	public void imprimeDatosPj(){
		ImpActor pj = (ImpActor) this.gc.getPjCliente();
		EstadoPjAGuardar estado = (pj.getFichaDePersonaje().creaEstadoPjAGuardar());

		
		String datosPj = 
				"<HTML> <table border='1'" +
				"<tr><td color=#FF0F00>" +
				"Personaje" +
				"</td><td>"+
				"Salud: "+ pj.dameHP() +"  " +
				"</td><td>"+
				"Exp.: "+ pj.dameXP() +"  " +
				"</td><td>"+
				"Nivel: "+ pj.dameLvl() +"  " +
				"</td><td>"+
								
				"</td></tr><td color=#FF0F00>" +
				"Stats" +		
				"</td><td>"+
				"Fuerza: "+ estado.getStr()+"  " +
				"</td><td>"+
				"Destreza:	"+ estado.getDex() 	+"  " +
				"</td><td>"+
				"Vitalidad: "+ estado.getVit() +"  " +
				"</td><td>"+
								
				"</td></tr><td color=#FF0F00>" +
				"Item" +
				"</td><td>"+
				"Item: "+ estado.getNombreItem() 	+"  " +
				"</td><td>"+
				"Daño: "+ estado.getDmgItem() 			+"  "	+
				"</td><td>"+
				"Armadura: "+ estado.getArmorItem() 	+"  " +
				"</td><td>"+
				"Vel. Ataque: "+ estado.getAtkSpdItem()+"  " +
				"</td></tr>"; //+
				//"</HTML>";
		
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
