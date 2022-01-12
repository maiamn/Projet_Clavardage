package network;

import java.net.DatagramSocket ;
import java.net.InetAddress;
import java.net.DatagramPacket ;

public class ServerUDP extends Thread{
	// Attributes 
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
	
	
	//Reception of a new message 
	public void dataProcessing(String data) {
		String[] token = data.split("/-/");
		
		NetworkManager.MessageType type = NetworkManager.MessageType.valueOf(token[0].toUpperCase());
		String username = token[1];
		String content ;
		
		switch (type) {
		
		case USERNAME_BRDCST:
			// Someone asks if a username is available 
			try {
				content = token[2];
				InetAddress IP = InetAddress.getByName(content); 
				NetworkManager.usernameRequest(username,IP);				
			}
			catch(Exception e) {
				System.out.println(e);
			}
			break;
			
		case USERNAME_CONNECTED:
			// A new user is connected
			try {
				content = token[2];
				InetAddress IP = InetAddress.getByName(content); 
				NetworkManager.newUserConnected(username,IP);				
			}
			catch(Exception e) {
				System.out.println(e);
			}
			break;
			
		case USERNAME_DISCONNECT:
			// A user is disconnected
			NetworkManager.userDisconnected(username);
			break;
			
		case GET_USERNAMES:
			// A user asks for our username
			NetworkManager.sendUsername(username);
			break;
			
		case USERNAME_CHANGED: 
			try {
				content = token[2];
				InetAddress IP = InetAddress.getByName(content); 
				NetworkManager.updateUsername(IP, username) ; 
			}
			catch(Exception e) {
				System.out.println(e);
			}
			
		
		default:
			break;
			
		}
		
	}
	
	
	// Direct communication with table
	public void run() {
		try {
			DatagramSocket socket = new DatagramSocket(port);
			DatagramPacket packet = null;
			byte[] buffer = new byte[length];
			
			while(this.connected) {
				packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				String data = new String(packet.getData(), 0, packet.getLength());
				
				dataProcessing(data);
				
				// Clear buffer
				buffer = new byte[length];
			}
			
			socket.close();
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

}