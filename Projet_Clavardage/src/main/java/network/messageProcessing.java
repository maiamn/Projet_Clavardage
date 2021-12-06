package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class messageProcessing implements Runnable {
	Socket clientSocket ;
	ServerSocket serverSocket ;
	
	
	public messageProcessing(Socket client, ServerSocket server) {
		clientSocket = client ;
		serverSocket = server ;
	}
	
	public void run() {
		BufferedReader input ;		
		try {
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String msg ;
			msg = input.readLine();
			while (msg != null ) {
				System.out.println("Client " + msg);
				msg = input.readLine();
			}
			clientSocket.close() ;
			serverSocket.close();
			input.close();
		}
		catch (Exception e) {
			System.out.println(e) ;
		}
	}
	
}