package mmorpg.mundo;

import java.io.Serializable;

public class ImpProveedorEstados implements ProveedorEstadosCelda,  Serializable {
	private OcupadorCeldaSegunEstado libre;
	private OcupadorCeldaSegunEstado inaccesible;
	
	public ImpProveedorEstados(){
		this.setLibre(new CeldaLibre());
		this.setInaccesible(new CeldaInaccesible());
	}
	
	
	public OcupadorCeldaSegunEstado getLibre() {
		return libre;
	}

	public void setLibre(OcupadorCeldaSegunEstado libre) {
		this.libre = libre;
	}

	public OcupadorCeldaSegunEstado getInaccesible() {
		return inaccesible;
	}

	public void setInaccesible(OcupadorCeldaSegunEstado inaccesible) {
		this.inaccesible = inaccesible;
	}

	

}
