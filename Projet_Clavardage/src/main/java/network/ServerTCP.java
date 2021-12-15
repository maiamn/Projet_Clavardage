package network;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//serveur : chaque fois qu'il reçoit un nv client il crée un nv thread 
//pour créer nvelle co et la main thread est toujours en train d'écouter 
//s'il y a une nvelle co
//accepte la co avec le thread main puis ensuite crée un nv thread
//on utilise le class extends thread car on peut passer des args 
//(dont le socket) l'autre implementation (avec le Runnable) peut être 
//utilisée dans le client 


public class ServerTCP extends Thread {
	
	boolean isAvailable = true;	
	
	public static void main (String [] args) {
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
				new Thread(new MessageProcessingTCP (clientSocket, serverSocket)).start() ;
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
		
	}
}