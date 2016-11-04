package ide.insert;

import javax.swing.JTextField;

import bin.DBOracleManager;

public class InsertVerifierForResource {

	JTextField field7;
	JTextField e7;
	
	public InsertVerifierForResource(JTextField field7,JTextField e7){
		
		this.field7 = field7;
		this.e7 = e7;

	}
	
	public String verify() {
				
		if(DBOracleManager.checkIfElementExists(field7.getText().trim().toUpperCase()) == null){
			e7.setText("1");
			return "* Valore obbligatorio o non valido";
		}else{
			e7.setText("0");
			return "";
		}

	}

}
