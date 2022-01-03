package network;

import java.net.*;
import java.util.*;
import java.io.*;

//Client : un thread par socket, on crée plusieurs socket sur le même port, 
//en plus du main thread


public class ClientTCP {
	public static void main (String [] args) {
		String host = "10.1.5.47" ;
		//Socket clientSocket ;
		int port = 5000 ;
		try { 
			Scanner s = new Scanner(System.in);
			PrintWriter output ;
			Socket clientSocket = new Socket(host, port) ;
			output = new PrintWriter(clientSocket.getOutputStream());
			String msg = "" ;
			while (!msg.equals("Over")) {
				msg = s.nextLine()	;
				output.println(msg);
				output.flush() ;
			}
			
			clientSocket.close();
			s.close();
			output.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void sendMessage(String formatedMsg, String host) {
		int port = 5000;
		PrintWriter output;
		try {
			
			Socket clientSocket = new Socket(host, port);
			output = new PrintWriter(clientSocket.getOutputStream());
			
			// Ajouter client socket en attribut si on veut le garder ouvert longtemps
			// Avec une fonction pour l'ouverture et une fonction pour la fermeture en plus du send message
			
			output.println(formatedMsg);
			output.flush() ;
			output.close();
			clientSocket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}