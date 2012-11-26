package mmorpg.servidor;
/**
 * Esta clase tiene un main para probar el DataBaseManager
 * 
 * @author rombus
 *
 */

public class _____MainDBkk_TEST_BORRAR_DEPRECATED {
	
	public static void main(String[] args){
		DataBaseManager dbm = new DataBaseManager("actores");
		
		PJ pj = new PJ();
		
		pj = (PJ) dbm.levantaPj("iber", "iberpass");
		
		pj.setUsr("lolo");
		
		dbm.guardaPj(pj);
		
		
		
	}

	

}
