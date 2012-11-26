package mmorpg.servidor;

public class PJ extends ImpActor {
	private String usr;
	private String pass;
	
	public PJ(){
		this.fp = new ImpFichaDePersonaje();
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}
	
}
