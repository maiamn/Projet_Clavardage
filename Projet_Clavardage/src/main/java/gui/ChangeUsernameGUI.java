package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChangeUsernameGUI implements ActionListener {
	
	static String username ; 
	
    JFrame changeUsernameFrame;
    JPanel changeUsernamePanel;
    
    // Elements for actual username
    JLabel actualUsername ; 
    
    // Explanations
    JLabel welcome ; 
    
    // Elements for new username
    JPanel newUsernamePanel ; 
    JTextField newChoosenUsername;
    JButton submit ; 
    
    // Error of username
    JPanel errorPanel ;
    JLabel errorMessage ; 
    JButton retry ; 
       
    
    int widthComponents ; 
    int heightComponents ; 

    public ChangeUsernameGUI(String actualUsername) {
    	
    	this.username = actualUsername ; 
    	
        //Create and set up the window.
        changeUsernameFrame = new JFrame("You want to change your username.");
        changeUsernameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        changeUsernameFrame.pack();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize() ; 
        int widthWindow = screenSize.width*1/2 ;
        int heightWindow = screenSize.height*1/2 ;
        this.widthComponents = widthWindow*1/4 ; 
        this.heightComponents = heightWindow*1/4 ; 
        changeUsernameFrame.setSize(widthWindow, heightWindow);
        changeUsernameFrame.setLocationRelativeTo(null); 

        //Create and set up the panel.
        changeUsernamePanel = new JPanel(new GridLayout(5, 1, 10, 10));

        //Add the widgets.
        addWidgets();

        //Add the panel to the window.
        changeUsernameFrame.getContentPane().add(changeUsernamePanel, BorderLayout.CENTER);

        //Display the window.
        changeUsernameFrame.setVisible(true);
    }

    /**
     * Create and add the widgets.
     */
    private void addWidgets() {
    	
    	//////////////////////// FIRST PANEL ////////////////////////
        actualUsername = new JLabel ("Your actual username is: " + username, SwingConstants.CENTER) ; 
        
    	
    	/////////////////////// SECOND PANEL ////////////////////////
    	welcome = new JLabel("Choose your new username here!", SwingConstants.CENTER) ;
        
        
    	//////////////////////// THIRD PANEL ////////////////////////
        newUsernamePanel = new JPanel();
        newUsernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        newChoosenUsername = new JTextField(10) ; 
        submit = new JButton("Submit");
        changeUsernameFrame.getRootPane().setDefaultButton(submit);
        //Listen to events from the button.
        submit.addActionListener(this);
        
        // Set size 
        newChoosenUsername.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        submit.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        
        // Add buttons to panel 
        newUsernamePanel.add(newChoosenUsername);
        newUsernamePanel.add(submit);
        
        // Set border
        newChoosenUsername.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        submit.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

    	
    	//////////////////////// MAIN PANEL ////////////////////////

        // Set size 
        actualUsername.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        welcome.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        newUsernamePanel.setPreferredSize(new Dimension(40, 40));
        
        //Add the widgets to the container.
        changeUsernamePanel.add(actualUsername);
        changeUsernamePanel.add(welcome);
        changeUsernamePanel.add(newUsernamePanel);
        
        
        actualUsername.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        welcome.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        newUsernamePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

    }

    public void actionPerformed(ActionEvent event) {
		// Get the username choosen by the user
		String newUsername = newChoosenUsername.getText() ; 
		// Check if the username is valid 
		boolean correct = GUIManager.checkUsername(newUsername) ; 
		if(!correct) {
			errorPanel = new JPanel() ; 
			// Define error message & add it to error panel
			errorMessage = new JLabel("<html>Your username is uncorrect! "
					+ "<br> Check if there is no special character and that it contains between 1 and 30 characters!"
					+ "<br> If it's not the case, it means that it is already used so choose another one!</html>", SwingConstants.CENTER) ; 
	        errorMessage.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
			errorPanel.add(errorMessage);
			errorMessage.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			
			// Define button to try again
	        retry = new JButton("Choose a new username") ; 
	        changeUsernameFrame.getRootPane().setDefaultButton(retry);
	        // Remove action from submit button
	        submit.removeActionListener(this) ; 
	        
	        retry.addActionListener(
	        		new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        				changeUsernameFrame.setVisible(false) ; 
	        				GUIManager.switchToChangeUsername(username) ; 
	                  }
	                }
	              );
	        retry.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
			errorPanel.add(retry);
			retry.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			
			// Add error panel to general panel
			errorPanel.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
			changeUsernamePanel.add(errorPanel) ; 
			errorPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		} 
		else {
			GUIManager.switchToConnection(newUsername) ; 
	        changeUsernameFrame.setVisible(false);
		}
    }


    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        ChangeUsernameGUI changeUsername = new ChangeUsernameGUI(username);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}