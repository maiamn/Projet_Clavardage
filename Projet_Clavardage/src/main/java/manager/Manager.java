package manager;

import java.net.InetAddress;
import database.* ;
import network.* ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
	
	protected static String username = null;
	private static NetworkManager networkManager = new NetworkManager();
	private static LocalDB localDB = new LocalDB() ;
	private static RemoteDB remoteDB = new RemoteDB();
	
	//Maximum length of usernames
	private static int maxLength = 30 ;
	
	
	//Function that checks if a string contains special characters
	public static boolean containsSpecialCharacter(String s) {
	     Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(s);
	     return m.find();
	}
	
	
	//////////////////////////////////////////////////////////////////////
	/////////////////////////// VALID USERNAME ///////////////////////////
	//////////////////////////////////////////////////////////////////////
	public static boolean validLengthUsername(String username) throws IncorrectUsernameException {
		boolean res = true ; 
		res = res && (username.length() < maxLength) ; 
		res = res && (username.length() > 1) ; 
		if (res) {
			return res ; 
		} 
		else {
			throw new IncorrectUsernameException("Invalid length of username. \n The length of the username must be between 1 and 30 characters. \n") ;
		}
	}
	
	public static boolean validCharUsername(String username) throws IncorrectUsernameException {
		boolean res = true ; 
		res = res && containsSpecialCharacter(username) ;
		if (res) {
			return res ;
		} 
		else {
			throw new IncorrectUsernameException("Username cannot contain special characters.") ; 
		}
	}
	
	public static boolean validUsername(String username) {
		boolean res = true ; 
		try {
			res = res && validLengthUsername(username) ; 
			res = res && validCharUsername(username) ; 
			res = res && networkManager.usernameAvailable(username) ; 
		} 
		catch (IncorrectUsernameException e) {
			System.out.println(e) ; 
		}
		return res ; 
	}
	
	public static String getUsername() {
		return username;
	}
	
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////CONNECTION//////////////////////////////
	//////////////////////////////////////////////////////////////////////
	public static void connection(String username) {
		if (validUsername(username)) {
			//once the username has been accepted, bc username
			networkManager.notifyConnected(username);
		}
	}
	
	
	//////////////////////////////////////////////////////////////////////
	////////////////////////////DISCONNECTION/////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public static void disconnection() {
		localDB.dropDatabase();
		localDB.closeConnection();
		remoteDB.dropDatabase();
		remoteDB.closeConnection();
		networkManager.disconnection();
	}
	
	
	public static void usernameRequest(String pseudo, InetAddress IP) {
		if (pseudo == Manager.username) {
			networkManager.sendUnavailableUsername(IP);
		}
	}
	
	
	//////////////////////////////////////////////////////////////////////
	////////////////////////////LOCAL DATABASE////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public static void newUserConnected(String username, InetAddress IP) {
		localDB.addUser(username, IP);
	}
	
	public static void userDisconnected(String username) {
		localDB.deleteUserByName(username);
	}
	
	public static void userDisconnected(InetAddress IP) {
		localDB.deleteUserByIP(IP);
	}
	
	public static String getUsername(InetAddress IP) {
		return localDB.getUsername(IP);
	}
	
	public static InetAddress getIP(String name) {
		return localDB.getIP(name);
	}
	
	
	//////////////////////////////////////////////////////////////////////
	////////////////////////////REMOTE DATABASE///////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public static void addMessageToHistory(String sender, String receiver, String msg, String dateTime) {
		remoteDB.addMessage(sender, receiver, msg, dateTime) ;
	}
	
	

	
	
	
	
	public static void main (String [] args) {
		connection("test");
		disconnection();
	}

}