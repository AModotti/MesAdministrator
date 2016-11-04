package ide.insert;

import javax.swing.JTextField;

public class InsertVerifierForState {
	
	JTextField field4;
	JTextField field5;
	JTextField e5;
	
	public InsertVerifierForState(JTextField field4,JTextField field5,JTextField e5){
		
		this.field4 = field4;
		this.field5 = field5;
		this.e5 = e5;
		
	}

	public String verify() {
		
		if((field4.getText().trim().toUpperCase().equals("ING"))||(field4.getText().trim().toUpperCase().equals("USC"))){
			field5.setText("");
			e5.setText("0");
			return "";
		}else{	
			if((field5.getText().trim().equalsIgnoreCase("A"))||(field5.getText().trim().equalsIgnoreCase("S"))){
				field5.setText(field5.getText().trim().toUpperCase());
				e5.setText("0");
				return "";
			}else{
				e5.setText("1");
				return "* Valore obbligatorio o non valido";
			}
		}

	}

}
