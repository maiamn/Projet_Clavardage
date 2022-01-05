package gui;

import manager.* ;

public class GUIManager {
	// Attributes
	static String username = "" ; 
	static AuthentificationGUI authentification ; 
	static ConnectionGUI connection ; 
	static HomePageGUI homepage ; 
	static ConnectedUsersGUI connectedUsers ; 
	static SendMessageGUI sendMessage ; 
	static HistoryGUI history ; 
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
	public static void switchToSendMessage() {
		sendMessage = new SendMessageGUI() ; 
	}
	
	// SWITCH TO HISTORY PAGE
	public static void switchToHistory() {
		history = new HistoryGUI() ; 
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
