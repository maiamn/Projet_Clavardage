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
        authentificationPanel = new JPanel(new GridLayout(4, 1, 10, 10));

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
        errorMessage = new JLabel("Your username is uncorrect\n", SwingConstants.CENTER) ; 

        //Listen to events from the Convert button.
        submit.addActionListener(this);

        // Set size 
        welcome.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        username.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        submit.setPreferredSize(new Dimension(40, 40));
        errorMessage.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));

        
        //Add the widgets to the container.
        authentificationPanel.add(welcome);
        authentificationPanel.add(username);
        authentificationPanel.add(submit);
        authentificationPanel.add(errorMessage);
        errorMessage.setVisible(false);

        welcome.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        submit.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    public void actionPerformed(ActionEvent event) {
    	boolean nextStep = false ; 
    	while(!nextStep) {
    		// Get the username choosen by the user
    		String name = username.getText() ; 
    		// Check if the username is valid 
    		boolean correct = GUIManager.checkUsername(name) ; 
    		System.out.println(correct); 
    		if(!correct) {
    	        errorMessage.setVisible(true);
    		} 
    		else {
    			GUIManager.switchToConnection(name) ; 
    	        authentificationFrame.setVisible(false);
    			nextStep = true ;
    		}
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