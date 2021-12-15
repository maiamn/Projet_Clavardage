package network;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP extends Thread {
	// Attributs 
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
	
	
	public void main (String [] args) {
		int port = 5000;
		Socket clientSocket ; //on ne veut pas l'initialiser
		ServerSocket serverSocket ;
		
		try {
			serverSocket = new ServerSocket(port);
			
			//on utilise un while pour permettre plusieurs connexions 
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
}