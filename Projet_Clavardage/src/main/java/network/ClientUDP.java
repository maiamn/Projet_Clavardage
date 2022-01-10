package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import java.net.InterfaceAddress ; 
import java.net.NetworkInterface ; 

public class ClientUDP {

	// Constructor => not needed because this class only defines static method 
	// that is to say that they can be called without creating the object


	// Broadcast function
	public static void broadcast (String msg) {
		System.out.println("[ClientUDP]"+ msg);
		
		// Forbidden address containing only bytes 0
		byte[] forbidAddrByte = new byte[]{(byte)0, (byte)0, (byte)0, (byte)0};
		
		// Used port 
		int port = 5001;

		try {
			InetAddress forbidAddr = InetAddress.getByAddress(forbidAddrByte);

			List<InetAddress> broadcasts = new ArrayList<InetAddress>() ; 
			
			// Get all broadcast addresses 
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface ni = en.nextElement();

				List<InterfaceAddress> list = ni.getInterfaceAddresses();
				Iterator<InterfaceAddress> it = list.iterator();

				while (it.hasNext()) {
					InterfaceAddress ia = it.next();

					if (ia.getBroadcast()!=null && !ia.getBroadcast().equals(forbidAddr)) {
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
		
	}

}