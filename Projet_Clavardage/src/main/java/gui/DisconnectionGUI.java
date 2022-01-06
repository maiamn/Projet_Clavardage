package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisconnectionGUI {
	private JFrame disconnectionFrame ; 
	
	private JPanel topPanel; 
	private JPanel centerPanel; 
	
	private final JLabel warning = new JLabel ("You are about to log out of the app. Are you sure?") ;  
	private final JButton confirm = new JButton("Confirm") ;
	private final JButton back = new JButton("Back to home page") ;
	
	public static String username ; 
	
	public DisconnectionGUI(String choosenUsername) {
		// Define username 
		username = choosenUsername ; 
		
		// Main Frame
		disconnectionFrame = new JFrame("~ MessengIR ~ Disconnection page") ;
		
		// Top part to write the warning sentence
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
		warning.setFont(new Font("Monospace", Font.BOLD, 16));
		topPanel.add(warning);
		
		// Center part to choose what to do
        // Confirm
		confirm.setPreferredSize(new Dimension(200,50));
        confirm.setMinimumSize(new Dimension(200,50));
        confirm.setMaximumSize(new Dimension(200,50));
        confirm.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				GUIManager.disconnection() ; 
        				System.exit(0);
                 }
                }
              );
      
        // Go back to home page
        back.setPreferredSize(new Dimension(200,50));
        back.setMinimumSize(new Dimension(200,50));
        back.setMaximumSize(new Dimension(200,50));
        back.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				disconnectionFrame.setVisible(false);
        				GUIManager.switchToHomePage(username) ; 
                  }
                }
              );
        
        
		Box options = Box.createHorizontalBox() ; 
		options.add(confirm) ; 
		options.add(back);
		centerPanel = new JPanel() ; 
		centerPanel.add(options) ; 
		centerPanel.setBorder(BorderFactory.createTitledBorder("Are you sure?"));


		
		// Main Frame 
        //authentificationFrame.setSize(widthWindow, heightWindow);
		disconnectionFrame.setLayout(new BorderLayout());
		// add panels to frame
		disconnectionFrame.add(topPanel, BorderLayout.PAGE_START) ; 
		disconnectionFrame.add(centerPanel, BorderLayout.CENTER) ; 
		
		// Set default close operation
		disconnectionFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;
        // Set appearance
		disconnectionFrame.pack() ; 
		disconnectionFrame.setLocationRelativeTo(null) ; 
		disconnectionFrame.setVisible(true) ; 
		disconnectionFrame.setExtendedState(JFrame.NORMAL) ; 

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

		            new DisconnectionGUI(username);
			 }
		 }) ;
	}
}
