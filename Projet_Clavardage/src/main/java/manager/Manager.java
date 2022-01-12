package manager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import database.* ;
import network.* ;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
	// Attributes 
	protected static String username ;
	private static NetworkManager networkManager = new NetworkManager();
	private static LocalDB localDB = new LocalDB() ;
	private static RemoteDB remoteDB = new RemoteDB();
	
	//Maximum length of usernames
	private static int maxLength = 30 ;
	
	
	
	//////////////////////////////////////////////////////////////////////
	/////////////////////////// VALID USERNAME ///////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	// Function that checks if a string contains special characters
	public static boolean noSpecialCharacter(String s) {
	     Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(s);
	     return !m.find();
	}
	
	// Function that checks that the length of the username is between 1 and maxLength characters
	public static boolean validLengthUsername(String usernameWanted) {
		boolean res = true ; 
		res = res && (usernameWanted.length() < maxLength) ; 
		res = res && (usernameWanted.length() > 0) ; 
		return res ; 
	}
	
	// Function that hecks that there is no special character
	public static boolean validCharUsername(String usernameWanted) {
		boolean res = true ; 
		res = res && noSpecialCharacter(usernameWanted) ;
		return res ; 
	}
	
	// Function that checks that the username is valid: length, special characters, availability
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
	
	
	// Get attribute username
	public static String getUsername() {
		return username;
	}
	
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////CONNECTION//////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	// Notify everyone that we are connected
	public static void connection(String username) {
		// Once the username has been accepted, bc username
		networkManager.notifyConnected(username);
		// We ask everyone their usernames
		networkManager.askUsernames(username);
	}
	
	public static void runServers() {
		networkManager.runServers();
	}
	
	//////////////////////////////////////////////////////////////////////
	///////////////////////////CHANGE USERNAME////////////////////////////
	//////////////////////////////////////////////////////////////////////
	public static void notifyUsernameChanged(String newUsername) {
		networkManager.notifyUserameChanged(newUsername);
	}
	
	public static void updateUsername(InetAddress IP, String newUsername) {
		localDB.updateUsername(IP, newUsername);
	}
	
	//////////////////////////////////////////////////////////////////////
	////////////////////////////DISCONNECTION/////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	// Notify everyone that we are disconnected and drop databases
	public static void disconnection() {
		localDB.closeConnection();
		remoteDB.closeConnection();
		networkManager.disconnection(username);
	}
	
	
	// When we are asked for the availability of a username, we answer only if it is the one we already use
	public static void usernameRequest(String pseudo, InetAddress IP) {
		if (pseudo.equals(username)) {
			networkManager.sendUnavailableUsername(IP);
		}
	}
	
	
	//////////////////////////////////////////////////////////////////////
	////////////////////////////LOCAL DATABASE////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	// [LocalDB] Add a user to the local database (correspondence username - IP)
	public static void newUserConnected(String username, InetAddress IP) {
		localDB.addUser(username, IP);
	}
	
	// [LocalDB] Delete a username from the local table when they disconnect
	public static void userDisconnected(String username) {
		localDB.userDisconnected(username);
	}
	
	// [LocalDB] Set the new last access 
	public static void setLastAccess(String username, String newDate) {
		String IPString = getIP(username).toString();
		if (IPString.charAt(0) == ('/')) {
			IPString = IPString.substring(1);
		}
		InetAddress IP = null;
		try {
			IP = InetAddress.getByName(IPString);
		} catch (UnknownHostException e) {
			System.out.println(e) ; 
		} 
		localDB.updateLastAccess(IP, newDate);
	}
	
	// [LocalDB] Get a username by their IP
	public static String getUsername(InetAddress IP) {
		return localDB.getUsername(IP);
	}
	
	// [LocalDB] Get an IP by their username
	public static InetAddress getIP(String name) {
		return localDB.getIP(name);
	}
	
	// [LocalDB] Get the last access to a conversation
	public static String getLastAccess(String username) {
		return localDB.getLastAccess(localDB.getIP(username)) ; 
	}
	
	// [LocalDB] Get connected usernames stored in the table
	public static ArrayList<String> getConnectedUsernames() {
		return localDB.getConnectedUsernames() ; 
	}
	
	// [LocalDB] Get all usernames stored in the table
	public static ArrayList<String> getAllUsernames() {
		return localDB.getAllUsernames() ; 
	}
	
	// [LocalDB] Drop the local database
	public static void dropTable() {
		localDB.dropDatabase();
	}
	
	
	//////////////////////////////////////////////////////////////////////
	////////////////////////////REMOTE DATABASE///////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	// [RemoteDB] Add a message to history 
	public static void addMessageToHistory(String sender, String receiver, String msg, String dateTime) {
		remoteDB.addMessage(sender, receiver, msg, dateTime) ;
	}
	
	
	// [RemoteDB] Get history
	public static Conversation getHistory(String username1, String username2) {
		String IP1 = getIP(username1).toString();
		String IP2 = getIP(username2).toString();
		if (IP1.charAt(0) == ('/')) {
			IP1 = IP1.substring(1);
		}
		if (IP2.charAt(0) == ('/')) {
			IP2 = IP2.substring(1);
		}
		return remoteDB.getMessages(IP1, IP2);
	}
	
	// [RemoteDB] Get interlocutors
	public static ArrayList<String> getInterlocutors(String username) {
		String IP = getIP(username).toString();
		if (IP.charAt(0) == ('/')) {
			IP = IP.substring(1);
		}
		String[] IPArray = remoteDB.getInterlocutors(IP) ; 
		ArrayList<String> interlocutors = new ArrayList<String>(); 
		for (int i=0; i<IPArray.length; i++) {
			try {
				interlocutors.add(getUsername(InetAddress.getByName(IPArray[i]))) ;
			} catch (UnknownHostException e) {
				System.out.println(e) ; 
			} 
		}
		return interlocutors ; 
	}
		
	// [RemoteDB] Get the date of the last message received 
	public static String getLastDate(String sender, String receiver) {
		return remoteDB.getDateLastMessage(sender, receiver) ; 
	}
	
	
	//////////////////////////////////////////////////////////////////////
	/////////////////////////////SEND MESSAGE/////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	// Know if there is an unread message 
	public static boolean newMessage(String user1, String user2) {
		boolean res ; 
		String lastAccessString = getLastAccess(user2) ; 
		String lastDateString = getLastDate(user1, user2) ;  
		
		if (lastAccessString.equals("") || lastDateString.equals("")) { 
			res = false ; 
		} else {
			Date lastAccess = null ; 
			Date lastDate = null ; 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				lastAccess = sdf.parse(lastAccessString);
				lastDate = sdf.parse(lastDateString); 
			} catch (ParseException e) {
				System.out.println(e) ; 
			}
			
			if (lastAccess.after(lastDate)) {
				res = false ; 
			}
			else {
				res = true ; 
			}
		}
		return res ; 
	}
	
	
	//////////////////////////////////////////////////////////////////////
	/////////////////////////////SEND MESSAGE/////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	// Send a message to a user with their name
	public static void sendMessage(String destinationUsername, String text) {
		InetAddress destinationIP = getIP(destinationUsername);
		networkManager.sendMessage(text, destinationIP);
	}
	
	
	
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////MAIN////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public static void main (String [] args) {
		connection("test");
		disconnection();
	}

}