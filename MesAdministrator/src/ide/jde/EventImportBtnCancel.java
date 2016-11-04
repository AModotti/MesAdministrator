package ide.jde;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class EventImportBtnCancel implements ActionListener{
	
	ImportWindow iw;
	JTextField txtutenteattuale;
	JTextField txtdataimportazioneattuale;
	JTextField txtrepartotransazioneattuale;
	JTextField txtdadatatransazioneattuale;
	JTextField txtadatatransazioneattuale;
	JTextField txtidinizialetransazioneattuale;
	JTextField txtrecordimportatitransazioneattuale;
	JTextField txtidfinaletransazioneattuale;
	
	public EventImportBtnCancel(ImportWindow iw,JTextField txtutenteattuale,JTextField txtdataimportazioneattuale,JTextField txtrepartotransazioneattuale,JTextField txtdadatatransazioneattuale,JTextField txtadatatransazioneattuale,JTextField txtidinizialetransazioneattuale,JTextField txtrecordimportatitransazioneattuale,JTextField txtidfinaletransazioneattuale){
		this.iw = iw;
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
		txtutenteattuale.setText("");
		txtdataimportazioneattuale.setText("");
		txtrepartotransazioneattuale.setText("");
		txtdadatatransazioneattuale.setText("");
		txtadatatransazioneattuale.setText("");
		txtidinizialetransazioneattuale.setText("");
		txtrecordimportatitransazioneattuale.setText("");
		txtidfinaletransazioneattuale.setText("");
		iw.dispose();
		iw.setCursor(normalCursor);
	}

}