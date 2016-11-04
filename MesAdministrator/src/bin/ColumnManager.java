package bin;


import java.util.HashMap;

public class ColumnManager {
	
	private static HashMap<Integer, Integer> column = new HashMap<>();

	public static void setColumnInformation(int col,int width){
		
		column.put(col, width);
		
	}
	
	public static void setColumnInformation(HashMap<Integer, Integer> getcolumn){

		column = getcolumn;
	}
	
	public static HashMap<Integer, Integer> getColumnInformation(){

		return column;
	}
	
	
}
