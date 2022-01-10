package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList; 

public class SendMessageGUI {
	private JFrame sendMessageFrame ; 
	
	private JPanel topPanel; 
	private JPanel centerPanel ;
	private JPanel bottomPanel ; 
	
	private final JLabel welcome = new JLabel("Who do you want to talk with?") ;
	
	private final JLabel check = new JLabel ("You can check if a user is online!") ; 
	private final JTextField search = new JTextField(30) ; 
	private final JButton isReachable = new JButton("Check!") ; 
	
	private final String other = "Otherwise you can check again the connected users or go back to home page!" ; 
	private final JButton connectedUsers = new JButton("See connected users") ; 
	private final JButton homePage = new JButton ("Back to home page") ; 
	
	public static JLabel errorMessage ; 
	
	public static String username ;
	
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
		
		// Button to check a username 
        isReachable.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				// Get the username choosen by the user
        				String name = search.getText() ; 
        				ArrayList<String> connectedUsers = GUIManager.getAllConnectedUsers() ;
        		  		// Check if the username corresponds to a connected users
        		  		boolean correct = GUIManager.userReachable(connectedUsers, name) ; 
        		  		if(!correct) {
        		  			errorMessage = new JLabel("<html>This user is unreachable! "
        		  					+ "<br> You can check in the connected users list who is online! </html>", SwingConstants.CENTER) ; 
        		  			// Pop up window
        		  			JOptionPane.showMessageDialog(sendMessageFrame, errorMessage);
        		  		} 
        		  		else {
        		  			if (name.equals(username)) {
            		  			errorMessage = new JLabel("<html>You cannot talk to yourself! </html>", SwingConstants.CENTER) ; 
            		  			// Pop up window
            		  			JOptionPane.showMessageDialog(sendMessageFrame, errorMessage);
        		  			} else {
	        		  			System.out.println("GUI : sender = " + username + " receiver = " + name);
	        		  			GUIManager.switchToChat(username, name) ; 
	        		  			sendMessageFrame.setVisible(false);
        		  			}
        		  		}
                  }
                }
              );
		
		Box checking = Box.createVerticalBox() ;
		checking.add(check) ; 
		checking.add(search) ; 
		checking.add(isReachable) ; 
		centerPanel = new JPanel() ; 
		centerPanel.add(checking) ; 
		centerPanel.setBorder(BorderFactory.createTitledBorder("Check is a user is reachable:"));


        // Bottom part to go back to home page or to check connected users
        connectedUsers.setPreferredSize(new Dimension(150,30));
        connectedUsers.setMinimumSize(new Dimension(150,30));
        connectedUsers.setMaximumSize(new Dimension(150,30));
        connectedUsers.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				sendMessageFrame.setVisible(false);
        				GUIManager.switchToConnectedUsers(username) ; 
                  }
                }
              );
        
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
        
		Box options = Box.createHorizontalBox() ; 
		options.add(connectedUsers) ; 
		options.add(homePage);
		bottomPanel = new JPanel() ; 
		bottomPanel.add(options) ; 
		bottomPanel.setBorder(BorderFactory.createTitledBorder(other));
		
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
	

    	
	public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() { 
			 public void run() {
		            try {
		                UIManager.setLookAndFeel(
		                        UIManager.getSystemLookAndFeelClassName());
		            } catch (Exception e) {
		                e.printStackTrace();
		            }

		            new SendMessageGUI(username);
			 }
		 }) ;
	}
}
