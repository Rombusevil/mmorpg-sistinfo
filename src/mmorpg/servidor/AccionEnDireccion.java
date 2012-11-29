package mmorpg.servidor;

public interface AccionEnDireccion {
	void actuar(Actor a);
	
	boolean getNewConnection();
	void setNewConnection(boolean b);

}
