package manager;

import java.util.Scanner;
import java.net.InetAddress;
import database.* ;
import network.* ;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
	
	protected static String username = null;
	private static NetworkManager networkManager = new NetworkManager();
	private static LocalDB localDB = new LocalDB() ;
	
	//Maximum length of usernames
	private static int maxLength = 30 ;
	
	
	//Function that checks if a string contains special characters
	public static boolean containsSpecialCharacter(String s) {
	     Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(s);
	     return m.find();
	}
	
	
	public static void connection() {
		String potentialUsername = null ;
		
		//while the username choosen is not available, enter a new one
		Scanner scanner = null;
		while(username==null) {
			try {
				//scan the username entered by the user
				scanner = new Scanner(System.in) ;
				System.out.println("Please enter a username:");
				
				potentialUsername = scanner.next(); 
			
				//Verify that the length of the username is not too important
				if (potentialUsername.length() > maxLength) {
					System.out.println("This username is too long. Please try another one.");
				}
				
				//Verify that username does not contain special characters
				else if (containsSpecialCharacter(potentialUsername)) {
					System.out.println("The username should not contain special characters. Please try another one.");
				}
				
				
				//Then verify that it is available
				else if (networkManager.usernameAvailable(potentialUsername)) {
					username = potentialUsername ;
				}
				else {
					System.out.println("This username is already used. Please try another one.");
				}
			}
			catch (Exception e) {
				System.out.println(e);
			}
		}
		scanner.close();
		
		//once the username has been accepted, bc username
		networkManager.notifyConnected(potentialUsername);
		
	}
	
	public static void usernameRequest(String pseudo, String host) {
		if (pseudo == Manager.username) {
			networkManager.sendUnavailableUsername(host);
		}
	}
	
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
	
	public static void disconnection() {
		localDB.dropDatabase();
		localDB.closeConnection();
	}
	
	public static void main (String [] args) {
		connection();
		disconnection();
	}

}