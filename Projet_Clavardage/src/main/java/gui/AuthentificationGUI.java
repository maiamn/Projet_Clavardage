package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AuthentificationGUI implements ActionListener {
    JFrame authentificationFrame;
    JPanel authentificationPanel;
    JTextField username;
    JLabel welcome;
    JButton submit;
    JLabel errorMessage ; 
    JButton retry ; 
    
    int widthComponents ; 
    int heightComponents ; 

    public AuthentificationGUI() {
        //Create and set up the window.
        authentificationFrame = new JFrame("Welcome to chat app!");
        authentificationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        authentificationFrame.pack();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize() ; 
        int widthWindow = screenSize.width*1/2 ;
        int heightWindow = screenSize.height*1/2 ;
        this.widthComponents = widthWindow*1/4 ; 
        this.heightComponents = heightWindow*1/4 ; 
        authentificationFrame.setSize(widthWindow, heightWindow);
        authentificationFrame.setLocationRelativeTo(null); 

        //Create and set up the panel.
        authentificationPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        //Add the widgets.
        addWidgets();
        
        //Set the default button.
        authentificationFrame.getRootPane().setDefaultButton(submit);

        //Add the panel to the window.
        authentificationFrame.getContentPane().add(authentificationPanel, BorderLayout.CENTER);

        //Display the window.
        authentificationFrame.setVisible(true);
    }

    /**
     * Create and add the widgets.
     */
    private void addWidgets() {
        //Create widgets.
        welcome = new JLabel("Welcome! What's your name?", SwingConstants.CENTER);
        username = new JTextField(10);
        submit = new JButton("Submit");

        //Listen to events from the Convert button.
        submit.addActionListener(this);

        // Set size 
        welcome.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        username.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        submit.setPreferredSize(new Dimension(40, 40));

        
        //Add the widgets to the container.
        authentificationPanel.add(welcome);
        authentificationPanel.add(username);
        authentificationPanel.add(submit);
        
        
        welcome.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        submit.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    public void actionPerformed(ActionEvent event) {
		// Get the username choosen by the user
		String name = username.getText() ; 
		// Check if the username is valid 
		boolean correct = GUIManager.checkUsername(name) ; 
		if(!correct) {
			errorMessage = new JLabel("<html>Your username is uncorrect! "
					+ "<br> Check if there is no special character and that it contains between 1 and 30 characters!"
					+ "<br> If it's not the case, it means that it is already used so choose another one!</html>", SwingConstants.CENTER) ; 
	        errorMessage.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
			authentificationPanel.add(errorMessage);
			errorMessage.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			submit.removeActionListener(this) ; 
	        retry = new JButton("Choose a new username") ; 
	        authentificationFrame.getRootPane().setDefaultButton(retry);
	        retry.addActionListener(
	        		new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        				authentificationFrame.setVisible(false) ; 
	        				GUIManager.switchToAuthentification() ; 
	                  }
	                }
	              );
	        retry.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
			authentificationPanel.add(retry);
			retry.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		} 
		else {
			GUIManager.switchToConnection(name) ; 
	        authentificationFrame.setVisible(false);
		}
    }


    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        AuthentificationGUI authentification = new AuthentificationGUI();
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