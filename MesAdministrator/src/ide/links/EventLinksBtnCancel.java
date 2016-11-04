package ide.links;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventLinksBtnCancel implements ActionListener{
	
	LinksWindow lw;
	
	public EventLinksBtnCancel(LinksWindow lw){
		this.lw = lw;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		lw.setCursor(hourglassCursor);
		lw.dispose();
		lw.setCursor(normalCursor);
	}

}
