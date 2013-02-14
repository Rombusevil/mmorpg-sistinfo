package mmorpg.userInterface.output.GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mmorpg.common.GestorComandos;
import mmorpg.entes.actor.Actor;
import mmorpg.entes.actor.ImpActor;
import mmorpg.mundo.ImpMundo;
import mmorpg.server.database.EstadoPjAGuardar;
import mmorpg.userInterface.input.DecodificadorTeclas;
import mmorpg.userInterface.output.ImpImprimidorMundosCLI;
import mmorpg.userInterface.output.ImpImprimidorMundosCLIJframe;

public class GamePanelGUI extends JPanel implements KeyListener{
	
	private DecodificadorTeclas decodificadorTeclas;
	private JLabel j1;
	private ImpActor pj; //Es un impActor pq necesitamos que tenga la ficha de personaje.
	private Socket socketConectado;
	private ImpMundo mundo;
	
	public GamePanelGUI(GestorComandos gc, Socket skt, Actor pj){
		super();


		this.socketConectado = skt;
		j1= new JLabel("");
		decodificadorTeclas = new DecodificadorTeclas(pj, gc, this.socketConectado);
		addKeyListener(this);
		
		this.pj = (ImpActor) pj;
		
		j1.setFont(new Font("Courier New", Font.PLAIN, 12));
		add(j1,BorderLayout.NORTH);

		setSize(800,300);

		setVisible(true);
		setFocusable(true);
		
		ImpImprimidorMundosCLI imprimidor;
		ImpImprimidorMundosCLIJframe impJframe;
		
		impJframe = new ImpImprimidorMundosCLIJframe();
		
		do{
			this.imprimeDatosPj();
			this.imprimiMundo(impJframe.dameMundoString(getMundo()));			
		}while(true);
	}
	
	public void imprimeDatosPj(){
		EstadoPjAGuardar estado = pj.getFichaDePersonaje().creaEstadoPjAGuardar();

		
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

	public void keyPressed(KeyEvent tecla) {
		//Mapeo tecla a char y lo paso al decodificador.
		decodificadorTeclas.identificaCharComoAccion(tecla.getKeyChar());
		//j1.setText("la tecla pulsada es: "+ tecla.getKeyChar());//esto es temporal para verificar los controles
	}
	
	private ImpMundo getMundo() {
		return mundo;
	}

	public void setMundo(ImpMundo mundo) {
		this.mundo = mundo;
	}
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
