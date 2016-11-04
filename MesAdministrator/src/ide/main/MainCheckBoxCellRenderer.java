package ide.main;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

class MainCheckBoxCellRenderer extends JCheckBox implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	protected MainCheckBoxCellRenderer() {
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
	}
 
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
				
		setForeground(table.getForeground());
		setBackground(table.getBackground());

		setFont(table.getFont());
		
		setSelected((value != null && ((Boolean) value).booleanValue()));	
		
		if((value != null && ((Boolean) value).booleanValue()) == true){
			setBackground(Color.YELLOW);
			table.repaint();
		}	

		return this;

	}

}

