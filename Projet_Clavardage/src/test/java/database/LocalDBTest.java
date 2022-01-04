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
import database.LocalDB.* ;
import java.net.InetAddress;
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
	protected static int nbTests = 100 ; 
	protected static String [] usernames = new String [nbTests] ; 
	protected static InetAddress [] IPAddresses = new InetAddress [nbTests] ; 
	
	// Random number 
	Random rand = new Random() ; 
	protected static int index ; 
	/* **************************************************************************************** */

	
	/* **************************************************************************************** */
	////////////////////////////////// addUSer - global variables ////////////////////////////////
	// Variables needed for the function 
	
	// Expected variables
	
	// Result variables

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
			db.connection = DriverManager.getConnection(db.addrDb, db.login, db.password);
		} catch (SQLException e)
        {
            fail(e.toString());
        }
		
		// Users and IPAdresses 
		for (int i=0; i<nbTests ; i++) { 
			usernames[i]="username"+i ;
			IPAddresses[i]= InetAddress.getLocalHost() ; 
		}
	
	}
	
	
	
	@Before 
	public void initBeforeTests() throws IOException {
	/* **************************************************************************************** */
	//////////////////////////////////// Add users to database ///////////////////////////////////
		for (int i=0; i<nbTests; i++) {
			db.addUser(usernames[i], IPAddresses[i]);
		}	
	/* **************************************************************************************** */
	}
	
	@After
	public void resetAfterTests() throws IOException {
		////////////////////////////////////// Drop database /////////////////////////////////////
		try {
			db.statement.executeUpdate("TRUNCATE TABLE UsernameToIP") ; 
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
			for (int i=0; i<nbTests; i++) {	
				ResultSet rs=db.statement.executeQuery("SELECT * FROM UsernameToIP WHERE Username='" + usernames[i] + "'") ; 
				assertTrue(rs.next());
				// Check usernames
				assertEquals(usernames[i], rs.getString("username")) ; 
				// Check IPs
				assertEquals(IPAddresses[i].toString(), rs.getString("ip")) ; 
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	} 

	
	//////////////////////////////// FUNCTION deleteUserByName //////////////////////////////////	
	@Test
	public void testDeleteUserByName() {
		try {
			// Delete 10 random users by name
			for (int i=0; i<10; i++) {	
				// Delete the user of random index
				index = rand.nextInt(nbTests);
				db.deleteUserByName(usernames[index]) ; 
				
				// Check if one and only one user has been deleted 
				ResultSet rs=db.statement.executeQuery("SELECT COUNT(*) FROM UsernameToIP") ; 
				if (rs.next()) {
					assertEquals((nbTests-i-1), rs.getInt(1)) ;
				}
				// Check that the good user has been deleted from the database 
				ResultSet rs2=db.statement.executeQuery("SELECT * FROM UsernameToIP WHERE Username='" + usernames[index] + "'") ;
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
			// All users have same ip -> by deleting by ip the table will be empty
			db.deleteUserByIP(IPAddresses[index]) ; 
			ResultSet rs=db.statement.executeQuery("SELECT COUNT(*) FROM UsernameToIP") ; 
			if (rs.next()) {
				assertEquals(0, rs.getInt(1)) ;
			}
			
			// Check that the good user has been deleted from the database 
			ResultSet rs2=db.statement.executeQuery("SELECT * FROM UsernameToIP WHERE IP='" + IPAddresses[index] + "'") ;
			assertFalse(rs2.next());
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	} 

	
	/////////////////////////////////// FUNCTION getUsername ///////////////////////////////////	
	/* @Test
	public void testGetUsername() {
		fail("Not yet implemented");
	} */

	
	////////////////////////////////////// FUNCTION getIP //////////////////////////////////////	
	/* @Test
	public void testGetIP() {
		fail("Not yet implemented");
	} */

	
	/////////////////////////////////// FUNCTION dropDatabase //////////////////////////////////	
	/* @Test
	public void testDropDatabase() {
		fail("Not yet implemented");
	} */

	
	///////////////////////////////// FUNCTION closeConnection /////////////////////////////////	
	/* @Test
	public void testCloseConnection() {
		fail("Not yet implemented");
	} */ 


}
