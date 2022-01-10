package gui;

import javax.swing.table.TableCellRenderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea ;

public class LineWrapCellRenderer extends JTextArea implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(
			JTable table, 
			Object value, 
			boolean isSelected, 
			boolean hasFocus, 
			int row,
			int column) {
		this.setText((String)value);
		this.setWrapStyleWord(true);
		this.setLineWrap(true);
		return this ; 
	}

}
