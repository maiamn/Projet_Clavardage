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
	//////////////////////////// HOW TO SWITCH BETWEEN DIFFERENT WINDOW  /////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	// SWITCH TO CONNECTION WINDOW 
	public static void switchToConnection(String username) {
		connection = new ConnectionGUI(username) ; 
	}
	
	// SWITCH TO HOME PAGE 
	public static void switchToHomePage(String username) {
		homepage = new HomePageGUI(username) ; 
	}
	
	// SWITCH TO CONNECTED USER PAGE
	public static void switchToConnectedUsers() {
		connectedUsers = new ConnectedUsersGUI() ; 
	}
	
	// SWITCH TO SEND MESSAGE PAGE 
	public static void switchToSendMessage() {
		sendMessage = new SendMessageGUI() ; 
	}
	
	// SWITCH TO HISTORY PAGE
	public static void switchToHistory() {
		history = new HistoryGUI() ; 
	}
	
	// SWITCH TO DISCONNECTION PAGE
	public static void switchToDisconnection() {
		disconnection = new DisconnectionGUI() ; 
	}
	
	public static void main (String [] args) {
		authentification = new AuthentificationGUI() ; 		
	}
	
}
