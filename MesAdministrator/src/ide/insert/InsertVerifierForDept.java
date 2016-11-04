package ide.insert;

import javax.swing.JTextField;

public class InsertVerifierForDept {

	JTextField field1;
	JTextField e1;
	
	public InsertVerifierForDept(JTextField field1,JTextField e1){
		
		this.field1 = field1;
		this.e1 = e1;
		
	}
	
	public String verify() {
				
		if((field1.getText().trim().equals("001"))||(field1.getText().trim().equals("002"))||(field1.getText().trim().equals("003"))||(field1.getText().trim().equals("004"))||(field1.getText().trim().equals("005"))){
			e1.setText("0");
			return "";
		}else{
			e1.setText("1");
			return "* Valore obbligatorio o non valido";
		}

	}

}
