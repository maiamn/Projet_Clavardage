package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConnectionGUI implements ActionListener {
	static String username ;
    JFrame authentificationFrame;
    JPanel authentificationPanel;
    JLabel welcome;
    JButton connect;
    
    int widthComponents ; 
    int heightComponents ; 

    public ConnectionGUI(String choosenUsername) {
    	// Set username 
    	username = choosenUsername ; 
    	
        //Create and set up the window.
        authentificationFrame = new JFrame("Welcome to chat app, " + username + "!");
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
        authentificationPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        //Add the widgets.
        addWidgets();
        
        //Set the default button.
        authentificationFrame.getRootPane().setDefaultButton(connect);

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
        welcome = new JLabel(username + " is a correct username! \n You can connect yourself to chat app!", SwingConstants.CENTER);
        connect = new JButton("Connection");

        //Listen to events from the Convert button.
        connect.addActionListener(this);

        // Set size 
        welcome.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        connect.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        
        //Add the widgets to the container.
        authentificationPanel.add(welcome);
        authentificationPanel.add(connect); 

        welcome.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        connect.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    public void actionPerformed(ActionEvent event) { 
        authentificationFrame.setVisible(false);
    	GUIManager.switchToHomePage(username) ; 
    }


    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        ConnectionGUI connection = new ConnectionGUI(username);
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