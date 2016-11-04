package bin;

import java.util.Comparator;
import java.util.List;

public class SortManager implements Comparator<Object> {
	private int column;
	private boolean ascending;

	SortManager(int column, boolean ascending){
		this.column = column;
		this.ascending = ascending;
	}

    @Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(Object a, Object b) {
    	
		List<Object> v1 = (List<Object>) a;
		List<Object> v2 = (List<Object>) b;

        Object o1 = v1.get(column);
        Object o2 = v2.get(column);

        // Treat empty strings like nulls
        if(o1 instanceof String && ((String)o1).length() == 0){
        	o1 = null;
        }

        if (o2 instanceof String && ((String)o2).length() == 0){
            o2 = null;
        }

        if (o1 == null && o2 == null){
        	return 0;
    	}
        
        if (o1 == null){
        	return 1;
        }
        
        if (o2 == null){
        	return -1;
        }

        if (o1 instanceof Comparable){
        	if (ascending){
        		return ((Comparable)o1).compareTo(o2);
            }else{
                return ((Comparable)o2).compareTo(o1);
            }
        }else{
        	if (ascending){
        		return o1.toString().compareTo(o2.toString());
            }else{
                return o2.toString().compareTo(o1.toString());
            }
        }
     }
}
