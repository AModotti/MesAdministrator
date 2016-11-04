package ide.island;

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

public class EventIslandBtnDelete implements ActionListener{
	
	IslandWindow iw;
	IslandTable tblisole;
	JTextField txtindirizzoip;
	JTextField txtnomeisola;
	JTextField txtdescrizioneisola;
	JTextField txtreparto;
	JComboBox<String> cmbabilitazione;
	JComboBox<String> cmbmodalitamultioperatore;
	JComboBox<String> cmbmodalitamultiordine;
	JComboBox<String> cmbricercaworkorderperarticolo;
	JLabel lblnumerorecord;
	
	public EventIslandBtnDelete(IslandWindow iw,IslandTable tblisole,JTextField txtindirizzoip,JTextField txtnomeisola,JTextField txtdescrizioneisola,JTextField txtreparto,JComboBox<String> cmbabilitazione,JComboBox<String> cmbmodalitamultioperatore,JComboBox<String> cmbmodalitamultiordine,JComboBox<String> cmbricercaworkorderperarticolo,JLabel lblnumerorecord){
		
		this.iw = iw;
		this.tblisole = tblisole;
		this.txtindirizzoip = txtindirizzoip;
		this.txtnomeisola = txtnomeisola;
		this.txtdescrizioneisola = txtdescrizioneisola;
		this.txtreparto = txtreparto;
		this.cmbabilitazione = cmbabilitazione;
		this.cmbmodalitamultioperatore = cmbmodalitamultioperatore;
		this.cmbmodalitamultiordine = cmbmodalitamultiordine;
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
		
		if(tblisole.getSelectedRow() > -1){
		
			int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sucuro di voler eliminare l'isola selezionata?", "Eliminazione Isola",JOptionPane.YES_NO_OPTION);
			
			if(dialogResult == JOptionPane.YES_OPTION){
				if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
					DBMySqlManager.deleteIsland(islandtablemodel.getValueAt(tblisole.getSelectedRow(), 1).toString());
				}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
					DBOracleManager.deleteIsland(islandtablemodel.getValueAt(tblisole.getSelectedRow(), 1).toString());
				}

				islandtablemodel.removeRow(tblisole.getSelectedRow());
				
				txtindirizzoip.setText("");
				txtnomeisola.setText("");
				txtdescrizioneisola.setText("");
				txtreparto.setText("");
				cmbabilitazione.setSelectedIndex(0);
				cmbmodalitamultioperatore.setSelectedIndex(0);
				cmbmodalitamultiordine.setSelectedIndex(0);
				cmbricercaworkorderperarticolo.setSelectedIndex(0);
			}
						
			tblisole.repaint();
			lblnumerorecord.setText("Numero righe: " + formatter.format(islandtablemodel.getRowCount()));
		
		}
		
		iw.setCursor(normalCursor);
		
	}

}
