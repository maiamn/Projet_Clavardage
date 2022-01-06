package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList; 

public class ConnectedUsersGUI {
	private JFrame connectedUsersFrame ; 
	
	private JPanel topPanel; 
	private JPanel centerPanel; 
	private JPanel bottomPanel ; 
	private JButton back = new JButton("Back to home page") ; 
	
	private final JLabel welcome ;
	
	protected ArrayList<String> allUsers = new ArrayList<String>() ; 
	public int nbUsers = GUIManager.getAllConnectedUsers().size() ; 
	protected Object usersTable[][] = new Object[nbUsers][1]; 
	final Object header[] = {"Connected users"} ; 
	final JTable table ;
	JScrollPane scrollPane ; 
	
	public static String username ; 
	
	public ConnectedUsersGUI(String choosenUsername) {
		// Define username 
		username = choosenUsername ; 
		
		// Main Frame
		connectedUsersFrame = new JFrame("~ MessengIR ~ " + username + ", you can talk with all these people!") ;
		
		// Top part to write the welcome sentence
		welcome = new JLabel("Who do you want to talk with?") ; 
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
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
	    
		
		// Bottom part to go back to home page 
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
        
		bottomPanel = new JPanel(new BorderLayout()) ; 
		bottomPanel.add(back) ; 


		// Main Frame 
        //authentificationFrame.setSize(widthWindow, heightWindow);
		connectedUsersFrame.setLayout(new BorderLayout());
		// add panels to frame
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
	

    	
	public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() { 
			 public void run() {
		            try {
		                UIManager.setLookAndFeel(
		                        UIManager.getSystemLookAndFeelClassName());
		            } catch (Exception e) {
		                e.printStackTrace();
		            }

		            new ConnectedUsersGUI(username);
			 }
		 }) ;
	}
}
//package gui;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.Toolkit;
//
//public class ConnectedUsersGUI {
//	public static String username ; 
//	JFrame connectedUsersFrame ; 
//	JPanel connectedUsersPanel ; 
//	JScrollPane scrollPane ; 
//	
//	final Object row[][] = {{"1","test1"}, {"2","test2"}, {"3","test3"}, {"4","test4"}};
//	final Object header[] = {"ID", "Usernames"};
//	final JTable table ;
//	
//	public ConnectedUsersGUI(String choosenUsername) {
//		// Define the actual username
//		username = choosenUsername ; 
//		
//		// Create and set up the window 
//		connectedUsersFrame = new JFrame(username + ", you can talk with all these people!") ; 
//		connectedUsersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		connectedUsersFrame.pack() ; 
//		
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize() ; 
//        int widthWindow = screenSize.width*1 ;
//        int heightWindow = screenSize.height*1 ;
//        connectedUsersFrame.setSize(widthWindow, heightWindow);
//        connectedUsersFrame.setLocationRelativeTo(null); 
//        
//		connectedUsersFrame.setVisible(true) ; 
//		
//		table = new JTable(row, header);
//	    scrollPane = new JScrollPane(table);
//	    connectedUsersFrame.add(scrollPane, BorderLayout.CENTER);
//		
//	}
//	
//    private static void createAndShowGUI() {
//        //Make sure we have nice window decorations.
//        JFrame.setDefaultLookAndFeelDecorated(true);
//
//        ConnectedUsersGUI connectedUsers = new ConnectedUsersGUI(username);
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
