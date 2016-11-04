package ide.insert;

import javax.swing.JTextField;

import bin.DBOracleManager;

public class InsertVerifierForBranch {

	JTextField field6;
	JTextField e6;

	
	public InsertVerifierForBranch(JTextField field6,JTextField e6){
		
		this.field6 = field6;
		this.e6 = e6;
		
	}
	
	public String verify() {
		
		if(DBOracleManager.checkIfBranchExists(field6.getText().trim().toUpperCase()) == null){
			e6.setText("1");
			return "* Valore obbligatorio o non valido";
		}else{
			e6.setText("0");
			field6.setText(field6.getText().toUpperCase());
			return "";
		}

	}

}
