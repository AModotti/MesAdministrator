package ide.island;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventIslandBtnCancel implements ActionListener{
	
	IslandWindow miw;
	
	public EventIslandBtnCancel(IslandWindow miw){
		this.miw = miw;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		miw.setCursor(hourglassCursor);
		miw.dispose();
		miw.setCursor(normalCursor);
	}

}
