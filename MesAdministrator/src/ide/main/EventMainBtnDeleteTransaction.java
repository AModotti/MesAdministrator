package ide.main;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;
import bin.TableParser;

public class EventMainBtnDeleteTransaction implements ActionListener {

	JFrame mw;
	MainTable tbldatitransazioni;
	JLabel lblnumerorecord;
	
	public EventMainBtnDeleteTransaction(JFrame mw,MainTable tbldatitransazioni,JLabel lblnumerorecord){
		
		this.mw = mw;
		this.tbldatitransazioni = tbldatitransazioni;
		this.lblnumerorecord = lblnumerorecord;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		mw.setCursor(hourglassCursor);
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		
		DefaultTableModel maintablemodel = (DefaultTableModel) tbldatitransazioni.getModel();
		
		int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sucuro di voler eliminare le transazioni selezionati?", "Eliminazione Transazioni",JOptionPane.YES_NO_OPTION);
		
		if(dialogResult == JOptionPane.YES_OPTION){
			
			int rowCount = maintablemodel.getRowCount();
			for(int i=0;i<rowCount;i++){
			  Boolean selected = (Boolean) maintablemodel.getValueAt(i, 0);
			  if(selected){
				  if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
					  DBMySqlManager.deleteTransaction(maintablemodel.getValueAt(i, 1).toString());
				  }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
					  DBOracleManager.deleteTransaction(maintablemodel.getValueAt(i, 1).toString());
				  } 

				  maintablemodel.removeRow(i);
				  i = i - 1;
				  rowCount = rowCount - 1;
			  }
			}
		}
		
		TableParser.clearData();
		tbldatitransazioni.revalidate();
		tbldatitransazioni.repaint();
		lblnumerorecord.setText("Numero righe: " + formatter.format(maintablemodel.getRowCount()));
		mw.setCursor(normalCursor);
	
	}
}
