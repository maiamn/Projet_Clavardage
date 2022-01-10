package network;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP extends Thread {
	// Attributes 
	boolean isAvailable = true;	
	boolean connected = true ; 
	
	
	// Getters 
	public boolean getAvailable() {
		return this.isAvailable ; 
	}
	
	// Setter 
	public void setConnected(boolean state) {
		this.connected = state ; 
	}
	
	// Run function
	public void run() {
		int port = 5000;
		Socket clientSocket ; // should not be initialized
		ServerSocket serverSocket ;
		
		try {
			serverSocket = new ServerSocket(port);
			
			// Use of a while loop to allow multiple connections
			while(connected) {
				System.out.println("Waiting for connection") ;
				clientSocket = serverSocket.accept();
				System.out.println("Connection successful") ;
				new Thread(new MessageProcessingTCP (clientSocket, serverSocket, this)).start() ;
			}
			
			serverSocket.close() ;
			
		}
		catch (Exception e){
			System.out.println(e);
		}
		
		this.isAvailable = true ; 
		
	}
	
	public static void main (String [] args) {
		
	}
}