package ide.dept;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;

public class EventDeptBtnUpdate implements ActionListener{
	
	DeptWindow dw;
	DeptTable tblreparti;
	JTextField txtnomereparto;
	JTextField txtdescrizionereparto;
	JComboBox<String> cmbabilitazione;
	
	public EventDeptBtnUpdate(DeptWindow dw,DeptTable tblreparti,JTextField txtnomereparto,JTextField txtdescrizionereparto,JComboBox<String> cmbabilitazione){
		
		this.dw = dw;
		this.tblreparti = tblreparti;
		this.txtnomereparto = txtnomereparto;
		this.txtdescrizionereparto = txtdescrizionereparto;
		this.cmbabilitazione = cmbabilitazione;
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		dw.setCursor(hourglassCursor);
		
		DefaultTableModel depttablemodel = (DefaultTableModel) tblreparti.getModel();
		
		if(txtnomereparto.getText().trim().equals("")){
			JOptionPane.showMessageDialog(dw, "Il reparto è un campo obbligatorio","Inserimento Reparto",JOptionPane.INFORMATION_MESSAGE);
			dw.setCursor(normalCursor);
		}else if(txtdescrizionereparto.getText().trim().equals("")){
			JOptionPane.showMessageDialog(dw, "La descrizione del reparto è un campo obbligatorio","Inserimento Reparto",JOptionPane.INFORMATION_MESSAGE);	
			dw.setCursor(normalCursor);
		}else{
			
			txtdescrizionereparto.setText(txtdescrizionereparto.getText().trim().toUpperCase());
			
			depttablemodel.setValueAt(txtdescrizionereparto.getText().trim().toUpperCase(),tblreparti.getSelectedRow(), 1);
			depttablemodel.setValueAt(cmbabilitazione.getSelectedItem(),tblreparti.getSelectedRow(), 2);
			depttablemodel.setValueAt(Settings.getIpAddress(),tblreparti.getSelectedRow(), 5);
			depttablemodel.setValueAt(Settings.getEventDate(),tblreparti.getSelectedRow(), 6);
			
			Vector<Object> dept = new Vector<Object>();
			
			dept.addElement(txtnomereparto.getText());
			dept.addElement(txtdescrizionereparto.getText().trim().toUpperCase());
			dept.addElement(cmbabilitazione.getSelectedItem());
			dept.addElement(Settings.getIpAddress());
			dept.addElement(Settings.getEventDate());
			
			if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
				DBMySqlManager.updateDept(dept);
			}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
				DBOracleManager.updateDept(dept);
			}
			
			JOptionPane.showMessageDialog(dw, "Reparto modificato correttamente","Modifica Reparto", JOptionPane.INFORMATION_MESSAGE);
	
			
			tblreparti.repaint();
			dw.setCursor(normalCursor);
	
		}
	}

}
