package ide.main;


import ide.island.IslandWindow;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EventMainBtnManageIsland implements ActionListener {
	
	JFrame mainwindow;
	
	public EventMainBtnManageIsland(JFrame mainwindow){

		this.mainwindow = mainwindow;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		mainwindow.setCursor(hourglassCursor);
		
		IslandWindow miw = new IslandWindow();
		miw.setModal(true);
		miw.setVisible(true);

		mainwindow.setCursor(normalCursor);
		
	}
}
