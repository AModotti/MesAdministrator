package ide.insert;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class InsertVerifierForType {
	
	JTextField field4;
	JTextField e4;
	JTextField e5;
	JTextField e6;
	JTextField e7;
	JTextField e8;
	JTextField e9;
	JTextField e10;
	JTextField field5;
	JTextField field6;
	JTextField field7;
	JTextField field8;
	JTextField field9;
	JTextField field10;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	
	public InsertVerifierForType(JTextField field4,JTextField e4,JTextField e5,JTextField e6,JTextField e7,JTextField e8,JTextField e9,JTextField e10,JTextField field5,JTextField field6,JTextField field7,JTextField field8,JTextField field9,JTextField field10,JLabel label1,JLabel label2,JLabel label3,JLabel label4,JLabel label5,JLabel label6){
		
		this.field4 = field4;
		this.e4 = e4;
		this.e5 = e5;
		this.e6 = e6;
		this.e7 = e7;
		this.e8 = e8;
		this.e9 = e9;
		this.e10 = e10;
		this.field5 = field5;
		this.field6 = field6;
		this.field7 = field7;
		this.field8 = field8;
		this.field9 = field9;
		this.field10 = field10;
		this.label1 = label1;
		this.label2 = label2;
		this.label3 = label3;
		this.label4 = label4;
		this.label5 = label5;
		this.label6 = label6;
		
	}

	public String verify() {
		
		if(!field4.getText().trim().toUpperCase().equals("")){
			if((field4.getText().trim().toUpperCase().equals("ING"))||(field4.getText().trim().toUpperCase().equals("USC"))){
				if(field6.getText().trim().equals("")){
					e6.setText("1");	
					label1.setText("* Valore obbligatorio o non valido");
				}else{
					e6.setText("0");
					label1.setText("");	
				}
				if(field7.getText().trim().equals("")){
					e7.setText("1");
					label2.setText("* Valore obbligatorio o non valido");
				}else{
					e7.setText("0");
					label2.setText("");	
				}
				if(field8.getText().trim().equals("")){
					e8.setText("1");
					label3.setText("* Valore obbligatorio o non valido");
				}else{
					e8.setText("0");
					label3.setText("");	
				}
				field5.setText("");
				field9.setText("");
				field10.setText("");
				label4.setText("");
				label5.setText("");
				label6.setText("");
				e5.setText("0");
				e9.setText("0");
				e10.setText("0");
			}else if((field4.getText().trim().toUpperCase().equals("ATT"))||(field4.getText().trim().toUpperCase().equals("LAV"))){
				if(field5.getText().trim().equals("")){
					e5.setText("1");
					label4.setText("* Valore obbligatorio o non valido");
				}else{
					e5.setText("0");
					label4.setText("");
				}
				if(field6.getText().trim().equals("")){
					e6.setText("1");
					label1.setText("* Valore obbligatorio o non valido");
				}else{
					e6.setText("0");
					label1.setText("");	
				}
				if(field7.getText().trim().equals("")){
					e7.setText("1");
					label2.setText("* Valore obbligatorio o non valido");
				}else{
					e7.setText("0");
					label2.setText("");	
				}
				if(field8.getText().trim().equals("")){
					e8.setText("1");
					label3.setText("* Valore obbligatorio o non valido");
				}else{
					e8.setText("0");
					label3.setText("");	
				}
				if(field9.getText().trim().equals("")){
					e9.setText("1");
					label5.setText("* Valore obbligatorio o non valido");
				}else{
					e9.setText("0");
					label5.setText("");
				}
				if(field10.getText().trim().equals("")){
					e10.setText("1");
					label6.setText("* Valore obbligatorio o non valido");
				}else{
					e10.setText("0");
					label6.setText("");
				}
			}else{
				e4.setText("1");
				return "* Valore obbligatorio o non valido";
			}
			field4.setText(field4.getText().trim().toUpperCase());
			e4.setText("0");
			return "";
		}else{
			e4.setText("1");
			return "* Valore obbligatorio o non valido";
		}
	
	}

}
