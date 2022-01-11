package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class HistoryGUI {
	// Attributes 
	private JFrame historyFrame ; 
	// Top part
	private JPanel topPanel ;
	private JLabel welcome ; 
	
	// Table with history
	protected Object senders[] ;
	protected Object receivers[] ; 
	protected Object dates[] ; 
	protected Object messages[] ; 
	protected Object history[][] = new Object [50][1] ; // We load only the 50 last messages 
	protected Object header[] = {"History"} ;
	final JTable historyTable ; 
	JScrollPane scrollPane ; 
	// Main part 
	private JPanel centerPanel ; 
	// Bottom part 
	private JPanel bottomPanel ;
	private JButton back = new JButton("Back to home page") ; 
	
	public static String you ; 
	public static String interlocutor ; 
	
	// Constructor 
	public HistoryGUI(String user1, String user2) {
		// Define usernames
		you = user1 ; 
		interlocutor = user2 ; 
		
		// Main frame
		historyFrame = new JFrame("~ MessengIR ~ When you talk with " + interlocutor ) ; 
		
		// Top part to write the welcome sentence
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
		topPanel.setBackground(new Color(161,236,236));
		welcome = new JLabel("Here is your conversation with " + interlocutor) ; 
		welcome.setFont(new Font("Century Gothic", Font.BOLD, 16));
		topPanel.add(welcome);
		
        // Middle part to display history of conversation
        // IP of sender 
		String yourIP = GUIManager.getIP(you).toString();
		if (yourIP.charAt(0) == ('/')) {
			yourIP = yourIP.substring(1);
		}
		// IP of receiver 
		String otherIP = GUIManager.getIP(interlocutor).toString();
		if (otherIP.charAt(0) == ('/')) {
			otherIP = otherIP.substring(1);
		}
		
		// Define element of table 
		senders = GUIManager.getSenders(you, interlocutor) ; 
		receivers = GUIManager.getReceivers(you, interlocutor) ; 
		dates = GUIManager.getDates(you, interlocutor) ; 
		messages = GUIManager.getMessages(you, interlocutor) ; 	
		
		// Construct table
        for (int i=0; i<senders.length; i++) {
        	if (senders[i].equals(otherIP)) {
        		history[i][0] = "[" + interlocutor + "] (" + dates[i] + ") : " + messages[i] ; 
        	} else if (senders[i].equals(yourIP)) {
        		history[i][0] = "[You] (" + dates[i] + ") : " + messages[i] ; 
        	} 
        }
        
        // Visualize history
        historyTable = new JTable(history, header) {
        	private static final long serialVersionUID = 1L;
            public String getToolTipText( MouseEvent e )
            {
                int row = rowAtPoint( e.getPoint() );
                int column = columnAtPoint( e.getPoint() );

                Object value = getValueAt(row, column);
                return value == null ? null : value.toString();
            }
            
            // Color of line different according of the sender 
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                Color sender1 = new Color(161, 236, 236);
                Color sender2 = new Color(230, 254, 255);
                if(!comp.getBackground().equals(getSelectionBackground())) {
                	if (row<senders.length) {
                		Color c = (senders[row].equals(interlocutor) ? sender1 : sender2);
                		comp.setBackground(c); 
                		c = null;
                	}
	                   
                }
                return comp;
        	}
        } ;
        
        // Wrap Line
        TableColumnModel tableModel = historyTable.getColumnModel() ; 
        LineWrapCellRenderer linesWrap = new LineWrapCellRenderer() ; 
        tableModel.getColumn(0).setCellRenderer(linesWrap) ; 
       
        scrollPane = new JScrollPane(historyTable) ; 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
        centerPanel.add(scrollPane, BorderLayout.CENTER) ; 
        
        
        // Bottom part to back to home page
		// Button to go back to home page
        back.setPreferredSize(new Dimension(150,30));
        back.setMinimumSize(new Dimension(150,30));
        back.setMaximumSize(new Dimension(150, 30));
        back.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				historyFrame.setVisible(false);
        				GUIManager.switchToHomePage(you) ; 
                  }
                }
              );
        bottomPanel = new JPanel() ; 
        bottomPanel.add(back) ; 
        
        // Main frame 
        historyFrame.setLayout(new BorderLayout());
        // Add panels to the frame 
        historyFrame.add(topPanel, BorderLayout.PAGE_START) ; 
        historyFrame.add(centerPanel, BorderLayout.CENTER) ; 
        historyFrame.add(bottomPanel, BorderLayout.PAGE_END) ; 
        
        // Set default close operation
        historyFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Set appearance 
        historyFrame.pack() ; 
        historyFrame.setLocationRelativeTo(null) ;
        historyFrame.setVisible(true) ; 
        historyFrame.setExtendedState(JFrame.NORMAL) ; 
		
	}

}
