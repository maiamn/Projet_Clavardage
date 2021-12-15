package network;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP extends Thread {
	// Attributs 
	boolean isAvailable = true;	
	
	// Getters 
	public boolean getAvailable() {
		return this.isAvailable ; 
	}
	
	
	public void main (String [] args) {
		int port = 5000;
		Socket clientSocket ; //on ne veut pas l'initialiser
		ServerSocket serverSocket ;
		
		try {
			serverSocket = new ServerSocket(port);
			
			//on utilise un while pour permettre plusieurs connexions 
			while(true) {
				System.out.println("Waiting for connection") ;
				clientSocket = serverSocket.accept();
				System.out.println("Connection successful") ;
				new Thread(new MessageProcessingTCP (clientSocket, serverSocket, this)).start() ;
			}
			
		}
		catch (Exception e){
			System.out.println(e);
		}
		
		this.isAvailable = true ; 
		
	}
}