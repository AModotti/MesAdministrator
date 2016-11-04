package ide.main;

import ide.info.ClientUpdateInfoWindow;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EventMainBtnClientUpdateInfo implements ActionListener{
	
	JFrame mainwindow;
	
	public EventMainBtnClientUpdateInfo(JFrame mainwindow){
		
		this.mainwindow = mainwindow;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		mainwindow.setCursor(hourglassCursor);
		
		ClientUpdateInfoWindow uw = new ClientUpdateInfoWindow();
		uw.setModal(true);
		uw.setVisible(true);
		
		mainwindow.setCursor(normalCursor);
		
	}

}
