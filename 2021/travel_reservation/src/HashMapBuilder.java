package main;

import java.util.HashMap;

public class HashMapBuilder {

	public static String informationBuild(HashMap<String, Object> hash, FType type) {
		String[] a = Form.getLabels(type);
		switch(type) {
		
			case VOYAGE_VOL:
				return hash.get(a[0]) + "-" + hash.get(a[1]) + ":[" + hash.get(a[2]) + "]" + hash.get(a[3]) +
					  "(" + hash.get(a[4]) + "-" + hash.get(a[5]) + ")";
				
			case VOYAGE_ITINERAIRE:
				return hash.get(a[0]) + ":[" + hash.get(a[1]) + "]" + hash.get(a[2]) +
					  "(" + hash.get(a[3]) + "-" + hash.get(a[4]) + ")";
				
			case VOYAGE_TRAJET:
				return hash.get(a[0]) + "-" + hash.get(a[1]) + ":[" + hash.get(a[2]) + "]" + hash.get(a[3]) +
						  "(" + hash.get(a[4]) + "-" + hash.get(a[5]) + ")";
				
			case STATION_AEROPORT:
				return hash.get(a[0]) + "";
			
			case STATION_PORT:
				return hash.get(a[0]) + "";
				
			case STATION_GARE:
				return hash.get(a[0]) + "";
			
			case COMPAGNIE_AERIENNE:
				return "ID : " + hash.get(a[0]) + " Tarif : " + hash.get(a[1]);
				
			case COMPAGNIE_CROISIERE:
				return "ID : " + hash.get(a[0]) + " Tarif : " + hash.get(a[1]);
				
			case COMPAGNIE_FERROVIAIRE:
				return "ID : " + hash.get(a[0]) + " Tarif : " + hash.get(a[1]);
				
			default: 
				return null;
		}
	}
	
	public static String sectionDetails(Section s) {
		String sectionID = (String) s.accept(new VisiteurSectionId());
		String takenSeats = (String)s.accept(new VisiteurTakenSeats());
		String maxSeats = (String)s.accept(new VisiteurMaxSeats());
		String prix = (String)s.accept(new VisiteurPrix());
		return DecoVerticalLine.decorate(sectionID) + 
			   DecoParentheseDivision.decorate(takenSeats, maxSeats) +
			   prix;
	}
}
