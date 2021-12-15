package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//https://stackoverflow.com/questions/9148899/returning-value-from-thread/9148992

public class MessageProcessingTCP implements Runnable {
	Socket clientSocket ;
	ServerSocket serverSocket ;
	ServerTCP serverTCP ; 
	String message;
	boolean isAvailable;
	
	
	public MessageProcessingTCP(Socket client, ServerSocket serverSocket, ServerTCP serverTCP) {
		this.clientSocket = client ;
		this.serverSocket = serverSocket ;
		this.serverTCP = serverTCP ; 
	}
	
	public void dataFilter(String msg) {
		String[] token = msg.split("|");
		if (token[0]=="0") { // response new username aka not available
			this.isAvailable = false;
		}else { // normal communication
			this.message = token[1];
		}
	}
	
	public void forbidUsername() {
		// Prevenir le serveur que le pseudo n'est pas disponible
		this.serverTCP.isAvailable = false ;
		// Reset le booleen isAvailable de MessageProcessingTCP 
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
			
			// Traitement du message selon la disponibilite du pseudo
			if (!this.isAvailable) {
				forbidUsername() ; 
			} else {
				processMessage() ; 
			}
			
			clientSocket.close() ;
			serverSocket.close();
			input.close();
		}
		catch (Exception e) {
			System.out.println(e) ;
		}
		
	}

	
	public boolean getAvailable() {
		return this.isAvailable;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}