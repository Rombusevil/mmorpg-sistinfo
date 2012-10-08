package servidor;

public class MundoElfico extends ImpMundo {
	private Celda entradaMundo;


	@Override
	public void setEntradaMundo(Celda c) {
		entradaMundo = c;		
	}
	public Celda getEntradaMundo(){
		return entradaMundo;
	}

}
