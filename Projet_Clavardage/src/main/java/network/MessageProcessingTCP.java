package network;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class MessageProcessingTCP implements Runnable {
	
	// Attributs 
	Socket clientSocket ;
	ServerSocket serverSocket ;
	ServerTCP serverTCP ; 
	String message;
	boolean isAvailable;
	
	// Constructor 
	public MessageProcessingTCP(Socket client, ServerSocket serverSocket, ServerTCP serverTCP) {
		this.clientSocket = client ;
		this.serverSocket = serverSocket ;
		this.serverTCP = serverTCP ; 
	}
	
	// Getters 
	public boolean getAvailable() {
		return this.isAvailable;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	// Filtrage des donnees selon le format de message 
	public void dataFilter(String msg) {
		String[] token = msg.split("|");
		NetworkManager.MessageType type = NetworkManager.MessageType.valueOf(token[0].toUpperCase());
		String host = token[1];
		String content = token[2];
		
		// TCP server only receives username availabilty related messages and normal messages
		switch(type) {
		
		case USERNAME_BRDCST :
			this.isAvailable = false;
			break;
			
		case GET_USERNAMES:
			try {
				InetAddress IP = InetAddress.getByName(host); 
				NetworkManager.newUserConnected(content, IP);	
			}
			catch(Exception e) {
				System.out.println(e);
			}
			break;
			
		case MESSAGE :
			this.message = token[1];
			break;
			
		default:
			// raise exception?
			break;
		}
	}
	
	// Interdire le pseudo au serveur TCP
	public void forbidUsername() {
		// Prevenir le serveur que le pseudo n'est pas disponible
		this.serverTCP.isAvailable = false ;
		// Reset le booleen isAvailable de MessageProcessingTCP 
		this.isAvailable = true ; 
	}
	
	// Traitement du message quand le pseudo est autorise
	public void processMessage() {
		System.out.println(this.message) ; 
	}
	
	
	public void run() {
		BufferedReader input ;		
		try {
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String msg ;
			msg = input.readLine();
			
			dataFilter(msg) ; 
			
			// Traitement du message selon la disponibilite du pseudo
			if (!this.isAvailable) {
				forbidUsername() ; 
			} else {
				processMessage() ; 
			}
			
			input.close();
		}
		catch (Exception e) {
			System.out.println(e) ;
		}
		
	}
	
}