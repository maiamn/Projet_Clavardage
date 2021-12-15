package network;

import java.io.*;

public class NetworkManager {
	
	// Attributs 
	ClientUDP clientBroadcast;
	ServerTCP serverTCP ; 
	
	
	// Constructeur 
	public NetworkManager() {
		this.clientBroadcast = new ClientUDP();
		this.serverTCP = new ServerTCP() ; 
	}
	
	
	// Mise en forme des messages 
	public String messageFormatter(int type,String message) {
		return (type + "|" + message);
	}
	
	
	// Disponibilité du pseudo 
	public synchronized boolean usernameAvailable(String username) {
		long timeElapsed = 0;
		long start = System.currentTimeMillis();
		long finish = 0;
		
		ClientUDP.broadcast(username);
		while(timeElapsed<1000) {
			finish = System.currentTimeMillis();
			timeElapsed = finish - start;			
		}
		
		// Regarder reponse du serverTCP
		return serverTCP.getAvailable() ;
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
