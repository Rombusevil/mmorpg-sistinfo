package mmorpg.servidor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * Esta va a ser la ventana jframe que va a mostrar los datos del pj
 * y va a tener el controladorTeclado.
 * @author rombus
 * 
 * La clase cliente va a instanciar esta ventana y le va a pasr el pj levantado de la BD
 * y un gestor de comandos.
 *
 */

public class Ventana extends JFrame implements KeyListener{
	private DecodificadorTeclas decodificadorTeclas;
	private JLabel j1;
	private ImpActor pj; //Es un impActor pq necesitamos que tenga la ficha de personaje.
	private Socket socketConectado;
	
	public Ventana(Actor pj, GestorComandos gc, Socket skt){
		this.socketConectado = skt;
		j1= new JLabel("");
		decodificadorTeclas = new DecodificadorTeclas(pj, gc, this.socketConectado);
		addKeyListener(this);
		
		this.pj = (ImpActor) pj;
		
		j1.setFont(new Font("Courier New", Font.PLAIN, 12));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(j1,BorderLayout.NORTH);
		setSize(800,800);
		setVisible(true);
		setFocusable(true);
	}
	
	/* El imprimidor de datos pj tiene que llamar a esta func. pasandole los datos
	 * a imprimir 
	 */
	public void imprimeDatosPj(){
		EstadoPjAGuardar estado = pj.fp.creaEstadoPjAGuardar();

		
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

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}		
	
}
