package ide.jde;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Notifications;
import bin.Settings;

public class EventImportBtnExecuteImport implements ActionListener{
	
	ImportWindow iw;
	JTextField txtreparto;
	JTextField txtdadata;
	JTextField txtadata;
	JTextField txtutenteattuale;
	JTextField txtdataimportazioneattuale;				
	JTextField txtrepartotransazioneattuale;
	JTextField txtdadatatransazioneattuale;
	JTextField txtadatatransazioneattuale;
	JTextField txtidinizialetransazioneattuale;
	JTextField txtrecordimportatitransazioneattuale;
	JTextField txtidfinaletransazioneattuale;
	HashMap<Integer, String> importrows = new HashMap<>();

	
	public EventImportBtnExecuteImport(ImportWindow iw,JTextField txtreparto,JTextField txtdadata,JTextField txtadata,JTextField txtutenteattuale,JTextField txtdataimportazioneattuale,JTextField txtrepartotransazioneattuale,JTextField txtdadatatransazioneattuale,JTextField txtadatatransazioneattuale,JTextField txtidinizialetransazioneattuale,JTextField txtrecordimportatitransazioneattuale,JTextField txtidfinaletransazioneattuale){
		this.iw = iw;
		this.txtreparto = txtreparto;
		this.txtdadata = txtdadata;
		this.txtadata = txtadata;
		this.txtutenteattuale = txtutenteattuale;
		this.txtdataimportazioneattuale = txtdataimportazioneattuale; 				
		this.txtrepartotransazioneattuale = txtrepartotransazioneattuale;
		this.txtdadatatransazioneattuale = txtdadatatransazioneattuale;
		this.txtadatatransazioneattuale = txtadatatransazioneattuale;
		this.txtidinizialetransazioneattuale = txtidinizialetransazioneattuale;
		this.txtrecordimportatitransazioneattuale = txtrecordimportatitransazioneattuale;
		this.txtidfinaletransazioneattuale = txtidfinaletransazioneattuale;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		iw.setCursor(hourglassCursor);
		
		int check = 0;
		
		String datepattern = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
		Pattern pattern = Pattern.compile(datepattern);
		Matcher matcherdadata = pattern.matcher(txtdadata.getText().trim());
		Matcher matcheradata = pattern.matcher(txtadata.getText().trim());
		
		if(txtreparto.getText().trim().equals("")){
			JOptionPane.showMessageDialog(iw, "Il reparto è un campo obbligatorio","Importazione Transazioni",JOptionPane.INFORMATION_MESSAGE);
			check = 1;
			iw.setCursor(normalCursor);
		}else{
			if((!txtreparto.getText().trim().equals("001"))&&(!txtreparto.getText().trim().equals("002"))&&(!txtreparto.getText().trim().equals("003"))&&(!txtreparto.getText().trim().equals("004"))&&(!txtreparto.getText().trim().equals("005"))){
				JOptionPane.showMessageDialog(iw, "Reparto non valido o inesistente","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);	
				check = 1;
				iw.setCursor(normalCursor);
			}
		}
		
        if(txtdadata.getText().trim().equals("")){
			JOptionPane.showMessageDialog(iw, "Il campo [Da Data] è un campo obbligatorio","Importazione Transazioni",JOptionPane.INFORMATION_MESSAGE);	
			check = 1;
			iw.setCursor(normalCursor);
        }else{
        	if(!matcherdadata.matches()){
    			JOptionPane.showMessageDialog(iw, "Formato data e ora del campo [Da Data] non è valido [formato: gg/mm/aaaa]","Inserimento Dati",JOptionPane.INFORMATION_MESSAGE);
    			check = 1;
    			iw.setCursor(normalCursor);
        	}
        }
        
		if(txtadata.getText().trim().equals("")){
			JOptionPane.showMessageDialog(iw, "Il campo [A Data] è un campo obbligatorio","Importazione Transazioni",JOptionPane.INFORMATION_MESSAGE);
			check = 1;
			iw.setCursor(normalCursor);
		}else{
			if(!matcheradata.matches()){
				JOptionPane.showMessageDialog(iw, "Formato data e ora del campo [A Data] non è valido [formato: gg/mm/aaaa]","Inserimento Dati",JOptionPane.INFORMATION_MESSAGE);
				check = 1;
				iw.setCursor(normalCursor);
			}
		} 

		if(check == 0){
			int dialogResult = JOptionPane.showConfirmDialog(iw, "Sei sucuro di voler procedere con l'importazione dei dati in JD Edwards?", "Importazione Transazioni",JOptionPane.YES_NO_OPTION);
				
			if(dialogResult == JOptionPane.YES_OPTION){
				//CONTROLLO CHE IL DATABASE SIA RAGGIUNGIBILE
				String checkdbconnection = Settings.checkDbConnection();
				//SE NON E' RAGGIUNGIBILE
				if(checkdbconnection.equals("false")){
					Notifications notification = new Notifications("[ Settings.checkDbConnection() ]","Impossibile connettersi al Gestionale JDE","Non è stato possibile connettersi al Gestionale a causa di un errore. Controllare la connettività o le impostazioni di rete.");
		        	notification.setModal(true);
		        	notification.setVisible(true);
				//SE E' RAGGIUNGIBILE	
				}else{
					if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
						DBMySqlManager.importToJde(txtreparto.getText().trim(),txtdadata.getText().trim(),txtadata.getText().trim());
					}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
						DBOracleManager.importToJde(txtreparto.getText().trim(),txtdadata.getText().trim(),txtadata.getText().trim());
					}
					
					if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
						importrows = DBMySqlManager.getLastImport();
					}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
						importrows = DBOracleManager.getLastImport();
					}
					
					txtutenteattuale.setText(importrows.get(0));
					txtdataimportazioneattuale.setText(importrows.get(1));					
					txtrepartotransazioneattuale.setText(importrows.get(2));
					txtdadatatransazioneattuale.setText(importrows.get(3));
					txtadatatransazioneattuale.setText(importrows.get(4));
					txtidinizialetransazioneattuale.setText(importrows.get(5));
					txtrecordimportatitransazioneattuale.setText(importrows.get(6));
					txtidfinaletransazioneattuale.setText(importrows.get(7));

					JOptionPane.showMessageDialog(iw, "Importazione dati eseguita correttamente","Importazione Transazioni",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		}

		iw.setCursor(normalCursor);
	}
}
