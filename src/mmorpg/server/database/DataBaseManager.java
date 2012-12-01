package mmorpg.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mmorpg.entes.actor.Actor;
import mmorpg.entes.actor.PJ;

/**
 * Esta clase tiene los métodos para escribir y leer pj's de BD
 * Tiene un main para mostrar los datos de la BD por consola. Modificandolo
 * se pueden agregar tuplas, borrar, etc.
 * @author rombus
 *
 */

public class DataBaseManager {
	String dbName; //Nombre de la BD a conectarse

	public DataBaseManager(String dbName){
		this.dbName = dbName;
	}


	/**
	 * Este método tiene que recibir del cliente el usr y el pass, así crea un pj con esos datos
	 * restaura todos los atributos viejos y lo devuelve. 
	 * @param usr
	 * @param pass
	 * @return
	 */
	public Actor levantaPj(String usr, String pass) {		
		Connection connection = null;
		PJ pj = null; //Voy a guardar un PJ, no un impactor ni un actor. El PJ tiene el usr y pass.
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			System.out.println("Error cargando driver BD.");
			e1.printStackTrace();
		}

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			ResultSet rs = statement.executeQuery("select * from actor where usr = '" + usr + "' and pass = '" + pass + "'");

			pj = new PJ(); //Creo un pj y le cargo los datos de la bd o lo inicializo
			/*Cargo con usr y pass al pj nuevo*/
			pj.setUsr(usr);
			pj.setPass(pass);
			
			if (!rs.next()) {
				//Cuando ponés datos que no existen en la BD te crea un pj nuevo y lo mete en la BD.
				guardaPj(pj);
				System.out.println("Usuario Creado.");
			} else {				
				pj.cargaFichaPj(rs.getString("nombre"),
								rs.getInt("lvl"),
								rs.getInt("xp"),
								rs.getInt("str"),
								rs.getInt("dex"),
								rs.getInt("vit"),
								rs.getString("nombreItem"),
								rs.getInt("dmgItem"),
								rs.getInt("armorItem"),
								rs.getDouble("atkSpdItem"));
				
				System.out.println("Usuario Recuperado.");
			}

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}
		return pj;
	}

	

	/**
	 * Esta clase guarda el pj en la BD. 
	 * 
	 * @param pj Recive un PJ pq guarda el usr y pass
	 */
	public void guardaPj(PJ pj){
		String usr 	= pj.getUsr();
		String pass = pj.getPass();
		Connection connection = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			System.out.println("Error cargando driver BD.");
			e1.printStackTrace();
		}

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			ResultSet rs = statement.executeQuery("select * from actor where usr = '" + usr + "' and pass = '" + pass + "'");
			//Agarro el estado que me interesa guardar del pj
			EstadoPjAGuardar estado = pj.getFichaDePersonaje().creaEstadoPjAGuardar();
			
			if (!rs.next()) {
			/**Si no existe es pq el pj es nuevo, entonces lo creo**/
				String sql = "INSERT INTO actor (usr,pass,nombre,lvl,xp,str,dex,vit,nombreItem,dmgItem,armorItem,atkSpdItem)" +
						"VALUES ('"+usr +"','"+ pass+"',"	+
						"'" + estado.getNombre() 	+ "'," 	+ 
						estado.getLvl() + "," +  
						estado.getXp() 	+ "," + 
						estado.getStr() + "," + 
						estado.getDex() + "," + 
						estado.getVit() + "," + 
						"'" + estado.getNombreItem()	+"'," + 
						estado.getDmgItem() 	+ "," + 
						estado.getArmorItem() 	+ "," + 
						estado.getAtkSpdItem() 	+ ")";
					
				System.out.println(sql);
					statement.executeUpdate(sql);

			} else {
				//Si el pj ya existe, hay que updatear la tabla
				String sql = "UPDATE actor SET usr='" + usr + "', pass='" + pass + "', " +
						"nombre='" + estado.getNombre() + "' " + 						
						", lvl=" + estado.getLvl() + 
						", xp=" + estado.getXp() + 
						", str =" + estado.getStr() + 
						", dex=" + estado.getDex() + 
						", vit=" + estado.getVit() + 
						", nombreItem='" + estado.getNombreItem()+"' " + 
						", dmgItem=" + estado.getDmgItem() + 
						", armorItem=" + estado.getArmorItem() + 
						", atkSpdItem=" + estado.getAtkSpdItem() +
						" WHERE usr='"+ usr +"' and pass='"+ pass +"'";
				
				System.out.println(sql);
				statement.executeUpdate(sql);
				System.out.println("supuestamente guardó tu estado en la BD");
			}

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}
		
		
	}

	
	
	
	

	//Este main se puede usar para crear una bd y cargarle datos.
	//Actualmente está puesto para que te muestre todos los datos cargados
	  public static void main(String[] args) throws ClassNotFoundException {
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try { // create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:actores.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

//			statement.executeUpdate("drop table if exists actor");
//			statement.executeUpdate("create table actor (usr string, pass string, " +
//					"nombre string, lvl integer, xp integer, str integer, dex integer, vit integer, " +
//					"nombreItem string, dmgItem integer, armorItem integer, atkSpdItem integer)");
//			
//			statement.executeUpdate("insert into actor values('iber', 'iberpass', 'rofl', 1, 10, 10, 12, 15, 'espada', 20, 22, 30)");
			
			


			String usr, pass;

			ResultSet rs = statement.executeQuery("select * from actor ");

			if (!rs.next()) {
				System.out.println("usr o pass incorrectos");
			} else {
				do {
					System.out.print("name = " + rs.getString("usr") + " | ");
					System.out.print("pass = " + rs.getString("pass") + " | ");
					System.out.print("nombre = " + rs.getString("nombre") + " | ");
					System.out.print("lvl = " + rs.getInt("lvl") + " | ");
					System.out.print("xp = " + rs.getInt("xp") + " | ");
					System.out.print("str = " + rs.getInt("str") + " | ");
					System.out.print("dex = " + rs.getInt("dex") + " | ");
					System.out.print("vit = " + rs.getInt("vit") + " | ");
					System.out.print("dmgItem = " + rs.getInt("dmgItem") + " | ");
					System.out.print("armorItem = " + rs.getInt("armorItem") + " | ");
					System.out.print("atkSpdItem = " + rs.getInt("atkSpdItem") + " | ");
					System.out.print("nombreItem = " + rs.getString("nombreItem") + " | ");
					System.out.println();
				} while (rs.next());
			}

		} catch (SQLException e) { // if the error message is "out of memory",
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) { // connection close
				System.err.println(e);
			}
		}
	  }
}