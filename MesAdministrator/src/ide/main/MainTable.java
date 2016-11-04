package ide.main;


import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import bin.ColumnManager;

/**
 *
 */
public class MainTable extends JTable {
	    
	private static final long serialVersionUID = 1L;
	
	private Vector<String> columnnames  = new Vector<String>();
	HashMap<Integer, Integer> column =  new HashMap<>();

	public MainTable() {
		super();
		columnnames.addElement("Sel.");
		columnnames.addElement("Id");
		columnnames.addElement("Rep.");
		columnnames.addElement("Isola");
		columnnames.addElement("Data Transazione");
		columnnames.addElement("Tipo");
		columnnames.addElement("Stato");
		columnnames.addElement("Branch");
		columnnames.addElement("Risorsa");
		columnnames.addElement("Descrizione Risorsa");
		columnnames.addElement("Operatore");
		columnnames.addElement("Descrizione Operatore");
		columnnames.addElement("Work Order");
		columnnames.addElement("Oper.");
		columnnames.addElement("Descrizione Oper.");
		columnnames.addElement("C.d.L.");
		columnnames.addElement("Commessa");
		columnnames.addElement("Articolo");
		columnnames.addElement("Descrizione Articolo");
		columnnames.addElement("Utenza");
		columnnames.addElement("Attributo");
		columnnames.addElement("Ut. Inserimento");
		columnnames.addElement("Dt. Inserimento");
		columnnames.addElement("Ut. Modifica");
		columnnames.addElement("Dt. Modifica");
		columnnames.addElement("Ut. Cancellazione");
		columnnames.addElement("Dt. Cancellazione");
		columnnames.addElement("Ut. Elaborazione");
		columnnames.addElement("Dt. Elaborazione");
	}
	
	public Vector<String> getHeaders() {
		return columnnames;
	}
	
	public void getTableLayout(){
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	    	
    	this.getColumnModel().getColumn(0).setCellRenderer(new MainCheckBoxCellRenderer());
    	this.getColumnModel().getColumn(1).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(2).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(3).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(4).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(5).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(6).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(7).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(8).setCellRenderer(new MainCellRender(SwingConstants.RIGHT,Color.BLACK));
    	this.getColumnModel().getColumn(9).setCellRenderer(new MainCellRender(SwingConstants.LEFT,Color.BLACK));
    	this.getColumnModel().getColumn(10).setCellRenderer(new MainCellRender(SwingConstants.RIGHT,Color.BLACK));
    	this.getColumnModel().getColumn(11).setCellRenderer(new MainCellRender(SwingConstants.LEFT,Color.BLACK));
    	this.getColumnModel().getColumn(12).setCellRenderer(new MainCellRender(SwingConstants.RIGHT,Color.BLACK));
    	this.getColumnModel().getColumn(13).setCellRenderer(new MainCellRender(SwingConstants.RIGHT,Color.BLACK));
    	this.getColumnModel().getColumn(14).setCellRenderer(new MainCellRender(SwingConstants.LEFT,Color.BLACK));
    	this.getColumnModel().getColumn(15).setCellRenderer(new MainCellRender(SwingConstants.RIGHT,Color.BLACK));
    	this.getColumnModel().getColumn(16).setCellRenderer(new MainCellRender(SwingConstants.RIGHT,Color.BLACK));
    	this.getColumnModel().getColumn(17).setCellRenderer(new MainCellRender(SwingConstants.LEFT,Color.BLACK));
    	this.getColumnModel().getColumn(18).setCellRenderer(new MainCellRender(SwingConstants.LEFT,Color.BLACK));
    	this.getColumnModel().getColumn(19).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(20).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(21).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(22).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(23).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(24).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(25).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(26).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(27).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	this.getColumnModel().getColumn(28).setCellRenderer(new MainCellRender(SwingConstants.CENTER,Color.BLACK));
    	
    	column = ColumnManager.getColumnInformation();
    	
    	int c0 = 50;
    	if(column.get(0) != null){c0 = column.get(0);}
    	int c1 = 80;
    	if(column.get(1) != null){c1 = column.get(1);}
    	int c2 = 50;
    	if(column.get(2) != null){c2 = column.get(2);}
    	int c3 = 80;
    	if(column.get(3) != null){c3 = column.get(3);}
    	int c4 = 150;
    	if(column.get(4) != null){c4 = column.get(4);}
    	int c5 = 50;
    	if(column.get(5) != null){c5 = column.get(5);}
    	int c6 = 50;
    	if(column.get(6) != null){c6 = column.get(6);}
    	int c7 = 80;
    	if(column.get(7) != null){c7 = column.get(7);}
    	int c8 = 80;
    	if(column.get(8) != null){c8 = column.get(8);}
    	int c9 = 250;
    	if(column.get(9) != null){c9 = column.get(9);}
    	int c10 = 80;
    	if(column.get(10) != null){c10 = column.get(10);}
    	int c11 = 250;
    	if(column.get(11) != null){c11 = column.get(11);}
    	int c12 = 80;
    	if(column.get(12) != null){c12 = column.get(12);}
    	int c13 = 80;
    	if(column.get(13) != null){c13 = column.get(13);}
    	int c14 = 250;
    	if(column.get(14) != null){c14 = column.get(14);}
    	int c15 = 80;
    	if(column.get(15) != null){c15 = column.get(15);}
    	int c16 = 80;
    	if(column.get(16) != null){c16 = column.get(16);}
    	int c17 = 150;
    	if(column.get(17) != null){c17 = column.get(17);}
    	int c18 = 250;
    	if(column.get(18) != null){c18 = column.get(18);}
    	int c19 = 120;
    	if(column.get(19) != null){c19 = column.get(19);}
    	int c20 = 80;
    	if(column.get(20) != null){c20 = column.get(20);}
    	int c21 = 250;
    	if(column.get(21) != null){c21 = column.get(21);}
    	int c22 = 150;
    	if(column.get(22) != null){c22 = column.get(22);}
    	int c23 = 250;
    	if(column.get(23) != null){c23 = column.get(23);}
    	int c24 = 150;
    	if(column.get(24) != null){c24 = column.get(24);}
    	int c25 = 250;
    	if(column.get(23) != null){c25 = column.get(25);}
    	int c26 = 150;
    	if(column.get(22) != null){c26 = column.get(26);}
    	int c27 = 250;
    	if(column.get(23) != null){c27 = column.get(27);}
    	int c28 = 150;
    	if(column.get(28) != null){c28 = column.get(28);}
    	
		this.getColumnModel().getColumn(0).setPreferredWidth(c0);
    	this.getColumnModel().getColumn(1).setPreferredWidth(c1);
    	this.getColumnModel().getColumn(2).setPreferredWidth(c2);
    	this.getColumnModel().getColumn(3).setPreferredWidth(c3);
    	this.getColumnModel().getColumn(4).setPreferredWidth(c4);
    	this.getColumnModel().getColumn(5).setPreferredWidth(c5);
    	this.getColumnModel().getColumn(6).setPreferredWidth(c6);
    	this.getColumnModel().getColumn(7).setPreferredWidth(c7);
    	this.getColumnModel().getColumn(8).setPreferredWidth(c8);
    	this.getColumnModel().getColumn(9).setPreferredWidth(c9);
    	this.getColumnModel().getColumn(10).setPreferredWidth(c10);
    	this.getColumnModel().getColumn(11).setPreferredWidth(c11);
    	this.getColumnModel().getColumn(12).setPreferredWidth(c12);
    	this.getColumnModel().getColumn(13).setPreferredWidth(c13);
    	this.getColumnModel().getColumn(14).setPreferredWidth(c14);
    	this.getColumnModel().getColumn(15).setPreferredWidth(c15);
    	this.getColumnModel().getColumn(16).setPreferredWidth(c16);
    	this.getColumnModel().getColumn(17).setPreferredWidth(c17);
    	this.getColumnModel().getColumn(18).setPreferredWidth(c18);
    	this.getColumnModel().getColumn(19).setPreferredWidth(c19);
    	this.getColumnModel().getColumn(20).setPreferredWidth(c20);
    	this.getColumnModel().getColumn(21).setPreferredWidth(c21);
    	this.getColumnModel().getColumn(22).setPreferredWidth(c22);
    	this.getColumnModel().getColumn(23).setPreferredWidth(c23);
    	this.getColumnModel().getColumn(24).setPreferredWidth(c24);
    	this.getColumnModel().getColumn(25).setPreferredWidth(c25);
    	this.getColumnModel().getColumn(26).setPreferredWidth(c26);
    	this.getColumnModel().getColumn(27).setPreferredWidth(c27);
    	this.getColumnModel().getColumn(28).setPreferredWidth(c28);
  	
    	
    	for (int i = 0; i < this.getRowCount(); i++) {
			this.setRowHeight(i, 20);
		}
    	
    	this.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
    	this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	
	}
		
}
