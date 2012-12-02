package mmorpg.acciones.enDireccion;

import mmorpg.acciones.Accion;

public interface ProveedorDeEnDireccion {
	public Accion dameArriba();
	public Accion dameAbajo();
	public Accion dameIzq();
	public Accion dameDer();
	//public accionEnDireccion
}
