package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class ConnectedUsersGUI {
	static String username ; 
	JFrame connectedUsersFrame ; 
	JPanel connectedUsersPanel ; 
	JScrollPane scrollPane ; 
	
	final Object row[][] = {{"1","test1"}, {"2","test2"}, {"3","test3"}, {"4","test4"}};
	final Object header[] = {"ID", "Usernames"};
	final JTable table ;
	
	public ConnectedUsersGUI(String choosenUsername) {
		// Define the actual username
		this.username = choosenUsername ; 
		
		// Create and set up the window 
		connectedUsersFrame = new JFrame(username + ", you can talk with all these people!") ; 
		connectedUsersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		connectedUsersFrame.pack() ; 
		
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize() ; 
        int widthWindow = screenSize.width*1 ;
        int heightWindow = screenSize.height*1 ;
        connectedUsersFrame.setSize(widthWindow, heightWindow);
        connectedUsersFrame.setLocationRelativeTo(null); 
        
		connectedUsersFrame.setVisible(true) ; 
		
		table = new JTable(row, header);
	    scrollPane = new JScrollPane(table);
	    connectedUsersFrame.add(scrollPane, BorderLayout.CENTER);
		
	}
	
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        ConnectedUsersGUI connectedUsers = new ConnectedUsersGUI(username);
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
