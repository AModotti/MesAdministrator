package ide.jde;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class ImportVerifierForDate extends InputVerifier {

	JButton button;
	JTextField field1;
	JTextField field2;
	JTextField field3;
	
	public ImportVerifierForDate(JButton button,JTextField field1,JTextField field2,JTextField field3){
		
		this.button = button;
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
		
	}

	@Override
	public boolean verify(JComponent c) {
		
		final JTextComponent source = (JTextComponent) c;
		String value = source.getText().trim();
		
		String datepattern = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)\\s([01]?[0-9]|2[0-3])[.][0-5][0-9][.][0-5][0-9]";
		Pattern pattern = Pattern.compile(datepattern);
		Matcher matcher = pattern.matcher(value);
		
		if(matcher.matches()){
			if((!field1.getText().trim().equals(""))&&(!field2.getText().trim().equals(""))&&(!field3.getText().trim().equals(""))){
				button.setEnabled(true);
			}else{
				button.setEnabled(false);
			}
			return true;
		}else{
			button.setEnabled(false);
			JOptionPane.showMessageDialog(source, "Formato data e ora non valido [formato: gg/mm/aaaa hh.mm.ss]","Inserimento Dati",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

	}

}
