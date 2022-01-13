package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator; 

public class SendMessageGUI { 
	// Attributes 
	private JFrame sendMessageFrame ; 
	
	private JPanel topPanel; 
	private JPanel centerPanel ;
	private JPanel bottomPanel ; 
	
	private final JLabel welcome = new JLabel("Who do you want to talk with?") ;
	
	ArrayList<String> connectedUsers ; 
	final JComboBox<String> jComboBox ; 
	private JButton choose = new JButton("Choose") ; 
	private final JButton isReachable = new JButton("Check!") ; 

	private final JButton homePage = new JButton ("Back to home page") ; 
	
	public static String username ;
	
	
	// Constructor 
	public SendMessageGUI(String choosenUsername) {
		// Define username 
		username = choosenUsername ; 
		
		// Main Frame
		sendMessageFrame = new JFrame("~ MessengIR ~ Send a message to another user online!") ;
		
		// Top part to write the welcome sentence
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
		topPanel.setBackground(new Color(161,236,236));
		welcome.setFont(new Font("Century Gothic", Font.BOLD, 16));
		topPanel.add(welcome);
		
		// Center part to check if a user is online
		
		// Get all connected users
		connectedUsers = GUIManager.getAllConnectedUsers() ; 
		connectedUsers.add("Mateo") ;
		connectedUsers.add("Celia") ; 
		connectedUsers.add("Clarisse") ; 
		connectedUsers.add("Maia") ; 
		// Remove our own username
        // Creating iterator object
        Iterator<String> itr = connectedUsers.iterator();
        // Remove element username
        // Iterator.remove()
        while (itr.hasNext()) {
            String x = itr.next();
            if (x.equals(username)) {
            	itr.remove();
            }      
        }
		String[] connectedUsersArray = new String [connectedUsers.size()]; 
		for (int k=0; k<connectedUsers.size(); k++) {
			connectedUsersArray[k] = connectedUsers.get(k) ; 
		}
		
		// Drop down menu
		jComboBox = new JComboBox<String>(connectedUsersArray) ; 
		// Button to switch to the history with this person 
        choose.setPreferredSize(new Dimension(150,30));
        choose.setMinimumSize(new Dimension(150,30));
        choose.setMaximumSize(new Dimension(150,30));
        choose.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				sendMessageFrame.setVisible(false);
        				String user = jComboBox.getItemAt(jComboBox.getSelectedIndex()); 
        				GUIManager.switchToChat(username, user) ; 
                  }
                }
              );
        
		Box options = Box.createVerticalBox() ; 
		options.add(jComboBox) ; 
		options.add(choose);
        centerPanel = new JPanel() ;
        centerPanel.add(options) ; 
        
        // Bottom panel
        homePage.setPreferredSize(new Dimension(150,30));
        homePage.setMinimumSize(new Dimension(150,30));
        homePage.setMaximumSize(new Dimension(150,30));
        homePage.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				sendMessageFrame.setVisible(false);
        				GUIManager.switchToHomePage(username) ; 
                  }
                }
              );
        
		bottomPanel = new JPanel() ; 
		bottomPanel.add(homePage) ; 
		
		// Main Frame 
        //authentificationFrame.setSize(widthWindow, heightWindow);
		sendMessageFrame.setLayout(new BorderLayout());
		// add panels to frame
		sendMessageFrame.add(topPanel, BorderLayout.PAGE_START) ; 
		sendMessageFrame.add(centerPanel, BorderLayout.CENTER) ; 
		sendMessageFrame.add(bottomPanel, BorderLayout.PAGE_END) ; 
		
		// Set default close operation
		sendMessageFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;
		// Set default button
        sendMessageFrame.getRootPane().setDefaultButton(isReachable);
        // Set appearance
		sendMessageFrame.pack() ; 
		sendMessageFrame.setLocationRelativeTo(null) ; 
		sendMessageFrame.setVisible(true) ; 
		sendMessageFrame.setExtendedState(JFrame.NORMAL) ; 

	}
	
}
