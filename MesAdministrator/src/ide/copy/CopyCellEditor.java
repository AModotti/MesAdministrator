package ide.copy;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

import bin.DBOracleManager;

public class CopyCellEditor implements TableCellEditor {

	private JTextField textfield = new JTextField();
	private JTable currenttable = new JTable();
	private int currentrow;
	private int currentcolumn;
	boolean state;
	
	@Override
	public void addCellEditorListener(CellEditorListener arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
	}

	@Override
	public Object getCellEditorValue() {
		return textfield.getText();
	}

	@Override
	public boolean isCellEditable(EventObject arg0) {
		return true;
	}

	@Override
	public void removeCellEditorListener(CellEditorListener arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public boolean shouldSelectCell(EventObject arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stopCellEditing() {
			
		switch (currentcolumn) {
			//********** REPARTO ********************************************************************************************************************
	        case 1:
	        	if(textfield.getText().trim().equals("")) {
	    			textfield.setBackground(Color.RED);
	    			textfield.setForeground(Color.BLUE);
	    			JOptionPane.showMessageDialog(null,"Il campo non può essere vuoto.");
	    			state = false;
	        	}else{
	        		if((textfield.getText().trim().equals("001"))||(textfield.getText().trim().equals("002"))||(textfield.getText().trim().equals("003"))||(textfield.getText().trim().equals("004"))||(textfield.getText().trim().equals("005"))){
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.BLUE);
	        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
	    	    		state = true;
	        		}else{
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.RED);
	        			JOptionPane.showMessageDialog(null,"Reparto non valido.");
	        			state = false;
	        		}
	        	}
	        	break; 
	        //********** ISOLA DI LAVORO ************************************************************************************************************	
	        case 2:
	        	if (textfield.getText().trim().equals("")) {
	    			textfield.setBackground(Color.RED);
	    			textfield.setForeground(Color.BLUE);
	    			JOptionPane.showMessageDialog(null,"Il campo non può essere vuoto.");
	    			state = false;
	        	}else{
	        		if(DBOracleManager.checkIfIslandExists(textfield.getText().trim().toUpperCase()) == null){
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.RED);
	        			JOptionPane.showMessageDialog(null,"Isola di lavoro non valida.");
	        			state = false;
	        		}else{
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.BLUE);
	        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
	        			state = true;
	        		}
	        	}
	            break; 
	        //********** DATA ***********************************************************************************************************************    
	        case 3:
	        	if(textfield.getText().trim().equals("")) {
	    			textfield.setBackground(Color.RED);
	    			textfield.setForeground(Color.BLUE);
	    			JOptionPane.showMessageDialog(null,"Il campo non può essere vuoto.");
	    			state = false;
	        	}else{
	        		String datepattern = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)\\s([01]?[0-9]|2[0-3])[.][0-5][0-9][.][0-5][0-9]";
	        		Pattern pattern = Pattern.compile(datepattern);
	        		Matcher matcher = pattern.matcher(textfield.getText().trim());
	        		if(matcher.matches()){
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.BLUE);
	        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
	    	    		state = true;
	        		}else{
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.RED);
	        			JOptionPane.showMessageDialog(null,"Data non valida");
	        			state = false;
	        		}
	        	}
	            break;
	        //********** TIPO ***********************************************************************************************************************    
	        case 4:
	        	if(textfield.getText().trim().equals("")) {
	    			textfield.setBackground(Color.RED);
	    			textfield.setForeground(Color.BLUE);
	    			JOptionPane.showMessageDialog(null,"Il campo non può essere vuoto.");
	    			state = false;
	        	}else{
	        		if((textfield.getText().trim().equalsIgnoreCase("ING"))||(textfield.getText().trim().equalsIgnoreCase("USC"))||(textfield.getText().trim().equalsIgnoreCase("ATT"))||(textfield.getText().trim().equalsIgnoreCase("LAV"))){
	        			if((textfield.getText().trim().equalsIgnoreCase("ING"))||(textfield.getText().trim().equalsIgnoreCase("USC"))){
	        				textfield.setBackground(currenttable.getBackground());
		        			textfield.setForeground(Color.BLUE);
		        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
		        			currenttable.getModel().setValueAt("", currentrow, currentcolumn+1);
		        			currenttable.getModel().setValueAt("0", currentrow, currentcolumn+5);
		        			currenttable.getModel().setValueAt("0", currentrow, currentcolumn+6);
		    	    		state = true;
	        			}else{
		        			textfield.setBackground(currenttable.getBackground());
		        			textfield.setForeground(Color.BLUE);
		        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
		    	    		state = true;
	        			}
	        		}else{
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.RED);
	        			JOptionPane.showMessageDialog(null,"Tipo non valido.");
	        			state = false;
	        		}
	        	}
	            break;
	        //********** STATO **********************************************************************************************************************    
	        case 5:
	        	if((currenttable.getModel().getValueAt(currentrow, currentcolumn-1).toString().equalsIgnoreCase("ING"))||(currenttable.getModel().getValueAt(currentrow, currentcolumn-1).toString().equalsIgnoreCase("USC"))){
	        		textfield.setBackground(currenttable.getBackground());
        			textfield.setForeground(Color.BLUE);
        			currenttable.getModel().setValueAt("", currentrow, currentcolumn);
	        	}else{
	        		if(textfield.getText().trim().equals("")) {
		    			textfield.setBackground(Color.RED);
		    			textfield.setForeground(Color.BLUE);
		    			JOptionPane.showMessageDialog(null,"Il campo non può essere vuoto.");
		    			state = false;
		        	}else if((textfield.getText().trim().equalsIgnoreCase("A"))||(textfield.getText().trim().equalsIgnoreCase("S"))){
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.BLUE);
	        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
	    	    		state = true;
	        		}else{
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.RED);
	        			JOptionPane.showMessageDialog(null,"Stato non valido.");
	        			state = false;
	        		}
	        	}
	            break;   
	        //********** BRANCH *********************************************************************************************************************    
	        case 6:
	        	if(textfield.getText().trim().equals("")) {
	    			textfield.setBackground(Color.RED);
	    			textfield.setForeground(Color.BLUE);
	    			JOptionPane.showMessageDialog(null,"Il campo non può essere vuoto.");
	    			state = false;
	        	}else{
	        		if(DBOracleManager.checkIfBranchExists(textfield.getText().trim().toUpperCase()) == null){
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.RED);
	        			JOptionPane.showMessageDialog(null,"Branch non valido.");
	        			state = false;
	        		}else{
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.BLUE);
	        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
	        			state = true;
	        		}
	        	}
	            break;
	        //********** RISORSA ********************************************************************************************************************    
	        case 7:
	        	if(textfield.getText().trim().equals("")) {
	    			textfield.setBackground(Color.RED);
	    			textfield.setForeground(Color.BLUE);
	    			JOptionPane.showMessageDialog(null,"Il campo non può essere vuoto.");
	    			state = false;
	        	}else{
	        		if(DBOracleManager.checkIfElementExists(textfield.getText().trim().toUpperCase()) == null){
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.RED);
	        			JOptionPane.showMessageDialog(null,"Risorsa non valida.");
	        			state = false;
	        		}else{
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.BLUE);
	        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
	        			state = true;
	        		}
	        	}
	            break;
	        //********** OPERATORE ******************************************************************************************************************    
	        case 8:
	        	if(textfield.getText().trim().equals("")) {
	    			textfield.setBackground(Color.RED);
	    			textfield.setForeground(Color.BLUE);
	    			JOptionPane.showMessageDialog(null,"Il campo non può essere vuoto.");
	    			state = false;
	        	}else{
	        		if(DBOracleManager.checkIfElementExists(textfield.getText().trim().toUpperCase()) == null){
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.RED);
	        			JOptionPane.showMessageDialog(null,"Operatore non valido.");
	        			state = false;
	        		}else{
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.BLUE);
	        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
	        			state = true;
	        		}
	        	}
	            break;
	        //********** WORK ORDER *****************************************************************************************************************    
	        case 9:
	        	if(textfield.getText().trim().equals("")) {
	    			textfield.setBackground(Color.RED);
	    			textfield.setForeground(Color.BLUE);
	    			JOptionPane.showMessageDialog(null,"Il campo non può essere vuoto.");
	    			state = false;
	        	}else{
	        		if((currenttable.getModel().getValueAt(currentrow, currentcolumn-5).toString().equalsIgnoreCase("ING"))||(currenttable.getModel().getValueAt(currentrow, currentcolumn-5).toString().equalsIgnoreCase("USC"))){
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.BLUE);
	        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
	        			state = true;
	        		}else{
		        		if(DBOracleManager.checkIfWorkOrderExists(textfield.getText().trim().toUpperCase()) == null){
		        			textfield.setBackground(currenttable.getBackground());
		        			textfield.setForeground(Color.RED);
		        			JOptionPane.showMessageDialog(null,"Ordine di produzione insesistente o chiuso.");
		        			state = false;
		        		}else{
		        			textfield.setBackground(currenttable.getBackground());
		        			textfield.setForeground(Color.BLUE);
		        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
		        			currenttable.getModel().setValueAt(DBOracleManager.getBranchOfWorkOrder(textfield.getText().toUpperCase()), currentrow, currentcolumn-3);
		        			state = true;
		        		}
	        		}
	        	}
	            break;
	        //********** OPERAZIONE *****************************************************************************************************************    
	        case 10:
	        	if(textfield.getText().trim().equals("")) {
	    			textfield.setBackground(Color.RED);
	    			textfield.setForeground(Color.BLUE);
	    			JOptionPane.showMessageDialog(null,"Il campo non può essere vuoto.");
	    			state = false;
	        	}else{
	        		if((currenttable.getModel().getValueAt(currentrow, currentcolumn-6).toString().equalsIgnoreCase("ING"))||(currenttable.getModel().getValueAt(currentrow, currentcolumn-6).toString().equalsIgnoreCase("USC"))){
	        			textfield.setBackground(currenttable.getBackground());
	        			textfield.setForeground(Color.BLUE);
	        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
	        			state = true;
	        		}else{
		        		if(DBOracleManager.checkIfOperationExists(currenttable.getModel().getValueAt(currentrow, currentcolumn-1).toString(),textfield.getText().trim().toUpperCase()) == null){
		        			textfield.setBackground(currenttable.getBackground());
		        			textfield.setForeground(Color.RED);
		        			JOptionPane.showMessageDialog(null,"Operazione insesistente.");
		        			state = false;
		        		}else{
		        			textfield.setBackground(currenttable.getBackground());
		        			textfield.setForeground(Color.BLUE);
		        			currenttable.getModel().setValueAt(textfield.getText().toUpperCase(), currentrow, currentcolumn);
		        			state = true;
		        		}
	        		}
	        	}
	            break;
	        default:
	        	state = true;
	        	break;
	    }
		
		return state;
		
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		currentrow = row;
		currentcolumn = column;
		currenttable = table;
		textfield.setBackground(Color.YELLOW);
		textfield.setForeground(Color.BLUE);
		textfield.setFont(currenttable.getFont());
		textfield.setHorizontalAlignment(SwingConstants.CENTER);
		textfield.setText(value.toString().toUpperCase());
		
		return textfield;
	}
	
}
