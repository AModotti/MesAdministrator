package ide.main;

import ide.jde.ImportWindow;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EventMainBtnImportDataToJde implements ActionListener{
	
	JFrame mainwindow;
	
	public EventMainBtnImportDataToJde(JFrame mainwindow){
		
		this.mainwindow = mainwindow;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		mainwindow.setCursor(hourglassCursor);
		
		ImportWindow iw = new ImportWindow();
		iw.setModal(true);
		iw.setVisible(true);
		
		mainwindow.setCursor(normalCursor);
		
	}

}
