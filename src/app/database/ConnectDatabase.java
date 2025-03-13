package app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectDatabase {
	 private String url = "jdbc:mysql://localhost:3306/manageexams";
	 private String username = "root";
	 private String password = "0108";
	 
	 private Connection conn = null;

	 public Connection connectToDB() throws SQLException {
		 
		 System.out.println("Connecting database ...");

		 try {
		     Class.forName("com.mysql.cj.jdbc.Driver"); // Use com.mysql.jdbc.Driver if you're not on MySQL 8+ yet.
		     System.out.println("Driver loaded!");
		     conn = DriverManager.getConnection(url, username, password);
		     if(conn != null) {
		    	 return conn;
		     } else {
		    	 System.out.println("Disconnect!!!");
		     }
		     
		 } catch (ClassNotFoundException e) {
		     throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		 }
		return conn;
	 }
	 
	 public void closeConnect() throws SQLException {
		 if(conn != null) {
			 conn.close();
		 }
	 }
}
