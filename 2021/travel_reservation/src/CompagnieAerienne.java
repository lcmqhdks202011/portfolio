package main;

public class CompagnieAerienne extends Entite {
	
	

	public CompagnieAerienne(Object[] args) {
		this.type = FType.COMPAGNIE_AERIENNE;
		String[] keys = Form.getLabels(type);
		this.information = MapBuilder.buildInfoMap(keys, args);
	}

	public double getTarif() {
		return (double)this.information.get("Tarif");
	}
	

}
