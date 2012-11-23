package mmorpg.servidor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ImpImprimidorConexionesJFrame extends JFrame implements ImprimidorConexiones {
	
	
	/* GUI Related */
	private JTextField commandLine;	 //Linea para escribir comandos
	private JTextArea commandWindow; //Ventana con el historial de comandos y mensajes

	@Override
	public void mostrarMensaje(String texto) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String preguntaUser(){
		return "";
		
	}
	
	@Override
	public String preguntaPass(){
		return "";		
	}
	
	/* Constructor */
	public ImpImprimidorConexionesJFrame(){
			super("SERVER WINDOW"); 			// Barra de titulo
			this.commandLine = new JTextField(); // Para escribir comandos
			//commandLine.setEditable(false); 	// Bloquea la escritura
			commandLine.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent event){     //Este metodo se llama cuando se escribe algo en commandLine y apretas enter
							mostrarMensaje(event.getActionCommand()+"\n");	// event.getActionCommand() tiene el texto de la caja de texto de commandLine
							commandLine.setText("");					    // Despues de enviar un comando, se borra y queda en blanco.
							
						}
					}
			);
			add(commandLine, BorderLayout.SOUTH); 	// Agrega la commandLine a la GUI
			commandWindow = new JTextArea();
			add(new JScrollPane(commandWindow));	// Agrega un scoll a la ventana
			setSize(300, 400);						// El tamaño de la ventana
			setVisible(true);			
	}
	
	// muestra un mensaje en la consola
	private void enviarMensaje(String str){
		try{
			output.writeObject("SERVER: " + str);
			output.flush();
			mostrarMensaje("SERVER: " + str);
		}catch(IOException ex){
			commandWindow.append("\n ERROR \n");
		}
	}
	
	// Actualiza la ventana de la consola
	private void mostrarMensaje2(final String text){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						commandWindow.append(text);
					}
				}
		);
	}

}
