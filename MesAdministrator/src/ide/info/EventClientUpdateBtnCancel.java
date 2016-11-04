package ide.info;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventClientUpdateBtnCancel implements ActionListener{
	
	ClientUpdateInfoWindow uw;
	
	public EventClientUpdateBtnCancel(ClientUpdateInfoWindow uw){
		this.uw = uw;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		uw.setCursor(hourglassCursor);
		uw.dispose();
		uw.setCursor(normalCursor);
	}

}
