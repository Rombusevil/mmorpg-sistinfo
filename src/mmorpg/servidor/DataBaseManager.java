package mmorpg.servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseManager {
	String dbName;

	public DataBaseManager(String dbName){
		this.dbName = dbName;
	}

	public Actor levantaPj(String usr, String pass) {		
		Connection connection = null;
		Actor pj = null;
		
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

			if (!rs.next()) {
				//TODO que cuando le pifies cree un pj nuevo
				System.out.println("usr o pass incorrectos");
			} else {
				pj = new PJ(); //Creo un pj y le cargo los datos de la bd
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
				
	
				
				/*
				System.out.print("name = " + rs.getString("usr") + " | ");
				System.out.print("pass = " + rs.getString("pass") + " | ");
				System.out.print("lvl = " + rs.getInt("lvl") + " | ");
				System.out.print("xp = " + rs.getInt("xp") + " | ");
				System.out.println();
				*/
				
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
		return null;
	}

	
	
	
	
	
//
//	  public static void main(String[] args) throws ClassNotFoundException {
//		// load the sqlite-JDBC driver using the current class loader
//		Class.forName("org.sqlite.JDBC");
//
//		Connection connection = null;
//		try { // create a database connection
//			connection = DriverManager.getConnection("jdbc:sqlite:actores.db");
//			Statement statement = connection.createStatement();
//			statement.setQueryTimeout(30); // set timeout to 30 sec.
//
//			statement.executeUpdate("drop table if exists actor");
//			statement.executeUpdate("create table actor (usr string, pass string, " +
//					"nombre string, lvl integer, xp integer, str integer, dex integer, vit integer, " +
//					"nombreItem string, dmgItem integer, armorItem integer, atkSpdItem integer)");
//			
//			statement.executeUpdate("insert into actor values('iber', 'iberpass', 'rofl', 1, 10, 10, 12, 15, 'espada', 20, 22, 30)");
//
//			String usr, pass;
//			usr = "iber";
//			pass = "iberpass";
//
//			ResultSet rs = statement.executeQuery("select * from actor where usr = '" + usr + "' and pass = '" + pass + "'");
//
//			if (!rs.next()) {
//				System.out.println("usr o pass incorrectos");
//			} else {
//				do {
//					System.out.print("name = " + rs.getString("usr") + " | ");
//					System.out.print("pass = " + rs.getString("pass") + " | ");
//					System.out.print("nombre = " + rs.getString("nombre") + " | ");
//					System.out.print("lvl = " + rs.getInt("lvl") + " | ");
//					System.out.print("xp = " + rs.getInt("xp") + " | ");
//					System.out.print("str = " + rs.getInt("str") + " | ");
//					System.out.print("dex = " + rs.getInt("dex") + " | ");
//					System.out.print("vit = " + rs.getInt("vit") + " | ");
//					System.out.print("dmgItem = " + rs.getInt("dmgItem") + " | ");
//					System.out.print("armorItem = " + rs.getInt("armorItem") + " | ");
//					System.out.print("atkSpdItem = " + rs.getInt("atkSpdItem") + " | ");
//					System.out.print("nombreItem = " + rs.getString("nombreItem") + " | ");
//					System.out.println();
//				} while (rs.next());
//			}
//
//		} catch (SQLException e) { // if the error message is "out of memory",
//									// //
//
//			System.err.println(e.getMessage());
//		} finally {
//			try {
//				if (connection != null)
//					connection.close();
//			} catch (SQLException e) { // connection close
//				System.err.println(e);
//			}
//		}
//	  }
}