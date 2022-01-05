package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomePageGUI {
	static String username ;
    JFrame authentificationFrame;
    JPanel authentificationPanel;
    JLabel todo;
    JButton connectedUsers ;
    JButton changeUsername ; 
    JButton sendMessage ; 
    JButton disconnection ; 
    
    int widthComponents ; 
    int heightComponents ; 

    public HomePageGUI(String choosenUsername) {
    	// Define username 
    	this.username = choosenUsername ; 
    	
        //Create and set up the window.
        authentificationFrame = new JFrame("Welcome to chat app, " + username + "!");
        authentificationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        authentificationFrame.pack();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize() ; 
        int widthWindow = screenSize.width*1 ;
        int heightWindow = screenSize.height*1 ;
        this.widthComponents = widthWindow*1/4 ; 
        this.heightComponents = heightWindow*1/4 ; 
        authentificationFrame.setSize(widthWindow, heightWindow);
        authentificationFrame.setLocationRelativeTo(null); 

        //Create and set up the panel.
        authentificationPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        //Add the widgets.
        addWidgets();

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
    	
    	// Label
        JLabel todo = new JLabel("What do you want to do?");
        
        // Connected Users
        connectedUsers = new JButton("See connected users");
        connectedUsers.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				authentificationFrame.setVisible(false);
        				GUIManager.switchToConnectedUsers() ; 
                  }
                }
              );
        
        // Change Username 
        changeUsername = new JButton("Change username") ; 
        changeUsername.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				authentificationFrame.setVisible(false);
        				System.out.println("Change username") ; 
                  }
                }
              );
        
        // Send Message
        sendMessage = new JButton("Send message") ; 
        sendMessage.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				authentificationFrame.setVisible(false);
        				GUIManager.switchToSendMessage() ; 
                  }
                }
              );
        
        // Disconnection
        disconnection = new JButton("Disconnection") ; 
        disconnection.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				authentificationFrame.setVisible(false);
        				GUIManager.switchToDisconnection() ; 
                  }
                }
              );



        // Set size 
        connectedUsers.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        changeUsername.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        sendMessage.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        disconnection.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        
        //Add the widgets to the container.
        authentificationPanel.add(connectedUsers); 
        authentificationPanel.add(changeUsername); 
        authentificationPanel.add(sendMessage) ; 
        authentificationPanel.add(disconnection) ; 

        connectedUsers.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        changeUsername.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        sendMessage.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        disconnection.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    public void actionPerformed(ActionEvent event) {  
        System.out.println("Button");

    }


    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        HomePageGUI homePage = new HomePageGUI(username);
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
