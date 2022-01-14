package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList; 

public class NewMessagesGUI {
	// Attributes 
	private JFrame newMessagesFrame ; 
	
	private JPanel topPanel; 
	private JPanel centerPanel; 
	private JPanel bottomPanel ; 
	private JButton back = new JButton("Back to home page") ;
	private JButton refresh = new JButton("Refresh") ; 
	
	private final JLabel welcome ;
	
	ArrayList<String> interlocutors ; 
	protected Object usersTable[][] ; 
	final Object header[] = {"Interlocutors", "New messages?"} ; 
	final JTable table ;
	JScrollPane scrollPane ; 
	
	public static String username ; 
	
	// Constructor 
	public NewMessagesGUI(String choosenUsername) {
		// Define username 
		username = choosenUsername ; 
		
		// Main Frame
		newMessagesFrame = new JFrame("~ MessengIR ~ " + username + ", you can talk with all these people!") ;
		
		// Top part to write the welcome sentence
		welcome = new JLabel("Who do you want to talk with?") ; 
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
		topPanel.setBackground(new Color(161,236,236));
		welcome.setFont(new Font("Century Gothic", Font.BOLD, 16));
		topPanel.add(welcome);
		
		// Get all usernames 
		interlocutors = GUIManager.getInterlocutors(username) ; 
        int nbInterlocutors = interlocutors.size() ; 
		usersTable = new Object[nbInterlocutors][2] ; 
		
		// Construct Table of usernames and buttons
		for (int k=0; k<nbInterlocutors; k++) {
			usersTable[k][0] = new String(interlocutors.get(k)) ;
			if (GUIManager.newMessages(username, interlocutors.get(k))) {
				System.out.println("NewMessages GUI : there is new messages ? " + GUIManager.newMessages(username, interlocutors.get(k))) ;
				usersTable[k][1] = "UNREAD MESSAGE(S)" ; 
			} else {
				usersTable[k][1] = "Up to date" ; 
			}
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
        				newMessagesFrame.setVisible(false);
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
        				newMessagesFrame.setVisible(false);
        				GUIManager.switchToNewMessages(username) ; 
                  }
                }
              );
        
		Box options = Box.createVerticalBox() ; 
		options.add(refresh);
		options.add(back);
		bottomPanel = new JPanel() ; 
		bottomPanel.add(options) ; 

		// Main Frame 
		newMessagesFrame.setLayout(new BorderLayout());
		// Add panels to frame
		newMessagesFrame.add(topPanel, BorderLayout.PAGE_START) ; 
		newMessagesFrame.add(centerPanel, BorderLayout.CENTER) ;
		newMessagesFrame.add(bottomPanel, BorderLayout.PAGE_END) ; 
		
		// Set default close operation
		newMessagesFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;
        // Set appearance
		newMessagesFrame.pack() ; 
		newMessagesFrame.setLocationRelativeTo(null) ; 
		newMessagesFrame.setVisible(true) ; 
		newMessagesFrame.setExtendedState(JFrame.NORMAL) ; 

	}

}

