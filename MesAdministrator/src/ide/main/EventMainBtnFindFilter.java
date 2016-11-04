package ide.main;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;
import bin.TableParser;

public class EventMainBtnFindFilter implements ActionListener{
	
	JFrame mainwindow;
	MainTable tbldatitransazioni;
	JLabel lblnumerorecord;
	JTextField txtdadata;
	JTextField txtadata;
	JTextField txtreparto;
	JTextField txtisola;
	JTextField txtstato;
	JTextField txttipo;
	JTextField txtbranch;
	JTextField txtrisorsa;
	JTextField txtdescrizionerisorsa;
	JTextField txtoperatore;
	JTextField txtdescrizioneoperatore;
	JTextField txtworkorder;
	JTextField txtoperazione;
	JTextField txtcommessa;
	JTextField txtarticolo;
	JTextField txtdescrizionearticolo;
	JCheckBox chcvisualizzarecoreliminati;
	JCheckBox chcvisualizzarecordprocessati;
	String filters = "";
	
	public EventMainBtnFindFilter(JFrame mainwindow,MainTable tbldatitransazioni,JLabel lblnumerorecord,JTextField txtdadata,JTextField txtadata,JTextField txtreparto,JTextField txtisola,JTextField txtstato,JTextField txttipo,JTextField txtbranch,JTextField txtrisorsa,JTextField txtdescrizionerisorsa,JTextField txtoperatore,JTextField txtdescrizioneoperatore,JTextField txtworkorder,JTextField txtoperazione,JTextField txtcommessa,JTextField txtarticolo,JTextField txtdescrizionearticolo,JCheckBox chcvisualizzarecoreliminati,JCheckBox chcvisualizzarecordprocessati){

		this.mainwindow = mainwindow;
		this.tbldatitransazioni = tbldatitransazioni;
		this.lblnumerorecord = lblnumerorecord;
		this.txtdadata = txtdadata;
		this.txtadata = txtadata;
		this.txtreparto = txtreparto;
		this.txtisola = txtisola;
		this.txtstato = txtstato;
		this.txttipo = txttipo;
		this.txtbranch = txtbranch;
		this.txtrisorsa = txtrisorsa;
		this.txtdescrizionerisorsa = txtdescrizionerisorsa;
		this.txtoperatore = txtoperatore;
		this.txtdescrizioneoperatore = txtdescrizioneoperatore;
		this.txtworkorder = txtworkorder;
		this.txtoperazione = txtoperazione;
		this.txtcommessa = txtcommessa;
		this.txtarticolo = txtarticolo;
		this.txtdescrizionearticolo = txtdescrizionearticolo;
		this.chcvisualizzarecoreliminati = chcvisualizzarecoreliminati;
		this.chcvisualizzarecordprocessati = chcvisualizzarecordprocessati;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		mainwindow.setCursor(hourglassCursor);
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		
		TableParser.clearData();
		
		if((txtdadata.getText().equals(""))&&(txtadata.getText().equals(""))){
			filters = "";
		}else if((!txtdadata.getText().equals(""))&&(txtadata.getText().equals(""))){
			if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
				filters = "AND DATE_FORMAT(ADDTUP,'%d/%m/%Y') >= DATE_FORMAT(STR_TO_DATE('" + txtdadata.getText() + "','%d/%m/%Y'),'%d/%m/%Y') ";
			}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
				filters = "AND TO_DATE(TO_CHAR(ADDTUP,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE('" + txtdadata.getText() + "','DD/MM/YYYY') ";
			}
		}else if((txtdadata.getText().equals(""))&&(!txtadata.getText().equals(""))){
			if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
				filters = "AND DATE_FORMAT(ADDTUP,'%d/%m/%Y') <= DATE_FORMAT(STR_TO_DATE('" + txtadata.getText() + "','%d/%m/%Y'),'%d/%m/%Y') ";
			}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
				filters = "AND TO_DATE(TO_CHAR(ADDTUP,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE('" + txtadata.getText() + "','DD/MM/YYYY') ";
			}
		}else if((!txtdadata.getText().equals(""))&&(!txtadata.getText().equals(""))){
			if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
				filters = "AND DATE_FORMAT(ADDTUP,'%d/%m/%Y') BETWEEN DATE_FORMAT(STR_TO_DATE('" + txtdadata.getText() + "','%d/%m/%Y'),'%d/%m/%Y') AND DATE_FORMAT(STR_TO_DATE('" + txtadata.getText() + "','%d/%m/%Y'),'%d/%m/%Y') ";
			}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
				filters = "AND TO_DATE(TO_CHAR(ADDTUP,'DD/MM/YYYY'),'DD/MM/YYYY') BETWEEN TO_DATE('" + txtdadata.getText() + "','DD/MM/YYYY') AND TO_DATE('" + txtadata.getText() + "','DD/MM/YYYY') ";
			}	
		}
		
		if(!txtreparto.getText().equals("")){
			filters = filters + "AND TRIM(ADDEPT) = '" + txtreparto.getText().trim() + "' ";
		}
		
		if(!txtisola.getText().equals("")){
			filters = filters + "AND TRIM(ADISL) = '" + txtisola.getText().trim().toUpperCase() + "' ";
		}
		
		if(!txtstato.getText().equals("")){
			filters = filters + "AND TRIM(ADYQ10ACCD) = '" + txtstato.getText().trim().toUpperCase() + "' ";
		}
		
		if(!txttipo.getText().equals("")){
			filters = filters + "AND TRIM(ADYQ10DECD) = '" + txttipo.getText().trim().toUpperCase() + "' ";
		}
		
		if(!txtbranch.getText().equals("")){
			filters = filters + "AND TRIM(ADMMCU) = '" + txtbranch.getText().trim().toUpperCase() + "' ";
		}
		
		if(!txtrisorsa.getText().equals("")){
			filters = filters + "AND ADAN8 = '" + txtrisorsa.getText().trim() + "' ";
		}
		
		if(!txtdescrizionerisorsa.getText().equals("")){
			filters = filters + "AND TRIM(ADALPH) LIKE '%" + txtdescrizionerisorsa.getText().trim() + "%' ";
		}
		
		if(!txtoperatore.getText().equals("")){
			filters = filters + "AND ADURAB = '" + txtoperatore.getText().trim() + "' ";
		}
		
		if(!txtdescrizioneoperatore.getText().equals("")){
			filters = filters + "AND TRIM(ADALPH1) LIKE '%" + txtdescrizioneoperatore.getText().trim() + "%' ";
		}
		
		if(!txtworkorder.getText().equals("")){
			filters = filters + "AND ADDOCO = '" + txtworkorder.getText().trim() + "' ";
		}
		
		if(!txtoperazione.getText().equals("")){
			filters = filters + "AND ADOPSQ = '" + txtoperazione.getText().trim() + "' ";
		}
		
		if(!txtcommessa.getText().equals("")){
			filters = filters + "AND ADPRJM = '" + txtcommessa.getText().trim() + "' ";
		}
		
		if(!txtarticolo.getText().equals("")){
			filters = filters + "AND TRIM(ADLITM) LIKE '%" + txtarticolo.getText().trim() + "%' ";
		}
		
		if(!txtdescrizionearticolo.getText().equals("")){
			filters = filters + "AND TRIM(ADDSC1) LIKE '%" + txtdescrizionearticolo.getText().trim().toUpperCase() + "%' ";
		}
		
		if(chcvisualizzarecoreliminati.isSelected()){
			filters = filters + "AND ADYQ10PR01 = 'D' ";
		}else{
			filters = filters + "AND ADYQ10PR01 != 'D' ";
		}
		
		if(!chcvisualizzarecordprocessati.isSelected()){
			filters = filters + "AND ADYQ10PR01 != 'P' ";
		}
		
		MainTableModel tm = new MainTableModel(null,null);
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			tm.setDataVector(DBMySqlManager.getTransaction(filters),tbldatitransazioni.getHeaders());
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			tm.setDataVector(DBOracleManager.getTransaction(filters),tbldatitransazioni.getHeaders());
		}
		
		tbldatitransazioni.setModel(tm);
		tbldatitransazioni.getTableLayout();
				
		lblnumerorecord.setText("Numero righe: " + formatter.format(tm.getRowCount()));
		
		filters = "";
		
		mainwindow.setCursor(normalCursor);
	}

}
