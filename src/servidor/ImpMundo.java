package servidor;

public abstract class ImpMundo implements Mundo {
	private Celda entradaMundo;
	
	public Celda getEntradaMundo(){
		
		return entradaMundo;
	}
	
	public void setEntradaMundo(Celda c){
		entradaMundo = c;
	}

}
