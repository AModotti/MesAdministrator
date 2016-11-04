package ide.main;

import ide.edit.EditWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventMainBtnEditTransaction implements ActionListener {

	MainTable tbldatitransazioni;
	
	public EventMainBtnEditTransaction(MainTable tbldatitransazioni){
		
		this.tbldatitransazioni = tbldatitransazioni;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		EditWindow ew = new EditWindow(tbldatitransazioni);
		ew.setModal(true);
		ew.setVisible(true);

	}
}
