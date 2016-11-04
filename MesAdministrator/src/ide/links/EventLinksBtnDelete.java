package ide.links;

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

public class EventLinksBtnDelete implements ActionListener{
	
	LinksWindow lw;
	LinksTable tbllinks;
	JTextField txtnomeisola;
	JTextField txtsequenza;
	JTextField txtrisorsa;
	JComboBox<String> cmbabilitazione;
	JLabel lblnumerorecord;
	
	public EventLinksBtnDelete(LinksWindow lw,LinksTable tbllinks,JTextField txtnomeisola,JTextField txtsequenza,JTextField txtrisorsa,JComboBox<String> cmbabilitazione,JLabel lblnumerorecord){
		
		this.lw = lw;
		this.tbllinks = tbllinks;
		this.txtnomeisola = txtnomeisola;
		this.txtsequenza = txtsequenza;
		this.txtrisorsa = txtrisorsa;
		this.cmbabilitazione = cmbabilitazione;
		this.lblnumerorecord = lblnumerorecord;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		lw.setCursor(hourglassCursor);
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		
		DefaultTableModel linkstablemodel = (DefaultTableModel) tbllinks.getModel();
		
		if(tbllinks.getSelectedRow() > -1){
			
			int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sucuro di voler eliminare l'isola selezionata?", "Eliminazione Abbinamenti",JOptionPane.YES_NO_OPTION);
			
			if(dialogResult == JOptionPane.YES_OPTION){
				
				Vector<Object> links = new Vector<Object>();
				
				links.addElement(linkstablemodel.getValueAt(tbllinks.getSelectedRow(), 0).toString());
				links.addElement(linkstablemodel.getValueAt(tbllinks.getSelectedRow(), 1).toString());
				links.addElement(linkstablemodel.getValueAt(tbllinks.getSelectedRow(), 2).toString());
				
				if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
					DBMySqlManager.deleteLink(links);
				}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
					DBOracleManager.deleteLink(links);
				}
				
				linkstablemodel.removeRow(tbllinks.getSelectedRow());
				txtnomeisola.setText("");
				txtsequenza.setText("");
				txtrisorsa.setText("");
				cmbabilitazione.setSelectedIndex(0);
			}
		
			tbllinks.repaint();
			lblnumerorecord.setText("Numero righe: " + formatter.format(linkstablemodel.getRowCount()));
			
		}
		
		lw.setCursor(normalCursor);
		
	}

}
