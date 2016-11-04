package ide.insert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

public class InsertVerifierForDate {

	JTextField field3;
	JTextField e3;
	
	public InsertVerifierForDate(JTextField field3,JTextField e3){
		
		this.field3 = field3;
		this.e3 = e3;

	}

	public String verify() {
		
		String datepattern = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)\\s([01]?[0-9]|2[0-3])[.][0-5][0-9][.][0-5][0-9]";
		Pattern pattern = Pattern.compile(datepattern);
		Matcher matcher = pattern.matcher(field3.getText().trim());
		
		if(matcher.matches()){
			e3.setText("0");
			return "";
		}else{
			e3.setText("1");
			return "* Valore obbligatorio o non valido";
		}

	}

}
