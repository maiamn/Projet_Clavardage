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
	
	// Message processing depending on message format
	public void dataFilter(String msg) {
		String[] token = msg.split("|");
		NetworkManager.MessageType type = NetworkManager.MessageType.valueOf(token[0].toUpperCase());
		String username = token[1];
		String content = token[2];
		
		// TCP server only receives username availabilty related messages and normal messages
		switch(type) {
		
		case USERNAME_BRDCST:
			//the username we want to use is already taken 
			this.isAvailable = false;
			break;
			
		case GET_USERNAMES:
			//we received a response when we asked someone for their username
			try {
				InetAddress IP = InetAddress.getByName(content); 
				NetworkManager.newUserConnected(username, IP);
			}
			catch(Exception e) {
				System.out.println(e);
			}
			break;
			
		case MESSAGE:
			//we received a new message, we notify networkmanager
			NetworkManager.notifyNewMessage(content, username);
			break;
			
		default:
			break;
		}
	}
	

	//Forbid the use of a username because it is not available 
	public void forbidUsername() {
		//Notify TCP server that this username is already used
		this.serverTCP.isAvailable = false ;
		//Reset our isAvailable boolean 
		this.isAvailable = true ; 
	}
	
	
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
			
			//Message processing depending on the availability
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