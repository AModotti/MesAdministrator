package ide.jde;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHistoryImportBtnCancel implements ActionListener{
	
	HistoryImportWindow hw;
	
	public EventHistoryImportBtnCancel(HistoryImportWindow hw){
		this.hw = hw;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		hw.setCursor(hourglassCursor);
		hw.dispose();
		hw.setCursor(normalCursor);
	}

}
