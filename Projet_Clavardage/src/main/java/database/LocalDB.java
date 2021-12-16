package database;

import java.net.InetAddress;

public class LocalDB {
	
	// Attributes 
	private String username ; 
	private InetAddress IPaddress ; 
	
	// Constructor 
	public LocalDB(String username, InetAddress IP) {
		this.username = username ; 
		this.IPaddress = IP ; 
	}
	
	// Getters
	public String getUsername() {
		return this.username ; 
	}
	
	public InetAddress getIP() {
		return this.IPaddress ; 
	}
	
	// Setters 
	public void setUsername(String username) {
		this.username = username ; 
	}
	
	public void setIP(InetAddress IP) {
		this.IPaddress = IP ; 
	}

}
