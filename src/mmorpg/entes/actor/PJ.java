package mmorpg.entes.actor;

import java.io.Serializable;

public class PJ extends ImpActor implements  Serializable {
	private String usr;
	private String pass;
	
	public PJ(){
		this.setFichaDePersonaje(new ImpFichaDePersonaje());
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
