package ide.main;

import ide.links.LinksWindow;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EventMainBtnManageLinks implements ActionListener {
	
	JFrame mainwindow;
	
	public EventMainBtnManageLinks(JFrame mainwindow){

		this.mainwindow = mainwindow;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		mainwindow.setCursor(hourglassCursor);
		
		LinksWindow lw = new LinksWindow();
		lw.setModal(true);
		lw.setVisible(true);

		mainwindow.setCursor(normalCursor);
		
	}
}