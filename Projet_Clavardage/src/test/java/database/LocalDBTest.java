package database;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import java.io.IOException;
import java.sql.DriverManager;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet ; 
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random ; 


public class LocalDBTest {
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////// Definition of global variables needed to realize tests ///////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	/* **************************************************************************************** */
	// GLOBAL VARIABLES 	
	
	// Database
	protected static LocalDB db = new LocalDB() ; 
	protected static Connection connection ; 
	protected static Statement statement ;
	protected static String addrDb ;
	protected static String login ;
	protected static String password ;
	
	// Person 
	protected static int nbUsers = 100 ; 
	protected static ArrayList<String> usernames = new ArrayList<String>() ;
	protected static ArrayList<String> connected = new ArrayList<String>() ; 
	protected static ArrayList<String> getUsernames = new ArrayList<String>() ; 
	protected static ArrayList<String> getConnectedUsernames = new ArrayList<String>() ; 
	protected static ArrayList<InetAddress> IPAddresses = new ArrayList<InetAddress>() ; 	
	// Last access 
	protected static String access = "17.06.2021 09:34:15" ;
	// Random number 
	Random rand = new Random() ;
	protected static int nbTests = 10 ;
	protected static ArrayList<Integer> indexes = new ArrayList<Integer>() ; 
	/* **************************************************************************************** */
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////// Before Class = realized before the class //////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	@BeforeClass 
	public static void initAll() throws IOException {
		// Connection to database
		addrDb = "jdbc:mysql://localhost:3306/localdatabase?";
		login = "root" ;
		password = "root" ;
 
		try {
			db.connection = DriverManager.getConnection("jdbc:sqlite:localDB.db");
		} catch (SQLException e)
        {
            fail(e.toString());
        }
		
		// Users and IPAdresses 
		for (int i=0; i<nbUsers ; i++) { 
			// Usernames
			usernames.add("username"+i) ;
			//IPAddresses
			byte[] ipAddr = new byte[]{(byte)192, (byte)168, (byte)0, (byte)i};
			IPAddresses.add(InetAddress.getByAddress(ipAddr)) ;
		}	
	}
	
	
	
	
	@Before 
	public void initBeforeTests() throws IOException {
	/* **************************************************************************************** */
	//////////////////////////////////// Add users to database ///////////////////////////////////
		for (int i=0; i<nbUsers; i++) {
			db.addUser(usernames.get(i), IPAddresses.get(i));
		}	
	/* **************************************************************************************** */
	}
	
	
	@After
	public void resetAfterTests() throws IOException {
		////////////////////////////////////// Drop database /////////////////////////////////////
		try {
			db.statement.executeUpdate("DELETE FROM UsernameToIP") ; 
		} catch (SQLException e) {
				System.out.println(e);
		}
	}


	//////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// TESTS ////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	

	
	////////////////////////////////////// FUNCTION addUser /////////////////////////////////////	
	@Test
	public void testAddUser() {
		try {			
			for (int i=0; i<nbUsers; i++) {	
				ResultSet rs=db.statement.executeQuery("SELECT * FROM UsernameToIP WHERE Username='" + usernames.get(i) + "'") ; 
				assertTrue(rs.next());
				// Check usernames
				assertEquals(usernames.get(i), rs.getString("username")) ; 
				// Check IPs
				String IPString = IPAddresses.get(i).toString();
				if (IPString.charAt(0) == ('/')) {
					IPString = IPString.substring(1);
				}
				assertEquals(IPString, rs.getString("ip")) ; 
				// Check connected
				assertTrue(rs.getBoolean("isConnected")) ; 
				// Check last access empty 
				assertEquals("", rs.getString("lastAccess")) ; 
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	} 


	/////////////////////////////////FUNCTION userDisconnected /////////////////////////////////
	@Test
	public void testUserDisconnected() {
		try {
			for(int i=0; i<nbUsers; i++) {
				// Disconnect the user
				db.userDisconnected(usernames.get(i));
				// Get the line corresponding to the user
				ResultSet rs = db.statement.executeQuery("SELECT * FROM UsernameToIP WHERE Username='" + usernames.get(i) + "' ; ") ; 
				if (rs.next()) {
					// Check connected
					assertFalse(rs.getBoolean("isConnected")) ; 
				}
			}
		} catch (SQLException e) {
			System.out.println(e) ; 
		}
	}
	
	
	///////////////////////////////// FUNCTION updateUsername //////////////////////////////////
	@Test
	public void testUpdateUsername() {
		try {
			for (int i=0; i<nbUsers; i++) {
				// IP address of the user 
				byte[] ipAddr = new byte[]{(byte)192, (byte)168, (byte)0, (byte)i} ;
				InetAddress IPAddress = InetAddress.getByAddress(ipAddr) ;
				// Update the username 
				db.updateUsername(IPAddress, ("user"+i));
				// Get the line corresponding to the user 
				ResultSet rs = db.statement.executeQuery("SELECT * FROM UsernameToIP WHERE Username='" + usernames.get(i) + "' ;") ; 
				if (rs.next()) {
					// Check username
					assertEquals("user"+i, rs.getString("Username")) ; 
				}
			}
		} catch (Exception e) {
			System.out.println(e) ; 
		}
	}

	
	//////////////////////////////// FUNCTION updateLastAccess /////////////////////////////////
	@Test
	public void testUpdateLastAccess() {
		try {
			for (int i=0; i<nbUsers; i++) {
				// IP address of the user 
				byte[] ipAddr = new byte[]{(byte)192, (byte)168, (byte)0, (byte)i} ;
				InetAddress IPAddress = InetAddress.getByAddress(ipAddr) ;
				// Update the last access 
				db.updateLastAccess(IPAddress, access);
				// Get the line corresponding to the user 
				ResultSet rs = db.statement.executeQuery("SELECT * FROM UsernameToIP WHERE Username='" + usernames.get(i) + "' ;") ; 
				if (rs.next()) {
					// Check last access
					assertEquals(access, rs.getString("lastAccess")) ; 
				}				
			}
		} catch (Exception e) {
			System.out.println(e) ; 
		}
	}
	
	
	/////////////////////////////////// FUNCTION getUsername ///////////////////////////////////	
	@Test
	public void testGetUsername() {
		for (int i=0; i<nbUsers; i++) {
			String user = db.getUsername(IPAddresses.get(i)) ; 
			assertEquals(usernames.get(i), user) ; 
		}
	} 
	
	
	////////////////////////////////////// FUNCTION getIP //////////////////////////////////////	
	@Test
	public void testGetIP() {
		for (int i=0; i<nbUsers; i++) {
			InetAddress IP = db.getIP(usernames.get(i)) ;
			assertEquals(IPAddresses.get(i), IP) ; 
		}
	} 

	
	////////////////////////////////// FUNCTION getLastAccess //////////////////////////////////	
	@Test
	public void testGetLastAccess() {
		try {
			for (int i=0; i<nbUsers; i++) {
				// IP address of the user 
				byte[] ipAddr = new byte[]{(byte)192, (byte)168, (byte)0, (byte)i} ;
				InetAddress IPAddress = InetAddress.getByAddress(ipAddr) ;
				// Update the last access 
				db.updateLastAccess(IPAddress, access);
				// Get the result
				String res = db.getLastAccess(IPAddress) ;
				assertEquals(access, res) ; 			
			}
		} catch (Exception e) {
			System.out.println(e) ; 
		}
	}

	
	///////////////////////////////// FUNCTION getAllUsernames /////////////////////////////////	
	@Test
	public void testGetAllUsernames() {
		getUsernames = db.getAllUsernames() ; 
		assertEquals(usernames, getUsernames) ; 
	} 
	
	
	//////////////////////////////// FUNCTION getConnectedUSers ////////////////////////////////	
	@Test
	public void testGetConnectedUsers() {
		// Disconnect 50 last users 
		for(int i=nbUsers/2; i<nbUsers; i++) {
			db.userDisconnected(usernames.get(i));
		}
		
		// Define connected users 
		for (int i=0 ; i<nbUsers/2 ; i++) {
			connected.add(usernames.get(i)) ; 
		}
		
		getConnectedUsernames = db.getConnectedUsernames() ; 
		assertEquals(connected, getConnectedUsernames) ; 
	} 
	
	
	/////////////////////////////// FUNCTION deleteUserByName /////////////////////////////////	
	@Test
	public void testDeleteUserByName() {
		try {
			// Define an array with 10 different random numbers 
			int nb=0 ; 
			while (nb<10) {
				int j = rand.nextInt(nbUsers) ;
				if (!indexes.contains(j)) { 
					indexes.add(j) ; 
					nb++ ; 
				}
			}
			// Delete 10 random users by name
			for (int i=0; i<nbTests; i++) {	
				// Delete the user of random index
				db.deleteUserByName(usernames.get(indexes.get(i))) ; 
				
				// Check if one and only one user has been deleted 
				ResultSet rs=db.statement.executeQuery("SELECT COUNT(*) FROM UsernameToIP") ; 
				if (rs.next()) {
					assertEquals((nbUsers-i-1), rs.getInt(1)) ;
				}
				// Check that the good user has been deleted from the database 
				ResultSet rs2=db.statement.executeQuery("SELECT * FROM UsernameToIP WHERE Username='" + usernames.get(indexes.get(i)) + "'") ;
				assertFalse(rs2.next());
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	} 

	
	///////////////////////////////// FUNCTION deleteUserByIP //////////////////////////////////	
	@Test
	public void testDeleteUserByIP() {
		try {
			// Define an array with 10 different random numbers 
			int nb=0 ; 
			while (nb<10) {
				int j = rand.nextInt(nbUsers) ;
				if (!indexes.contains(j)) { 
					indexes.add(j) ;
					nb++ ; 
				}
			}
			// Delete 10 random users by IP
			for (int i=0; i<nbTests; i++) {	
				// Delete the user of random index
				db.deleteUserByIP(IPAddresses.get(indexes.get(i))) ; 
				
				// Check if one and only one user has been deleted 
				ResultSet rs=db.statement.executeQuery("SELECT COUNT(*) FROM UsernameToIP") ; 
				if (rs.next()) {
					assertEquals((nbUsers-i-1), rs.getInt(1)) ;
				}
				// Check that the good user has been deleted from the database 
				ResultSet rs2=db.statement.executeQuery("SELECT * FROM UsernameToIP WHERE ip='" + IPAddresses.get(indexes.get(i)) + "'") ;
				assertFalse(rs2.next());
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	} 


	
	/////////////////////////////////// FUNCTION dropDatabase //////////////////////////////////	
	@Test
	public void testDropDatabase() {
		db.dropDatabase();
		try {
			db.statement.executeUpdate("SELECT * FROM UsernameToIP") ;
		} catch (SQLException e) {
			assertTrue(true) ;
		}	
		db = new LocalDB() ; 
	} 
	

}
