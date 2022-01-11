package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePageGUI {
	// Attributes
	private JFrame homePageFrame ; 
	
	private JPanel topPanel; 
	private JPanel centerPanel; 
	
	private final JLabel welcome ;  
	private final JButton connectedUsers = new JButton("See connected users") ;
	private final JButton sendMessage = new JButton("Send a message") ;
	private final JButton histories = new JButton ("See conversations") ; 
	private final JButton changeUsername = new JButton("Change my username") ; 
	private final JButton disconnection = new JButton("Disconnection") ; 
	
	public static String username ; 
	
	
	// Constructor 
	public HomePageGUI(String choosenUsername) {
		// Define username 
		username = choosenUsername ; 
		
		// Main Frame
		homePageFrame = new JFrame("~ MessengIR ~ Welcome to home page!") ;
		
		// Top part to write the welcome sentence
		welcome = new JLabel("What do you want to do on MessengIR, " + username + "?") ; 
		welcome.setFont(new Font("Century Gothic", Font.BOLD, 16));
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
		topPanel.setBackground(new Color(161,236,236));
		topPanel.add(welcome);
		
		// Center part to choose what to do
        // Connected Users
		connectedUsers.setPreferredSize(new Dimension(200,50));
        connectedUsers.setMinimumSize(new Dimension(200,50));
        connectedUsers.setMaximumSize(new Dimension(200,50));
        connectedUsers.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				homePageFrame.setVisible(false);
        				GUIManager.switchToConnectedUsers(username) ; 
                 }
                }
              );
      
        // Change Username 
        changeUsername.setPreferredSize(new Dimension(200,50));
        changeUsername.setMinimumSize(new Dimension(200,50));
        changeUsername.setMaximumSize(new Dimension(200,50));
        changeUsername.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				homePageFrame.setVisible(false);
        				GUIManager.switchToChangeUsername(username) ; 
                  }
                }
              );
        
        // Send Message
        sendMessage.setPreferredSize(new Dimension(200,50));
        sendMessage.setMinimumSize(new Dimension(200,50));
        sendMessage.setMaximumSize(new Dimension(200,50));
        sendMessage.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				homePageFrame.setVisible(false);
        				GUIManager.switchToSendMessage(username) ; 
                  }
                }
              );
        
        // See conversations
        histories.setPreferredSize(new Dimension(200,50));
        histories.setMinimumSize(new Dimension(200,50));
        histories.setMaximumSize(new Dimension(200,50));
        histories.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				homePageFrame.setVisible(false);
        				GUIManager.switchToSendMessage(username) ; 
                  }
                }
              );
        
        // Disconnection
        disconnection.setPreferredSize(new Dimension(200,50));
        disconnection.setMinimumSize(new Dimension(200,50));
        disconnection.setMaximumSize(new Dimension(200,50));
        disconnection.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				homePageFrame.setVisible(false);
        				GUIManager.switchToDisconnection(username) ; 
                  }
                }
              );
        
		Box options = Box.createVerticalBox() ; 
		options.add(connectedUsers) ; 
		options.add(sendMessage);
		options.add(histories);
		options.add(changeUsername) ; 
		options.add(disconnection) ; 
		centerPanel = new JPanel() ; 
		centerPanel.add(options) ; 
		centerPanel.setBorder(BorderFactory.createTitledBorder("What to do?"));


		
		// Main Frame 
        //authentificationFrame.setSize(widthWindow, heightWindow);
		homePageFrame.setLayout(new BorderLayout());
		// add panels to frame
		homePageFrame.add(topPanel, BorderLayout.PAGE_START) ; 
		homePageFrame.add(centerPanel, BorderLayout.CENTER) ; 
		
		// Set default close operation
		homePageFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;
        // Set appearance
		homePageFrame.pack() ; 
		homePageFrame.setLocationRelativeTo(null) ; 
		homePageFrame.setVisible(true) ; 
		homePageFrame.setExtendedState(JFrame.NORMAL) ; 

	}

}