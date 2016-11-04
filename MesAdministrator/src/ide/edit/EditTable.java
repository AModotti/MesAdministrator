package ide.edit;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 *
 */
public class EditTable extends JTable {
	    
	private static final long serialVersionUID = 1L;
	
	private Vector<String> columnnames  = new Vector<String>();

	public EditTable() {
		super();
		columnnames.addElement("Id");
		columnnames.addElement("Rep.");
		columnnames.addElement("Isola");
		columnnames.addElement("Data Transazione");
		columnnames.addElement("Tipo");
		columnnames.addElement("Stato");
		columnnames.addElement("Branch");
		columnnames.addElement("Risorsa");
		columnnames.addElement("Operatore");
		columnnames.addElement("Work Order");
		columnnames.addElement("Oper.");
		columnnames.addElement("Nr. Riga Or.");
	}
	
	public Vector<String> getHeaders() {
		return columnnames;
	}
	
	public void setLayout(){
		
		this.setCellSelectionEnabled(false);
				
		EditCellEditor editcelleditor = new EditCellEditor();
		
    	this.getColumnModel().getColumn(0).setCellRenderer(new EditCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(0).setPreferredWidth(80);
    	this.getColumnModel().getColumn(1).setCellEditor(editcelleditor);
    	this.getColumnModel().getColumn(1).setCellRenderer(new EditCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(1).setPreferredWidth(50);
    	this.getColumnModel().getColumn(2).setCellEditor(editcelleditor);
    	this.getColumnModel().getColumn(2).setCellRenderer(new EditCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(2).setPreferredWidth(80);
    	this.getColumnModel().getColumn(3).setCellEditor(editcelleditor);
    	this.getColumnModel().getColumn(3).setCellRenderer(new EditCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(3).setPreferredWidth(150);
    	this.getColumnModel().getColumn(4).setCellEditor(editcelleditor);
    	this.getColumnModel().getColumn(4).setCellRenderer(new EditCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(4).setPreferredWidth(50);
    	this.getColumnModel().getColumn(5).setCellEditor(editcelleditor);
    	this.getColumnModel().getColumn(5).setCellRenderer(new EditCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(5).setPreferredWidth(50);
    	this.getColumnModel().getColumn(6).setCellEditor(editcelleditor);
    	this.getColumnModel().getColumn(6).setCellRenderer(new EditCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(6).setPreferredWidth(80);
    	this.getColumnModel().getColumn(7).setCellEditor(editcelleditor);
    	this.getColumnModel().getColumn(7).setCellRenderer(new EditCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(7).setPreferredWidth(80);
    	this.getColumnModel().getColumn(8).setCellEditor(editcelleditor);
    	this.getColumnModel().getColumn(8).setCellRenderer(new EditCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(8).setPreferredWidth(80);
    	this.getColumnModel().getColumn(9).setCellEditor(editcelleditor);
    	this.getColumnModel().getColumn(9).setCellRenderer(new EditCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(9).setPreferredWidth(80);
    	this.getColumnModel().getColumn(10).setCellEditor(editcelleditor);
    	this.getColumnModel().getColumn(10).setCellRenderer(new EditCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(10).setPreferredWidth(80);
    	this.getColumnModel().getColumn(11).setCellRenderer(new EditCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(11).setPreferredWidth(80);
    	
    	for (int i = 0; i < this.getRowCount(); i++) {
			this.setRowHeight(i, 20);
		}
  	
    	this.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
    	this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
	}
	
}
