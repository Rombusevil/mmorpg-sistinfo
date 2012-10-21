package mmorpg.servidor;

public class ImpProveedorEstados implements ProveedorEstados {
	private Estado libre;
	private Estado inaccesible;
	
	public ImpProveedorEstados(){
		this.setLibre(new CeldaLibre());
		this.setInaccesible(new CeldaInaccesible());
	}
	
	
	public Estado getLibre() {
		return libre;
	}

	public void setLibre(Estado libre) {
		this.libre = libre;
	}

	public Estado getInaccesible() {
		return inaccesible;
	}

	public void setInaccesible(Estado inaccesible) {
		this.inaccesible = inaccesible;
	}

	

}
