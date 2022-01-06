package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatGUI {
	private JFrame chatFrame ; 
	
	private JPanel topPanel ; 
	//private JPanel centerPanel ; 
	private JPanel bottomPanel ; 
	
	private JLabel welcome ; 
	private JButton back = new JButton("Back to home page") ; 
	
	//final JTable history ; 
	
	private JTextField messageArea = new JTextField(50) ; 
	private JButton send = new JButton("Send message") ; 
	
	public static String sender ; 
	public static String receiver ; 
	
	public ChatGUI(String senderUsername, String receiverUsername) {
		// Define sender and receiver of conversation
		sender = senderUsername ; 
		receiver = receiverUsername ; 
		
		// Main Frame
		chatFrame = new JFrame("~ MessengIR ~ CONVERSATION") ; 
		
		// Top part to write an introduction sentence and add a button to go back to home page
		welcome = new JLabel(sender + ", you are talking with " + receiver, SwingConstants.CENTER) ; 
		welcome.setFont(new Font("Monospace", Font.BOLD, 16));
        topPanel = new JPanel() ; 
        topPanel.add(welcome) ; 
        
        
        // Middle part to display history of conversation
        
        
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
        				chatFrame.setVisible(false);
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
        //chatFrame.add(centerPanel, BorderLayout.CENTER) ; 
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
