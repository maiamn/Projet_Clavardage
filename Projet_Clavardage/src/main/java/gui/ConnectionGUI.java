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
	
	public static String username ; 
	
	public ConnectionGUI(String choosenUsername) {
		// Define username 
		username = choosenUsername ; 
		
		// Main Frame
		connectionFrame = new JFrame("~ MessengIR ~ Welcome on the chat app MessengIR, " + username + "!") ;
		
		// Top part to write the welcome sentence
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
		topPanel.setBackground(new Color(161,236,236));
		welcome.setFont(new Font("Century Gothic", Font.BOLD, 16));
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

		            new ConnectionGUI(username);
			 }
		 }) ;
	}
}