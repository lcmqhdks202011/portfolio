package main;

import java.util.HashMap;

public class Classe {
	
	private static String[] classeLetters = {"F", "A", "P", "E", "I", "O", "S", "F", "D"};
	private static double[] taux = {1, 0.75, 0.6, 0.5, 0.5, 0.75, 0.9, 0.9, 1};
	private static HashMap<ClasseType, Double> hashTaux = initTaux();
	private static HashMap<ClasseType, String> hashClasseLetters = initClasseLetters();
	
	private static HashMap<ClasseType, Double> initTaux(){
		HashMap<ClasseType, Double> res = new HashMap<ClasseType, Double>();
		ClasseType[] dtypes = ClasseType.values();
		
		for(int i=0; i<taux.length;i++) {
			res.put(dtypes[i], taux[i]);
		}
		
		return res;
		
	}
	
	private static HashMap<ClasseType, String> initClasseLetters() {
		HashMap<ClasseType, String> res = new HashMap<ClasseType, String>();
		ClasseType[] dtypes = ClasseType.values();
		
		for(int i=0; i<classeLetters.length;i++) {
			res.put(dtypes[i], classeLetters[i]);
		}
		
		return res;
	}
 
	
	public static double getTaux(ClasseType dtype) {
		return hashTaux.get(dtype);
	}
	
	
	public static String getClasseLetter(ClasseType dtype) {
		return hashClasseLetters.get(dtype);
	}


}
