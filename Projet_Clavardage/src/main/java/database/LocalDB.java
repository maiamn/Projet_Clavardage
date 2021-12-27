package database;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet ; 
import java.sql.DriverManager;

//http://blog.paumard.org/cours/jdbc/chap02-apercu-exemple.html
public class LocalDB {
	
	// Attributes 
	Connection connection ; 
	Statement statement ;
	
	// Constructor 
	public LocalDB() {
		System.out.println("Calling LocalDB constructor");
		
		// Load the driver class file 
		try {
			System.out.println("loading the driver class file");
			Class.forName("com.mysql.cj.jdbc.Driver") ; 
		} 
		catch (ClassNotFoundException e) {
			System.out.println(e) ; 
		}
		
		try {
			// Make a database connection
			System.out.println("database connection");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/local_db");
			//this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/local_db", "tp_servlet_008", "ees7Lozu");
			
			// Create a statement object
			System.out.println("statement objects creation");
			this.statement = this.connection.createStatement() ; 

			String query = "CREATE DATABASE UsernameToIP(Username STRING, IP STRING) ;" ;
		
			// Execute the statement 
			System.out.println("executing the query");
			ResultSet rs = this.statement.executeQuery(query) ; 
			System.out.println("closing the result");
			rs.close(); 
		} 
		
		catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	// Add a user to the database
	public void addUser(String username, InetAddress IP) {
		String query = "INSERT INTO UsernameToIP (Username, IP) VALUES ('" + username + "', '" + IP + "') ;" ; 
		
		try {
			// Execute the statement 
			ResultSet rs = this.statement.executeQuery(query) ; 
			rs.close(); 
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	// Delete a user to the database
	public void deleteUserByName(String username) {
		String query = "DELETE FROM UsernameToIP WHERE Username='" + username + "' ;" ;	
		
		try {
			// Execute the statement 
			ResultSet rs = this.statement.executeQuery(query) ; 
			rs.close(); 
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void deleteUserByIP(InetAddress IP) {
		String query = "DELETE FROM UsernameToIP WHERE IP='" + IP + "' ;" ;		
		
		try {
			// Execute the statement 
			ResultSet rs = this.statement.executeQuery(query) ; 
			rs.close(); 
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	// Get information in the database
	public String getUsername(InetAddress IP) {
		String query = "SELECT UsernameToIP.Username FROM UsernameToIP WHERE IP = '" + IP + "' ;";	
		String username = "" ; 
		try {
			// Execute the statement 
			ResultSet rs = this.statement.executeQuery(query) ; 
			username = rs.getString(0) ;
			rs.close(); 
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return username ;	
	}
	
	public InetAddress getIP(String username) {
		String query = "SELECT UsernameToIP.IP FROM UsernameToIP WHERE Username = '" + username + "' ;";	
		String IPString = "" ; 
		InetAddress IP = null ; 
		try {
			// Execute the statement 
			ResultSet rs = this.statement.executeQuery(query) ; 
			IPString = rs.getString(0) ;
			IP = InetAddress.getByName(IPString) ; 
			rs.close(); 
		} catch (Exception e) {
			System.out.println(e);
		}
		return IP ; 
	}
	
	// Drop database 
	public void dropDatabase() {
		String query = "DROP TABLE UsernameToIP ;" ;
		
		try {
			// Execute the statement 
			ResultSet rs = this.statement.executeQuery(query) ; 
			rs.close(); 
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	// Close the connection of database 
	public void closeConnection() {
		try {
			this.connection.close() ;
		} catch (SQLException e) {
			System.out.println(e) ; 
		}
		
	}	
	

	public static void main(String[] args) {
		
	}
	
}
