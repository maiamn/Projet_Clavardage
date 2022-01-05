package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AuthentificationGUI {
	private JFrame authentificationFrame ; 
	
	private JPanel topPanel; 
	private JPanel leftCenterPanel ;
	private JPanel rightCenterPanel ; 
	private JPanel bottomPanel ; 
	
	private final JButton submit = new JButton("Submit") ; 
	private final JLabel welcome = new JLabel("Welcome! What's your name?") ; 
	private final JLabel constraint1 = new JLabel("The username must contain between 1 and 30 characters.") ; 
	private final JLabel constraint2 = new JLabel("The username cannot contain special characters") ; 
	private final JTextField username = new JTextField(30) ; 
	
	private JLabel errorMessage ; 
	
	public AuthentificationGUI() {
		// Main Frame
		authentificationFrame = new JFrame("Please enter a username to log in!") ;
		// Top part to write the welcome sentence
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
		topPanel.add(welcome);
		
		// Left part to write constraints 
		Box constraints = Box.createVerticalBox() ; 
		constraints.add(constraint1) ; 
		constraints.add(constraint2);
		leftCenterPanel = new JPanel() ; 
		leftCenterPanel.add(constraints) ; 
		leftCenterPanel.setBorder(BorderFactory.createTitledBorder("Constraints"));
		
		// Right part to enter username
		rightCenterPanel = new JPanel(new GridBagLayout()) ; 
		rightCenterPanel.add(username) ; 
		
		// Bottom part to submit username 
        submit.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				// Get the username choosen by the user
        		  		String name = username.getText() ; 
        		  		// Check if the username is valid 
        		  		boolean correct = GUIManager.checkUsername(name) ; 
        		  		if(!correct) {
        		  			errorMessage = new JLabel("<html>Your username is uncorrect! "
        		  					+ "<br> Check if there is no special character and that it contains between 1 and 30 characters!"
        		  					+ "<br> If it's not the case, it means that it is already used so choose another one!</html>", SwingConstants.CENTER) ; 
        		  			// Pop up window
        		  			JOptionPane.showMessageDialog(authentificationFrame, errorMessage);
        		  		} 
        		  		else {
        		  			GUIManager.switchToConnection(name) ; 
        		   	        authentificationFrame.setVisible(false);
        		  		}
                  }
                }
              );
        
		bottomPanel = new JPanel(new BorderLayout()) ; 
		bottomPanel.add(submit) ; 
		
		// Main Frame 
        //authentificationFrame.setSize(widthWindow, heightWindow);
		authentificationFrame.setLayout(new BorderLayout());
		// add panels to frame
		authentificationFrame.add(topPanel, BorderLayout.PAGE_START) ; 
		authentificationFrame.add(leftCenterPanel, BorderLayout.LINE_START) ; 
		authentificationFrame.add(rightCenterPanel, BorderLayout.LINE_END) ; 
		authentificationFrame.add(bottomPanel, BorderLayout.PAGE_END) ; 
		
		// Set default close operation
		authentificationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;
		// Set default button
        authentificationFrame.getRootPane().setDefaultButton(submit);
        // Set appearance
        authentificationFrame.pack() ; 
        authentificationFrame.setLocationRelativeTo(null) ; 
        authentificationFrame.setVisible(true) ; 
        authentificationFrame.setExtendedState(JFrame.NORMAL) ; 

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