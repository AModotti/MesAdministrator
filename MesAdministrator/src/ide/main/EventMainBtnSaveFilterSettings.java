package ide.main;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bin.FiltersSerialization;

public class EventMainBtnSaveFilterSettings implements ActionListener{
	
	JFrame mainwindow;
	HashMap<Integer, Object> filters =  new HashMap<>();
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
	
	public EventMainBtnSaveFilterSettings(JFrame mainwindow,JTextField txtdadata,JTextField txtadata,JTextField txtreparto,JTextField txtisola,JTextField txtstato,JTextField txttipo,JTextField txtbranch,JTextField txtrisorsa,JTextField txtdescrizionerisorsa,JTextField txtoperatore,JTextField txtdescrizioneoperatore,JTextField txtworkorder,JTextField txtoperazione,JTextField txtcommessa,JTextField txtarticolo,JTextField txtdescrizionearticolo,JCheckBox chcvisualizzarecoreliminati,JCheckBox chcvisualizzarecordprocessati){
		
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
		
		filters.put(0, txtdadata.getText().trim());
		filters.put(1, txtadata.getText().trim());
		filters.put(2, txtreparto.getText().trim().toUpperCase());
		filters.put(3, txtisola.getText().trim().toUpperCase());
		filters.put(4, txtstato.getText().trim().toUpperCase());
		filters.put(5, txttipo.getText().trim());
		filters.put(6, txtbranch.getText().trim().toUpperCase());
		filters.put(7, txtrisorsa.getText().trim());
		filters.put(8, txtdescrizionerisorsa.getText().trim().toUpperCase());
		filters.put(9, txtoperatore.getText().trim());
		filters.put(10, txtdescrizioneoperatore.getText().trim().toUpperCase());
		filters.put(11, txtworkorder.getText().trim());
		filters.put(12, txtoperazione.getText().trim());
		filters.put(13, txtcommessa.getText().trim());
		filters.put(14, txtarticolo.getText().trim());
		filters.put(15, txtdescrizionearticolo.getText().trim());
		filters.put(16, chcvisualizzarecoreliminati.isSelected());
		filters.put(17, chcvisualizzarecordprocessati.isSelected());
		
		FiltersSerialization.serialize(filters);
		
		JOptionPane.showMessageDialog(mainwindow, "Impostazioni salvate correttamente","Salvataggio Impostazioni",JOptionPane.INFORMATION_MESSAGE);
		
		mainwindow.setCursor(normalCursor);
		
	}

}
