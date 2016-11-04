package ide.main;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MainCellRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	int align;
	Color color;
	
	public MainCellRender(int align,Color color) {
		this.align = align;
		this.color = color;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col){

		JLabel renderedLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

		renderedLabel.setForeground(color);
		renderedLabel.setFont(table.getFont());
		
		if(align == 0) {
			renderedLabel.setHorizontalAlignment(CENTER);
		}else if(align == 4) {
			renderedLabel.setHorizontalAlignment(RIGHT);
		}else if(align == 2) {
			renderedLabel.setHorizontalAlignment(LEFT);
		}
		
		if((table.getModel().getValueAt(table.convertRowIndexToModel(row),5).equals("ING")||(table.getModel().getValueAt(table.convertRowIndexToModel(row),5).equals("USC")))) {
			setOpaque(true);
			setBackground(new Color(230, 247, 255));
			table.repaint();
		}else{
			setOpaque(true);
			setBackground(table.getBackground());
			table.repaint();
		}
		
		if ((Boolean) table.getModel().getValueAt(table.convertRowIndexToModel(row),0)) {
			setOpaque(true);
			setBackground(Color.YELLOW);
			table.repaint();
		}
		
		return renderedLabel;

	}
}
