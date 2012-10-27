package mmorpg.servidor;

public class ImpDibujoChar extends ImpDibujo {
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
