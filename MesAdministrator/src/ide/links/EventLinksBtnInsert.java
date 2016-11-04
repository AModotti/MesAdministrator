package ide.links;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;

public class EventLinksBtnInsert implements ActionListener{
	
	LinksWindow lw;
	LinksTable tbllinks;
	JTextField txtnomeisola;
	JTextField txtsequenza;
	JTextField txtrisorsa;
	JComboBox<String> cmbabilitazione;
	JLabel lblnumerorecord;
	
	public EventLinksBtnInsert(LinksWindow lw,LinksTable tbllinks,JTextField txtnomeisola,JTextField txtsequenza,JTextField txtrisorsa,JComboBox<String> cmbabilitazione,JLabel lblnumerorecord){
		
		this.lw = lw;
		this.tbllinks = tbllinks;
		this.txtnomeisola = txtnomeisola;
		this.txtsequenza = txtsequenza;
		this.txtrisorsa = txtrisorsa;;
		this.cmbabilitazione = cmbabilitazione;
		this.lblnumerorecord = lblnumerorecord;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		lw.setCursor(hourglassCursor);
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		
		DefaultTableModel linkstablemodel = (DefaultTableModel) tbllinks.getModel();
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			
			if(txtnomeisola.getText().trim().equals("")){
				JOptionPane.showMessageDialog(lw, "Il nome isola è un campo obbligatorio","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);
				lw.setCursor(normalCursor);
			}else if(txtsequenza.getText().trim().equals("")){
				JOptionPane.showMessageDialog(lw, "La sequenza è un campo obbligatorio","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);	
				lw.setCursor(normalCursor);
			}else if(txtrisorsa.getText().trim().equals("")){
				JOptionPane.showMessageDialog(lw, "Il codice risorsa è un campo obbligatorio","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);	
				lw.setCursor(normalCursor);
			}else if(DBMySqlManager.checkIfElementExists(txtrisorsa.getText()) == null){
				JOptionPane.showMessageDialog(lw, "Codice risorsa non valido o inesistente","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);	
				lw.setCursor(normalCursor);
			}else if(DBMySqlManager.checkIfIslandExists(txtnomeisola.getText()) == null){
				JOptionPane.showMessageDialog(lw, "L'isola di lavoro è inesistente","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);	
				lw.setCursor(normalCursor);
			}else{
				int exists = 0;
				for (int i=0;i<linkstablemodel.getRowCount();i++) {
					if((linkstablemodel.getValueAt(i, 0).toString() + linkstablemodel.getValueAt(i, 1).toString() + linkstablemodel.getValueAt(i, 2).toString()).equals(txtnomeisola.getText().trim() + txtsequenza.getText().trim() + txtrisorsa.getText().trim())){
						exists = exists + 1;	
					}else{
						exists = exists + 0;
					}
				}
				
				if(exists == 1){
					JOptionPane.showMessageDialog(lw, "Esiste già un legame che risponde ai criteri inseriti","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);
				}else{
					Vector<Object> copyrow = new Vector<Object>();
			
					copyrow.add(txtnomeisola.getText());
					copyrow.add(txtsequenza.getText());
					copyrow.add(txtrisorsa.getText());
					copyrow.add(DBMySqlManager.getElementDescription(txtrisorsa.getText()));
					copyrow.add(cmbabilitazione.getSelectedItem());
					copyrow.add(Settings.getIpAddress());
					copyrow.add(Settings.getEventDate());
					linkstablemodel.addRow(copyrow);
					
					Vector<Object> links = new Vector<Object>();
					
					links.addElement(txtnomeisola.getText());
					links.addElement(txtsequenza.getText());
					links.addElement(txtrisorsa.getText());
					links.addElement(DBMySqlManager.getElementDescription(txtrisorsa.getText()));
					links.addElement(cmbabilitazione.getSelectedItem());
					links.addElement("0");
					links.addElement(Settings.getIpAddress());
					links.addElement(Settings.getEventDate());
					links.addElement(" ");
					links.addElement(java.sql.Types.TIMESTAMP);
					links.addElement(" ");
					links.addElement(java.sql.Types.TIMESTAMP);
					
					DBMySqlManager.insertLinks(links);
									
					tbllinks.setRowHeight(20);
								
					txtnomeisola.setText("");
					txtsequenza.setText("");
					txtrisorsa.setText("");
					cmbabilitazione.getItemAt(0);
			                    
					JOptionPane.showMessageDialog(lw, "Abbinamento Isola - Risorsa inserito correttamente","Abbinamento Isola - Risorsa",JOptionPane.INFORMATION_MESSAGE);
				
					tbllinks.repaint();
					lblnumerorecord.setText("Numero righe: " + formatter.format(linkstablemodel.getRowCount()));

				}
			}
			
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			
			if(txtnomeisola.getText().trim().equals("")){
				JOptionPane.showMessageDialog(lw, "Il nome isola è un campo obbligatorio","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);
				lw.setCursor(normalCursor);
			}else if(txtsequenza.getText().trim().equals("")){
				JOptionPane.showMessageDialog(lw, "La sequenza è un campo obbligatorio","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);	
				lw.setCursor(normalCursor);
			}else if(txtrisorsa.getText().trim().equals("")){
				JOptionPane.showMessageDialog(lw, "Il codice risorsa è un campo obbligatorio","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);	
				lw.setCursor(normalCursor);
			}else if(DBOracleManager.checkIfElementExists(txtrisorsa.getText()) == null){
				JOptionPane.showMessageDialog(lw, "Codice risorsa non valido o inesistente","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);	
				lw.setCursor(normalCursor);
			}else if(DBOracleManager.checkIfIslandExists(txtnomeisola.getText()) == null){
				JOptionPane.showMessageDialog(lw, "L'isola di lavoro è inesistente","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);	
				lw.setCursor(normalCursor);
			}else{
				int exists = 0;
				for (int i=0;i<linkstablemodel.getRowCount();i++) {
					if((linkstablemodel.getValueAt(i, 0).toString() + linkstablemodel.getValueAt(i, 1).toString() + linkstablemodel.getValueAt(i, 2).toString()).equals(txtnomeisola.getText().trim() + txtsequenza.getText().trim() + txtrisorsa.getText().trim())){
						exists = exists + 1;	
					}else{
						exists = exists + 0;
					}
				}
				
				if(exists == 1){
					JOptionPane.showMessageDialog(lw, "Esiste già un legame che risponde ai criteri inseriti","Inserimento Abbinamenti",JOptionPane.INFORMATION_MESSAGE);
				}else{
					Vector<Object> copyrow = new Vector<Object>();
			
					copyrow.add(txtnomeisola.getText());
					copyrow.add(txtsequenza.getText());
					copyrow.add(txtrisorsa.getText());
					copyrow.add(DBOracleManager.getElementDescription(txtrisorsa.getText()));
					copyrow.add(cmbabilitazione.getSelectedItem());
					copyrow.add(Settings.getIpAddress());
					copyrow.add(Settings.getEventDate());
					linkstablemodel.addRow(copyrow);
					
					Vector<Object> links = new Vector<Object>();
					
					links.addElement(txtnomeisola.getText());
					links.addElement(txtsequenza.getText());
					links.addElement(txtrisorsa.getText());
					links.addElement(DBOracleManager.getElementDescription(txtrisorsa.getText()));
					links.addElement(cmbabilitazione.getSelectedItem());
					links.addElement("0");
					links.addElement(Settings.getIpAddress());
					links.addElement(Settings.getEventDate());
					links.addElement(" ");
					links.addElement(java.sql.Types.TIMESTAMP);
					links.addElement(" ");
					links.addElement(java.sql.Types.TIMESTAMP);
					
					DBOracleManager.insertLinks(links);
									
					tbllinks.setRowHeight(20);
								
					txtnomeisola.setText("");
					txtsequenza.setText("");
					txtrisorsa.setText("");
					cmbabilitazione.getItemAt(0);
			                    
					JOptionPane.showMessageDialog(lw, "Abbinamento Isola - Risorsa inserito correttamente","Abbinamento Isola - Risorsa",JOptionPane.INFORMATION_MESSAGE);
				
					tbllinks.repaint();
					lblnumerorecord.setText("Numero righe: " + formatter.format(linkstablemodel.getRowCount()));

				}
			}
		}
				
		lw.setCursor(normalCursor);
	}
	
}
