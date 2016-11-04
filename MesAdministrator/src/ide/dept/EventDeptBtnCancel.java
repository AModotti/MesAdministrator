package ide.dept;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventDeptBtnCancel implements ActionListener{
	
	DeptWindow dw;
	
	public EventDeptBtnCancel(DeptWindow dw){
		this.dw = dw;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		dw.setCursor(hourglassCursor);
		dw.dispose();
		dw.setCursor(normalCursor);
	}

}
