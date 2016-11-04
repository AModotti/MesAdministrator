package ide.insert;

import javax.swing.JTextField;

import bin.DBOracleManager;

public class InsertVerifierForIsland {

	JTextField field2;
	JTextField e2;

	public InsertVerifierForIsland(JTextField field2,JTextField e2){
		
		this.field2 = field2;
		this.e2 = e2;
		
	}
	
	public String verify() {
		
		if(DBOracleManager.checkIfIslandExists(field2.getText().trim().toUpperCase()) == null){
			e2.setText("1");
			return "* Valore obbligatorio o non valido";
		}else{
			field2.setText(field2.getText().trim().toUpperCase());
			e2.setText("0");
			return "";
		}

	}

}
