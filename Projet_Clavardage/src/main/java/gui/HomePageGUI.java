package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePageGUI {
	private JFrame homePageFrame ; 
	
	private JPanel topPanel; 
	private JPanel centerPanel; 
	
	private final JLabel welcome ;  
	private final JButton connectedUsers = new JButton("See connected users") ;
	private final JButton sendMessage = new JButton("Send a message") ;
	private final JButton changeUsername = new JButton("Change my username") ; 
	private final JButton disconnection = new JButton("Disconnection") ; 
	
	public String username ; 
	
	public HomePageGUI(String choosenUsername) {
		// Define username 
		username = choosenUsername ; 
		
		// Main Frame
		homePageFrame = new JFrame("~ MessengIR ~") ;
		
		// Top part to write the welcome sentence
		welcome = new JLabel("What do you want to do on MessengIR, " + username + "?") ; 
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
		topPanel.add(welcome);
		
		// Center part to choose what to do
        // Connected Users
		connectedUsers.setPreferredSize(new Dimension(500,100));
        connectedUsers.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				homePageFrame.setVisible(false);
        				GUIManager.switchToConnectedUsers(username) ; 
                 }
                }
              );
      
        // Change Username 
        changeUsername.setPreferredSize(new Dimension(500,100));
        changeUsername.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				homePageFrame.setVisible(false);
        				GUIManager.switchToChangeUsername(username) ; 
                  }
                }
              );
        
        // Send Message
        sendMessage.setPreferredSize(new Dimension(500,100));
        sendMessage.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				homePageFrame.setVisible(false);
        				GUIManager.switchToSendMessage() ; 
                  }
                }
              );
        
        // Disconnection
        disconnection.setPreferredSize(new Dimension(500,100));
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
	

    	
	public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() { 
			 public void run() {
		            try {
		                UIManager.setLookAndFeel(
		                        UIManager.getSystemLookAndFeelClassName());
		            } catch (Exception e) {
		                e.printStackTrace();
		            }

		            new AuthentificationGUI();
			 }
		 }) ;
	}
}
//package gui;
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//public class ConnectionGUI implements ActionListener {
//	static String username ;
//    JFrame connectionFrame;
//    JPanel connectionPanel;
//    JLabel welcome;
//    JButton connect;
//    
//    int widthComponents ; 
//    int heightComponents ; 
//
//    public ConnectionGUI(String choosenUsername) {
//    	// Set username 
//    	username = choosenUsername ; 
//    	
//        //Create and set up the window.
//        connectionFrame = new JFrame("Welcome to chat app, " + username + "!");
//        connectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        
//        connectionFrame.pack();
//        
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize() ; 
//        int widthWindow = screenSize.width*1/2 ;
//        int heightWindow = screenSize.height*1/2 ;
//        this.widthComponents = widthWindow*1/4 ; 
//        this.heightComponents = heightWindow*1/4 ; 
//        connectionFrame.setSize(widthWindow, heightWindow);
//        connectionFrame.setLocationRelativeTo(null); 
//
//        //Create and set up the panel.
//        connectionPanel = new JPanel(new GridLayout(2, 1, 10, 10));
//
//        //Add the widgets.
//        addWidgets();
//        
//        //Set the default button.
//        connectionFrame.getRootPane().setDefaultButton(connect);
//
//        //Add the panel to the window.
//        connectionFrame.getContentPane().add(connectionPanel, BorderLayout.CENTER);
//
//        //Display the window.
//        connectionFrame.setVisible(true);
//    }
//
//    /**
//     * Create and add the widgets.
//     */
//    private void addWidgets() {
//        //Create widgets.
//        welcome = new JLabel(username + " is a correct username! \n You can connect yourself to chat app!", SwingConstants.CENTER);
//        connect = new JButton("Connection");
//
//        //Listen to events from the Convert button.
//        connect.addActionListener(this);
//
//        // Set size 
//        welcome.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
//        connect.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
//        
//        //Add the widgets to the container.
//        connectionPanel.add(welcome);
//        connectionPanel.add(connect); 
//
//        welcome.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//        connect.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//    }
//
//    public void actionPerformed(ActionEvent event) {
//    	GUIManager.connection(username);
//        connectionFrame.setVisible(false);
//    	GUIManager.switchToHomePage(username) ; 
//    }
//
//
//    private static void createAndShowGUI() {
//        //Make sure we have nice window decorations.
//        JFrame.setDefaultLookAndFeelDecorated(true);
//
//        ConnectionGUI connection = new ConnectionGUI(username);
//    }
//
//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
//}




//package gui;
//
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
//import java.awt.event.*;
//
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//public class HomePageGUI {
//	static String username ;
//    JFrame homePageFrame;
//    JPanel homePagePanel;
//    JLabel todo;
//    JButton connectedUsers ;
//    JButton changeUsername ; 
//    JButton sendMessage ; 
//    JButton disconnection ; 
//    
//    int widthComponents ; 
//    int heightComponents ; 
//
//    public HomePageGUI(String choosenUsername) {
//    	// Define username 
//    	this.username = choosenUsername ; 
//    	
//        //Create and set up the window.
//        homePageFrame = new JFrame("Welcome to chat app, " + username + "!");
//        homePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        
//        homePageFrame.pack();
//        
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize() ; 
//        int widthWindow = screenSize.width*1 ;
//        int heightWindow = screenSize.height*1 ;
//        this.widthComponents = widthWindow*1/4 ; 
//        this.heightComponents = heightWindow*1/4 ; 
//        homePageFrame.setSize(widthWindow, heightWindow);
//        homePageFrame.setLocationRelativeTo(null); 
//
//        //Create and set up the panel.
//        homePagePanel = new JPanel(new GridLayout(5, 1, 10, 10));
//
//        //Add the widgets.
//        addWidgets();
//
//        //Add the panel to the window.
//        homePageFrame.getContentPane().add(homePagePanel, BorderLayout.CENTER);
//
//        //Display the window.
//        homePageFrame.setVisible(true);
//    }
//
//    /**
//     * Create and add the widgets.
//     */
//    private void addWidgets() {
//        //Create widgets.
//    	
//    	// Label
//        JLabel todo = new JLabel("What do you want to do?");
//        
//        // Connected Users
//        connectedUsers = new JButton("See connected users");
//        connectedUsers.addActionListener(
//        		new ActionListener() {
//        			public void actionPerformed(ActionEvent e) {
//        				homePageFrame.setVisible(false);
//        				GUIManager.switchToConnectedUsers(username) ; 
//                  }
//                }
//              );
//        
//        // Change Username 
//        changeUsername = new JButton("Change username") ; 
//        changeUsername.addActionListener(
//        		new ActionListener() {
//        			public void actionPerformed(ActionEvent e) {
//        				homePageFrame.setVisible(false);
//        				GUIManager.switchToChangeUsername(username) ; 
//                  }
//                }
//              );
//        
//        // Send Message
//        sendMessage = new JButton("Send message") ; 
//        sendMessage.addActionListener(
//        		new ActionListener() {
//        			public void actionPerformed(ActionEvent e) {
//        				homePageFrame.setVisible(false);
//        				GUIManager.switchToSendMessage() ; 
//                  }
//                }
//              );
//        
//        // Disconnection
//        disconnection = new JButton("Disconnection") ; 
//        disconnection.addActionListener(
//        		new ActionListener() {
//        			public void actionPerformed(ActionEvent e) {
//        				homePageFrame.setVisible(false);
//        				GUIManager.switchToDisconnection(username) ; 
//                  }
//                }
//              );
//
//
//
//        // Set size 
//        connectedUsers.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
//        changeUsername.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
//        sendMessage.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
//        disconnection.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
//        
//        //Add the widgets to the container.
//        homePagePanel.add(connectedUsers); 
//        homePagePanel.add(changeUsername); 
//        homePagePanel.add(sendMessage) ; 
//        homePagePanel.add(disconnection) ; 
//
//        connectedUsers.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//        changeUsername.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//        sendMessage.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//        disconnection.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//    }
//
//
//    private static void createAndShowGUI() {
//        //Make sure we have nice window decorations.
//        JFrame.setDefaultLookAndFeelDecorated(true);
//
//        HomePageGUI homePage = new HomePageGUI(username);
//    }
//
//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
//
//}
