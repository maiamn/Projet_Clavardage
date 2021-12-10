package network;

import java.net.DatagramSocket ;
import java.net.DatagramPacket ;
// import table interface

public class serverUDP implements Runnable {
	
	// Constructor
	int port;
	int length;
	
	public serverUDP(int port, int length) {
		this.port = port;
		this.length = length;
	}
	
	// Communication directe avec table
	public void run() {
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