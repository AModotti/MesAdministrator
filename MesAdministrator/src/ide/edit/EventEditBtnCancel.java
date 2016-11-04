package ide.edit;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventEditBtnCancel implements ActionListener{
	
	EditWindow ew;
	
	public EventEditBtnCancel(EditWindow ew){
		this.ew = ew;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		ew.setCursor(hourglassCursor);
		ew.dispose();
		ew.setCursor(normalCursor);
	}

}
