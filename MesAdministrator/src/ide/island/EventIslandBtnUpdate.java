package ide.island;

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

public class EventIslandBtnUpdate implements ActionListener{
	
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
	
	public EventIslandBtnUpdate(IslandWindow iw,IslandTable tblisole,JTextField txtindirizzoip,JTextField txtnomeisola,JTextField txtdescrizioneisola,JTextField txtreparto,JComboBox<String> cmbabilitazione,JComboBox<String> cmbmodalitamultioperatore,JComboBox<String> cmbabilitazionemultiordine,JComboBox<String> cmbricercaworkorderperarticolo){
		
		this.iw = iw;
		this.tblisole = tblisole;
		this.txtindirizzoip = txtindirizzoip;
		this.txtnomeisola = txtnomeisola;
		this.txtdescrizioneisola = txtdescrizioneisola;
		this.txtreparto = txtreparto;
		this.cmbabilitazione = cmbabilitazione;
		this.cmbmodalitamultioperatore = cmbmodalitamultioperatore;
		this.cmbabilitazionemultiordine = cmbabilitazionemultiordine;
		this.cmbricercaworkorderperarticolo = cmbricercaworkorderperarticolo;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		iw.setCursor(hourglassCursor);
		
		DefaultTableModel islandtablemodel = (DefaultTableModel) tblisole.getModel();
		
		if(txtindirizzoip.getText().trim().equals("")){
			JOptionPane.showMessageDialog(iw, "L'indirizzo ip è un campo obbligatorio","Inserimento Isola",JOptionPane.INFORMATION_MESSAGE);
			iw.setCursor(normalCursor);
		}else if(txtnomeisola.getText().trim().equals("")){
			JOptionPane.showMessageDialog(iw, "Il nome dell'isola è un campo obbligatorio","Inserimento Isola",JOptionPane.INFORMATION_MESSAGE);	
			iw.setCursor(normalCursor);
		}else if(txtdescrizioneisola.getText().trim().equals("")){
			JOptionPane.showMessageDialog(iw, "La descrizione dell'isola è un campo obbligatorio","Inserimento Isola",JOptionPane.INFORMATION_MESSAGE);	
			iw.setCursor(normalCursor);
		}else if(txtreparto.getText().trim().equals("")){
			JOptionPane.showMessageDialog(iw, "Il reparto è un campo obbligatorio","Inserimento Isola",JOptionPane.INFORMATION_MESSAGE);	
			iw.setCursor(normalCursor);
		}else{
			int existsip = 0;
			for (int i=0;i<islandtablemodel.getRowCount();i++) {
				if((islandtablemodel.getValueAt(i, 0).toString().equals(txtindirizzoip.getText().trim()))&&
				   (!islandtablemodel.getValueAt(i, 1).toString().equals(txtnomeisola.getText().trim().toUpperCase()))){
					existsip = existsip + 1;
				}else{
					existsip = existsip + 0;
				}
			}
			
			if(existsip == 1){
				JOptionPane.showMessageDialog(iw, "L'indirizzo ip inserito è già assegnato ad un isola esistente","Inserimento Isola",JOptionPane.INFORMATION_MESSAGE);
				iw.setCursor(normalCursor);
			}else{
				
				txtdescrizioneisola.setText(txtdescrizioneisola.getText().trim().toUpperCase());
				txtreparto.setText(txtreparto.getText().trim().toUpperCase());
				
				islandtablemodel.setValueAt(txtindirizzoip.getText(),tblisole.getSelectedRow(), 0);
				islandtablemodel.setValueAt(txtdescrizioneisola.getText().trim().toUpperCase(),tblisole.getSelectedRow(), 2);
				islandtablemodel.setValueAt(txtreparto.getText().trim().toUpperCase(),tblisole.getSelectedRow(), 3);
				islandtablemodel.setValueAt(cmbabilitazione.getSelectedItem(),tblisole.getSelectedRow(), 4);
				islandtablemodel.setValueAt(cmbmodalitamultioperatore.getSelectedItem(),tblisole.getSelectedRow(), 5);
				islandtablemodel.setValueAt(cmbabilitazionemultiordine.getSelectedItem(),tblisole.getSelectedRow(), 6);
				islandtablemodel.setValueAt(cmbricercaworkorderperarticolo.getSelectedItem(),tblisole.getSelectedRow(), 7);
				islandtablemodel.setValueAt(Settings.getIpAddress(),tblisole.getSelectedRow(), 10);
				islandtablemodel.setValueAt(Settings.getEventDate(),tblisole.getSelectedRow(), 11);
				
				Vector<Object> island = new Vector<Object>();
				
				island.addElement(txtindirizzoip.getText());
				island.addElement(txtnomeisola.getText().trim().toUpperCase());
				island.addElement(txtdescrizioneisola.getText().trim().toUpperCase());
				island.addElement(txtreparto.getText().trim().toUpperCase());
				island.addElement(cmbmodalitamultioperatore.getSelectedItem());
				island.addElement(cmbabilitazionemultiordine.getSelectedItem());
				island.addElement(cmbabilitazione.getSelectedItem());
				island.addElement(cmbricercaworkorderperarticolo.getSelectedItem());
				island.addElement(Settings.getIpAddress());
				island.addElement(Settings.getEventDate());
				
				if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
					DBMySqlManager.updateIsland(island);
				}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
					DBOracleManager.updateIsland(island);
				}
				
				JOptionPane.showMessageDialog(iw, "Isola modificata correttamente","Modifica Isola", JOptionPane.INFORMATION_MESSAGE);
		
				
				tblisole.repaint();
				iw.setCursor(normalCursor);
			}
	
		}
	}

}
