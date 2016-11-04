package ide.links;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class EventLinksTableSelection implements ListSelectionListener{

	LinksWindow lw;
	LinksTable tbllinks;
	JTextField txtnomeisola;
	JTextField txtsequenza;
	JTextField txtrisorsa;
	JComboBox<String> cmbabilitazione;
	
	public EventLinksTableSelection(LinksWindow lw,LinksTable tbllinks,JTextField txtnomeisola,JTextField txtsequenza,JTextField txtrisorsa,JComboBox<String> cmbabilitazione){
		
		this.lw = lw;
		this.tbllinks = tbllinks;
		this.txtnomeisola = txtnomeisola;
		this.txtsequenza = txtsequenza;
		this.txtrisorsa = txtrisorsa;
		this.cmbabilitazione = cmbabilitazione;
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		DefaultTableModel tm = (DefaultTableModel) tbllinks.getModel();
		
		if (tbllinks.getSelectedRow() > -1) {
			txtnomeisola.setText(tm.getValueAt(tbllinks.getSelectedRow(), 0).toString());
			txtsequenza.setText(tm.getValueAt(tbllinks.getSelectedRow(), 1).toString());
			txtrisorsa.setText(tm.getValueAt(tbllinks.getSelectedRow(), 2).toString());
			cmbabilitazione.setSelectedItem(tm.getValueAt(tbllinks.getSelectedRow(), 5).toString());

        }
		 
	}

}
