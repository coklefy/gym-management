package db;

import java.sql.*;

public class DBConnection {
	
	// Nome del dataBase
	private static String dbName = "db_technogym";
	
	public static void main(String[] args) {
		getMsSQLConnection();
		
		/*try {
			Tabella_Cliente.class.newInstance().dropAndCreateTable();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		*/
	}
	
	public static Connection getMsSQLConnection() {
		// MSSQL jtds
		
		String driver = "com.mysql.jdbc.Driver";
		String dbUri = "jdbc:mysql://localhost:3306/"+dbName;
		String userName = "root";
		String password = "";
		
		Connection connection = null;
		
	    try {
				System.out.println("DataSource.getConnection() driver = "+driver);
				Class.forName(driver);
				System.out.println("DataSource.getConnection() dbUri = "+dbUri);
				connection = DriverManager.getConnection(dbUri, userName, password);
			}
			catch (ClassNotFoundException e) {
				new Exception(e.getMessage());
				System.out.println("ErroreCNF"+ e.getMessage());
			}
			catch (SQLException e ) {
				new Exception (e.getMessage());
				System.out.println("ErroreSQL "+ e.getMessage());
			}
			return connection;
	}

}
