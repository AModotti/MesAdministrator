package ide.insert;

import ide.main.MainTable;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;

public class EventInsertBtnSave implements ActionListener{
	
	InsertWindow iw;
	MainTable tbldatitransazioni;
	JTextField txtreparto;
	JTextField txtisola;
	JTextField txtdatatransazione;
	JTextField txtstato;
	JTextField txttipo;
	JTextField txtbranch;
	JTextField txtrisorsa;
	JTextField txtoperatore;
	JTextField txtworkorder;
	JTextField txtoperazione;
	JLabel lblnumerorecord;
	JTextField e1;
	JTextField e2;
	JTextField e3;
	JTextField e4;
	JTextField e5;
	JTextField e6;
	JTextField e7;
	JTextField e8;
	JTextField e9;
	JTextField e10;
	
	public EventInsertBtnSave(InsertWindow iw,MainTable tbldatitransazioni,JTextField txtreparto,JTextField txtisola,JTextField txtdatatransazione,JTextField txtstato,JTextField txttipo,JTextField txtbranch,JTextField txtrisorsa,JTextField txtoperatore,JTextField txtworkorder,JTextField txtoperazione,JLabel lblnumerorecord,JTextField e1,JTextField e2,JTextField e3,JTextField e4,JTextField e5,JTextField e6,JTextField e7,JTextField e8,JTextField e9,JTextField e10){

		this.iw = iw;
		this.tbldatitransazioni = tbldatitransazioni;
		this.txtreparto = txtreparto;
		this.txtisola = txtisola;
		this.txtdatatransazione = txtdatatransazione;
		this.txtstato = txtstato;
		this.txttipo = txttipo;
		this.txtbranch = txtbranch;
		this.txtrisorsa = txtrisorsa;
		this.txtoperatore = txtoperatore;
		this.txtworkorder = txtworkorder;
		this.txtoperazione = txtoperazione;
		this.lblnumerorecord = lblnumerorecord;
		this.e1 = e1;
		this.e2 = e2;
		this.e3 = e3;
		this.e4 = e4;
		this.e5 = e5;
		this.e6 = e6;
		this.e7 = e7;
		this.e8 = e8;
		this.e9 = e9;
		this.e10 = e10;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		iw.setCursor(hourglassCursor);
		
		if((e1.getText().equals("0"))&&(e2.getText().equals("0"))&&(e3.getText().equals("0"))&&(e4.getText().equals("0"))&&(e5.getText().equals("0"))&&
		   (e6.getText().equals("0"))&&(e7.getText().equals("0"))&&(e8.getText().equals("0"))&&(e9.getText().equals("0"))&&(e10.getText().equals("0"))){
		
			DecimalFormat formatter = new DecimalFormat("###,###");
			
			DefaultTableModel maintablemodel = (DefaultTableModel) tbldatitransazioni.getModel();
			
			Vector<Object> transaction = new Vector<Object>();
			
			if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					transaction.addElement(txtbranch.getText().trim().toUpperCase());
				}else{
					transaction.addElement(DBOracleManager.getBranchOfWorkOrder(txtworkorder.getText().trim()));
				}
				
				transaction.addElement(txtrisorsa.getText().trim());
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					transaction.addElement(" ");
				}else{
					transaction.addElement(DBMySqlManager.getWorkCenterOfOperation(txtworkorder.getText().trim(), txtoperazione.getText().trim()));
				}
				
				transaction.addElement(txttipo.getText().trim());
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					transaction.addElement(" ");	
				}else{
					transaction.addElement(txtstato.getText().trim().toUpperCase());	
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					transaction.addElement("0");
					transaction.addElement("0");
				}else{
					transaction.addElement(txtworkorder.getText().trim());
					transaction.addElement(txtoperazione.getText().trim());
				}
				
				transaction.addElement("0");
				transaction.addElement("0");
				transaction.addElement(" ");
				transaction.addElement(" ");
				transaction.addElement(txtdatatransazione.getText().trim());
				transaction.addElement("O");
				transaction.addElement(" ");
				transaction.addElement(" ");
				transaction.addElement(" ");
				transaction.addElement("0");
				transaction.addElement(txtoperatore.getText().trim());
				transaction.addElement("0");
				transaction.addElement(" ");
				transaction.addElement(" ");
				transaction.addElement("ENET");
				transaction.addElement("EWEBJ");
				transaction.addElement("E20MANAGER");
				transaction.addElement(Settings.getDate());
				transaction.addElement(Settings.getTime());
				transaction.addElement("0");
				transaction.addElement(DBMySqlManager.getElementDescription(txtrisorsa.getText().trim()));
				transaction.addElement(DBMySqlManager.getElementDescription(txtoperatore.getText().trim()));
				transaction.addElement(txtreparto.getText().trim());
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					transaction.addElement("0");
					transaction.addElement(" ");
					transaction.addElement(" ");
					transaction.addElement(" ");
				}else{
					transaction.addElement(DBMySqlManager.getProject(txtworkorder.getText()));
					transaction.addElement(DBMySqlManager.getItemOfWorkOrder(txtworkorder.getText().trim()));
					transaction.addElement(DBMySqlManager.getItemDescription(txtworkorder.getText().trim()));
					transaction.addElement(DBMySqlManager.getOperationDescription(txtworkorder.getText().trim(), txtoperazione.getText().trim()));
				}
				
				transaction.addElement(txtisola.getText().trim().toUpperCase());
				transaction.addElement(" ");
				transaction.addElement(java.sql.Types.TIMESTAMP);
				transaction.addElement(Settings.getIpAddress());
				transaction.addElement(Settings.getEventDate());
				transaction.addElement(" ");
				transaction.addElement(java.sql.Types.TIMESTAMP);
				transaction.addElement(" ");
				transaction.addElement(java.sql.Types.TIMESTAMP);

				long id = DBMySqlManager.insertTransaction(transaction);
				    	
				Vector<Object> insertrow = new Vector<Object>();
				
				insertrow.add(new Boolean(false));
				insertrow.add(id);
				insertrow.add(txtreparto.getText().trim());
				insertrow.add(txtisola.getText().trim().toUpperCase());
				insertrow.add(txtdatatransazione.getText().trim());
				insertrow.add(txttipo.getText().trim());
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("");
				}else{
					insertrow.add(txtstato.getText().trim().toUpperCase());
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add(txtbranch.getText().trim().toUpperCase());
				}else{
					insertrow.add(DBMySqlManager.getBranchOfWorkOrder(txtworkorder.getText().trim()));
				}
				
				insertrow.add(txtrisorsa.getText().trim());
				insertrow.add(DBMySqlManager.getElementDescription(txtrisorsa.getText().trim()));
				insertrow.add(txtoperatore.getText().trim());
				insertrow.add(DBMySqlManager.getElementDescription(txtoperatore.getText().trim()));
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("0");
				}else{
					insertrow.add(txtworkorder.getText().trim());
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("0");
				}else{
					insertrow.add(txtoperazione.getText().trim());
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("");
				}else{
					insertrow.add(DBMySqlManager.getOperationDescription(txtworkorder.getText().trim(), txtoperazione.getText().trim()));
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("");
				}else{
					insertrow.add(DBMySqlManager.getWorkCenterOfOperation(txtworkorder.getText().trim(), txtoperazione.getText().trim()));
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("0");
				}else{
					insertrow.add(DBMySqlManager.getProject(txtworkorder.getText()));
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("");
				}else{
					insertrow.add(DBMySqlManager.getItemOfWorkOrder(txtworkorder.getText().trim()));
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("");
				}else{
					insertrow.add(DBMySqlManager.getItemDescription(txtworkorder.getText().trim()));
				}
				
				insertrow.add("E20MANAGER");
				insertrow.add("");
				insertrow.add(Settings.getIpAddress());
				insertrow.add(Settings.getEventDate());
				insertrow.add("");
				insertrow.add("");
				insertrow.add("");
				insertrow.add("");
				insertrow.add("");
				insertrow.add("");
				
				maintablemodel.addRow(insertrow);
				
			}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){

				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					transaction.addElement(txtbranch.getText().trim().toUpperCase());
				}else{
					transaction.addElement(DBOracleManager.getBranchOfWorkOrder(txtworkorder.getText().trim()));
				}
				
				transaction.addElement(txtrisorsa.getText().trim());
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					transaction.addElement(" ");
				}else{
					transaction.addElement(DBOracleManager.getWorkCenterOfOperation(txtworkorder.getText().trim(), txtoperazione.getText().trim()));
				}
				
				transaction.addElement(txttipo.getText().trim());
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					transaction.addElement(" ");	
				}else{
					transaction.addElement(txtstato.getText().trim().toUpperCase());	
				}

				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					transaction.addElement("0");
					transaction.addElement("0");
				}else{
					transaction.addElement(txtworkorder.getText().trim());
					transaction.addElement(txtoperazione.getText().trim());
				}
				
				transaction.addElement("0");
				transaction.addElement("0");
				transaction.addElement(" ");
				transaction.addElement(" ");
				transaction.addElement(txtdatatransazione.getText().trim());
				transaction.addElement("O");
				transaction.addElement(" ");
				transaction.addElement(" ");
				transaction.addElement(" ");
				transaction.addElement("0");
				transaction.addElement(txtoperatore.getText().trim());
				transaction.addElement("0");
				transaction.addElement(" ");
				transaction.addElement(" ");
				transaction.addElement("ENET");
				transaction.addElement("EWEBJ");
				transaction.addElement("E20MANAGER");
				transaction.addElement(Settings.getDate());
				transaction.addElement(Settings.getTime());
				transaction.addElement("0");
				transaction.addElement(DBOracleManager.getElementDescription(txtrisorsa.getText().trim()));
				transaction.addElement(DBOracleManager.getElementDescription(txtoperatore.getText().trim()));
				transaction.addElement(txtreparto.getText().trim());
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					transaction.addElement("0");
					transaction.addElement(" ");
					transaction.addElement(" ");
					transaction.addElement(" ");
				}else{
					transaction.addElement(DBOracleManager.getProject(txtworkorder.getText()));
					transaction.addElement(DBOracleManager.getItemOfWorkOrder(txtworkorder.getText().trim()));
					transaction.addElement(DBOracleManager.getItemDescription(txtworkorder.getText().trim()));
					transaction.addElement(DBOracleManager.getOperationDescription(txtworkorder.getText().trim(), txtoperazione.getText().trim()));
				}
				
				transaction.addElement(txtisola.getText().trim().toUpperCase());
				transaction.addElement(" ");
				transaction.addElement(java.sql.Types.TIMESTAMP);
				transaction.addElement(Settings.getIpAddress());
				transaction.addElement(Settings.getEventDate());
				transaction.addElement(" ");
				transaction.addElement(java.sql.Types.TIMESTAMP);
				transaction.addElement(" ");
				transaction.addElement(java.sql.Types.TIMESTAMP);

				long id = DBOracleManager.insertTransaction(transaction);
				    	
				Vector<Object> insertrow = new Vector<Object>();
				
				insertrow.add(new Boolean(false));
				insertrow.add(id);
				insertrow.add(txtreparto.getText().trim());
				insertrow.add(txtisola.getText().trim().toUpperCase());
				insertrow.add(txtdatatransazione.getText().trim());
				insertrow.add(txttipo.getText().trim());
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("");
				}else{
					insertrow.add(txtstato.getText().trim().toUpperCase());
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add(txtbranch.getText().trim().toUpperCase());
				}else{
					insertrow.add(DBOracleManager.getBranchOfWorkOrder(txtworkorder.getText().trim()));
				}
				
				insertrow.add(txtrisorsa.getText().trim());
				insertrow.add(DBOracleManager.getElementDescription(txtrisorsa.getText().trim()));
				insertrow.add(txtoperatore.getText().trim());
				insertrow.add(DBOracleManager.getElementDescription(txtoperatore.getText().trim()));
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("0");
				}else{
					insertrow.add(txtworkorder.getText().trim());
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("0");
				}else{
					insertrow.add(txtoperazione.getText().trim());
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("");
				}else{
					insertrow.add(DBOracleManager.getOperationDescription(txtworkorder.getText().trim(), txtoperazione.getText().trim()));
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("");
				}else{
					insertrow.add(DBOracleManager.getWorkCenterOfOperation(txtworkorder.getText().trim(), txtoperazione.getText().trim()));
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("0");
				}else{
					insertrow.add(DBOracleManager.getProject(txtworkorder.getText()));
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("");
				}else{
					insertrow.add(DBOracleManager.getItemOfWorkOrder(txtworkorder.getText().trim()));
				}
				
				if((txttipo.getText().trim().equals("ING"))||(txttipo.getText().trim().equals("USC"))){
					insertrow.add("");
				}else{
					insertrow.add(DBOracleManager.getItemDescription(txtworkorder.getText().trim()));
				}
				
				insertrow.add("E20MANAGER");
				insertrow.add("");
				insertrow.add(Settings.getIpAddress());
				insertrow.add(Settings.getEventDate());
				insertrow.add("");
				insertrow.add("");
				insertrow.add("");
				insertrow.add("");
				insertrow.add("");
				insertrow.add("");
				
				maintablemodel.addRow(insertrow);
			
			}
			
			tbldatitransazioni.setRowHeight(20);
	    
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tbldatitransazioni.getModel());
            tbldatitransazioni.setRowSorter(sorter);

            ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();

            sortKeys.add(new RowSorter.SortKey(8, SortOrder.ASCENDING));
            sortKeys.add(new RowSorter.SortKey(4, SortOrder.DESCENDING));
            sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
            
            sorter.setSortKeys(sortKeys);
            
            for(int i=0;i<maintablemodel.getColumnCount();i++){
                sorter.setSortable(i, false);
			}

            
			tbldatitransazioni.repaint();
			lblnumerorecord.setText("Numero righe: " + formatter.format(maintablemodel.getRowCount()));
			iw.dispose();
		}else{
			JOptionPane.showMessageDialog(iw,"I dati inseriti non sono corretti o sono incompleti","Salvataggio Dati",JOptionPane.ERROR_MESSAGE);
		}
		
		iw.setCursor(normalCursor);
	}

}
