package ide.main;

import ide.copy.CopyWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class EventMainBtnCopyTransaction implements ActionListener {

	MainTable tbldatitransazioni;
	JLabel lblnumerorecord;
	
	public EventMainBtnCopyTransaction(MainTable tbldatitransazioni,JLabel lblnumerorecord){
		
		this.tbldatitransazioni = tbldatitransazioni;
		this.lblnumerorecord = lblnumerorecord;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		CopyWindow cw = new CopyWindow(tbldatitransazioni,lblnumerorecord);
		cw.setModal(true);
		cw.setVisible(true);

	}
}
