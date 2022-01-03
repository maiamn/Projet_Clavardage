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
	
	
	////////// messageFormatter - global variables //////////
	// Variables needed for the function 
	protected static MessageType [] correctTypes = new MessageType [4] ; 
	protected static String test = new String();
	
	// Expected variables
	protected static String [] testCorrect = new String [4] ;
	
	// Result variables
	protected static String [] resultCorrect = new String [4] ; 
	protected static String resultUncorrect = new String() ; 

	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////// Before Class = realized before the class //////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	@BeforeClass 
	public static void initAll() throws IOException {
	
	// Init of variables useful for tests of messageFormatter
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
				resultCorrect[i] = NetworkManager.messageFormatter(correctTypes[i], test) ; 
		}
		
		resultUncorrect = NetworkManager.messageFormatter(null, test) ;
	
	}
	
	@Test
	public void testCorrectTypesMessage() {
		assertArrayEquals(testCorrect, resultCorrect) ; 
	}
	


}
