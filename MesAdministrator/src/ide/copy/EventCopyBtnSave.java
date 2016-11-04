package ide.copy;

import ide.main.MainTable;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;

public class EventCopyBtnSave implements ActionListener{
	
	CopyWindow cw;
	CopyTable tblcopiarecords;
	MainTable tbldatitransazioni;
	JLabel lblnumerorecord;
	
	public EventCopyBtnSave(CopyWindow cw,CopyTable tblcopiarecords,MainTable tbldatitransazioni,JLabel lblnumerorecord){

		this.cw = cw;
		this.tblcopiarecords = tblcopiarecords;
		this.tbldatitransazioni = tbldatitransazioni;
		this.lblnumerorecord = lblnumerorecord;

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		
		DefaultTableModel maintablemodel = (DefaultTableModel) tbldatitransazioni.getModel();
		DefaultTableModel copytablemodel = (DefaultTableModel) tblcopiarecords.getModel();

		cw.setCursor(hourglassCursor);
		
		try{
		
			if(tblcopiarecords.getCellEditor().stopCellEditing()){		
					
				int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sucuro di voler copiare i dati?", "Salvataggio Dati",JOptionPane.YES_NO_OPTION);
				
				if(dialogResult == JOptionPane.YES_OPTION){					
					for(int i=0;i<copytablemodel.getRowCount();i++){
						
						Vector<Object> transaction = new Vector<Object>();
						
						long id = 0;
						
						if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
							
							if(copytablemodel.getValueAt(i, 9).toString().equals("0")){
								transaction.addElement(copytablemodel.getValueAt(i, 6).toString());
							}else{
								transaction.addElement(DBMySqlManager.getBranchOfWorkOrder(copytablemodel.getValueAt(i, 9).toString()));
							}
							
							transaction.addElement(copytablemodel.getValueAt(i, 7).toString());
							
							if(copytablemodel.getValueAt(i, 9).toString().equals("0")){
								transaction.addElement(" ");
							}else{
								transaction.addElement(DBMySqlManager.getWorkCenterOfOperation(copytablemodel.getValueAt(i, 9).toString(), copytablemodel.getValueAt(i, 10).toString()));
							}
							
							transaction.addElement(copytablemodel.getValueAt(i, 4).toString().toUpperCase());

							if(copytablemodel.getValueAt(i, 5).toString().toUpperCase().equals("")){
								transaction.addElement(" ");
							}else{
								transaction.addElement(copytablemodel.getValueAt(i, 5).toString().toUpperCase());
							}
							
							transaction.addElement(copytablemodel.getValueAt(i, 9).toString());
							transaction.addElement(copytablemodel.getValueAt(i, 10).toString());
							transaction.addElement("0");
							transaction.addElement("0");
							transaction.addElement(" ");
							transaction.addElement(" ");
							transaction.addElement(copytablemodel.getValueAt(i, 3).toString());
							transaction.addElement("O");
							transaction.addElement(" ");
							transaction.addElement(" ");
							transaction.addElement(" ");
							transaction.addElement("0");
							transaction.addElement(copytablemodel.getValueAt(i, 8).toString());
							transaction.addElement("0");
							transaction.addElement(" ");
							transaction.addElement(" ");
							transaction.addElement("ENET");
							transaction.addElement("EWEBJ");
							transaction.addElement("E20MANAGER");
							transaction.addElement(Settings.getDate());
							transaction.addElement(Settings.getTime());
							transaction.addElement("0");
							transaction.addElement(DBMySqlManager.getElementDescription(copytablemodel.getValueAt(i, 7).toString()));
							transaction.addElement(DBMySqlManager.getElementDescription(copytablemodel.getValueAt(i, 8).toString()));
							transaction.addElement(copytablemodel.getValueAt(i, 1).toString());
							
							if(copytablemodel.getValueAt(i, 9).toString().equals("0")){
								transaction.addElement("0");
								transaction.addElement(" ");
								transaction.addElement(" ");
								transaction.addElement(" ");
							}else{
								transaction.addElement(DBMySqlManager.getProject(copytablemodel.getValueAt(i, 9).toString()));
								transaction.addElement(DBMySqlManager.getItemOfWorkOrder(copytablemodel.getValueAt(i, 9).toString()));
								transaction.addElement(DBMySqlManager.getItemDescription(copytablemodel.getValueAt(i, 9).toString()));
								transaction.addElement(DBMySqlManager.getOperationDescription(copytablemodel.getValueAt(i, 9).toString(), copytablemodel.getValueAt(i, 10).toString()));
							}
							
							transaction.addElement(copytablemodel.getValueAt(i, 2).toString().toUpperCase());
							transaction.addElement(" ");
							transaction.addElement(java.sql.Types.TIMESTAMP);
							transaction.addElement(Settings.getIpAddress());
							transaction.addElement(Settings.getEventDate());
							transaction.addElement(" ");
							transaction.addElement(java.sql.Types.TIMESTAMP);
							transaction.addElement(" ");
							transaction.addElement(java.sql.Types.TIMESTAMP);
	
							id = DBMySqlManager.copyTransaction(transaction);
							
						}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
							
							if(copytablemodel.getValueAt(i, 9).toString().equals("0")){
								transaction.addElement(copytablemodel.getValueAt(i, 6).toString());
							}else{
								transaction.addElement(DBOracleManager.getBranchOfWorkOrder(copytablemodel.getValueAt(i, 9).toString()));
							}
							
							transaction.addElement(copytablemodel.getValueAt(i, 7).toString());
							
							if(copytablemodel.getValueAt(i, 9).toString().equals("0")){
								transaction.addElement(" ");
							}else{
								transaction.addElement(DBOracleManager.getWorkCenterOfOperation(copytablemodel.getValueAt(i, 9).toString(), copytablemodel.getValueAt(i, 10).toString()));
							}
							
							transaction.addElement(copytablemodel.getValueAt(i, 4).toString().toUpperCase());
							
							if(copytablemodel.getValueAt(i, 5).toString().toUpperCase().equals("")){
								transaction.addElement(" ");
							}else{
								transaction.addElement(copytablemodel.getValueAt(i, 5).toString().toUpperCase());
							}
							
							transaction.addElement(copytablemodel.getValueAt(i, 9).toString());
							transaction.addElement(copytablemodel.getValueAt(i, 10).toString());
							transaction.addElement("0");
							transaction.addElement("0");
							transaction.addElement(" ");
							transaction.addElement(" ");
							transaction.addElement(copytablemodel.getValueAt(i, 3).toString());
							transaction.addElement("O");
							transaction.addElement(" ");
							transaction.addElement(" ");
							transaction.addElement(" ");
							transaction.addElement("0");
							transaction.addElement(copytablemodel.getValueAt(i, 8).toString());
							transaction.addElement("0");
							transaction.addElement(" ");
							transaction.addElement(" ");
							transaction.addElement("ENET");
							transaction.addElement("EWEBJ");
							transaction.addElement("E20MANAGER");
							transaction.addElement(Settings.getDate());
							transaction.addElement(Settings.getTime());
							transaction.addElement("0");
							transaction.addElement(DBOracleManager.getElementDescription(copytablemodel.getValueAt(i, 7).toString()));
							transaction.addElement(DBOracleManager.getElementDescription(copytablemodel.getValueAt(i, 8).toString()));
							transaction.addElement(copytablemodel.getValueAt(i, 1).toString());
							
							if(copytablemodel.getValueAt(i, 9).toString().equals("0")){
								transaction.addElement("0");
								transaction.addElement(" ");
								transaction.addElement(" ");
								transaction.addElement(" ");
							}else{
								transaction.addElement(DBOracleManager.getProject(copytablemodel.getValueAt(i, 9).toString()));
								transaction.addElement(DBOracleManager.getItemOfWorkOrder(copytablemodel.getValueAt(i, 9).toString()));
								transaction.addElement(DBOracleManager.getItemDescription(copytablemodel.getValueAt(i, 9).toString()));
								transaction.addElement(DBOracleManager.getOperationDescription(copytablemodel.getValueAt(i, 9).toString(), copytablemodel.getValueAt(i, 10).toString()));
							}
							
							transaction.addElement(copytablemodel.getValueAt(i, 2).toString().toUpperCase());
							transaction.addElement(" ");
							transaction.addElement(java.sql.Types.TIMESTAMP);
							transaction.addElement(Settings.getIpAddress());
							transaction.addElement(Settings.getEventDate());
							transaction.addElement(" ");
							transaction.addElement(java.sql.Types.TIMESTAMP);
							transaction.addElement(" ");
							transaction.addElement(java.sql.Types.TIMESTAMP);
	
							id = DBOracleManager.copyTransaction(transaction);
							
						}
						
						Vector<Object> copyrow = new Vector<Object>();
						
						copyrow.add(new Boolean(false));
						copyrow.add(id);
						copyrow.add(copytablemodel.getValueAt(i, 1));
						copyrow.add(copytablemodel.getValueAt(i, 2));
						copyrow.add(copytablemodel.getValueAt(i, 3));
						copyrow.add(copytablemodel.getValueAt(i, 4));
						copyrow.add(copytablemodel.getValueAt(i, 5));
						copyrow.add(copytablemodel.getValueAt(i, 6));
						copyrow.add(copytablemodel.getValueAt(i, 7));
						copyrow.add(DBOracleManager.getElementDescription(copytablemodel.getValueAt(i, 7).toString()));
						copyrow.add(copytablemodel.getValueAt(i, 8));
						copyrow.add(DBOracleManager.getElementDescription(copytablemodel.getValueAt(i, 8).toString()));
						copyrow.add(copytablemodel.getValueAt(i, 9));
						copyrow.add(copytablemodel.getValueAt(i, 10));
						copyrow.add(DBOracleManager.getOperationDescription(copytablemodel.getValueAt(i, 9).toString(), copytablemodel.getValueAt(i, 10).toString()));
						copyrow.add(DBOracleManager.getWorkCenterOfOperation(copytablemodel.getValueAt(i, 9).toString(), copytablemodel.getValueAt(i, 10).toString()));
						copyrow.add(DBOracleManager.getProject(copytablemodel.getValueAt(i, 9).toString()));
						copyrow.add(DBOracleManager.getItemOfWorkOrder(copytablemodel.getValueAt(i, 9).toString()));
						copyrow.add(DBOracleManager.getItemDescription(copytablemodel.getValueAt(i, 9).toString()));
						copyrow.add("E20MANAGER");
						copyrow.add("");
						copyrow.add(Settings.getIpAddress());
						copyrow.add(Settings.getEventDate());
						copyrow.add("");
						copyrow.add("");
						copyrow.add("");
						copyrow.add("");
						copyrow.add("");
						copyrow.add("");
						maintablemodel.addRow(copyrow);
					}
					
					for(int i=0;i<maintablemodel.getRowCount();i++){
						maintablemodel.setValueAt(false, i, 0);
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
					
					cw.dispose();
				}
				
			}
		}catch(NullPointerException e){
			
			JOptionPane.showMessageDialog(null,"Impossibile salvare i dati in quanto non è stata apportata alcuna modifica e non è possibile inserire record identici.");
			
		}
			
		cw.setCursor(normalCursor);


	}

}
