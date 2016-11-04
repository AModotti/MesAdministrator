package ide.dept;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;

public class EventDeptBtnDelete implements ActionListener{
	
	DeptWindow dw;
	DeptTable tblreparti;
	JTextField txtnomereparto;
	JTextField txtdescrizionereparto;
	JComboBox<String> cmbabilitazione;
	JLabel lblnumerorecord;
	
	public EventDeptBtnDelete(DeptWindow dw,DeptTable tblreparti,JTextField txtnomereparto,JTextField txtdescrizionereparto,JComboBox<String> cmbabilitazione,JLabel lblnumerorecord){
		
		this.dw = dw;
		this.tblreparti = tblreparti;
		this.txtnomereparto = txtnomereparto;
		this.txtdescrizionereparto = txtdescrizionereparto;
		this.cmbabilitazione = cmbabilitazione;
		this.lblnumerorecord = lblnumerorecord;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		dw.setCursor(hourglassCursor);
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		
		DefaultTableModel depttablemodel = (DefaultTableModel) tblreparti.getModel();
		
		if(tblreparti.getSelectedRow() > -1){
		
			int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sucuro di voler eliminare il reparto selezionato?", "Eliminazione Reparto",JOptionPane.YES_NO_OPTION);
			
			if(dialogResult == JOptionPane.YES_OPTION){
				if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
					DBMySqlManager.deleteDept(depttablemodel.getValueAt(tblreparti.getSelectedRow(), 0).toString());
				}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
					DBOracleManager.deleteDept(depttablemodel.getValueAt(tblreparti.getSelectedRow(), 0).toString());
				}

				depttablemodel.removeRow(tblreparti.getSelectedRow());
				
				txtnomereparto.setText("");
				txtdescrizionereparto.setText("");
				cmbabilitazione.setSelectedIndex(0);
			}
						
			tblreparti.repaint();
			lblnumerorecord.setText("Numero righe: " + formatter.format(depttablemodel.getRowCount()));
		
		}
		
		dw.setCursor(normalCursor);
		
	}

}
