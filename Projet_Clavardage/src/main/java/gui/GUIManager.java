package gui;

import manager.* ;

import java.net.InetAddress;
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
	static Conversations conversations ; 
	
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
	/////////////////////////////////////// CHANGE USERNAME //////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static void updateAllLocalTables(String newUsername) {
		Manager.notifyUsernameChanged(newUsername) ; 
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////// CONNECTED USERS ////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static ArrayList<String> getAllConnectedUsers() { 
		return Manager.getConnectedUsernames() ; 
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
	///////////////////////////////////////////// CHAT ///////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	//get an IP by their username
	public static InetAddress getIP(String name) {
		return Manager.getIP(name);
	}
	
	public static String[] getSenders(String sender, String receiver) {
		return Manager.getHistory(sender, receiver).getSenders() ;
	}
	
	public static String[] getReceivers(String sender, String receiver) {
		return Manager.getHistory(sender, receiver).getReceivers() ; 
	}
	
	public static String[] getDates(String sender, String receiver) {
		return Manager.getHistory(sender, receiver).getDates() ; 
	}
	
	public static String[] getMessages(String sender, String receiver) {
		return Manager.getHistory(sender, receiver).getMessages() ; 
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
	
	// SWITCH TO HOME PAGE TO SELECT WHO YOU WANT TO TALK WITH
	public static void switchToSendMessage(String username) {
		sendMessage = new SendMessageGUI(username) ; 
	}
	
	// SWITCH TO SEND MESSAGE PAGE
	public static void switchToChat(String sender, String receiver) {
		System.out.println("ChatGUI : sender = " + sender + " receiver = " + receiver) ; 
		chat = new ChatGUI(sender, receiver) ; 
	}
	
	// SWITH TO CONVERSATIONS PAGE 
	public static void switchToConversations(String username) {
		conversations = new Conversations() ; 
	}
	
	// SWITCH TO CHANGE USERNAME
	public static void switchToChangeUsername(String username) {
		changeUsername = new ChangeUsernameGUI(username) ; 
	}
	
	// SWITCH TO DISCONNECTION PAGE
	public static void switchToDisconnection(String username) {
		disconnection = new DisconnectionGUI(username) ; 
	}
	
	
	
	// MAIN 
	public static void main (String [] args) {
		Manager.runServers();
		authentification = new AuthentificationGUI() ; 		
	}
	
}
