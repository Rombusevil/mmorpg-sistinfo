package mmorpg.servidor;

import java.io.Serializable;

public class ImpDibujoCharVacio extends ImpDibujoVacio implements Serializable {

	@Override
	public void imprimeDibujo() {
		System.out.print(" ");
	}

	@Override
	public char getChar() {
		
		
		return ' ';
	}

}
