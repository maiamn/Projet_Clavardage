package manager;

import java.util.Scanner;
import java.net.InetAddress;
import database.* ;
import network.* ;

public class Manager {
	
	protected static String username = null;
	private static NetworkManager networkManager = new NetworkManager();
	private static LocalDB localDB = new LocalDB() ;
	
	//Maximum length of usernames
	private static int maxLength = 30 ;
	
	public static void connection() {
		String potentialUsername = null ;
		
		//while the username choosen is not available, enter a new one
		while(username==null) {
			try {
				//scan the username entered by the user
				Scanner scanner = new Scanner(System.in) ;
				System.out.println("Please enter a username:");
				
				potentialUsername = scanner.next(); 
				//scanner.close(); //� enlever car g�n�re des erreurs quand on le met dans un while
				
				//Verify that the length of the username is not too important
				if (potentialUsername.length() > maxLength) {
					System.out.println("This username is too long. Please try another one.");
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
		
		//once the username has been accepted, bc username
		networkManager.notifyConnected(potentialUsername);
		
	}
	
	public static void newUserConnected(String username, InetAddress IP) {
		localDB.addUser(username, IP);
	}
	
	public static void userDisconnected(String username) {
		localDB.deleteUserByName(username);
	}
	
	public static void main (String [] args) {
		connection();
	}

}
