package main;

public class CompagnieFerroviaire extends Entite {
	
	

	public CompagnieFerroviaire(Object[] args) {
		this.type = FType.COMPAGNIE_FERROVIAIRE;
		String[] keys = Form.getLabels(type);
		this.information = MapBuilder.buildInfoMap(keys, args);
	}


	public double getTarif() {
		return (double)this.information.get("Tarif");
	}

}
