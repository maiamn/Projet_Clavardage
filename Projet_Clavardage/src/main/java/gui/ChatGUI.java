package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatGUI {
	private JFrame chatFrame ; 
	
	private JPanel topPanel ; 
	private JPanel centerPanel ; 
	private JPanel bottomPanel ; 
	
	private JLabel welcome ; 
	private JButton back = new JButton("Back to home page") ; 
	
	public static String sender ; 
	public static String receiver ; 
	
	// Table with history
	protected int nbMessages = GUIManager.getMessages(sender, receiver).length ; 
	protected Object senders[] ;
	protected Object receivers[] ; 
	protected Object dates[] ; 
	protected Object messages[] ; 
	protected Object history[][] = new Object [nbMessages][4] ; 
	protected Object header[] = {"Sender", "Receiver", "Date", "Message"} ;
	final JTable historyTable ; 
	JScrollPane scrollPane ; 
	
	// Field to send a message 
	private JTextField messageArea = new JTextField(50) ; 
	private JButton send = new JButton("Send message") ; 
	
	
	
	public ChatGUI(String senderUsername, String receiverUsername) {
		// Define sender and receiver of conversation
		sender = senderUsername ; 
		receiver = receiverUsername ;
		
		// Define element of table 
		senders = GUIManager.getSenders(sender, receiver) ; 
		receivers = GUIManager.getReceivers(sender, receiver) ; 
		dates = GUIManager.getDates(sender, receiver) ; 
		messages = GUIManager.getMessages(sender, receiver) ; 		
		
		// Main Frame
		chatFrame = new JFrame("~ MessengIR ~ CONVERSATION") ; 
		
		// Top part to write an introduction sentence and add a button to go back to home page
		welcome = new JLabel(sender + ", you are talking with " + receiver, SwingConstants.CENTER) ; 
		welcome.setFont(new Font("Century Gothic", Font.BOLD, 16));
        topPanel = new JPanel() ; 
        topPanel.setBackground(new Color(161,236,236));
        topPanel.add(welcome) ; 
        
        
        // Middle part to display history of conversation
        // Construct table 
        for (int i=0; i<senders.length; i++) {
        	history[i][0] = senders[i] ; 
        	history[i][1] = receivers[i] ; 
        	history[i][2] = dates[i] ; 
        	history[i][3] = messages[i] ; 
        }
        
        // Visualize history
        historyTable = new JTable(history, header) ; 
        historyTable.getColumn("Sender").setPreferredWidth(30);
        historyTable.getColumn("Receiver").setPreferredWidth(30);
        historyTable.getColumn("Date").setPreferredWidth(30);
        historyTable.getColumn("Message").setPreferredWidth(100);
        scrollPane = new JScrollPane(historyTable) ; 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
        centerPanel.add(scrollPane, BorderLayout.CENTER) ; 
        
        // Bottom part to display an area to enter the message to send and the button
		// Button to go back to home page
        back.setPreferredSize(new Dimension(150,30));
        back.setMinimumSize(new Dimension(150,30));
        back.setMaximumSize(new Dimension(150, 30));
        back.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				chatFrame.setVisible(false);
        				GUIManager.switchToHomePage(sender) ; 
                  }
                }
              );
        // Button to send the message 
        send.setPreferredSize(new Dimension(150,30));
        send.setMinimumSize(new Dimension(150,30));
        send.setMaximumSize(new Dimension(150, 30));
        send.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				String message = messageArea.getText() ; 
        				GUIManager.sendMessage(receiver, message) ; 
        				GUIManager.switchToChat(sender, receiver);
        			}
        		});
        Box bottom = Box.createHorizontalBox() ; 
        bottom.add(messageArea) ; 
        bottom.add(send); 
        bottom.add(back) ; 
        bottomPanel = new JPanel() ; 
        bottomPanel.add(bottom) ; 
        
        // Main frame 
        chatFrame.setLayout(new BorderLayout());
        // Add panels to the frame 
        chatFrame.add(topPanel, BorderLayout.PAGE_START) ; 
        chatFrame.add(centerPanel, BorderLayout.CENTER) ; 
        chatFrame.add(bottomPanel, BorderLayout.PAGE_END) ; 
        
        // Set default close operation
        chatFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Set appearance 
        chatFrame.pack() ; 
        chatFrame.setLocationRelativeTo(null) ;
        chatFrame.setVisible(true) ; 
        chatFrame.setExtendedState(JFrame.NORMAL) ; 
        
	}

}
