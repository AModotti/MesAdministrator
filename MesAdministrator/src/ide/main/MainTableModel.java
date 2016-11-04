package ide.main;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import bin.TableParser;

public class MainTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	public MainTableModel(Vector<Object> data, Vector<String> columnnames){
		super(data,columnnames);
	}
	
	@Override
	public Class<?> getColumnClass(int col) {
        if (col == 0) {
        	return Boolean.class;
        }
        return super.getColumnClass(col);
	}

	@Override
    public boolean isCellEditable(int row, int col) {
        return col == 0;
    }
    
    @Override
    public Object getValueAt(int row, int col) {
		return super.getValueAt(row, col);
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) { 
    	if(col == 0){
    		Vector<Object> field = new Vector<Object>();
    		if(value == (Boolean) true){
    			field.addElement(getValueAt(row, 1));
    			field.addElement(getValueAt(row, 2));
    			field.addElement(getValueAt(row, 3));
    			field.addElement(getValueAt(row, 4));
    			field.addElement(getValueAt(row, 5));
    			field.addElement(getValueAt(row, 6));
    			field.addElement(getValueAt(row, 7));
    			field.addElement(getValueAt(row, 8));
    			field.addElement(getValueAt(row, 10));
    			field.addElement(getValueAt(row, 12));
    			field.addElement(getValueAt(row, 13));
    			field.addElement(row);
    			TableParser.addData(Integer.parseInt(getValueAt(row, 1).toString()),field);
    		}else{
    			TableParser.removeData(Integer.parseInt(getValueAt(row, 1).toString()));
			}
    	}
    	super.setValueAt(value, row, col);
    }
    
}
