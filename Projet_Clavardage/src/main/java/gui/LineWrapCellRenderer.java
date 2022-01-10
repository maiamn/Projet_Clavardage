package gui;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea ;
import java.util.Map ;
import java.util.Enumeration;
import java.util.HashMap ; 

public class LineWrapCellRenderer extends JTextArea implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	// Default renderer 
	private final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() ; 
	
	// Column heights are placed in this map
	private final Map<JTable, Map<Object, Map<Object, Integer>>> cellSizes = 
			new HashMap<JTable, Map<Object, Map<Object, Integer>>>();	
	
	// Creation of a text area renderer
	public LineWrapCellRenderer() {
		setLineWrap(true) ; 
		setWrapStyleWord(true) ; 
	}
	
	// This method will add size to cell based on row and column number 
    private void addSize(JTable table, int row, int column, int height) { 
        Map<Object, Map<Object, Integer>> rowsMap = cellSizes.get(table); 
        if (rowsMap == null) { 
        	cellSizes.put(table, rowsMap = new HashMap<Object, Map<Object, Integer>>()); 
        } 
        Map<Object, Integer> rowheightsMap = rowsMap.get(row); 
        if (rowheightsMap == null) { 
            rowsMap.put(row, rowheightsMap = new HashMap<Object, Integer>()); 
        } 
        rowheightsMap.put(column, height); 
    } 
    
    // Look through all columns and get the renderer.  
    // If it is also a TextAreaRenderer, we look at the maximum height in its hash table for this row. 
    private int findTotalMaximumRowSize(JTable table, int row) { 
    	// Initialize maximum height 
        int maximum_height = 0; 
        // Get all columns 
        Enumeration<TableColumn> columns = table.getColumnModel().getColumns(); 
        while (columns.hasMoreElements()) { 
            TableColumn tc = columns.nextElement(); 
            TableCellRenderer cellRenderer = tc.getCellRenderer(); 
            if (cellRenderer instanceof LineWrapCellRenderer) { 
            	LineWrapCellRenderer line = (LineWrapCellRenderer) cellRenderer; 
                maximum_height = Math.max(maximum_height, 
                        line.findMaximumRowSize(table, row)); 
            } 
        } 
        return maximum_height; 
    } 
    
    // Method to find the maximum row size 
    private int findMaximumRowSize(JTable table, int row) { 
    	// Check rows 
        Map<Object, Map<Object, Integer>> rows = cellSizes.get(table); 
        if (rows == null) {
        	return 0; 
        }
        // Check row heights
        Map<Object, Integer> rowheights = rows.get(row); 
        if (rowheights == null) {
        	return 0; 
        }
        // Else initialize maximum height and search for the max 
        int maximum_height = 0; 
        for (Map.Entry<Object, Integer> entry : rowheights.entrySet()) { 
            int cellHeight = entry.getValue(); 
            maximum_height = Math.max(maximum_height, cellHeight); 
        } 
        return maximum_height; 
    } 
    
    public Component getTableCellRendererComponent(
			JTable table, 
			Object value, 
			boolean isSelected, 
			boolean hasFocus, 
			int row,
			int column) {
 
    	// Set foreground, background, border, font and text as it was in the initial cell
        renderer.getTableCellRendererComponent(table, value, 
                isSelected, hasFocus, row, column); 
        setForeground(renderer.getForeground()); 
        setBackground(renderer.getBackground()); 
        setBorder(renderer.getBorder()); 
        setFont(renderer.getFont()); 
        setText(renderer.getText()); 
 
        TableColumnModel columnModel = table.getColumnModel(); 
        setSize(columnModel.getColumn(column).getWidth(), 0); 
        int height_wanted = (int) getPreferredSize().getHeight(); 
        addSize(table, row, column, height_wanted); 
        height_wanted = findTotalMaximumRowSize(table, row); 
        
        // If the wanted height is not the actual one -> set with the new height
        if (height_wanted != table.getRowHeight(row)) { 
            table.setRowHeight(row, height_wanted); 
        } 
        return this; 
	}

}
