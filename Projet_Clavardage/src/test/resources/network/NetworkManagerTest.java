import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class TestNetwokManager {

	//////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////// Definition of global variables needed to realize tests ///////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////

	
	// messageFormatter - global variables 
	protected int [] correctTypes = new int [4] ; 
	protected int [] uncorrectTypes = new int [2] ; 
    protected String test = new String();
    
    protected String testCorrect0 = new String() ;
    protected String testCorrect1 = new String() ; 
    protected String testCorrect2 = new String() ; 
    protected String testCorrect3 = new String() ; 
    
    protected String [] resultCorrect = new String [4] ; 
    protected String [] resultUncorrect = new String [2] ;
	
    protected String testUncorrect1 = new String() ; 
    protected String testUncorrect2 = new String() ;
	
	
	
    
	//////////////////////////////////////////////////////////////////////////////////////////////    
	////////////////////////// Before Class = realized before the class //////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////

	@BeforeClass 
	public static void initAll() throws IOException {
		
		// Init of variables useful for tests of messageFormatter
		correctTypes = [0; 1; 2; 3] ; 
		uncorrectTypes = [-1;  4] ;
		test = "Test" ; 
		
		testCorrect0 = "0|Test" ; 
		testCorrect1 = "1|Test" ; 
		testCorrect2 = "2|Test" ; 
		testCorrect3 = "3|Test" ; 
		
		for (int i=0; i<correctTypes.length; i++) {
			resultCorrect[i] = messageFormatter(correctTypes[i], test) ; 
		}
		
		for (int i=0; i<uncorrectTypes.length; i++) {
			resultUncorrect[i] = messageFormatter(uncorrectTypes[i], test) ; 
		}
		
	}
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////// List of tests to be realized ////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////

	
	/* Tests of function messageFormatter
	 * 1. if the type is correct (0,1,2,3) and the message correct -> check if the format of message is ok 
	 * 2. if the type is uncorrect -> raise an error 
	 */
	
	@Test
	public void testCorrectTypesMessage() {
		fail("Not yet implemented");
	}
	
	@Test 
	public void testUncorrectTypesMessage() {
		fail("Not yet implemented");
	}
	
	
	/* Tests of function usernameAvailable
	 * 1. if the bdd is empty -> username available 
	 * 2. if the bdd already contains the username -> username unavailable
	 * 3. if the bdd does not contain the username -> username available
	 */
	
	@Test
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
	}
	
	// notifyConnected
	
	// disconnection
	


}
