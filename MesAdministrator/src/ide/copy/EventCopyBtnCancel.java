package ide.copy;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventCopyBtnCancel implements ActionListener{
	
	CopyWindow cw;
	
	public EventCopyBtnCancel(CopyWindow cw){
		this.cw = cw;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		cw.setCursor(hourglassCursor);
		cw.dispose();
		cw.setCursor(normalCursor);
	}

}
