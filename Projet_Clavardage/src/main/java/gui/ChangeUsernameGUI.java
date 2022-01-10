package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChangeUsernameGUI {
	private JFrame changeUsernameFrame ; 
	
	private JPanel topPanel; 
	private JPanel leftCenterPanel ;
	private JPanel rightCenterPanel ; 
	private JPanel bottomPanel ; 
	
	private final JLabel welcome = new JLabel("Here you can change your username!") ; 
	private final JLabel actual = new JLabel ("Your actual username is:") ; 
	private final JLabel actualUsername ; 
	private final JLabel next = new JLabel ("You can choose your new username here:") ; 
	private final JTextField newUsername = new JTextField(30) ; 
	private final JButton submit = new JButton("Submit") ; 
	private final JButton back = new JButton ("Back to home page") ;

	private JLabel errorMessage ; 
	
	public static String username ; 
	
	public ChangeUsernameGUI(String choosenUsername) {
		// Define username 
		username = choosenUsername ; 
		
		// Main Frame
		changeUsernameFrame = new JFrame("~ MessengIR ~ You want to change your username!") ;
		
		// Top part to write the welcome sentence
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
		topPanel.setBackground(new Color(161,236,236));
		welcome.setFont(new Font("Century Gothic", Font.BOLD, 16));
		topPanel.add(welcome);
		
		// Left part to write the actual username 
		Box actualVersion = Box.createVerticalBox() ;
		actualVersion.add(actual) ; 
		actualUsername = new JLabel(username) ; 
		actualVersion.add(actualUsername);
		leftCenterPanel = new JPanel() ; 
		leftCenterPanel.add(actualVersion) ; 
		leftCenterPanel.setBorder(BorderFactory.createTitledBorder("Actual username"));
		
		
		// Button to submit a new username 
        submit.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				// Get the username choosen by the user
        		  		String name = newUsername.getText() ; 
        		  		// Check if the username is valid 
        		  		boolean correct = GUIManager.checkUsername(name) ; 
        		  		if(!correct) {
        		  			errorMessage = new JLabel("<html>Your username is uncorrect! "
        		  					+ "<br> Check if there is no special character and that it contains between 1 and 30 characters!"
        		  					+ "<br> If it's not the case, it means that it is already used so choose another one!</html>", SwingConstants.CENTER) ; 
        		  			// Pop up window
        		  			JOptionPane.showMessageDialog(changeUsernameFrame, errorMessage);
        		  		} 
        		  		else {
        		  			GUIManager.deletePreviousUsername(username) ; 
        		  			GUIManager.switchToConnection(name) ; 
        		  			changeUsernameFrame.setVisible(false);
        		  		}
                  }
                }
              );
        
		// Right part to enter username
		Box newVersion = Box.createVerticalBox() ; 
		newVersion.add(next) ; 
		newVersion.add(newUsername) ; 
		newVersion.add(submit) ; 
		rightCenterPanel = new JPanel() ; 
		rightCenterPanel.add(newVersion) ; 
		rightCenterPanel.setBorder(BorderFactory.createTitledBorder("Choose new username"));

        // Bottom part to go back to home page
        back.setPreferredSize(new Dimension(150,30));
        back.setMinimumSize(new Dimension(150,30));
        back.setMaximumSize(new Dimension(150,30));
        back.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				changeUsernameFrame.setVisible(false);
        				GUIManager.switchToHomePage(username) ; 
                  }
                }
              );
		bottomPanel = new JPanel(new BorderLayout()) ; 
		bottomPanel.add(back) ; 
		
		// Main Frame 
        //authentificationFrame.setSize(widthWindow, heightWindow);
		changeUsernameFrame.setLayout(new BorderLayout());
		// add panels to frame
		changeUsernameFrame.add(topPanel, BorderLayout.PAGE_START) ; 
		changeUsernameFrame.add(leftCenterPanel, BorderLayout.LINE_START) ; 
		changeUsernameFrame.add(rightCenterPanel, BorderLayout.LINE_END) ; 
		changeUsernameFrame.add(bottomPanel, BorderLayout.PAGE_END) ; 
		
		// Set default close operation
		changeUsernameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;
        // Set appearance
		changeUsernameFrame.pack() ; 
		changeUsernameFrame.setLocationRelativeTo(null) ; 
		changeUsernameFrame.setVisible(true) ; 
		changeUsernameFrame.setExtendedState(JFrame.NORMAL) ; 

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

		            new ChangeUsernameGUI(username);
			 }
		 }) ;
	}
}
