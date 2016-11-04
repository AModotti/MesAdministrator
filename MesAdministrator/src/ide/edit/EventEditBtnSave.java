package ide.edit;

import ide.main.MainTable;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;

public class EventEditBtnSave implements ActionListener{
	
	EditWindow ew;
	EditTable tblmodificarecords;
	MainTable tbldatitransazioni;
	
	public EventEditBtnSave(EditWindow ew,EditTable tblmodificarecords,MainTable tbldatitransazioni){

		this.ew = ew;
		this.tblmodificarecords = tblmodificarecords;
		this.tbldatitransazioni = tbldatitransazioni;

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		DefaultTableModel maintablemodel = (DefaultTableModel) tbldatitransazioni.getModel();
		DefaultTableModel edittablemodel = (DefaultTableModel) tblmodificarecords.getModel();

		ew.setCursor(hourglassCursor);
		
		try{
			
			if(tblmodificarecords.getCellEditor().stopCellEditing()){

				int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sucuro di voler salvare i dati?", "Salvataggio Dati",JOptionPane.YES_NO_OPTION);
				
				if(dialogResult == JOptionPane.YES_OPTION){
					for(int i=0;i<edittablemodel.getRowCount();i++){
						maintablemodel.setValueAt(edittablemodel.getValueAt(i, 1).toString().toUpperCase(),(int)edittablemodel.getValueAt(i, 11), 2);
						maintablemodel.setValueAt(edittablemodel.getValueAt(i, 2).toString().toUpperCase(),(int)edittablemodel.getValueAt(i, 11), 3);
						maintablemodel.setValueAt(edittablemodel.getValueAt(i, 3).toString().toUpperCase(),(int)edittablemodel.getValueAt(i, 11), 4);
						maintablemodel.setValueAt(edittablemodel.getValueAt(i, 4).toString().toUpperCase(),(int)edittablemodel.getValueAt(i, 11), 5);
						maintablemodel.setValueAt(edittablemodel.getValueAt(i, 5).toString().toUpperCase(),(int)edittablemodel.getValueAt(i, 11), 6);
						maintablemodel.setValueAt(edittablemodel.getValueAt(i, 6).toString().toUpperCase(),(int)edittablemodel.getValueAt(i, 11), 7);
						maintablemodel.setValueAt(edittablemodel.getValueAt(i, 7).toString().toUpperCase(),(int)edittablemodel.getValueAt(i, 11), 8);
						
						String resourcedescription = DBOracleManager.getElementDescription(edittablemodel.getValueAt(i, 7).toString());
						if(resourcedescription == null){resourcedescription = "";}
						maintablemodel.setValueAt(resourcedescription,(int)edittablemodel.getValueAt(i, 11), 9);
						
						maintablemodel.setValueAt(edittablemodel.getValueAt(i, 8),(int)edittablemodel.getValueAt(i, 11), 10);
						
						String operatordescription = DBOracleManager.getElementDescription(edittablemodel.getValueAt(i, 8).toString());
						if(operatordescription == null){operatordescription = "";}
						maintablemodel.setValueAt(operatordescription,(int)edittablemodel.getValueAt(i, 11), 11);
						
						maintablemodel.setValueAt(edittablemodel.getValueAt(i, 9),(int)edittablemodel.getValueAt(i, 11), 12);
						maintablemodel.setValueAt(edittablemodel.getValueAt(i, 10),(int)edittablemodel.getValueAt(i, 11), 13);
						
						String operationdescription = DBOracleManager.getOperationDescription(edittablemodel.getValueAt(i, 9).toString(),edittablemodel.getValueAt(i, 10).toString());
						if(operationdescription == null){operationdescription = "";}
						maintablemodel.setValueAt(operationdescription,(int)edittablemodel.getValueAt(i, 11), 14);
						
						String getworkcenterofoperation = DBOracleManager.getWorkCenterOfOperation(edittablemodel.getValueAt(i, 9).toString(),edittablemodel.getValueAt(i, 10).toString());
						if(getworkcenterofoperation == null){getworkcenterofoperation = "";}
						maintablemodel.setValueAt(getworkcenterofoperation,(int)edittablemodel.getValueAt(i, 11), 15);
						
						String project = DBOracleManager.getProject(edittablemodel.getValueAt(i, 9).toString());
						if(project == null){project = "0";}
						maintablemodel.setValueAt(project,(int)edittablemodel.getValueAt(i, 11), 16);
						
						String getitemofworkorder = DBOracleManager.getItemOfWorkOrder(edittablemodel.getValueAt(i, 9).toString());
						if(getitemofworkorder == null){getitemofworkorder = "";}
						maintablemodel.setValueAt(getitemofworkorder,(int)edittablemodel.getValueAt(i, 11), 17);
						
						String getitemdescription = DBOracleManager.getItemDescription(edittablemodel.getValueAt(i, 9).toString());
						if(getitemdescription == null){getitemdescription = "";}
						maintablemodel.setValueAt(getitemdescription,(int)edittablemodel.getValueAt(i, 11), 18);	
						
						maintablemodel.setValueAt(Settings.getIpAddress(),(int)edittablemodel.getValueAt(i, 11), 23);
						maintablemodel.setValueAt(Settings.getEventDate(),(int)edittablemodel.getValueAt(i, 11), 24);
						
						Vector<Object> transaction = new Vector<Object>();
						
						transaction.addElement(edittablemodel.getValueAt(i, 6).toString());
						transaction.addElement(edittablemodel.getValueAt(i, 7).toString());
						transaction.addElement(getworkcenterofoperation);
						transaction.addElement(edittablemodel.getValueAt(i, 4).toString());
						transaction.addElement(edittablemodel.getValueAt(i, 5).toString());
						transaction.addElement(edittablemodel.getValueAt(i, 9).toString());
						transaction.addElement(edittablemodel.getValueAt(i, 10).toString());
						transaction.addElement(edittablemodel.getValueAt(i, 3).toString());
						transaction.addElement(edittablemodel.getValueAt(i, 8).toString());
						transaction.addElement(resourcedescription);
						transaction.addElement(operatordescription);
						transaction.addElement(edittablemodel.getValueAt(i, 1).toString());
						transaction.addElement(project);
						transaction.addElement(getitemofworkorder);
						transaction.addElement(getitemdescription);
						transaction.addElement(operationdescription);
						transaction.addElement(edittablemodel.getValueAt(i, 2).toString());
						transaction.addElement("E20MANAGER");
						transaction.addElement(Settings.getIpAddress());
						transaction.addElement(Settings.getEventDate());
						transaction.addElement(edittablemodel.getValueAt(i, 0).toString());
						
						if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
							DBMySqlManager.updateTransaction(transaction);
						}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
							DBOracleManager.updateTransaction(transaction);
						} 
						
					}
					
					tbldatitransazioni.repaint();
					ew.dispose();
				}
			}
		}catch(NullPointerException e){
			tbldatitransazioni.repaint();
			ew.dispose();
		}
		
		ew.setCursor(normalCursor);
    }

}
