package ide.main;


import ide.dept.DeptWindow;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EventMainBtnManageDept implements ActionListener {
	
	JFrame mainwindow;
	
	public EventMainBtnManageDept(JFrame mainwindow){

		this.mainwindow = mainwindow;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		mainwindow.setCursor(hourglassCursor);
		
		DeptWindow dw = new DeptWindow();
		dw.setModal(true);
		dw.setVisible(true);

		mainwindow.setCursor(normalCursor);
		
	}
}
