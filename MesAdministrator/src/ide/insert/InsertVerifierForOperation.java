package ide.insert;

import javax.swing.JTextField;

import bin.DBOracleManager;

public class InsertVerifierForOperation {
	
	JTextField field4;
	JTextField field9;
	JTextField field10;
	JTextField e10;
	
	public InsertVerifierForOperation(JTextField field4,JTextField field9,JTextField field10,JTextField e10){
		
		this.field4 = field4;
		this.field9 = field9;
		this.field10 = field10;
		this.e10 = e10;
		
	}

	public String verify() {
		
		String value = field10.getText().trim();
		
		if((field4.getText().trim().toUpperCase().equals("ING"))||(field4.getText().trim().toUpperCase().equals("USC"))){
			field9.setText("");
			field10.setText("");
			e10.setText("0");
			return "";
		}else{
			if(DBOracleManager.checkIfOperationExists(field9.getText().trim(),value) == null){
				e10.setText("1");
				return "* Valore obbligatorio o non valido";
			}else{
				e10.setText("0");
				return "";
			}
		}

	}

}
