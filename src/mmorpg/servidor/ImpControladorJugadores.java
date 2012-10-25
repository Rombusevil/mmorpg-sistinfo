package mmorpg.servidor;

import java.util.HashMap;

public class ImpControladorJugadores implements ControladorJugadores{
	private ProveedorDeMovedores movimientos;
	private HashMap<Integer, Actor> mapaPjs;//sacado de aca:https://gist.github.com/644307
	private int num_jugadores;//sería como un contador.
	
	
	public ImpControladorJugadores(){
		this.setMapaPjs(new HashMap<Integer, Actor>());
		this.setMovimientos(new ImpProveedorDeMovedores());
		setNum_jugadores(0);
	}
	
	

	public void CargarJugador(Actor unActor){//
		getMapaPjs().put((getNum_jugadores()), unActor);
		
	}
	public void decodificaIdPj(String mensaje){
		//si fuera por socket tendriamos que definir como seria el formato del mensaje para extrer el id, por ejemplo
		//los primeros 2 char del mje seria el id.
		
		
	}
	
	
	
	
	//seters y geters 

	public HashMap<Integer, Actor> getMapaPjs() {
		return mapaPjs;
	}

	public void setMapaPjs(HashMap<Integer, Actor> mapaPjs) {
		this.mapaPjs = mapaPjs;
	}


	public ProveedorDeMovedores getMovimientos() {
		return movimientos;
	}


	public void setMovimientos(ProveedorDeMovedores movimientos) {
		this.movimientos = movimientos;
	}



	public int getNum_jugadores() {
		return num_jugadores++;
	}



	public void setNum_jugadores(int num_jugadores) {
		this.num_jugadores = num_jugadores;
	}

}
	