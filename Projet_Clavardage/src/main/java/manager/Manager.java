package manager;

import java.net.InetAddress;
import database.* ;
import network.* ;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
	
	protected static String username ;
	private static NetworkManager networkManager = new NetworkManager();
	private static LocalDB localDB = new LocalDB() ;
	private static RemoteDB remoteDB = new RemoteDB();
	
	//Maximum length of usernames
	private static int maxLength = 30 ;
	
	
	
	//////////////////////////////////////////////////////////////////////
	/////////////////////////// VALID USERNAME ///////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	//Function that checks if a string contains special characters
	public static boolean noSpecialCharacter(String s) {
	     Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(s);
	     return !m.find();
	}
	
	//checks that the length of the username is between 1 and maxLength characters
	public static boolean validLengthUsername(String usernameWanted) {
		boolean res = true ; 
		res = res && (usernameWanted.length() < maxLength) ; 
		res = res && (usernameWanted.length() > 0) ; 
		return res ; 
	}
	
	//checks that there is no special character
	public static boolean validCharUsername(String usernameWanted) {
		boolean res = true ; 
		res = res && noSpecialCharacter(usernameWanted) ;
		return res ; 
	}
	
	//checks that the username is valid: length, special characters, availability
	public static boolean validUsername(String usernameWanted) {
		boolean res = true ; 
		res = res && validLengthUsername(usernameWanted) ; 
		res = res && validCharUsername(usernameWanted) ; 
		res = res && networkManager.usernameAvailable(usernameWanted) ; 
		if(res) {
			username = usernameWanted;
		}
		return res ; 
	}
	
	//get attribute username
	public static String getUsername() {
		return username;
	}
	
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////CONNECTION//////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	//notify everyone that we are connected
	public static void connection(String username) {
		//once the username has been accepted, bc username
		networkManager.notifyConnected(username);
		//we ask everyone their usernames
		networkManager.askUsernames(username);
	}
	
	public static void runServers() {
		networkManager.runServers();
	}
	
	
	//////////////////////////////////////////////////////////////////////
	////////////////////////////DISCONNECTION/////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	//notify everyone that we are disconnected and drop databases
	public static void disconnection() {
		localDB.dropDatabase();
		localDB.closeConnection();
		remoteDB.closeConnection();
		networkManager.disconnection(username);
	}
	
	
	//when we are asked for the availability of a username, we answer only if it is the one we already use
	public static void usernameRequest(String pseudo, InetAddress IP) {
		if (pseudo.equals(username)) {
			networkManager.sendUnavailableUsername(IP);
		}
	}
	
	
	//////////////////////////////////////////////////////////////////////
	////////////////////////////LOCAL DATABASE////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	//Add a user to the local database (correspondence username - IP)
	public static void newUserConnected(String username, InetAddress IP) {
		localDB.addUser(username, IP);
	}
	
	//Delete a username from the local table when they disconnect
	public static void userDisconnected(String username) {
		localDB.deleteUserByName(username);
	}
	
	//Get a username by their IP
	public static String getUsername(InetAddress IP) {
		return localDB.getUsername(IP);
	}
	
	//get an IP by their username
	public static InetAddress getIP(String name) {
		return localDB.getIP(name);
	}
	
	public static ArrayList<String> getAllUsernames() {
		return localDB.getAllUsernames() ; 
	}
	
	public static void dropTable() {
		localDB.dropDatabase();
	}
	
	
	//////////////////////////////////////////////////////////////////////
	////////////////////////////REMOTE DATABASE///////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	//add a message to history (remote db)
	public static void addMessageToHistory(String sender, String receiver, String msg, String dateTime) {
		remoteDB.addMessage(sender, receiver, msg, dateTime) ;
	}
	
	
	// Get history
	public static Conversation getHistory(String username1, String username2) {
		System.out.println("MANAGER / username1 : " + username1 + " username2 : " + username2);
		String IP1 = getIP(username1).toString();
		String IP2 = getIP(username2).toString();
		if (IP1.charAt(0) == ('/')) {
			IP1 = IP1.substring(1);
		}
		if (IP2.charAt(0) == ('/')) {
			IP2 = IP2.substring(1);
		}
		System.out.println("MANAGER / IP1 : " + IP1 + " IP2 : " + IP2);
		return remoteDB.getMessages(IP1, IP2);
	}
	
	
	
	
	//////////////////////////////////////////////////////////////////////
	/////////////////////////////SEND MESSAGE/////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	//send a message to a user with their name
	public static void sendMessage(String destinationUsername, String text) {
		InetAddress destinationIP = getIP(destinationUsername);
		networkManager.sendMessage(text, destinationIP);
	}
	
	
	
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////MAIN////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public static void main (String [] args) {
		connection("test");
		System.out.println(networkManager.getMyIPString());
		disconnection();
	}

}