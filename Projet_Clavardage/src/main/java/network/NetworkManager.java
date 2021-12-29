package network;

public class NetworkManager {
	
	// Attributs 
	ServerTCP serverTCP ; 
	ServerUDP serverUDP ; 
	
	// Enum for each message type
	public static enum MessageType {
		USERNAME_BRDCST,
		USERNAME_CONNECTED,
		MESSAGE
	}
	
	// Constructeur 
	public NetworkManager() {
		this.serverTCP = new ServerTCP() ; 
		this.serverUDP = new ServerUDP(5000, 50000) ; 
	}
	
	
	// Mise en forme des messages 
	public String messageFormatter(MessageType type,String message) {
		return (type + "|" + message);
	}
	
	
	// Disponibilit� du pseudo 
	public synchronized boolean usernameAvailable(String username) {
		System.out.println("Calling usernameAvailable(username)");
		long timeElapsed = 0;
		long start = System.currentTimeMillis();
		long finish = 0;
		String msg = messageFormatter(MessageType.USERNAME_BRDCST, username);
		
		ClientUDP.broadcast(msg);
		while(timeElapsed<1000) {
			finish = System.currentTimeMillis();
			timeElapsed = finish - start;			
		}
		
		// Regarder reponse du serverTCP
		return serverTCP.getAvailable() ;
	}
	
	
	public void notifyConnected(String username) {
		System.out.println("Calling notifyConnected(username)");
		//broadcast UDP of the username : type 0
		String msg = messageFormatter(MessageType.USERNAME_CONNECTED, username) ;
		ClientUDP.broadcast(msg);
	}
	
	
	// Fonction principale de deconnexion 
	public void disconnection() {
		System.out.println("disconnection()");
		serverUDP.setConnected(false) ;
		serverTCP.setConnected(false) ;
	}
	
	
	// Classic message sending function
	public void sendMessage(String msg, String host) {
		System.out.println("Sending message...");
		// Classic message between users : type 1 ??
		String formatedMsg = this.messageFormatter(MessageType.MESSAGE, msg);
		// Sends message and closes socket
		ClientTCP.sendMessage(formatedMsg, host);
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
