package ide.main;

import ide.insert.InsertWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class EventMainBtnInsertTransaction implements ActionListener {

	MainTable tbldatitransazioni;
	JLabel lblnumerorecord;
	
	public EventMainBtnInsertTransaction(MainTable tbldatitransazioni,JLabel lblnumerorecord){
		
		this.tbldatitransazioni = tbldatitransazioni;
		this.lblnumerorecord = lblnumerorecord;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		InsertWindow iw = new InsertWindow(tbldatitransazioni,lblnumerorecord);
		iw.setModal(true);
		iw.setVisible(true);

	}
}
