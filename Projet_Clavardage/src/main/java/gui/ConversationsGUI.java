package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ConversationsGUI {
	// Attributes
	private JFrame conversationsFrame ; 
	// Top part
	private JPanel topPanel;
	private final JLabel welcome ;
	// Main part 
	ArrayList<String> interlocutors ; 
	private JPanel centerPanel ; 
	final JComboBox<String> jComboBox ; 
	private JButton choose = new JButton("Choose") ; 
	// Bottom part 
	private JPanel bottomPanel ; 
	private JButton back = new JButton("Back to home page") ; 
	
	public static String username ; 
	
	// Constructor
	public ConversationsGUI(String choosenUsername) {
		// Define username 
		username = choosenUsername ; 
		
		// Main frame
		conversationsFrame = new JFrame("~ MessengIR ~ See all conversations ") ; 
		
		// Top part to write the welcome sentence 
		welcome = new JLabel("Which conversation do you want to see?") ; 
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
		topPanel.setBackground(new Color(161,236,236));
		welcome.setFont(new Font("Century Gothic", Font.BOLD, 16));
		topPanel.add(welcome);
		
		// Get all users with whom the user has already spoken
		interlocutors = GUIManager.getInterlocutors(username) ;
		String[] interlocutorsArray = new String [interlocutors.size()]; 
		for (int k=0; k<interlocutors.size(); k++) {
			interlocutorsArray[k] = interlocutors.get(k) ; 
		}
		
		// Drop down menu
		jComboBox = new JComboBox<String>(interlocutorsArray) ; 
		// Button to switch to the history with this person 
        choose.setPreferredSize(new Dimension(150,30));
        choose.setMinimumSize(new Dimension(150,30));
        choose.setMaximumSize(new Dimension(150,30));
        choose.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				conversationsFrame.setVisible(false);
        				String interlocutor = jComboBox.getItemAt(jComboBox.getSelectedIndex()); 
        				GUIManager.switchToHistory(username, interlocutor) ; 
                  }
                }
              );
        
		Box options = Box.createVerticalBox() ; 
		options.add(jComboBox) ; 
		options.add(choose);
        centerPanel = new JPanel() ;
        centerPanel.add(options) ; 
        
        // Bottom part to display an area to enter the message to send and the button
		// Button to go back to home page
        back.setPreferredSize(new Dimension(150,30));
        back.setMinimumSize(new Dimension(150,30));
        back.setMaximumSize(new Dimension(150, 30));
        back.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				conversationsFrame.setVisible(false);
        				GUIManager.switchToHomePage(username) ; 
                  }
                }
              );
        bottomPanel = new JPanel() ; 
        bottomPanel.add(back) ; 
		
		
		// Main Frame 
		conversationsFrame.setLayout(new BorderLayout());
		// Add panels to frame
		conversationsFrame.add(topPanel, BorderLayout.PAGE_START) ; 
		conversationsFrame.add(centerPanel, BorderLayout.CENTER) ;
		conversationsFrame.add(bottomPanel, BorderLayout.PAGE_END) ; 
		
		// Set default close operation
		conversationsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;
        // Set appearance
		conversationsFrame.pack() ; 
		conversationsFrame.setLocationRelativeTo(null) ; 
		conversationsFrame.setVisible(true) ; 
		conversationsFrame.setExtendedState(JFrame.NORMAL) ; 
		
	}
	
}
