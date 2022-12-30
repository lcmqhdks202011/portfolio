package main;

public class Modify extends Commande {
	
	int index;

	protected Object[] args;
	
	Modify(Database db, Object[] args2, int i){
		this.targetDatabase = db;
		this.type = db.getFType();
		this.args = args2;
		this.index = i;
		execute();
	}

	@Override
	public void execute() {

		switch(type) {
		
		case VOYAGE_VOL:
			this.targetDatabase.replace(new FabriqueVol().fabriquer(args), index);
			break;
			
		case VOYAGE_ITINERAIRE:
			this.targetDatabase.replace(new FabriqueItineraire().fabriquer(args), index);
			break;
			
		case VOYAGE_TRAJET:
			this.targetDatabase.replace(new FabriqueTrajet().fabriquer(args), index);
			break;
			
		case STATION_AEROPORT:
			this.targetDatabase.replace(new FabriqueAeroport().fabriquer(args), index);
			break;
		
		case STATION_PORT:
			this.targetDatabase.replace(new FabriquePort().fabriquer(args), index);
			break;
			
		case STATION_GARE:
			this.targetDatabase.replace(new FabriqueGare().fabriquer(args), index);
			break;
		
		case COMPAGNIE_AERIENNE:
			this.targetDatabase.replace(new FabriqueCompagnieAerienne().fabriquer(args), index);
			break;
			
		case COMPAGNIE_CROISIERE:
			this.targetDatabase.replace(new FabriqueCompagnieCroisiere().fabriquer(args), index);
			break;
			
		case COMPAGNIE_FERROVIAIRE:
			this.targetDatabase.replace(new FabriqueCompagnieFerroviaire().fabriquer(args), index);
			break;
			
		default:
			break;
		}
		
		
		
		
		
	}

}
