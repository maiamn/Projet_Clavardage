package network;

public class NetworkManager {
	
	// Attributs 
	ServerTCP serverTCP ; 
	ServerUDP serverUDP ; 
	
	// Enum for each message type
	public static enum MessageType {
		USERNAME_BRDCST,
		USERNAME_CONNECTED,
		USERNAME_DISCONNECT,
		MESSAGE
	}
	
	// Constructeur 
	public NetworkManager() {
		this.serverTCP = new ServerTCP() ; 
		this.serverUDP = new ServerUDP(5001, 50000) ; 
	}
	
	
	// Mise en forme des messages 
	public String messageFormatter(MessageType type,String message) {
		return (type + "|" + message);
	}
	
	
	// Disponibilite du pseudo 
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
	
	
	public void sendUnavailableUsername(String host) {
		System.out.println("Calling sendUnavailableUsername(host)");
		String msg = messageFormatter(MessageType.USERNAME_BRDCST, "") ;
		ClientTCP.sendMessage(msg, host);
	}
	
	
	public void notifyConnected(String username) {
		System.out.println("Calling notifyConnected(username)");
		String msg = messageFormatter(MessageType.USERNAME_CONNECTED, username) ;
		ClientUDP.broadcast(msg);
	}
	
	
	// Fonction principale de deconnexion 
	public void disconnection() {
		System.out.println("[NetworkManager] Calling disconnection()");
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
	
}