package ide.dept;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class EventDeptTableSelection implements ListSelectionListener{

	DeptWindow dw;
	DeptTable tblreparto;
	JTextField txtnomereparto;
	JTextField txtdescrizionereparto;
	JComboBox<String> cmbabilitazione;
	
	public EventDeptTableSelection(DeptWindow dw,DeptTable tblreparto,JTextField txtnomereparto,JTextField txtdescrizionereparto,JComboBox<String> cmbabilitazione){
		
		this.dw = dw;
		this.tblreparto = tblreparto;
		this.txtnomereparto = txtnomereparto;
		this.txtdescrizionereparto = txtdescrizionereparto;
		this.cmbabilitazione = cmbabilitazione;
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		DefaultTableModel tm = (DefaultTableModel) tblreparto.getModel();
		
		if (tblreparto.getSelectedRow() > -1) {
			txtnomereparto.setText(tm.getValueAt(tblreparto.getSelectedRow(), 0).toString());
			txtdescrizionereparto.setText(tm.getValueAt(tblreparto.getSelectedRow(), 1).toString());
 			cmbabilitazione.setSelectedItem(tm.getValueAt(tblreparto.getSelectedRow(), 2).toString());

        }
		 
	}

}
