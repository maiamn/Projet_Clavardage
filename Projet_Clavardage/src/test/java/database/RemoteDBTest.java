package database;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RemoteDBTest {
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////// Definition of global variables needed to realize tests ///////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	/* **************************************************************************************** */
	// GLOBAL VARIABLES 	
	
	// Database
	protected static RemoteDB db = new RemoteDB() ; 
	protected static Connection connection ; 
	protected static Statement statement ;
	protected static String addrDb ;
	protected static String login ;
	protected static String password ;
	
	// Users 
	protected static int nbUsers = 30 ;
	protected static ArrayList<String> usernames = new ArrayList<String>() ; 
	//Messages
	protected static int nbMsgs = 20 ;
	protected static ArrayList<String> messages = new ArrayList<String>() ; 
	// Dates
	protected static String date = "04-01-2022 14:36:10" ; 
	Random rand = new Random() ; 
	
	// Result getMessage
	protected static Conversation resultGet = new Conversation(null, null, null, null) ; 
	
	/* **************************************************************************************** */
	
	
	// Function to construct an understandable message 
	protected String formattingMessage(String user1, String user2) {
		return "message from " + user1 + " to " + user2 ; 
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////// Before Class = realized before the class //////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	@BeforeClass 
	public static void initAll() throws IOException {
		
		// Connection to database
		addrDb = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_008?";
		login = "tp_servlet_008" ;
		password = "ees7Lozu" ;
 
		try {
			db.connection = DriverManager.getConnection(db.addrDb, db.login, db.password);
		} catch (SQLException e)
        {
            fail(e.toString());
        }
		
		// Users 
		for (int i=0; i<nbUsers ; i++) {
			usernames.add("username"+i) ; 
		}
	}
	
	
	
	
	@Before 
	public void initBeforeTests() throws IOException {
	/* **************************************************************************************** */
	/////////////////////////////////// Add messages to database /////////////////////////////////
		int count = 0 ; 
		while(count<(nbMsgs/2)) {
			int i=count ; 
			int j=i+1 ; 
			String message = formattingMessage(usernames.get(i), usernames.get(j)) ;
			db.addMessage(usernames.get(i), usernames.get(j), message, date);
			db.addMessage(usernames.get(j), usernames.get(i), message, date);
			count++ ; 
		}
	/* **************************************************************************************** */
	}
	
	
	@After
	public void resetAfterTests() throws IOException {
	//////////////////////////////////////// Drop database ///////////////////////////////////////
		try {
			db.statement.executeUpdate("TRUNCATE TABLE History") ; 
		} catch (SQLException e) {
				System.out.println(e);
		}
	}


	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// TESTS ////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	

	
	///////////////////////////////////// FUNCTION addMessage ////////////////////////////////////	
	@Test
	public void testAddMessage() {
		try {			
			for (int i=0; i<(nbMsgs/2); i++) {	
				ResultSet rs=db.statement.executeQuery("SELECT * FROM History WHERE "
														+ "Sender='" + usernames.get(i) +
														"' AND Receiver='" + usernames.get(i+1) + "'") ; 
				assertTrue(rs.next());
				// Check message
				assertEquals(formattingMessage(usernames.get(i), usernames.get(i+1)), rs.getString("Message")) ; 
				// Check date
				assertEquals(date, rs.getString("Date")) ; 
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}  

	
	///////////////////////////////////// FUNCTION getMessage ////////////////////////////////////	
	@Test
	public void testGetMessage() {
		
		int count = 0 ; 
		while(count<(nbMsgs/2)) {
			int i=count ; 
			int j=i+1 ; 
			
			// Get the messages between user i and user j 
			resultGet = db.getMessages(usernames.get(i), usernames.get(j)) ;
			
			// Check if the number of result is correct in all arraylist of result 
			assertEquals(2, resultGet.getSenders().length) ;  
			assertEquals(2, resultGet.getReceivers().length) ; 
			assertEquals(2, resultGet.getDates().length) ; 
			assertEquals(2, resultGet.getMessages().length) ;
			
			// First message -> user i to user j 
			if (resultGet.getSenders()[0]==usernames.get(i)) {
				
				// Check the sender
				assertEquals(usernames.get(i), resultGet.getSenders()[0]) ; 
				assertEquals(usernames.get(j), resultGet.getSenders()[1]) ; 
				
				// Check the receiver 
				assertEquals(usernames.get(j), resultGet.getReceivers()[0]) ; 
				assertEquals(usernames.get(i), resultGet.getReceivers()[1]) ; 
				
				// Check the message
				String message0 = formattingMessage(usernames.get(i), usernames.get(j)) ;
				String message1 = formattingMessage(usernames.get(j), usernames.get(i)) ; 
				
				assertEquals(message0, resultGet.getMessages()[0]) ; 
				assertEquals(message1, resultGet.getMessages()[1]) ; 
				
				// Check the date 
				assertEquals(date, resultGet.getDates()[0]) ; 
				assertEquals(date, resultGet.getDates()[1]) ; 
			} 
			
			// Second message -> user j to user i 
			else if (resultGet.getSenders()[0]==usernames.get(j)) {
				// Check the sender
				assertEquals(usernames.get(j), resultGet.getSenders()[0]) ; 
				assertEquals(usernames.get(i), resultGet.getSenders()[1]) ; 
				
				// Check the receiver 
				assertEquals(usernames.get(i), resultGet.getReceivers()[0]) ; 
				assertEquals(usernames.get(j), resultGet.getReceivers()[1]) ; 
				
				// Check the message
				String message0 = formattingMessage(usernames.get(j), usernames.get(i)) ;
				String message1 = formattingMessage(usernames.get(i), usernames.get(j)) ; 
				
				assertEquals(message0, resultGet.getMessages()[0]) ; 
				assertEquals(message1, resultGet.getMessages()[1]) ; 
				
				// Check the date 
				assertEquals(date, resultGet.getDates()[0]) ; 
				assertEquals(date, resultGet.getDates()[1]) ; 
			} 	
			
			count++ ; 
		}
		
	}
	

	//////////////////////////////////// FUNCTION dropDatabase ///////////////////////////////////	
	@Test
	public void testDropDatabase() {
		db.dropDatabase();
		try {
			db.statement.executeUpdate("SELECT * FROM UsernameToIP") ;
		} catch (SQLException e) {
			assertTrue(true) ;
		}	
		db = new RemoteDB() ; 
	} 

}
