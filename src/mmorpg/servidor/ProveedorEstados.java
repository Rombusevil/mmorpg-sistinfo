package mmorpg.servidor;

public interface ProveedorEstados {
	public Estado getLibre();

	public void setLibre(Estado libre);

	public Estado getInaccesible();

	public void setInaccesible(Estado inaccesible);

}
