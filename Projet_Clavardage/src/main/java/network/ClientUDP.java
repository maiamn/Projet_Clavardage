package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import java.net.InterfaceAddress ; 
import java.net.NetworkInterface ; 

public class ClientUDP {
	
	// Constructor => not needed 'cause only used methods are static aka can be called without creating the object
	//public clientUDP() {
		// ....
	//}
	
	// Fonction de broadcast 
	public static void broadcast (String msg) {
		
		int port = 5000;
		
		try {
			List<InetAddress> broadcasts = new ArrayList<InetAddress>() ; 
			
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
		    while (en.hasMoreElements()) {
		      NetworkInterface ni = en.nextElement();

		      List<InterfaceAddress> list = ni.getInterfaceAddresses();
		      Iterator<InterfaceAddress> it = list.iterator();

		      while (it.hasNext()) {
		        InterfaceAddress ia = it.next();
		        if (ia.getBroadcast()!=null) {
		        	broadcasts.add(ia.getBroadcast()) ; 
		        }
		      }
		    }
		    
		    
			DatagramSocket socket = new DatagramSocket();
			byte buffer[] = null;
		    

			buffer = msg.getBytes();
				
			for(InetAddress ip : broadcasts) {
		    	System.out.println(" Broadcast = " + ip);
		    	DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, port);
		    	socket.send(packet);
		    }
			
			socket.close() ;

		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	public static void main (String [] args) {
		broadcast("test fonction broadcast") ;
	}

}
	