package network;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;
import java.io.IOException;
import network.NetworkManager.* ;


public class NetworkManagerTest {
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////// Definition of global variables needed to realize tests ///////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	/* **************************************************************************************** */
	// GLOBAL VARIABLES 
	// <!> We need a static instance of network manager to access to the non-static method
	protected static NetworkManager net = new NetworkManager() ; 
	/* **************************************************************************************** */

	
	/* **************************************************************************************** */
	///////////////////////////// messageFormatter - global variables ////////////////////////////
	// Variables needed for the function 
	protected static MessageType [] correctTypes = new MessageType [4] ; 
	protected static String test = new String();
	
	// Expected variables
	protected static String [] testCorrect = new String [4] ;
	
	// Result variables
	protected static String [] resultCorrect = new String [4] ; 
	protected static String resultUncorrect = new String() ; 
	/* **************************************************************************************** */

	
	/* **************************************************************************************** */
	//////////////////////////// usernameAvailable - global variables ////////////////////////////
	// Variables needed for the function 
	
	// Expected variables 
	
	// Result variables 
	
	/* **************************************************************************************** */

	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////// Before Class = realized before the class //////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	@BeforeClass 
	public static void initAll() throws IOException {
	
	/* **************************************************************************************** */
	/////////////////// Init of variables useful for tests of messageFormatter ///////////////////
		MessageType [] correctTypes = {MessageType.USERNAME_BRDCST, 
									   MessageType.USERNAME_CONNECTED, 
									   MessageType.USERNAME_DISCONNECT, 
									   MessageType.MESSAGE} ; 

		test = "Test" ; 
	
		testCorrect[0] = "USERNAME_BRDCST|Test" ; 
		testCorrect[1] = "USERNAME_CONNECTED|Test" ; 
		testCorrect[2] = "USERNAME_DISCONNECT|Test" ; 
		testCorrect[3] = "MESSAGE|Test" ; 
	
		for (int i=0; i<correctTypes.length; i++) {
				resultCorrect[i] = net.messageFormatter(correctTypes[i], test) ; 
		}
		
		resultUncorrect = net.messageFormatter(null, test) ;
	/* **************************************************************************************** */
	
		
		
	/* **************************************************************************************** */
	/////////////////// Init of variables useful for tests of usernameAvailable //////////////////
		
		
	/* **************************************************************************************** */


	}
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// TESTS ////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	///////////////////////////////// FUNCTION messageFormatter /////////////////////////////////
	@Test
	public void testCorrectTypesMessage() {
		assertArrayEquals(testCorrect, resultCorrect) ; 
	}
		
	
	//////////////////////////////// FUNCTION usernameAvailable /////////////////////////////////	
	/* Tests of function usernameAvailable
	 * 1. if the bdd is empty -> username available 
	 * 2. if the bdd already contains the username -> username unavailable
	 * 3. if the bdd does not contain the username -> username available
	 */
	
	/*@Test
	public void testUsernameAvailableEmptyBDD() {
		fail("Not yet implemented") ;
	}
	
	@Test
	public void testUsernameUnavailable() {
		fail("Not yet implemented") ;
	}
	
	@Test
	public void testUsernameAvailable() {
		fail("Not yet implemented") ;
	} */ 
	
	
	///////////////////////////////// FUNCTION notifyConnected //////////////////////////////////	
	
		
	////////////////////////////////// FUNCTION disconnection ///////////////////////////////////	
		
	
}
