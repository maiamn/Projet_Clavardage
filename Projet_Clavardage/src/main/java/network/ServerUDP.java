package network;

import java.net.DatagramSocket ;
import java.net.InetAddress;
import java.net.DatagramPacket ;
import manager.*;
//import table interface

public class ServerUDP implements Runnable {
	// Attributs 
	int port;
	int length;
	boolean connected ; 
	
	// Constructor
	public ServerUDP(int port, int length) {
		this.port = port;
		this.length = length;
		this.connected = true ; 
	}
	
	// Setter 
	public void setConnected(boolean state) {
		this.connected = state ; 
	}
	
	
	public void dataProcessing(String data) {
		String[] token = data.split("|");
		//case 0 : connection
		if (token[0]=="0") {
			try {
				InetAddress IP = InetAddress.getByName(token[2]); 
				Manager.newUserConnected(token[1],IP);
			}
			catch(Exception e) {
			}
		}
		//case 1 : disconnection
		else {
			Manager.userDisconnected(token[1]);
		}
	}
	
	
	// Communication directe avec table
	public void run() {
		try {
			DatagramSocket socket = new DatagramSocket(port);
			DatagramPacket packet = null;
			byte[] buffer = new byte[length];
			
			while(this.connected) {
				packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				String data = new String(packet.getData(), 0, packet.getLength());
				
				System.out.println("Client : " + data);
				
				//clear buffer
				buffer = new byte[length];
			}
			
			socket.close();
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

}