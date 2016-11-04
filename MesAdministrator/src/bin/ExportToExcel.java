package bin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ExportToExcel {

	public static void exportTable(JFrame mainwindow,JTable table) {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setSelectedFile(new File("TransazioniMes.xls"));
		
		File selectedFile = null;
		
		int result = fileChooser.showOpenDialog(mainwindow);
		
		if (result == JFileChooser.APPROVE_OPTION) {
		    selectedFile = fileChooser.getSelectedFile();
		
		    TableModel model = table.getModel();
		    FileWriter out;
		    
			try {
				
				out = new FileWriter(selectedFile);
				
				for(int i=1; i < model.getColumnCount(); i++) {
		        	out.write(model.getColumnName(i) + "\t");
		        }
				
				 out.write("\n");
					
		        for(int i=0; i< model.getRowCount(); i++) {
		        	for(int j=1; j < model.getColumnCount(); j++) {
		        		out.write(model.getValueAt(i,j).toString() + "\t");
		        	}
		        	out.write("\n");
		        }
		        
				out.close();
		        JOptionPane.showMessageDialog(mainwindow, "File esportato correttamente in : " + selectedFile,"Esportazione dati",JOptionPane.INFORMATION_MESSAGE);
					
			} catch (IOException e) {
				LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				LogWriter.write("[E] Errore in classe: [ ExportToExcel.exportTable() ]");
				LogWriter.write("[E] " + e.getMessage());
				LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	        	Notifications notification = new Notifications("[ ExportToExcel.exportTable() ]",e.getMessage(),"");
	        	notification.setModal(true);
	        	notification.setVisible(true);
			}
		}
  
	}
}
