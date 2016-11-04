package ide.dept;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;

public class EventDeptBtnInsert implements ActionListener{
	
	DeptWindow dw;
	DeptTable tblreparti;
	JTextField txtnomereparto;
	JTextField txtdescrizionereparto;
	JComboBox<String> cmbabilitazione;
	JLabel lblnumerorecord;
	
	public EventDeptBtnInsert(DeptWindow dw,DeptTable tblreparti,JTextField txtnomereparto,JTextField txtdescrizionereparto,JComboBox<String> cmbabilitazione,JLabel lblnumerorecord){
		
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
		
		if(txtnomereparto.getText().trim().equals("")){
			JOptionPane.showMessageDialog(dw, "Il reparto è un campo obbligatorio","Inserimento Reparto",JOptionPane.INFORMATION_MESSAGE);
			dw.setCursor(normalCursor);
		}else if(txtdescrizionereparto.getText().trim().equals("")){
			JOptionPane.showMessageDialog(dw, "La descrizione del reparto è un campo obbligatorio","Inserimento Reparto",JOptionPane.INFORMATION_MESSAGE);	
			dw.setCursor(normalCursor);
		}else{
			int existname = 0;
			for (int i=0;i<depttablemodel.getRowCount();i++) {
				if(depttablemodel.getValueAt(i, 0).toString().equals(txtnomereparto.getText().trim())){
					existname = existname + 1;	
				}else{
					existname = existname + 0;
				}
			}
						
			if(existname == 1){
				JOptionPane.showMessageDialog(dw, "Esiste già un reparto con questo nome","Inserimento Isola",JOptionPane.INFORMATION_MESSAGE);
				dw.setCursor(normalCursor);
			}else{
				Vector<Object> copyrow = new Vector<Object>();
		
				copyrow.add(txtnomereparto.getText());
				copyrow.add(txtdescrizionereparto.getText().trim().toUpperCase());
				copyrow.add(cmbabilitazione.getSelectedItem());
				copyrow.add(Settings.getIpAddress());
				copyrow.add(Settings.getEventDate());
				copyrow.add("");
				copyrow.add("");
				copyrow.add("");
				copyrow.add("");
				depttablemodel.addRow(copyrow);
				
				Vector<Object> dept = new Vector<Object>();
				
				dept.addElement(txtnomereparto.getText());
				dept.addElement(txtdescrizionereparto.getText().trim().toUpperCase());
				dept.addElement(cmbabilitazione.getSelectedItem());
				dept.addElement("0");
				dept.addElement(Settings.getIpAddress());
				dept.addElement(Settings.getEventDate());
				dept.addElement(" ");
				dept.addElement(java.sql.Types.TIMESTAMP);
				dept.addElement(" ");
				dept.addElement(java.sql.Types.TIMESTAMP);
				
				if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
					DBMySqlManager.insertDept(dept);
				}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
					DBOracleManager.insertDept(dept);
				}
				
				tblreparti.setRowHeight(20);
				
				txtnomereparto.setText("");
				txtdescrizionereparto.setText("");
				cmbabilitazione.setSelectedIndex(0);
		        
				JOptionPane.showMessageDialog(dw, "Reparto inserito correttamente","Inserimento Reparto",JOptionPane.INFORMATION_MESSAGE);
			
				tblreparti.repaint();
				lblnumerorecord.setText("Numero righe: " + formatter.format(depttablemodel.getRowCount()));

			}
		}
		
		dw.setCursor(normalCursor);
	}
	
}
