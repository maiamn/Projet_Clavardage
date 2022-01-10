package network;

import java.net.DatagramSocket ;
import java.net.InetAddress;
import java.net.DatagramPacket ;
//import table interface

public class ServerUDP extends Thread{
	// Attributes 
	int port;
	int length;
	boolean connected ; 
	
	// Constructor
	public ServerUDP(int port, int length) {
		System.out.println("[ServerUDP] Calling constructor");
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
		System.out.println("[ServeurUDP]"+ data);
		String[] token = data.split("/-/");
//		System.out.println("type:" + token[0]);
//		System.out.println("username:" + token[1]);
//		System.out.println("content:" + token[2]);		
		
		
		NetworkManager.MessageType type = NetworkManager.MessageType.valueOf(token[0].toUpperCase());
		String username = token[1];

		String content ;
		
		switch (type) {
		
		case USERNAME_BRDCST:
			System.out.println("[ServeurUDP] USERNAME_BRDCST");
			//someone asks if a username is available 
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
			System.out.println("[ServeurUDP] USERNAME_CONNECTED");
			//a new user is connected
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
			System.out.println("[ServeurUDP] USERNAME_DISCONNECT");
			//a user is disconnected
			NetworkManager.userDisconnected(username);
			break;
			
		case GET_USERNAMES:
			//a user asks for our username
			System.out.println("[ServeurUDP] GET_USERNAMES");
			NetworkManager.sendUsername(username);
			System.out.println("[ServeurUDP] GET_USERNAMES done");
			break;
		
		default:
			System.out.println("[ServeurUDP] Type de message non reconnu");
			break;
			
		}
		
	}
	
	
	// Communication directe avec table
	public void run() {
		System.out.println("[ServerUDP] running");
		try {
			DatagramSocket socket = new DatagramSocket(port);
			DatagramPacket packet = null;
			byte[] buffer = new byte[length];
			
			while(this.connected) {
				packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				String data = new String(packet.getData(), 0, packet.getLength());
				
				dataProcessing(data);
				
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