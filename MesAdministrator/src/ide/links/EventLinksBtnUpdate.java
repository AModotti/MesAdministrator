package ide.links;

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

public class EventLinksBtnUpdate implements ActionListener{
	
	LinksWindow lw;
	LinksTable tbllinks;
	JTextField txtnomeisola;
	JTextField txtsequenza;
	JTextField txtrisorsa;
	JComboBox<String> cmbabilitazione;
	
	public EventLinksBtnUpdate(LinksWindow lw,LinksTable tbllinks,JTextField txtnomeisola,JTextField txtsequenza,JTextField txtrisorsa,JComboBox<String> cmbabilitazione){
		
		this.lw = lw;
		this.tbllinks = tbllinks;
		this.txtnomeisola = txtnomeisola;
		this.txtsequenza = txtsequenza;
		this.txtrisorsa = txtrisorsa;
		this.cmbabilitazione = cmbabilitazione;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		lw.setCursor(hourglassCursor);
		
		DefaultTableModel linkstablemodel = (DefaultTableModel) tbllinks.getModel();
					
		linkstablemodel.setValueAt(txtsequenza.getText(),tbllinks.getSelectedRow(), 1);
		linkstablemodel.setValueAt(cmbabilitazione.getSelectedItem(),tbllinks.getSelectedRow(), 4);
		linkstablemodel.setValueAt(Settings.getIpAddress(),tbllinks.getSelectedRow(), 7);
		linkstablemodel.setValueAt(Settings.getEventDate(),tbllinks.getSelectedRow(), 8);
		
		Vector<Object> links = new Vector<Object>();
		
		links.addElement(txtnomeisola.getText());
		links.addElement(txtsequenza.getText());
		links.addElement(txtrisorsa.getText());
		links.addElement(cmbabilitazione.getSelectedItem());
		links.addElement(Settings.getIpAddress());
		links.addElement(Settings.getEventDate());
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			DBMySqlManager.updateLinks(links);
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			DBOracleManager.updateLinks(links);
		}
		
		JOptionPane.showMessageDialog(lw, "Abbinamento Isola - Risorsa modificato correttamente","Abbinamento Isola - Risorsa", JOptionPane.INFORMATION_MESSAGE);
		
		tbllinks.repaint();
		lw.setCursor(normalCursor);
		
	}

}
