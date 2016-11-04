package ide.island;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class EventIslandTableSelection implements ListSelectionListener{

	IslandWindow iw;
	IslandTable tblisole;
	JTextField txtindirizzoip;
	JTextField txtnomeisola;
	JTextField txtdescrizioneisola;
	JTextField txtreparto;
	JComboBox<String> cmbabilitazione;
	JComboBox<String> cmbmodalitamultioperatore;
	JComboBox<String> cmbabilitazionemultiordine;
	JComboBox<String> cmbricercaworkorderperarticolo;
	
	
	public EventIslandTableSelection(IslandWindow iw,IslandTable tblisole,JTextField txtindirizzoip,JTextField txtnomeisola,JTextField txtreparto,JTextField txtdescrizioneisola,JComboBox<String> cmbabilitazione,JComboBox<String> cmbmodalitamultioperatore,JComboBox<String> cmbabilitazionemultiordine,JComboBox<String> cmbricercaworkorderperarticolo){
		
		this.iw = iw;
		this.tblisole = tblisole;
		this.txtindirizzoip = txtindirizzoip;
		this.txtnomeisola = txtnomeisola;
		this.txtreparto = txtreparto;
		this.txtdescrizioneisola = txtdescrizioneisola;
		this.cmbabilitazione = cmbabilitazione;
		this.cmbmodalitamultioperatore = cmbmodalitamultioperatore;
		this.cmbabilitazionemultiordine = cmbabilitazionemultiordine;
		this.cmbricercaworkorderperarticolo = cmbricercaworkorderperarticolo;
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		DefaultTableModel tm = (DefaultTableModel) tblisole.getModel();
		
		if (tblisole.getSelectedRow() > -1) {
			txtindirizzoip.setText(tm.getValueAt(tblisole.getSelectedRow(), 0).toString());
 			txtnomeisola.setText(tm.getValueAt(tblisole.getSelectedRow(), 1).toString());
 			txtreparto.setText(tm.getValueAt(tblisole.getSelectedRow(), 2).toString());
 			txtdescrizioneisola.setText(tm.getValueAt(tblisole.getSelectedRow(), 3).toString());
 			cmbabilitazione.setSelectedItem(tm.getValueAt(tblisole.getSelectedRow(), 4).toString());
 			cmbmodalitamultioperatore.setSelectedItem(tm.getValueAt(tblisole.getSelectedRow(), 5).toString());
 			cmbabilitazionemultiordine.setSelectedItem(tm.getValueAt(tblisole.getSelectedRow(), 6).toString());
 			cmbricercaworkorderperarticolo.setSelectedItem(tm.getValueAt(tblisole.getSelectedRow(), 7).toString());
        }
		 
	}

}
