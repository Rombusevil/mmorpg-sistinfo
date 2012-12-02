package mmorpg.mundo;

public interface ProveedorEstadosCelda {
	public OcupadorCeldaSegunEstado getLibre();

	public void setLibre(OcupadorCeldaSegunEstado libre);

	public OcupadorCeldaSegunEstado getInaccesible();

	public void setInaccesible(OcupadorCeldaSegunEstado inaccesible);

}
