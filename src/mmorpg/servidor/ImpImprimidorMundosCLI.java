package mmorpg.servidor;

import java.io.Serializable;

public class ImpImprimidorMundosCLI implements ImprimidorMundos,  Serializable {

	@Override
	public void imprimi(Mundo mundo) {
		int alto, ancho, i, j;
		Celda[][] mundoImprimir = mundo.getMundo();
		
		alto = mundo.getAlto();
		ancho = mundo.getAncho();
		
		
		for(i=0; i<alto; i++){
			for(j=0; j<ancho; j++){
				System.out.print("[ ");
				mundoImprimir[i][j].imprimite(); // Le dice a la celda [i][j] que se imprima
				System.out.print(" ]");
			}
			System.out.println();
		}
		System.out.println();
	}
}
