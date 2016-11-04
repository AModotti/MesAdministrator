package ide.island;

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

public class EventIslandBtnInsert implements ActionListener{
	
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
	JLabel lblnumerorecord;
	
	public EventIslandBtnInsert(IslandWindow iw,IslandTable tblisole,JTextField txtindirizzoip,JTextField txtnomeisola,JTextField txtdescrizioneisola,JTextField txtreparto,JComboBox<String> cmbabilitazione,JComboBox<String> cmbmodalitamultioperatore,JComboBox<String> cmbabilitazionemultiordine,JComboBox<String> cmbricercaworkorderperarticolo,JLabel lblnumerorecord){
		
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
		this.lblnumerorecord = lblnumerorecord;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		iw.setCursor(hourglassCursor);
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		
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
				if(islandtablemodel.getValueAt(i, 0).toString().equals(txtindirizzoip.getText().trim())){
					existsip = existsip + 1;	
				}else{
					existsip = existsip + 0;
				}
			}
			int existsisland = 0;
			for (int i=0;i<islandtablemodel.getRowCount();i++) {
				if(islandtablemodel.getValueAt(i, 1).toString().equals(txtnomeisola.getText().trim())){
					existsisland = existsisland + 1;	
				}else{
					existsisland = existsisland + 0;
				}
			}
			
			if(existsip == 1){
				JOptionPane.showMessageDialog(iw, "L'indirizzo ip inserito è già assegnato ad un isola esistente","Inserimento Isola",JOptionPane.INFORMATION_MESSAGE);
				iw.setCursor(normalCursor);
			}else if(existsisland == 1){
				JOptionPane.showMessageDialog(iw, "L'isola in oggetto ha già un indirizzo ip assegnato","Inserimento Isola",JOptionPane.INFORMATION_MESSAGE);
				iw.setCursor(normalCursor);
			}else{
				
				Vector<Object> copyrow = new Vector<Object>();
		
				copyrow.add(txtindirizzoip.getText());
				copyrow.add(txtnomeisola.getText().trim().toUpperCase());
				copyrow.add(txtdescrizioneisola.getText().trim().toUpperCase());
				copyrow.add(txtreparto.getText().trim().toUpperCase());
				copyrow.add(cmbabilitazione.getSelectedItem());
				copyrow.add(cmbmodalitamultioperatore.getSelectedItem());
				copyrow.add(cmbabilitazionemultiordine.getSelectedItem());
				copyrow.add(cmbricercaworkorderperarticolo.getSelectedItem());
				copyrow.add(Settings.getIpAddress());
				copyrow.add(Settings.getEventDate());
				copyrow.add("");
				copyrow.add("");
				copyrow.add("");
				copyrow.add("");
				islandtablemodel.addRow(copyrow);
				
				Vector<Object> island = new Vector<Object>();
				
				island.addElement(txtindirizzoip.getText());
				island.addElement(txtnomeisola.getText().trim().toUpperCase());
				island.addElement(txtdescrizioneisola.getText().trim().toUpperCase());
				island.addElement(txtreparto.getText().trim().toUpperCase());
				island.addElement(cmbmodalitamultioperatore.getSelectedItem());
				island.addElement(cmbabilitazionemultiordine.getSelectedItem());
				island.addElement(cmbabilitazione.getSelectedItem());
				island.addElement("0");
				island.addElement(cmbricercaworkorderperarticolo.getSelectedItem());
				island.addElement(Settings.getIpAddress());
				island.addElement(Settings.getEventDate());
				island.addElement(" ");
				island.addElement(java.sql.Types.TIMESTAMP);
				island.addElement(" ");
				island.addElement(java.sql.Types.TIMESTAMP);
				
				if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
					DBMySqlManager.insertIsland(island);
				}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
					DBOracleManager.insertIsland(island);
				}
				
				tblisole.setRowHeight(20);
				
				txtindirizzoip.setText("");
				txtnomeisola.setText("");
				txtdescrizioneisola.setText("");
				txtreparto.setText("");
				cmbabilitazione.setSelectedIndex(0);
				cmbmodalitamultioperatore.setSelectedIndex(0);
				cmbabilitazionemultiordine.setSelectedIndex(0);
				cmbricercaworkorderperarticolo.setSelectedIndex(0);
		        
				JOptionPane.showMessageDialog(iw, "Isola inserita correttamente","Inserimento Isola",JOptionPane.INFORMATION_MESSAGE);
			
				tblisole.repaint();
				lblnumerorecord.setText("Numero righe: " + formatter.format(islandtablemodel.getRowCount()));

			}
		}
		
		iw.setCursor(normalCursor);
	}
	
}
