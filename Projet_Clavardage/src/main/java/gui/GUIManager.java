package gui;

import manager.* ;

public class GUIManager {
	// Attributes
	AuthentificationGUI authentification = new AuthentificationGUI() ; 
	
	//Constructor
	public GUIManager() {
	}
	
	public void checkUsername(String username) {
		Manager.connection(username);
	}
	
}
