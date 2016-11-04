package ide.insert;

import javax.swing.JTextField;

import bin.DBOracleManager;

public class InsertVerifierForOperator {

	JTextField field8;
	JTextField e8;
	
	public InsertVerifierForOperator(JTextField field8,JTextField e8){
		
		this.field8 = field8;
		this.e8 = e8;
		
	}
	
	public String verify() {
		
		if(DBOracleManager.checkIfElementExists(field8.getText().trim()) == null){
			e8.setText("1");
			return "* Valore obbligatorio o non valido";
		}else{
			e8.setText("0");
			return "";
		}

	}

}
