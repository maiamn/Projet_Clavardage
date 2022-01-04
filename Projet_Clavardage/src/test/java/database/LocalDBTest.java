package database;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import java.io.IOException;
import java.sql.DriverManager;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet ; 
import org.junit.Test;
import database.LocalDB.* ;
import network.NetworkManager.MessageType;

public class LocalDBTest {
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////// Definition of global variables needed to realize tests ///////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	/* **************************************************************************************** */
	// GLOBAL VARIABLES 	
	
	// Database
	protected static LocalDB db = new LocalDB() ; 
	protected static Connection connection ; 
	protected static String addrDb ;
	protected static String login ;
	protected static String password ;
	
	// Person 
	protected static String [] usernames = new String [10] ; 
	protected static String [] IPAdresses = new String [10] ; 
	/* **************************************************************************************** */

	
	/* **************************************************************************************** */
	////////////////////////////////// addUSer - global variables ////////////////////////////////
	// Variables needed for the function 
	
	// Expected variables
	
	// Result variables

	/* **************************************************************************************** */

	
	/* **************************************************************************************** */
	///////////////////////////// deleteUserByName - global variables ////////////////////////////
	// Variables needed for the function 
	
	// Expected variables 
	
	// Result variables 
	
	/* **************************************************************************************** */

	
	/* **************************************************************************************** */
	////////////////////////////// deleteUserByIP - global variables /////////////////////////////
	// Variables needed for the function 
	
	// Expected variables 
	
	// Result variables 
	
	/* **************************************************************************************** */
	
	
	/* **************************************************************************************** */
	//////////////////////////////// getUsername - global variables //////////////////////////////
	// Variables needed for the function 
	
	// Expected variables 
	
	// Result variables 
	
	/* **************************************************************************************** */
	
	
	/* **************************************************************************************** */
	/////////////////////////////////// getIP - global variables /////////////////////////////////
	// Variables needed for the function 
	
	// Expected variables 
	
	// Result variables 
	
	/* **************************************************************************************** */
	
	
	/* **************************************************************************************** */
	/////////////////////////////// dropDatabase - global variables //////////////////////////////
	// Variables needed for the function 
	
	// Expected variables 
	
	// Result variables 
	
	/* **************************************************************************************** */
	
	
	/* **************************************************************************************** */
	////////////////////////////// closeConnection - global variables ////////////////////////////
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
		for (int i=0; i<usernames.length ; i++) {
			usernames[i]="username"+i ;
			IPAdresses[i]="192.168.1."+i ; 
			
		}
		
		

	
	/* **************************************************************************************** */
	//////////////////////// Init of variables useful for tests of assUser ///////////////////////

		
	/* **************************************************************************************** */
	

	}


	//////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// TESTS ////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////////// FUNCTION constructor ////////////////////////////////////
	@Test
	public void testLocalDB() {
		fail("Not yet implemented");
	}

	
	////////////////////////////////////// FUNCTION addUser /////////////////////////////////////	
	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

	
	//////////////////////////////// FUNCTION deleteUserByName //////////////////////////////////	
	@Test
	public void testDeleteUserByName() {
		fail("Not yet implemented");
	}

	
	///////////////////////////////// FUNCTION deleteUserByIP //////////////////////////////////	
	@Test
	public void testDeleteUserByIP() {
		fail("Not yet implemented");
	}

	
	/////////////////////////////////// FUNCTION getUsername ///////////////////////////////////	
	@Test
	public void testGetUsername() {
		fail("Not yet implemented");
	}

	
	////////////////////////////////////// FUNCTION getIP //////////////////////////////////////	
	@Test
	public void testGetIP() {
		fail("Not yet implemented");
	}

	
	/////////////////////////////////// FUNCTION dropDatabase //////////////////////////////////	
	@Test
	public void testDropDatabase() {
		fail("Not yet implemented");
	}

	
	///////////////////////////////// FUNCTION closeConnection /////////////////////////////////	
	@Test
	public void testCloseConnection() {
		fail("Not yet implemented");
	}


}
