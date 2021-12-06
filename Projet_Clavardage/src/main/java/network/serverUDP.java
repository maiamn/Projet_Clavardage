package network;

import java.net.DatagramSocket ;
import java.net.DatagramPacket ;

public class serverUDP {
	
	// Constructor
	public serverUDP() {
		// ....
	}
	
	public static void main (String [] args) {
		
		int port = 5000;
		int length = 50000 ;
		try {
			DatagramSocket socket = new DatagramSocket(port);
			DatagramPacket packet = null;
			byte[] buffer = new byte[length];
			
			while(true) {
				packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				String data = new String(packet.getData(), 0, packet.getLength());
				System.out.println("Client : " + data);
				
				//clear buffer
				buffer = new byte[length];
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
		
	}

}