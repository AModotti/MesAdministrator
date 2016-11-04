package ide.copy;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class CopyTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	public CopyTableModel(Vector<Object> data, Vector<String> columnnames){
		super(data,columnnames);
	}
		
	@Override
	public Class<?> getColumnClass(int col) {
		return super.getColumnClass(col);
	}

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 1||col == 2||col == 3||col == 4||col == 5||col == 6||col == 7||col == 8||col == 9||col == 10;
    }
    
    @Override
    public Object getValueAt(int row, int col) {
		return super.getValueAt(row, col);
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) { 
    	super.setValueAt(value, row, col);
    }
   
}
