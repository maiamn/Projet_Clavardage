package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet ; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class RemoteDB {
	
	// Attributes 
	Connection connection ; 
	Statement statement ;
	String addrDb = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_008?";
	String login = "tp_servlet_008" ;
	String password = "ees7Lozu" ;
	Conversation history ; 
	
	// Constructor 
	public RemoteDB() {
		
		// Load the driver class file 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver") ; 
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Error while loading the driver class file" + e) ; 
		}
		
		try {
			// Make a database connection
			this.connection = DriverManager.getConnection(this.addrDb, this.login, this.password);
						
			// Create a statement object
			this.statement = this.connection.createStatement() ; 
	
			// Execute the statement 
			String query = "CREATE TABLE IF NOT EXISTS History " +
	                   "(Sender VARCHAR(255) not NULL, " +
	                   " Receiver VARCHAR(255) not NULL, " +
	                   " Message VARCHAR(50000) not NULL, " +
	                   " Date VARCHAR(255) not NULL)"; 
			
			this.statement.executeUpdate(query) ;
		} 
		
		catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	// Add a message to the database
	public void addMessage(String sender, String receiver, String msg, String dateTime) {
		String updatedQuery = "INSERT INTO History (Sender, Receiver, Message, Date) values (?, ?, ?, ?);";
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(updatedQuery); 
			pstmt.setString(1, sender);
			pstmt.setString(2, receiver);
			pstmt.setString(3, msg);
			pstmt.setString(4, dateTime);
			pstmt.executeUpdate();
			pstmt.close();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}	
	}
	
	
	// Get messages history (50 last messages) from the database
	public Conversation getMessages(String person1, String person2) {
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
				// getArray not supported by mysql
				// Move sql.array content to ArrayList
				senders.add( rs.getString(1) );
				receivers.add(rs.getString(2));
				messages.add(rs.getString(3));
				dates.add(rs.getString(4));
			}
			rs.close(); 
			
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
	
	
	// Get messages history (50 last messages) from the database
		public Conversation getReceivedMessages(String person1, String person2) {
			// Init conversation
			Conversation result = new Conversation(null, null, null, null) ;
			
			// Query to order DB by descending date and select messages 
			String query = "SELECT * FROM History "
					+ "WHERE (Sender = '" + person2 + "' AND Receiver = '" + person1 + "') "
					+ "ORDER BY Date DESC LIMIT 50" ; 
			
			ArrayList<String> senders = new ArrayList<String>();
			ArrayList<String> receivers = new ArrayList<String>();
			ArrayList<String> messages = new ArrayList<String>();
			ArrayList<String> dates = new ArrayList<String>();
			
			try {
				// Execute the statement 
				ResultSet rs = this.statement.executeQuery(query) ;
				
				while(rs.next()) {
					// getArray not supported by mysql
					// Move sql.array content to ArrayList
					senders.add( rs.getString(1) );
					receivers.add(rs.getString(2));
					messages.add(rs.getString(3));
					dates.add(rs.getString(4));
				}
				rs.close(); 
				
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
		
	// Get messages history (50 last messages) from the database
	public String getLastDateMessage(String person1, String person2) { 
		// Init date
		String date = "" ; 
		
		// Query to order DB by descending date and select messages 
		String query = "SELECT * FROM History "
				+ "WHERE (Sender = '" + person1 + "' AND Receiver = '" + person2 + "') "
				+ "OR (Sender = '" + person2 + "' AND Receiver = '" + person1 + "') "
				+ "ORDER BY Date DESC LIMIT 50" ; 
		System.out.println("Remote DB" + query) ; 
		try {
			// Execute the statement 
			ResultSet rs = this.statement.executeQuery(query) ;
			
			if (rs.next()) {
				System.out.println("RemoteDB -> There is a result") ; 
				date = rs.getString(4) ; 
			}
			rs.close(); 
			
		} catch (Exception e) {
			System.out.println(e) ; 
		}
		
		// return resul 
		System.out.println("REMOTE DB -> " + date) ; 
		return date ;
	}
	
	
	// Function to get all the interlocutors of one person 
	public String[] getInterlocutors(String person) {
		
		// Init result
		String[] interlocutors ; 
		
		// Query to select all interlocutors (only one time with DISTINCT) 
		String query = "SELECT DISTINCT History.Sender FROM History WHERE Receiver='" + person + "'" +
				       " UNION " +
				       "SELECT DISTINCT History.Receiver FROM History WHERE Sender='" + person + "' ; " ;
		
		// Array List to store the result 
		ArrayList<String> interlocutorsArray = new ArrayList<String>();
		
		try {
			// Execute the statement 
			ResultSet rs = this.statement.executeQuery(query) ;
			while(rs.next()) {
				interlocutorsArray.add(rs.getString(1));
			}
			rs.close(); 
			
		} catch (Exception e) {
			System.out.println(e) ; 
		}
		
		// Define String[] instead of ArrayList 
		interlocutors = new String[interlocutorsArray.size()];
		
		for (int k=0; k<interlocutorsArray.size(); k++) {
			interlocutors[k]=interlocutorsArray.get(k) ; 
		}
		
		return interlocutors ; 
	}
		

	// Drop database 
	public void dropDatabase() {
		String query = "DROP TABLE History ;" ;
		
		try {
			// Execute the statement 
			this.statement.executeUpdate(query) ;
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
	}
	
		
	// Close the connection of database 
	public void closeConnection() {
		try {
			this.connection.close() ;
		} 
		catch (SQLException e) {
			System.out.println(e) ; 
		}
	}	
	
	

	public static void main(String[] args) {
		
	}
	
}
