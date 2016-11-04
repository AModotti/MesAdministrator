package ide.dept;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/**
 *
 */
public class DeptTable extends JTable {
	    
	private static final long serialVersionUID = 1L;
	
	private Vector<String> columnnames  = new Vector<String>();

	public DeptTable() {
		super();
		columnnames.addElement("Nome Reparto");
		columnnames.addElement("Descrizione Reparto");
		columnnames.addElement("Abilit.");
		columnnames.addElement("Ut. Inserimento");
		columnnames.addElement("Dt. Inserimento");
		columnnames.addElement("Ut. Modifica");
		columnnames.addElement("Dt. Modifica");

	}
	
	public Vector<String> getHeaders() {
		return columnnames;
	}
	public void setLayout(){
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	    	    	
    	this.getColumnModel().getColumn(0).setCellRenderer(new DeptCellRender(SwingConstants.CENTER,Color.BLACK));
		this.getColumnModel().getColumn(0).setPreferredWidth(120);
		this.getColumnModel().getColumn(1).setCellRenderer(new DeptCellRender(SwingConstants.LEFT,Color.BLACK));
    	this.getColumnModel().getColumn(1).setPreferredWidth(600);
    	this.getColumnModel().getColumn(2).setCellRenderer(new DeptCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(2).setPreferredWidth(80);
    	this.getColumnModel().getColumn(3).setCellRenderer(new DeptCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(3).setPreferredWidth(250);
    	this.getColumnModel().getColumn(4).setCellRenderer(new DeptCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(4).setPreferredWidth(150);
    	this.getColumnModel().getColumn(5).setCellRenderer(new DeptCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(5).setPreferredWidth(250);
    	this.getColumnModel().getColumn(6).setCellRenderer(new DeptCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(6).setPreferredWidth(150);
    	
  	
    	for (int i = 0; i < this.getRowCount(); i++) {
			this.setRowHeight(i, 20);
		}
    	
    	this.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
    	this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
	}
	
}
