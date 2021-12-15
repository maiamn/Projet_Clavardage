package network;

import java.io.*;

public class NetworkManager {
	
	ClientUDP clientBroadcast;
	
	public NetworkManager() {
		clientBroadcast = new ClientUDP();
	}
	
	public String messageFormatter(int type,String message) {
		return (type+"|"+message);
	}
	
	
	
	public synchronized boolean usernameAvailable(String username) {
		boolean isAvailable = false;
		long timeElapsed = 0;
		long start = System.currentTimeMillis();
		long finish = 0;
		
		ClientUDP.broadcast(username);
		while(!isAvailable  || timeElapsed<1000) {
			// regarder reponse du serverTCP
			finish = System.currentTimeMillis();
			timeElapsed = finish - start;
		}
		for (Thread threadResponse : ServerTCP.threadList) {
			// get attribute from class ran by the thread
		}
		return isAvailable;
	}
	
	
	
	public void notifyConnected(String username) {
		clientBroadcast.broadcast(username);
	}
	// Format des messages en broadcast 
	/* flag de connexion/deconnexion
	 * adresse ip de celui qui se connecte ou se deconnecte 
	 * pseudo  
	 * 
	 * <!> REPONSE EN TCP
	 * 
	 * Pour le broadcast du pseudo lors de la connexion, seul l'utilisateur qui 
	 * a deja le pseudo que l'on veut peut repondre NON -> on met en place en timer
	 * donc si on n'a pas de reponse on dit que c'est OK
	 */
	
	/*
	 * 
	 * */

	// Fonctions possibles:
	/*
	 * - Traitement des des paquets broadcasts
	 * 
	 * - Broadcast de connexion username (timer a part?)
	 * 
	 * - Broadcast de deconnexion
	 * 
	 * - Reponse des paquets Broadcast
	 * 
	 * - Traitement des paquets TCP aka message users
	 * */
	
	
	
}
