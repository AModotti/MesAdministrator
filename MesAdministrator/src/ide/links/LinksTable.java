package ide.links;

import ide.island.IslandCellRender;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/**
 *
 */
public class LinksTable extends JTable {
	    
	private static final long serialVersionUID = 1L;
	
	private Vector<String> columnnames  = new Vector<String>();

	public LinksTable() {
		super();
		columnnames.addElement("Nome Isola");
		columnnames.addElement("Seq.");
		columnnames.addElement("Risorsa");
		columnnames.addElement("Descrizione Risorsa");
		columnnames.addElement("Abilitazione");
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
    	    	
    	this.getColumnModel().getColumn(0).setCellRenderer(new IslandCellRender(SwingConstants.CENTER,Color.BLACK));
		this.getColumnModel().getColumn(0).setPreferredWidth(100);
		this.getColumnModel().getColumn(1).setCellRenderer(new IslandCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(1).setPreferredWidth(50);
    	this.getColumnModel().getColumn(2).setCellRenderer(new IslandCellRender(SwingConstants.RIGHT,Color.BLACK));
    	this.getColumnModel().getColumn(2).setPreferredWidth(100);
    	this.getColumnModel().getColumn(3).setCellRenderer(new IslandCellRender(SwingConstants.LEFT,Color.BLACK));
    	this.getColumnModel().getColumn(3).setPreferredWidth(310);
    	this.getColumnModel().getColumn(4).setCellRenderer(new IslandCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(4).setPreferredWidth(100);
    	this.getColumnModel().getColumn(5).setCellRenderer(new IslandCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(5).setPreferredWidth(250);
    	this.getColumnModel().getColumn(6).setCellRenderer(new IslandCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(6).setPreferredWidth(150);
    	this.getColumnModel().getColumn(7).setCellRenderer(new IslandCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(7).setPreferredWidth(250);
    	this.getColumnModel().getColumn(8).setCellRenderer(new IslandCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(8).setPreferredWidth(150);
  	
    	for (int i = 0; i < this.getRowCount(); i++) {
			this.setRowHeight(i, 20);
		}
    	
    	this.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
    	this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
	}
	
}
