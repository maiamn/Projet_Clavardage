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
}