package ide.main;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class EventMainBtnClearFilter implements ActionListener{
	
	JFrame mainwindow;
	JTextField txtdadata;
	JTextField txtadata;
	JTextField txtreparto;
	JTextField txtisola;
	JTextField txtstato;
	JTextField txttipo;
	JTextField txtbranch;
	JTextField txtrisorsa;
	JTextField txtdescrizionerisorsa;
	JTextField txtoperatore;
	JTextField txtdescrizioneoperatore;
	JTextField txtworkorder;
	JTextField txtoperazione;
	JTextField txtcommessa;
	JTextField txtarticolo;
	JTextField txtdescrizionearticolo;
	JCheckBox chcvisualizzarecoreliminati;
	JCheckBox chcvisualizzarecordprocessati;

	public EventMainBtnClearFilter(JFrame mainwindow,JTextField txtdadata,JTextField txtadata,JTextField txtreparto,JTextField txtisola,JTextField txtstato,JTextField txttipo,JTextField txtbranch,JTextField txtrisorsa,JTextField txtdescrizionerisorsa,JTextField txtoperatore,JTextField txtdescrizioneoperatore,JTextField txtworkorder,JTextField txtoperazione,JTextField txtcommessa,JTextField txtarticolo,JTextField txtdescrizionearticolo,JCheckBox chcvisualizzarecoreliminati,JCheckBox chcvisualizzarecordprocessati){

		this.mainwindow = mainwindow;
		this.txtdadata = txtdadata;
		this.txtadata = txtadata;
		this.txtreparto = txtreparto;
		this.txtisola = txtisola;
		this.txtstato = txtstato;
		this.txttipo = txttipo;
		this.txtbranch = txtbranch;
		this.txtrisorsa = txtrisorsa;
		this.txtdescrizionerisorsa = txtdescrizionerisorsa;
		this.txtoperatore = txtoperatore;
		this.txtdescrizioneoperatore = txtdescrizioneoperatore;
		this.txtworkorder = txtworkorder;
		this.txtoperazione = txtoperazione;
		this.txtcommessa = txtcommessa;
		this.txtarticolo = txtarticolo;
		this.txtdescrizionearticolo = txtdescrizionearticolo;
		this.chcvisualizzarecoreliminati = chcvisualizzarecoreliminati;
		this.chcvisualizzarecordprocessati = chcvisualizzarecordprocessati;

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		mainwindow.setCursor(hourglassCursor);

		txtdadata.setText(""); 
		txtadata.setText(""); 
		txtreparto.setText(""); 
		txtisola.setText(""); 
		txtstato.setText(""); 
		txttipo.setText(""); 
		txtbranch.setText(""); 
		txtrisorsa.setText(""); 
		txtdescrizionerisorsa.setText(""); 
		txtoperatore.setText(""); 
		txtdescrizioneoperatore.setText(""); 
		txtworkorder.setText(""); 
		txtoperazione.setText(""); 
		txtcommessa.setText(""); 
		txtarticolo.setText(""); 
		txtdescrizionearticolo.setText("");
		chcvisualizzarecoreliminati.setSelected(false);
		chcvisualizzarecordprocessati.setSelected(false);
		
		mainwindow.setCursor(normalCursor);
		
	}

}
