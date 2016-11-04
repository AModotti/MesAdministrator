package bin;

import java.util.Collections;
import java.util.TreeMap;
import java.util.Vector;

public class TableParser {
	
	private static TreeMap<Integer, Vector<Object>> dataobject = new TreeMap<Integer, Vector<Object>>(Collections.reverseOrder());

	public static void addData(int index,Vector<Object> data){
		dataobject.put(index,data);
	}
	
	public static void removeData(int index){
		dataobject.remove(index);
	}
	
	public static Vector<Object> getData(){
		Object[] data = dataobject.values().toArray();
		
		Vector<Object> rows = new Vector<Object>();
		
		for (int i=0;i<data.length; i++) {
			rows.add(data[i]);
		}
		
		return rows;
	}
	
	public static void clearData(){
		dataobject.clear();
	}
	
	
	public static int getSize(){
		return dataobject.size();
	}
		
}



