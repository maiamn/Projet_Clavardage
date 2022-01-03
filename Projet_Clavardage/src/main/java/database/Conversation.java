package database;

public class Conversation {
	
	// Attributes 
	private String[] sendersTab ; 
	private String[]  receiversTab ;  
	private String[]  messagesTab ; 
	private String[]  datesTab ; 
	
	// Constructor 
	public Conversation(String[]  senders, String[]  receivers,  String[]  messages, String[] dates) {
		this.sendersTab = senders ; 
		this.receiversTab = receivers ; 
		this.messagesTab = messages ; 
		this.datesTab = dates ;
	}
	
	// Setters
	public void setSenders(String[]  senders) {
		this.sendersTab = senders ; 
	}
	
	public void setReceivers(String[] receivers) {
		this.receiversTab = receivers ; 
	}

	public void setMessages(String[] messages) {
		this.messagesTab = messages ; 
	}

	public void setDates(String[] dates) {
		this.datesTab = dates ;
	}
	
	
	// Getters 
	public String[] getSenders() {
		return this.sendersTab ;
	}
	
	public String[] getReceivers() {
		return this.receiversTab ; 
	}
	
	public String[] getMessages() {
		return this.messagesTab ; 
	}
	
	public String[] getDates() {
		return this.datesTab ; 
	}

}
