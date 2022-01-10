package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class ChatGUI {
	private JFrame chatFrame ; 
	
	private JPanel topPanel ; 
	private JPanel centerPanel ; 
	private JPanel bottomPanel ; 
	
	private JLabel welcome ; 
	private JButton back = new JButton("Back to home page") ; 
	
	public static String sender ; 
	public static String receiver ; 
	
	// Table with history
	//protected int nbMessages = GUIManager.getMessages(sender, receiver).length ; 
	protected Object senders[] ;
	protected Object receivers[] ; 
	protected Object dates[] ; 
	protected Object messages[] ; 
	protected Object history[][] = new Object [50][1] ; 
	protected Object header[] = {"History"} ;
	final JTable historyTable ; 
	JScrollPane scrollPane ; 
	
	// Field to send a message 
	private JTextField messageArea = new JTextField(50) ; 
	private JButton send = new JButton("Send message") ; 
	
	
	
	public ChatGUI(String you, String dest) {
		// Define sender and receiver of conversation
		sender = you ; 
		receiver = dest ;
		System.out.println("ChatGUI / Init : nb messages = " + 50) ; 
		System.out.println("ChatGUI / Init : sender = " + sender + " receiver = " + receiver) ; 
			
		// Define element of table 
		senders = GUIManager.getSenders(sender, receiver) ; 
		receivers = GUIManager.getReceivers(sender, receiver) ; 
		dates = GUIManager.getDates(sender, receiver) ; 
		messages = GUIManager.getMessages(sender, receiver) ; 		
		
		// Main Frame
		chatFrame = new JFrame("~ MessengIR ~ CONVERSATION") ; 
		
		// Top part to write an introduction sentence and add a button to go back to home page
		welcome = new JLabel(sender + ", you are talking with " + receiver, SwingConstants.CENTER) ; 
		welcome.setFont(new Font("Century Gothic", Font.BOLD, 16));
        topPanel = new JPanel() ; 
        topPanel.setBackground(new Color(161,236,236));
        topPanel.add(welcome) ; 
        
        
        // Middle part to display history of conversation
        // Construct table 
        // IP of sender 
		String IPSender = GUIManager.getIP(sender).toString();
		if (IPSender.charAt(0) == ('/')) {
			IPSender = IPSender.substring(1);
		}
		
		String IPReceiver = GUIManager.getIP(receiver).toString();
		if (IPReceiver.charAt(0) == ('/')) {
			IPReceiver = IPReceiver.substring(1);
		}
		
        for (int i=0; i<senders.length; i++) {
        	if (senders[i].equals(IPReceiver)) {
        		history[i][0] = "[" + receiver + "] (" + dates[i] + ") : " + messages[i] ; 
        	} else if (senders[i].equals(IPSender)) {
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
            
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                Color sender1 = new Color(161, 236, 236);
                Color sender2 = new Color(230, 254, 255);
                if(!comp.getBackground().equals(getSelectionBackground())) {
                	if (row<senders.length) {
                		Color c = (senders[row].equals(receiver) ? sender1 : sender2);
                		comp.setBackground(c); 
                		c = null;
                	}
	                   
                }
                return comp;
        	}
        } ;
        historyTable.setDefaultRenderer(String.class, new LineWrapCellRenderer()) ; 
        scrollPane = new JScrollPane(historyTable) ; 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) ; 
        centerPanel.add(scrollPane, BorderLayout.CENTER) ; 
         
        // Bottom part to display an area to enter the message to send and the button
		// Button to go back to home page
        back.setPreferredSize(new Dimension(150,30));
        back.setMinimumSize(new Dimension(150,30));
        back.setMaximumSize(new Dimension(150, 30));
        back.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				chatFrame.setVisible(false);
        				GUIManager.switchToHomePage(sender) ; 
                  }
                }
              );
        // Button to send the message 
        send.setPreferredSize(new Dimension(150,30));
        send.setMinimumSize(new Dimension(150,30));
        send.setMaximumSize(new Dimension(150, 30));
        send.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				String message = messageArea.getText() ; 
        				GUIManager.sendMessage(receiver, message) ; 
        				chatFrame.setVisible(false) ; 
        				GUIManager.switchToChat(sender, receiver);
        			}
        		});
        Box bottom = Box.createHorizontalBox() ; 
        bottom.add(messageArea) ; 
        bottom.add(send); 
        bottom.add(back) ; 
        bottomPanel = new JPanel() ; 
        bottomPanel.add(bottom) ; 
        
        // Main frame 
        chatFrame.setLayout(new BorderLayout());
        chatFrame.getRootPane().setDefaultButton(send);
        // Add panels to the frame 
        chatFrame.add(topPanel, BorderLayout.PAGE_START) ; 
        chatFrame.add(centerPanel, BorderLayout.CENTER) ; 
        chatFrame.add(bottomPanel, BorderLayout.PAGE_END) ; 
        
        // Set default close operation
        chatFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Set appearance 
        chatFrame.pack() ; 
        chatFrame.setLocationRelativeTo(null) ;
        chatFrame.setVisible(true) ; 
        chatFrame.setExtendedState(JFrame.NORMAL) ; 
        
	}

}
