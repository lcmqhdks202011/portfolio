package main;

import java.util.HashMap;

public class GUIStrings {
	
	public static final String main_menu = "Main Menu";
	public static final String btn_client = "Client";
	public static final String btn_admin = "Admin";
	public static final String[] cb_type = {"Vol", "Itineraire", "Trajet de Train", "Aeroport", "Port", "Gare",
			"Compagnie Aerienne", "Compagnie Croisiere", "Compagnie Ferroviaire"};
	public static final HashMap<String, FType> transTable = createTranslateTable();

	public static HashMap<String, FType> createTranslateTable(){
		HashMap<String, FType> result = new HashMap<String, FType>();
		FType[] types = FType.values();
		for(int i=0; i < cb_type.length; i++) {
			result.put(cb_type[i], types[i]);
		}
		return result;
	}
	
	public static FType translate(String str) {
		return transTable.get(str);
	}
	


}
