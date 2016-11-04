package ide.jde;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;


public class EventImportBtnHistoryImport implements ActionListener{
	
	JDialog importwindow;
		
	public EventImportBtnHistoryImport(JDialog importwindow){
		
		this.importwindow = importwindow;
				
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		importwindow.setCursor(hourglassCursor);
		
		HistoryImportWindow hw = new HistoryImportWindow();
		hw.setModal(true);
		hw.setVisible(true);
		
		importwindow.setCursor(normalCursor);
		
	}

}
