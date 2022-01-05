package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisconnectionGUI{
	static String username ; 
    JFrame disconnectionFrame;
    JPanel disconnectionPanel;
    JPanel buttonsPanel ; 
    JLabel warning;
    JButton confirm;
    JButton back ; 
    
    int widthComponents ; 
    int heightComponents ; 

    public DisconnectionGUI(String choosenUsername) {
    	this.username = choosenUsername ; 
        //Create and set up the window.
        disconnectionFrame = new JFrame("Disconnection?");
        disconnectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        disconnectionFrame.pack();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize() ; 
        int widthWindow = screenSize.width*1/2 ;
        int heightWindow = screenSize.height*1/2 ;
        this.widthComponents = widthWindow*1/4 ; 
        this.heightComponents = heightWindow*1/4 ; 
        disconnectionFrame.setSize(widthWindow, heightWindow);
        disconnectionFrame.setLocationRelativeTo(null); 

        //Create and set up the panel.
        disconnectionPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        //Add the widgets.
        addWidgets();

        //Add the panel to the window.
        disconnectionFrame.getContentPane().add(disconnectionPanel, BorderLayout.CENTER);

        //Display the window.
        disconnectionFrame.setVisible(true);
    }

    /**
     * Create and add the widgets.
     */
    private void addWidgets() {
        //Create widgets.
        warning = new JLabel("<html> You are about to disconnect yourself! <br> Are you sure? </html>", SwingConstants.CENTER);
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Listen to events from the button.
        // Confirm
        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				GUIManager.disconnection() ; 
        				System.exit(0);
                  }
                }
              );
        
        // Back
        JButton back = new JButton("Back");
        back.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        		        disconnectionFrame.setVisible(false);
        				GUIManager.switchToHomePage(username) ; 
                  }
                }
              );
        
        // Set size 
        confirm.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        back.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        
        // Add buttons to panel 
        buttonsPanel.add(confirm);
        buttonsPanel.add(back);
        
        // Set border
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        
        // Set size 
        warning.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        buttonsPanel.setPreferredSize(new Dimension(this.widthComponents, this.heightComponents));
        
        //Add the widgets to the container.
        disconnectionPanel.add(warning);
        disconnectionPanel.add(buttonsPanel);
        
        warning.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }



    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        DisconnectionGUI disconnection = new DisconnectionGUI(username);
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


