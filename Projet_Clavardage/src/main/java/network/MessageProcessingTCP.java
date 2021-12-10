package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//https://stackoverflow.com/questions/9148899/returning-value-from-thread/9148992

public class MessageProcessingTCP implements Runnable {
	Socket clientSocket ;
	ServerSocket serverSocket ;
	String message;
	boolean isAvailable;
	
	
	public MessageProcessingTCP(Socket client, ServerSocket server) {
		clientSocket = client ;
		serverSocket = server ;
	}
	
	public void dataFilter(String msg) {
		String[] token = msg.split("|");
		if (token[0]=="0") { // response new username aka not available
			this.isAvailable = false;
		}else { // normal communication
			this.message = token[1];
		}
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
	
	public boolean getAvailable() {
		return this.isAvailable;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}