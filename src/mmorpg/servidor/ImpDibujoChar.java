package mmorpg.servidor;

import java.io.Serializable;

public class ImpDibujoChar extends ImpDibujo implements Serializable {
	char dibujo;
	
	public ImpDibujoChar(char c){
		this.dibujo = c;
	}

	@Override
	public void imprimeDibujo() {
		System.out.print(this.getChar());
	}
	public char getChar(){
		return this.dibujo;
	}

}
