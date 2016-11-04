package ide.copy;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 *
 */
public class CopyTable extends JTable {
	    
	private static final long serialVersionUID = 1L;
	
	private Vector<String> columnnames  = new Vector<String>();

	public CopyTable() {
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
		
		CopyCellEditor copycelleditor = new CopyCellEditor();
		
    	this.getColumnModel().getColumn(0).setCellRenderer(new CopyCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(0).setPreferredWidth(80);
    	this.getColumnModel().getColumn(1).setCellEditor(copycelleditor);
    	this.getColumnModel().getColumn(1).setCellRenderer(new CopyCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(1).setPreferredWidth(50);
    	this.getColumnModel().getColumn(2).setCellEditor(copycelleditor);
    	this.getColumnModel().getColumn(2).setCellRenderer(new CopyCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(2).setPreferredWidth(80);
    	this.getColumnModel().getColumn(3).setCellEditor(copycelleditor);
    	this.getColumnModel().getColumn(3).setCellRenderer(new CopyCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(3).setPreferredWidth(150);
    	this.getColumnModel().getColumn(4).setCellEditor(copycelleditor);
    	this.getColumnModel().getColumn(4).setCellRenderer(new CopyCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(4).setPreferredWidth(50);
    	this.getColumnModel().getColumn(5).setCellEditor(copycelleditor);
    	this.getColumnModel().getColumn(5).setCellRenderer(new CopyCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(5).setPreferredWidth(50);
    	this.getColumnModel().getColumn(6).setCellEditor(copycelleditor);
    	this.getColumnModel().getColumn(6).setCellRenderer(new CopyCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(6).setPreferredWidth(80);
    	this.getColumnModel().getColumn(7).setCellEditor(copycelleditor);
    	this.getColumnModel().getColumn(7).setCellRenderer(new CopyCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(7).setPreferredWidth(80);
    	this.getColumnModel().getColumn(8).setCellEditor(copycelleditor);
    	this.getColumnModel().getColumn(8).setCellRenderer(new CopyCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(8).setPreferredWidth(80);
    	this.getColumnModel().getColumn(9).setCellEditor(copycelleditor);
    	this.getColumnModel().getColumn(9).setCellRenderer(new CopyCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(9).setPreferredWidth(80);
    	this.getColumnModel().getColumn(10).setCellEditor(copycelleditor);
    	this.getColumnModel().getColumn(10).setCellRenderer(new CopyCellRender(SwingConstants.CENTER,Color.BLUE));
    	this.getColumnModel().getColumn(10).setPreferredWidth(80);
    	this.getColumnModel().getColumn(11).setCellRenderer(new CopyCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(11).setPreferredWidth(80);
    	
    	for (int i = 0; i < this.getRowCount(); i++) {
			this.setRowHeight(i, 20);
		}
  	
    	this.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
    	this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
	}
	
}
