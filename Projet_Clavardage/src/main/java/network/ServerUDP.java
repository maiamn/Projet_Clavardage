package network;

import java.net.DatagramSocket ;
import java.net.InetAddress;
import java.net.DatagramPacket ;
//import table interface

public class ServerUDP {
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
		String[] token = data.split("|");
		NetworkManager.MessageType type = NetworkManager.MessageType.valueOf(token[0].toUpperCase());
		String username = token[1];
		String content = token[2];
		
		
		switch (type) {
		
		case USERNAME_BRDCST:
			// traitement pour broacast de demande de co avec reponse 
			// Envoi de reponse par TCP de disponibilite
			try {
				InetAddress IP = InetAddress.getByName(content); 
				NetworkManager.usernameRequest(username,IP);				
			}
			catch(Exception e) {
				System.out.println(e);
			}
			break;
			
		case USERNAME_CONNECTED:
			try {
				InetAddress IP = InetAddress.getByName(content); 
				NetworkManager.newUserConnected(username,IP);				
			}
			catch(Exception e) {
				System.out.println(e);
			}
			break;
			
		case USERNAME_DISCONNECT:
			// Traitement pour deconnexion de utilisateur
			NetworkManager.userDisconnected(username);
			break;
			
		case GET_USERNAMES:
			try {
				InetAddress IP = InetAddress.getByName(content); 
				NetworkManager.sendUsername(IP);	
			}
			catch(Exception e) {
				System.out.println(e);
			}
			break;
		
		default:
			break;
			
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