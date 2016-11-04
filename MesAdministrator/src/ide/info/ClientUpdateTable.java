package ide.info;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/**
 *
 */
public class ClientUpdateTable extends JTable {
	    
	private static final long serialVersionUID = 1L;
	
	private Vector<String> columnnames  = new Vector<String>();

	public ClientUpdateTable() {
		super();
		columnnames.addElement("Indirizzo Ip");
		columnnames.addElement("Tipo Programma");
		columnnames.addElement("Versione");
		columnnames.addElement("Data Aggiornamento");

	}
	
	public Vector<String> getHeaders() {
		return columnnames;
	}
	
	public void setLayout(){
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	
    	this.getColumnModel().getColumn(0).setCellRenderer(new ClientUpdateDefaultCellRenderer(SwingConstants.CENTER,Color.BLACK));
		this.getColumnModel().getColumn(0).setPreferredWidth(250);
    	this.getColumnModel().getColumn(1).setCellRenderer(new ClientUpdateDefaultCellRenderer(SwingConstants.CENTER,Color.BLACK));
		this.getColumnModel().getColumn(1).setPreferredWidth(300);
		this.getColumnModel().getColumn(2).setCellRenderer(new ClientUpdateDefaultCellRenderer(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(2).setPreferredWidth(80);
    	this.getColumnModel().getColumn(3).setCellRenderer(new ClientUpdateDefaultCellRenderer(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(3).setPreferredWidth(150);

  	
    	for (int i = 0; i < this.getRowCount(); i++) {
			this.setRowHeight(i, 20);
		}
    	
    	this.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
    	this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
	}
	
}
