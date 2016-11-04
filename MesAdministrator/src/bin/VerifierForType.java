package bin;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

public class VerifierForType extends InputVerifier {
	
	@Override
	public boolean verify(JComponent c) {
		
		final JTextComponent source = (JTextComponent) c;
		String value = source.getText().trim().toUpperCase();
		
		if((value.equals("ING"))||(value.equals("ATT")||(value.equals("LAV"))||(value.equals("USC")))){
			return true;
		}else{
			JOptionPane.showMessageDialog(source, "Tipo non valido","Inserimento Dati",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

	}

}
