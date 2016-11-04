package ide.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import bin.ColumnManager;

public class MainMouseAdapter extends MouseAdapter {

    private boolean resizing = false;
    private int resizingColumn = -1;
    private int oldWidth = -1;
    
    HashMap<Integer, Integer> position = new HashMap<>();
        
	@Override
    public void mousePressed(MouseEvent e) {
        //CODICE PER INTERCETTAZIONE RIDIMENSIONAMENTO COLONNA
        if(e.getSource() instanceof JTableHeader) {
            TableColumn tc = ((JTableHeader)e.getSource()).getResizingColumn();
            if(tc != null) {
                resizing = true;
                resizingColumn = tc.getModelIndex();
                oldWidth = tc.getPreferredWidth();
            } else {
                resizingColumn = -1;
                oldWidth = -1;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //CODICE PER GESTIONE RIDIMENSIONAMENTO COLONNA
        if(resizing) {
            if(e.getSource() instanceof JTableHeader) {
                TableColumn tc = ((JTableHeader)e.getSource()).getColumnModel().getColumn(resizingColumn);
                if(tc != null) {
                    int newWidth = tc.getPreferredWidth();
                    if(newWidth != oldWidth) {
                        ColumnManager.setColumnInformation(resizingColumn,newWidth);
                    }
                }
            }   
        }
        
        resizing = false;
        resizingColumn = -1;
        oldWidth = -1;
                
    }
	
}
