package mmorpg.servidor;

public interface ProveedorDeMovedores {
	public MovedorEnDireccion dameArriba();
	public MovedorEnDireccion dameAbajo();
	public MovedorEnDireccion dameIzq();
	public MovedorEnDireccion dameDer();
	public MovedorEnDireccion decodificaStringADire(String comando);

}
