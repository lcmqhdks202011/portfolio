package main;

import java.util.HashMap;

public class Disposition {
	
	private static Disposition singleton;
	
	
	
	private int[] nbColumns = {3, 4, 6, 10};
	private String[] dispositionLetters = {"S", "C", "M", "L"};
	private HashMap<DispositionType, Integer> hashNbColumns = initNbColumns();
	private HashMap<DispositionType, char[]> hashColumnLetters = initColumnLetters();
	private HashMap<DispositionType, String> hashDispositionLetters = initDispositionLetters();
	
	public  HashMap<DispositionType, Integer> initNbColumns(){
		HashMap<DispositionType, Integer> res = new HashMap<DispositionType, Integer>();
		DispositionType[] dtypes = DispositionType.values();
		
		for(int i=0; i<nbColumns.length;i++) {
			res.put(dtypes[i], nbColumns[i]);
		}
		
		return res;
		
	}
	
	public  HashMap<DispositionType, String> initDispositionLetters() {
		HashMap<DispositionType, String> res = new HashMap<DispositionType, String>();
		DispositionType[] dtypes = DispositionType.values();
		
		for(int i=0; i<dispositionLetters.length;i++) {
			res.put(dtypes[i], dispositionLetters[i]);
		}
		
		return res;
	}

	public  HashMap<DispositionType, char[]> initColumnLetters(){
		HashMap<DispositionType, char[]> res = new HashMap<DispositionType, char[]>();
		DispositionType[] dtypes = DispositionType.values();
		
		for(int i=0; i<nbColumns.length;i++) {
			char[] letters = new char[nbColumns[i]];
			for(int j=0;j<nbColumns[i];j++) {
				letters[j] =(char)(65 + j);
			}
			res.put(dtypes[i], letters);
		}	
		return res;
		
	}
 
	
	public  int getNumberColumn(DispositionType dtype) {
		return hashNbColumns.get(dtype);
	}
	
	public char[] getColumnLetters(DispositionType dtype) {
		return hashColumnLetters.get(dtype);
	}
	
	public String getDispositionLetter(DispositionType dtype) {
		return hashDispositionLetters.get(dtype);
	}
	
	public static Disposition getInstance() {
		if(singleton != null) return singleton;
		else return new Disposition();
		
	}


}
