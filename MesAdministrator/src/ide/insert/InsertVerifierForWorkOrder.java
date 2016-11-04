package ide.insert;

import javax.swing.JTextField;

import bin.DBOracleManager;

public class InsertVerifierForWorkOrder {
	
	JTextField field4;
	JTextField field6;
	JTextField field9;
	JTextField e9;
	
	public InsertVerifierForWorkOrder(JTextField field4,JTextField field6,JTextField field9,JTextField e9){
		
		this.field4 = field4;
		this.field6 = field6;
		this.field9 = field9;
		this.e9 = e9;
		
	}

	public String verify() {
				
		if((field4.getText().trim().toUpperCase().equals("ING"))||(field4.getText().trim().toUpperCase().equals("USC"))){
			field9.setText("");
			e9.setText("0");
			return "";
		}else{
			if(DBOracleManager.checkIfWorkOrderExists(field9.getText().trim()) == null){
				e9.setText("1");
				return "* Valore obbligatorio o non valido";
			}else{
				field6.setText(DBOracleManager.getBranchOfWorkOrder(field9.getText().trim()));
				e9.setText("0");
				return "";
			}
		}

	}

}
