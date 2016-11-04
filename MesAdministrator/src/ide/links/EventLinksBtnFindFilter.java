package ide.links;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;
import bin.TableParser;


public class EventLinksBtnFindFilter implements ActionListener{
	
	LinksWindow lw;
	LinksTable tbllinks;
	JTextField txtnomeisola;
	JTextField txtsequenza;
	JTextField txtrisorsa;
	JTextField txtfiltrinomeisola;
	JTextField txtfiltririsorsa;
	JComboBox<String> cmbabilitazione;
	JLabel lblnumerorecord;
	String filters = "";
	
	public EventLinksBtnFindFilter(LinksWindow lw,LinksTable tbllinks,JTextField txtnomeisola,JTextField txtsequenza,JTextField txtrisorsa,JTextField txtfiltrinomeisola,JTextField txtfiltririsorsa,JComboBox<String> cmbabilitazione,JLabel lblnumerorecord){

		this.lw = lw;
		this.tbllinks = tbllinks;
		this.txtnomeisola = txtnomeisola;
		this.txtsequenza = txtsequenza;
		this.txtrisorsa = txtrisorsa;
		this.cmbabilitazione = cmbabilitazione;
		this.txtfiltrinomeisola = txtfiltrinomeisola;
		this.txtfiltririsorsa = txtfiltririsorsa;
		this.lblnumerorecord = lblnumerorecord;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		lw.setCursor(hourglassCursor);
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		
		TableParser.clearData();
				
		if(!txtfiltrinomeisola.getText().equals("")){
			filters = filters + "AND TRIM(IMUKID) LIKE '%" + txtfiltrinomeisola.getText().trim().toUpperCase() + "%' ";
		}
		
		if(!txtfiltririsorsa.getText().equals("")){
			filters = filters + "AND IMAN8 LIKE '%" + txtfiltririsorsa.getText().trim().toUpperCase() + "%' ";
		}
				
		LinksTableModel tm = new LinksTableModel(null,null);
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			tm.setDataVector(DBMySqlManager.getLinks(filters),tbllinks.getHeaders());
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			tm.setDataVector(DBOracleManager.getLinks(filters),tbllinks.getHeaders());
		}
				
		tbllinks.setModel(tm);
		tbllinks.setLayout();
		
		lblnumerorecord.setText("Numero righe: " + formatter.format(tm.getRowCount()));
		
		filters = "";
		
		lw.setCursor(normalCursor);
	}

}
