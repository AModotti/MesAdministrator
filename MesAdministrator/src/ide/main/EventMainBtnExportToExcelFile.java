package ide.main;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import bin.ExportToExcel;

public class EventMainBtnExportToExcelFile implements ActionListener{
	
	JFrame mainwindow;
	MainTable tbldatitransazioni;
	
	public EventMainBtnExportToExcelFile(JFrame mainwindow,MainTable tbldatitransazioni){
		
		this.mainwindow = mainwindow;
		this.tbldatitransazioni = tbldatitransazioni;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		mainwindow.setCursor(hourglassCursor);
		
		ExportToExcel.exportTable(mainwindow,tbldatitransazioni);
		
		mainwindow.setCursor(normalCursor);
		
	}

}
