package main;

import java.util.HashMap;

public class DBListBuilder {
	
	public static HashMap<FType, Database> dbBuild(String[] dbNames){
		FType[] types = FType.values();
		
		HashMap<FType, Database> res = new HashMap<FType, Database>();
		
		for(int i=0;i<types.length;i++) {
			res.put(types[i], new Database(types[i], dbNames[i]));
		}

		
		return res;
	}

}
