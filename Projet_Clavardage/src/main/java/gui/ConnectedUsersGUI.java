package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList; 

public class ConnectedUsersGUI {
	// Attributes 
	private JFrame connectedUsersFrame ; 
	
	private JPanel topPanel; 
	private JPanel centerPanel; 
	private JPanel bottomPanel ; 
	private JButton back = new JButton("Back to home page") ;
	private JButton refresh = new JButton("Refresh") ; 
	private JButton send = new JButton("Send a message") ; 
	
	private final JLabel welcome ;
	
	protected ArrayList<String> allUsers = new ArrayList<String>() ; 
	public int nbUsers = GUIManager.getAllConnectedUsers().size() ; 
	protected Object usersTable[][] = new Object[nbUsers][1]; 
	final Object header[] = {"Connected users"} ; 
	final JTable table ;
	JScrollPane scrollPane ; 
	
	public static String username ; 
	
	// Constructor 
	public ConnectedUsersGUI(String choosenUsername) {
		// Define username 
		username = choosenUsername ; 
		
		// Main Frame
		connectedUsersFrame = new JFrame("~ MessengIR ~ " + username + ", you can talk with all these people!") ;
		
		// Top part to write the welcome sentence
		welcome = new JLabel("Who do you want to talk with?") ; 
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
		topPanel.setBackground(new Color(161,236,236));
		welcome.setFont(new Font("Century Gothic", Font.BOLD, 16));
		topPanel.add(welcome);
		
		// Get all usernames 
		allUsers = GUIManager.getAllConnectedUsers() ;
        
		// Construct Table of usernames and buttons
		for (int k=0; k<nbUsers; k++) {
			usersTable[k][0] = new String(allUsers.get(k)) ;
		}
		
		// Center part to visualize all connected users 
		table = new JTable(usersTable, header);
	    scrollPane = new JScrollPane(table);
	    centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
	    centerPanel.add(scrollPane, BorderLayout.CENTER);
	    
		
		// Bottom part to go back to home page, refresh or to send a message
        back.setPreferredSize(new Dimension(150,30));
        back.setMinimumSize(new Dimension(150,30));
        back.setMaximumSize(new Dimension(150,30));
        back.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				connectedUsersFrame.setVisible(false);
        				GUIManager.switchToHomePage(username) ; 
                  }
                }
              );
        
        refresh.setPreferredSize(new Dimension(150,30));
        refresh.setMinimumSize(new Dimension(150,30));
        refresh.setMaximumSize(new Dimension(150,30));
        refresh.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				connectedUsersFrame.setVisible(false);
        				GUIManager.switchToConnectedUsers(username) ; 
                  }
                }
              );
        
        send.setPreferredSize(new Dimension(150,30));
        send.setMinimumSize(new Dimension(150,30));
        send.setMaximumSize(new Dimension(150,30));
        send.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				connectedUsersFrame.setVisible(false);
        				GUIManager.switchToSendMessage(username) ; 
                  }
                }
              );
        
		Box options = Box.createVerticalBox() ; 
		options.add(send) ; 
		options.add(back);
		bottomPanel = new JPanel() ; 
		bottomPanel.add(options) ; 

		// Main Frame 
		connectedUsersFrame.setLayout(new BorderLayout());
		// Add panels to frame
		connectedUsersFrame.add(topPanel, BorderLayout.PAGE_START) ; 
		connectedUsersFrame.add(centerPanel, BorderLayout.CENTER) ;
		connectedUsersFrame.add(bottomPanel, BorderLayout.PAGE_END) ; 
		
		// Set default close operation
		connectedUsersFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;
        // Set appearance
		connectedUsersFrame.pack() ; 
		connectedUsersFrame.setLocationRelativeTo(null) ; 
		connectedUsersFrame.setVisible(true) ; 
		connectedUsersFrame.setExtendedState(JFrame.NORMAL) ; 

	}

}

