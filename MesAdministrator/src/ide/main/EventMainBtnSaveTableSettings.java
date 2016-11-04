package ide.main;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import bin.ColumnManager;
import bin.TableSerialization;


public class EventMainBtnSaveTableSettings implements ActionListener{
	
	JFrame mainwindow;
	
	public EventMainBtnSaveTableSettings(JFrame mainwindow){
		
		this.mainwindow = mainwindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		mainwindow.setCursor(hourglassCursor);
				
		TableSerialization.serialize(ColumnManager.getColumnInformation());
		
		JOptionPane.showMessageDialog(mainwindow, "Impostazioni salvate correttamente","Salvataggio Impostazioni",JOptionPane.INFORMATION_MESSAGE);
		
		mainwindow.setCursor(normalCursor);
		
	}
	
}