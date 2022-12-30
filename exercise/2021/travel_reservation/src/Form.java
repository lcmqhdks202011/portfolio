package main;

public class Form {
	
	public enum InputType {
		TEXT, COMBOBOX
	}
	
	private static String[] vol = {"Origin", "Destination","Company", "ID", "Depart", "Arrival"};
	private static InputType[] vol_input_type = {InputType.COMBOBOX,
												 InputType.COMBOBOX,
												 InputType.COMBOBOX,
												 InputType.TEXT,
												 InputType.TEXT,
												 InputType.TEXT
												 }; 
	private static FType[] vol_ftypes = {FType.STATION_AEROPORT,
										 FType.STATION_AEROPORT,
										 FType.COMPAGNIE_AERIENNE,
										 null,
										 null,
										 null
							
	};
	private static String[] itineraire = {"Origin","Company", "ID", "Depart", "Arrival"};
	private static InputType[] itineraire_input_type = {InputType.COMBOBOX,
														 InputType.COMBOBOX,
														 InputType.TEXT,
														 InputType.TEXT,
														 InputType.TEXT}; 
	private static FType[] itineraire_ftypes = {FType.STATION_PORT,
			 FType.COMPAGNIE_CROISIERE,
			 null,
			 null,
			 null
};
	private static String[] trajet = {"Origin", "Destination","Company", "ID", "Depart", "Arrival"};
	private static InputType[] trajet_input_type = {InputType.COMBOBOX,
													InputType.COMBOBOX,
													InputType.COMBOBOX,
													InputType.TEXT,
													InputType.TEXT,
													InputType.TEXT}; 
	private static FType[] trajet_ftypes = {FType.STATION_GARE,
			 FType.STATION_GARE,
			 FType.COMPAGNIE_FERROVIAIRE,
			 null,
			 null,
			 null
};
	private static InputType[] station_input_type = {InputType.TEXT,
			 										 InputType.TEXT};
	private static String[] aeroport = {"ID", "City"};
	private static String[] port = aeroport;
	private static String[] gare = aeroport;
	private static String[] compagnieAerienne = {"ID", "Initial", "Tarif"};
	private static String[] compagnieCroisiere = compagnieAerienne;
	private static String[] compagnieFerroviaire = compagnieAerienne;
	
	private static InputType[] compagnie_input_type = {InputType.TEXT,
													   InputType.TEXT,
													   InputType.TEXT};
	
	public static String[] getLabels(FType type) {
		
		switch(type) {
		
			case VOYAGE_VOL:            return vol;
			case VOYAGE_ITINERAIRE:     return itineraire;
			case VOYAGE_TRAJET:         return trajet;
			case STATION_AEROPORT:      return aeroport;
			case STATION_PORT:          return port;
			case STATION_GARE:          return gare;
			case COMPAGNIE_AERIENNE:    return compagnieAerienne;
			case COMPAGNIE_CROISIERE:   return compagnieCroisiere;
			case COMPAGNIE_FERROVIAIRE: return compagnieFerroviaire;
			default:
				break;
		}
		
		return null;
		
	};
	
	public static InputType[] getInputType(FType type) {
			
			switch(type) {
				case VOYAGE_VOL:        return vol_input_type;
				case VOYAGE_ITINERAIRE: return itineraire_input_type;
				case VOYAGE_TRAJET:     return trajet_input_type;
				
				case STATION_AEROPORT:
				case STATION_PORT:
				case STATION_GARE:      return station_input_type;
				
				case COMPAGNIE_AERIENNE:
				case COMPAGNIE_CROISIERE:
				case COMPAGNIE_FERROVIAIRE:
					                    return compagnie_input_type;
				default:
					break;
			}
			return null;
			
		};
		
	public static FType[] getFTypeList(FType type) {
			
			switch(type) {
				case VOYAGE_VOL:        return vol_ftypes;
				case VOYAGE_ITINERAIRE: return itineraire_ftypes;
				case VOYAGE_TRAJET:     return trajet_ftypes;
				
				default:
					break;
			}
			return null;
			
		};

}
