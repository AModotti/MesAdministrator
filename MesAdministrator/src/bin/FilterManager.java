package bin;


import java.util.HashMap;

public class FilterManager {
	
	private static HashMap<Integer, Object> filters = new HashMap<>();

	public static void setFiltersInformation(int item,Object filter){
		
		filters.put(item, filter);
		
	}
	
	public static void setFiltersInformation(HashMap<Integer, Object> getfilters){

		filters = getfilters;
	}
	
	public static HashMap<Integer, Object> getFiltersInformation(){

		return filters;
	}
	
	
}
