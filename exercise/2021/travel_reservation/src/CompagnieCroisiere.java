package main;

public class CompagnieCroisiere extends Entite {
	
	

	public CompagnieCroisiere(Object[] args) {
		this.type = FType.COMPAGNIE_CROISIERE;
		String[] keys = Form.getLabels(type);
		this.information = MapBuilder.buildInfoMap(keys, args);
	}


	public double getTarif() {
		return (double)this.information.get("Tarif");
	}

}
