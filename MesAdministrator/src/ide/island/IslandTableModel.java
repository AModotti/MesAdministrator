package ide.island;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class IslandTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	public IslandTableModel(Vector<Object> data,Vector<String> columnnames){
		super(data,columnnames);
	}
	
	@Override
	public Class<?> getColumnClass(int col) {
        return super.getColumnClass(col);
	}

	@Override
    public boolean isCellEditable(int row, int col) {
		return false;
    }
    
    @Override
    public Object getValueAt(int row, int col) {
		return super.getValueAt(row, col);
    }
    
    @Override
    public void setValueAt(Object value, int row, int col){ 
       	super.setValueAt(value, row, col); 	
    }

}
