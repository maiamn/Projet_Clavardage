package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConnectionGUI {
	private JFrame connectionFrame ; 
	
	private JPanel topPanel; 
	private JPanel centerPanel; 
	
	private final JLabel welcome = new JLabel("You can connect to the app with this username or go back to choose another username.") ; 
	private final JButton connect = new JButton("Connect") ;
	private final JButton back = new JButton("Choose another unsername") ;
	
	public String username ; 
	
	public ConnectionGUI(String choosenUsername) {
		// Define username 
		username = choosenUsername ; 
		
		// Main Frame
		connectionFrame = new JFrame("Welcome on the chat app MessengIR, " + username + "!") ;
		
		// Top part to write the welcome sentence
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
		topPanel.add(welcome);
		
		// Center part to choose what to do
		// Add action to Connect button
        connect.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				GUIManager.connection(username);
         		        connectionFrame.setVisible(false);
        		    	GUIManager.switchToHomePage(username) ; 
                  }
                }
              );
        
        // Add action to back button
        back.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				connectionFrame.setVisible(false);
        				GUIManager.switchToAuthentification(); 
                  }
                }
              );
		
		Box options = Box.createHorizontalBox(); 
		options.add(connect) ; 
		options.add(back);
		centerPanel = new JPanel() ; 
		centerPanel.add(options) ; 


		
		// Main Frame 
        //authentificationFrame.setSize(widthWindow, heightWindow);
		connectionFrame.setLayout(new BorderLayout());
		// add panels to frame
		connectionFrame.add(topPanel, BorderLayout.PAGE_START) ; 
		connectionFrame.add(centerPanel, BorderLayout.CENTER) ; 
		
		// Set default close operation
		connectionFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;
        // Set appearance
		connectionFrame.pack() ; 
		connectionFrame.setLocationRelativeTo(null) ; 
		connectionFrame.setVisible(true) ; 
		connectionFrame.setExtendedState(JFrame.NORMAL) ; 

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