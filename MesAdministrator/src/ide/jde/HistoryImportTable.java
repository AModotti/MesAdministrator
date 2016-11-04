package ide.jde;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/**
 *
 */
public class HistoryImportTable extends JTable {
	    
	private static final long serialVersionUID = 1L;
	
	private Vector<String> columnnames  = new Vector<String>();

	public HistoryImportTable() {
		super();
		columnnames.addElement("Data Importazione");
		columnnames.addElement("Utente");
		columnnames.addElement("Reparto");
		columnnames.addElement("Da Data Transazione");
		columnnames.addElement("A Data Transazione");
		columnnames.addElement("Id Iniziale");
		columnnames.addElement("Record Importati");
		columnnames.addElement("Id Finale");

	}
	
	public Vector<String> getHeaders() {
		return columnnames;
	}
	
	public void setLayout(){
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	
    	this.getColumnModel().getColumn(0).setCellRenderer(new HistoryImportDefaultCellRenderer(SwingConstants.CENTER,Color.BLACK));
		this.getColumnModel().getColumn(0).setPreferredWidth(150);
    	this.getColumnModel().getColumn(1).setCellRenderer(new HistoryImportDefaultCellRenderer(SwingConstants.CENTER,Color.BLACK));
		this.getColumnModel().getColumn(1).setPreferredWidth(250);
		this.getColumnModel().getColumn(2).setCellRenderer(new HistoryImportDefaultCellRenderer(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(2).setPreferredWidth(80);
    	this.getColumnModel().getColumn(3).setCellRenderer(new HistoryImportDefaultCellRenderer(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(3).setPreferredWidth(150);
    	this.getColumnModel().getColumn(4).setCellRenderer(new HistoryImportDefaultCellRenderer(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(4).setPreferredWidth(150);
    	this.getColumnModel().getColumn(5).setCellRenderer(new HistoryImportDefaultCellRenderer(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(5).setPreferredWidth(100);
    	this.getColumnModel().getColumn(6).setCellRenderer(new HistoryImportDefaultCellRenderer(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(6).setPreferredWidth(130);
    	this.getColumnModel().getColumn(7).setCellRenderer(new HistoryImportDefaultCellRenderer(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(7).setPreferredWidth(100);
  	
    	for (int i = 0; i < this.getRowCount(); i++) {
			this.setRowHeight(i, 20);
		}
    	
    	this.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
    	this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
	}
	
}
