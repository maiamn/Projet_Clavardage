package network;

import manager.Manager;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class NetworkManager {

	// Attributes 
	ServerTCP serverTCP ; 
	ServerUDP serverUDP ;
	static InetAddress myIP ;
	static String myIPString ;

	// Constructor 
	public NetworkManager() {
		System.out.println("Constructeur du NetworkManager");
		this.serverTCP = new ServerTCP() ; 
		this.serverUDP = new ServerUDP(5001, 50000) ;

		//get IP address (!= 127.0.0.1)
		try {
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface ni = en.nextElement();

				List<InterfaceAddress> list = ni.getInterfaceAddresses();
				Iterator<InterfaceAddress> it = list.iterator();

				while (it.hasNext()) {
					InterfaceAddress ia = it.next();
					if (ia.getBroadcast()!=null && myIP == null) {
						myIP = ia.getAddress() ;
					}
				}		
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		myIPString = myIP.toString();
		if (myIPString.charAt(0) == ('/')) {
			myIPString = myIPString.substring(1);
		}
	}

	public InetAddress getMyIP() {
		return myIP ;
	}
	
	public String getMyIPString() {
		return myIPString ;
	}


	//////////////////////////////////////////////////////////////////////
	///////////////////////////// RUN SERVERS ////////////////////////////
	//////////////////////////////////////////////////////////////////////
	public void runServers() {
		System.out.println("[NetworkManager] running servers");
		new Thread(this.serverTCP).start();
		new Thread(this.serverUDP).start();
	}


	//////////////////////////////////////////////////////////////////////
	///////////////////////////MESSAGES FORMAT////////////////////////////
	//////////////////////////////////////////////////////////////////////

	// Enum for each message type
	public static enum MessageType {
		USERNAME_BRDCST,
		USERNAME_CONNECTED,
		USERNAME_DISCONNECT,
		GET_USERNAMES,
		MESSAGE
	}


	// Message formatting
	public static String messageFormatter(MessageType type,String username, String content) {
		return (type + "/-/" + username+ "/-/" + content);
	}


	//////////////////////////////////////////////////////////////////////
	////////////////////PRINCIPAL SENDMESSAGE FUNCTION////////////////////
	//////////////////////////////////////////////////////////////////////

	// Classic message sending function
	public void sendMessage(String msg, InetAddress destinationIP) {
		System.out.println("[ClientTCP] " + msg);
		// Classic message between users : type 1 ??
		String formatedMsg = messageFormatter(MessageType.MESSAGE, Manager.getUsername(), msg);
		// Sends message and closes socket
		ClientTCP.sendMessage(formatedMsg, destinationIP);
	}


	//////////////////////////////////////////////////////////////////////
	///////////////////////////////EMISSION///////////////////////////////
	//////////////////////////////////////////////////////////////////////

	// Availability of the username
	public synchronized boolean usernameAvailable(String username) {
		System.out.println("Calling usernameAvailable(username)");
		long timeElapsed = 0;
		long start = System.currentTimeMillis();
		long finish = 0;
		String msg = messageFormatter(MessageType.USERNAME_BRDCST, username, myIPString);

		//ask everyone if the username is available 
		ClientUDP.broadcast(msg);

		//We wait for an answer (users only answer if their username is the same as the one we want)
		while(timeElapsed<1000) {
			finish = System.currentTimeMillis();
			timeElapsed = finish - start;			
		}

		//We check if our TCP server has received a message notifying that 
		//the username is not available or not
		return serverTCP.getAvailable() ;
	}


	//Once we are connected, we send a broadcast with our username and our IP
	public void notifyConnected(String username) {
		System.out.println("Calling notifyConnected(username) with IP: " + myIPString);
		String msg = messageFormatter(MessageType.USERNAME_CONNECTED, username, myIPString) ;
		ClientUDP.broadcast(msg);
	}


	public void notifyDisconnected(String username) {
		System.out.println("Calling notifyDisonnected()");
		String msg = messageFormatter(MessageType.USERNAME_DISCONNECT, username, myIPString) ;
		ClientUDP.broadcast(msg);
	}


	//Broadcast asking everyone for their username
	public void askUsernames(String myusername) {
		System.out.println("Calling askUsernames()");
		String msg = messageFormatter(MessageType.GET_USERNAMES, myusername, "") ;
		ClientUDP.broadcast(msg);
	}


	//////////////////////////////////////////////////////////////////////
	///////////////////////////////ANSWER/////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	//Call Manager to know if the username wanted is valid
	public static void usernameRequest(String username, InetAddress destinationIP) {
		Manager.usernameRequest(username, destinationIP);
	}


	//The username wanted is not available so we notify the user
	public void sendUnavailableUsername(InetAddress destinationIP) {
		System.out.println("Calling sendUnavailableUsername(host)");
		String msg = messageFormatter(MessageType.USERNAME_BRDCST, Manager.getUsername(), "") ;
		ClientTCP.sendMessage(msg, destinationIP);
	}


	//We add the user to the DB
	public static void newUserConnected(String username, InetAddress IP) {
		Manager.newUserConnected(username, IP);
	}


	//We delete the user from the DB
	public static void userDisconnected(String username) {
		Manager.userDisconnected(username);
	}


	//If someone asks our username, we answer with our username and our IP
	public static void sendUsername(String destinationUsername) {
		if (!destinationUsername.equals(Manager.getUsername())) {
			System.out.println("Calling sendUsername");
			String msg = messageFormatter(MessageType.GET_USERNAMES, Manager.getUsername(), myIPString) ;
			InetAddress destinationIP = Manager.getIP(destinationUsername);
			System.out.println("sendUsername : getIP ok");
			ClientTCP.sendMessage(msg, destinationIP);	
			System.out.println("senUsername : sendMessage ok");
		}
	}


	//When we receive a message, we add it to the history
	public static void notifyNewMessage(String message, String user) {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDate = date.format(format);
		
		String IPSender = Manager.getIP(user).toString() ;
		if (IPSender.charAt(0) == ('/')) {
			IPSender = IPSender.substring(1);
		}
		Manager.addMessageToHistory(IPSender, myIPString, message, formattedDate);

		System.out.println(user + " at " + formattedDate + ": " + message);
	}


	//////////////////////////////////////////////////////////////////////
	////////////////////////////DISCONNECTION/////////////////////////////
	//////////////////////////////////////////////////////////////////////

	// Principal function of disconnection
	public void disconnection(String username) {
		System.out.println("[NetworkManager] Calling disconnection()");
		notifyDisconnected(username);
		serverUDP.setConnected(false) ;
		serverTCP.setConnected(false) ;
	}


	public static void main (String [] args) {
		new Thread(new ServerUDP(5001, 50000)).start();
		//notifyConnected("Celia");
	}


}