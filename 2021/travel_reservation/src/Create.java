package main;

public class Create extends Commande {
	
	protected Object[] args;
	
	Create(Database db, Object[] args2){
		this.targetDatabase = db;
		this.type = db.getFType();
		this.args = args2;
		execute();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		switch(type) {
		
		case VOYAGE_VOL:
			this.targetDatabase.add(new FabriqueVol().fabriquer(args));
			break;
			
		case VOYAGE_ITINERAIRE:
			this.targetDatabase.add(new FabriqueItineraire().fabriquer(args));
			break;
			
		case VOYAGE_TRAJET:
			this.targetDatabase.add(new FabriqueTrajet().fabriquer(args));
			break;
			
		case STATION_AEROPORT:
			this.targetDatabase.add(new FabriqueAeroport().fabriquer(args));
			break;
		
		case STATION_PORT:
			this.targetDatabase.add(new FabriquePort().fabriquer(args));
			break;
			
		case STATION_GARE:
			this.targetDatabase.add(new FabriqueGare().fabriquer(args));
			break;
		
		case COMPAGNIE_AERIENNE:
			this.targetDatabase.add(new FabriqueCompagnieAerienne().fabriquer(args));
			break;
			
		case COMPAGNIE_CROISIERE:
			this.targetDatabase.add(new FabriqueCompagnieCroisiere().fabriquer(args));
			break;
			
		case COMPAGNIE_FERROVIAIRE:
			this.targetDatabase.add(new FabriqueCompagnieFerroviaire().fabriquer(args));
			break;
			
		default:
			
		
		
		}
		
		
	}

}
