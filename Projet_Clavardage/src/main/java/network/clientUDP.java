package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class clientUDP {
	
	// Constructor
	public clientUDP() {
		// ....
	}
	
	public static void main (String [] args) {
		
		int port = 5000;
		
		try {
			Scanner scanner = new Scanner(System.in);
			DatagramSocket socket = new DatagramSocket();
			InetAddress ip = InetAddress.getLocalHost();
			byte buffer[] =null;
			
			while(true) {
				String message = scanner.nextLine();
				buffer = message.getBytes();
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, port);
				socket.send(packet);
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
}