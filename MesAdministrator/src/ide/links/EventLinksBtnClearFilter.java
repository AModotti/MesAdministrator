package ide.links;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class EventLinksBtnClearFilter implements ActionListener{
	
	LinksWindow lw;
	JTextField txtfiltrinomeisola;
	JTextField txtfiltririsorsa;
	
	public EventLinksBtnClearFilter(LinksWindow lw,JTextField txtfiltrinomeisola,JTextField txtfiltririsorsa){
		
		this.lw = lw;
		this.txtfiltrinomeisola = txtfiltrinomeisola;
		this.txtfiltririsorsa = txtfiltririsorsa;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		lw.setCursor(hourglassCursor);
		
		txtfiltrinomeisola.setText("");
		txtfiltririsorsa.setText("");
		
		lw.setCursor(normalCursor);
	}

}
