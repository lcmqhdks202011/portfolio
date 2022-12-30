package main;

import java.util.HashMap;

public class MapBuilder {
	
	public static HashMap<String, Object> buildInfoMap(String[] keys, Object[] values){
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		for(int i=0;i<keys.length;i++) {
			result.put(keys[i], values[i]);
		}
		
		return result;
	}

}
