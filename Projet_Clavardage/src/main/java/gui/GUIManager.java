package gui;

import manager.* ;
import java.util.ArrayList;

public class GUIManager {
	// Attributes
	static String username = "" ; 
	static AuthentificationGUI authentification ; 
	static ConnectionGUI connection ; 
	static HomePageGUI homepage ; 
	static ConnectedUsersGUI connectedUsers ; 
	static SendMessageGUI sendMessage ; 
	static ChatGUI chat ; 
	static ChangeUsernameGUI changeUsername ; 
	static DisconnectionGUI disconnection ;
	
	//Constructor
	public GUIManager() {
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////// AUTHENTIFICATION ////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////

	// Check if the username is correct 
	// Check how to handle exception
	public static boolean checkUsername(String username) {
		return Manager.validUsername(username);
	}
			
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// CONNECTION ///////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static void connection(String username) {
		Manager.connection(username);
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////// CONNECTED USERS ////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static ArrayList<String> getAllConnectedUsers() {
//		ArrayList<String> usernames = new ArrayList<String>();
//		usernames.add("user1") ; 
//		usernames.add("user2") ; 
//		usernames.add("user3") ; 
//		usernames.add("user4") ; 
//		usernames.add("user5") ; 
//		usernames.add("user6") ; 
//		usernames.add("user7") ; 
//		usernames.add("user8") ; 
//		usernames.add("user9") ; 
//		usernames.add("user10") ; 
//		usernames.add("user11") ; 
//		usernames.add("user12") ; 
//		usernames.add("user13") ; 
//		usernames.add("user14") ; 
//		usernames.add("user15") ; 
//		usernames.add("user16") ; 
//		usernames.add("user17") ; 
//		usernames.add("user18") ; 
//		usernames.add("user19") ; 
//		usernames.add("user20") ; 
//		usernames.add("user21") ; 
//		usernames.add("user22") ; 
//		usernames.add("user23") ; 
//		usernames.add("user24") ; 
//		usernames.add("user25") ; 
//		usernames.add("user26") ; 
//		usernames.add("user27") ; 
//		usernames.add("user28") ; 
//		usernames.add("user29") ;
//		return usernames ; 
		return Manager.getAllUsernames() ; 
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////// SEND A MESSAGE //////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean userReachable(ArrayList<String> connectedUsers, String userToReach) {
		return connectedUsers.contains(userToReach) ; 
	}
	
	public static void sendMessage(String destinationUsername, String message) {
		Manager.sendMessage(destinationUsername, message);
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////// DISCONNECTION //////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static void disconnection() {
		Manager.disconnection();
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////// HOW TO SWITCH BETWEEN DIFFERENT WINDOW  /////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	// SWITCH TO AUTHENTIFICATION WINDOW 
	public static void switchToAuthentification() {
		authentification = new AuthentificationGUI() ; 
	}
	
	// SWITCH TO CONNECTION WINDOW 
	public static void switchToConnection(String username) {
		connection = new ConnectionGUI(username) ; 
	}
	
	// SWITCH TO HOME PAGE 
	public static void switchToHomePage(String username) {
		homepage = new HomePageGUI(username) ; 
	}
	
	// SWITCH TO CONNECTED USER PAGE
	public static void switchToConnectedUsers(String username) {
		connectedUsers = new ConnectedUsersGUI(username) ; 
	}
	
	// SWITCH TO SEND MESSAGE PAGE 
	public static void switchToSendMessage(String username) {
		sendMessage = new SendMessageGUI(username) ; 
	}
	
	// SWITCH TO HISTORY PAGE
	public static void switchToChat(String sender, String receiver) {
		chat = new ChatGUI(sender, receiver) ; 
	}
	
	// SWITCH TO CHANGE USERNAME
	public static void switchToChangeUsername(String username) {
		changeUsername = new ChangeUsernameGUI(username) ; 
	}
	
	// SWITCH TO DISCONNECTION PAGE
	public static void switchToDisconnection(String username) {
		disconnection = new DisconnectionGUI(username) ; 
	}
	
	public static void main (String [] args) {
		authentification = new AuthentificationGUI() ; 		
	}
	
}
