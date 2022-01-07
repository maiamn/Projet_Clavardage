package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet ; 
import java.sql.DriverManager;
import java.util.ArrayList;

public class RemoteDB {
	
	// Attributes 
	Connection connection ; 
	Statement statement ;
	String addrDb = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_008?";
	String login = "tp_servlet_008" ;
	String password = "ees7Lozu" ;
	
	// <!> //
	Conversation history ; 
	
	// Constructor 
	public RemoteDB() {
		System.out.println("[RemoteDB] Calling RemoteDB constructor");
		
		// Load the driver class file 
		try {
			System.out.println("[RemoteDB] Loading the driver class file");
			Class.forName("com.mysql.cj.jdbc.Driver") ; 
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Error while loading the driver class file" + e) ; 
		}
		
		try {
			// Make a database connection
			System.out.println("[RemoteDB] Database connection...");
			this.connection = DriverManager.getConnection(this.addrDb, this.login, this.password);
			System.out.println("[RemoteDB] Database connected");
			
			// Create a statement object
			System.out.println("[RemoteDB] Statement objects creation...");
			this.statement = this.connection.createStatement() ; 

			System.out.println("[RemoteDB] Statement objects created");

	
			// Execute the statement 
			String query = "CREATE TABLE IF NOT EXISTS History " +
	                   "(Sender VARCHAR(255) not NULL, " +
	                   " Receiver VARCHAR(255) not NULL, " +
	                   " Message VARCHAR(255) not NULL, " +
	                   " Date VARCHAR(255) not NULL)"; 
			
			System.out.println("[RemoteDB] Creating the table...");
			this.statement.executeUpdate(query) ;
			System.out.println("[RemoteDB] Table created");
		} 
		
		catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	// Add a message to the database
	public void addMessage(String sender, String receiver, String msg, String dateTime) {
		System.out.println("[RemoteDB] Calling addMessage...");
		String query = "INSERT INTO History (Sender, Receiver, Message, Date) VALUES ('" + sender + "', '" + receiver + "', '" + msg + "', '" + dateTime + "') ;" ; 
		
		try {
			// Execute the statement 
			this.statement.executeUpdate(query) ; 
			System.out.println("[RemoteDB] Message added");
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	
	// Get messages history (50 last messages) from the database
	public Conversation getMessages(String person1, String person2) {
		System.out.println("[RemoteDB} Calling getMessage...") ; 
		// Init conversation
		Conversation result = new Conversation(null, null, null, null) ;
		
		// Query to order DB by descending date and select messages 
		String query = "SELECT * FROM History "
				+ "WHERE (Sender = '" + person1 + "' AND Receiver = '" + person2 + "') "
				+ "OR (Sender = '" + person2 + "' AND Receiver = '" + person1 + "') "
				+ "ORDER BY Date DESC LIMIT 50" ; 
		
		ArrayList<String> senders = new ArrayList<String>();
		ArrayList<String> receivers = new ArrayList<String>();
		ArrayList<String> messages = new ArrayList<String>();
		ArrayList<String> dates = new ArrayList<String>();
		
		try {
			// Execute the statement 
			ResultSet rs = this.statement.executeQuery(query) ;
			
			while(rs.next()) {
				//getArray not supported by mysql
				// Move sql.array content to ArrayList
				senders.add( rs.getString(1) );
				receivers.add(rs.getString(2));
				messages.add(rs.getString(3));
				dates.add(rs.getString(4));
			}
			rs.close(); 
			System.out.println("[RemoteDB] Message fetched");
			
		} catch (Exception e) {
			System.out.println(e) ; 
		}
		
		// Define String[] instead of ArrayList 
		String[] sendersArray = new String[senders.size()];
		String[] receiversArray = new String[receivers.size()] ; 
		String[] datesArray = new String[dates.size()] ; 
		String[] messagesArray = new String[messages.size()] ;
		
		for (int k=0; k<senders.size(); k++) {
			sendersArray[k]=senders.get(k) ; 
			receiversArray[k] = receivers.get(k) ; 
			datesArray[k] = dates.get(k) ; 
			messagesArray[k] = messages.get(k) ; 
		}
		
		// Define the result
		result.setSenders(sendersArray);
		result.setReceivers(receiversArray);
		result.setDates(datesArray);
		result.setMessages(messagesArray);
		
		// Return result
		return result ;
	}
		

	// Drop database 
	public void dropDatabase() {
		System.out.println("[RemoteDB] Dropping the database...");
		String query = "DROP TABLE History ;" ;
		
		try {
			// Execute the statement 
			this.statement.executeUpdate(query) ;
			System.out.println("[RemoteDB] Database dropped");
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
	}
	
		
	// Close the connection of database 
	public void closeConnection() {
		System.out.println("[RemoteDB] Closing connection...");
		try {
			this.connection.close() ;
			System.out.println("[RemoteDB] Connection closed");
		} 
		catch (SQLException e) {
			System.out.println(e) ; 
		}
	}	
	
	

	public static void main(String[] args) {
		
	}
	
}
