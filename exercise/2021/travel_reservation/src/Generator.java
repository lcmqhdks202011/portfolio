package main;

import java.util.Random;

public class Generator {
	
	public static String getRandomNumbers(int size) {
		
		String res = "";
		Random rand = new Random(size);
		
		for(int i=0; i<size; i++) {
			res += rand.nextInt();
		}
		
		return res;
	}
	

}
