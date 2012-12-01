package mmorpg.userInterface.output;

import java.io.Serializable;

import mmorpg.mundo.Celda;
import mmorpg.mundo.Mundo;


public class ImpImprimidorMundosCLIJframe implements ImprimidorMundos, Serializable {

	@Override
	public void imprimi(Mundo mundo){
		
	}
	public String dameMundoString(Mundo mundo) {
		int alto, ancho, i, j;
		Celda[][] mundoImprimir = mundo.getMundo();
		
		alto = mundo.getAlto();
		ancho = mundo.getAncho();
		
		String mundoText;
		mundoText = "";
		mundoText = "<html> ";
		
		for(i=0; i<alto; i++){
			for(j=0; j<ancho; j++){
				mundoText += "[ ";
				if(mundoImprimir[i][j].getDibujo().getChar() == ' ')
					mundoText += "&nbsp;";
				else
					mundoText += mundoImprimir[i][j].getDibujo().getChar();
				mundoText += " ]"; 
			}
			mundoText +="<br>";
		}
		mundoText +="<br> </html>";
		
		return mundoText;
	}
	
	
}
