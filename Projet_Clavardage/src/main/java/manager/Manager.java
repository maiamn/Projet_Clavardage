package manager;

import java.util.Scanner;
import java.net.InetAddress;
import database.* ;
import network.* ;

public class Manager {
	
	protected String username = null;
	private NetworkManager networkManager ;
	private LocalDB localDB = new LocalDB() ;
	
	public void connection() {
		String potentialUsername = null ;
		
		//while the username choosen is not available, enter a new one
		while(username==null) {
			try {
				//scan the username entered by the user
				Scanner scanner = new Scanner(System.in) ;
				System.out.println("Please enter a username:");
				potentialUsername = scanner.next(); 
				scanner.close();
				
				//TO-DO : verify that the length of the username is not too important
				
				//check if this username is available
				if (networkManager.usernameAvailable(potentialUsername)) {
					username = potentialUsername ;
				}
			}
			catch (Exception e) {
				System.out.println(e);
			}
		}
		
		//once the username has been accepted, bc username
		networkManager.notifyConnected(potentialUsername);
		
	}
	
	public void newUserConnected(String username, InetAddress IP) {
		localDB.addUser(username, IP);
	}
	

}
