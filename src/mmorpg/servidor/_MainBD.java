package mmorpg.servidor;
/**
 * Esta clase tiene un main para probar el DataBaseManager
 * 
 * @author rombus
 *
 */

public class _MainBD {
	
	public static void main(String[] args){
		DataBaseManager dbm = new DataBaseManager("actores");
		
//		PJ pj = new PJ();
//		
//		pj = (PJ) dbm.levantaPj("pedro", "pedropass");
//		
//		//pj.setUsr("lolo");
//		//dbm.guardaPj(pj);
	
		
		//Probando la ventana
			Ventana v = new Ventana(new PJ(), new GestorComandos());
			v.imprimeDatosPj();

		
		
	}

	

}
