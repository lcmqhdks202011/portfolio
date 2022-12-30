package main;

public class Aeroport extends Entite {
	
	

	public Aeroport(Object[] args) {
		this.type = FType.STATION_AEROPORT;
		String[] keys = Form.getLabels(type);
		this.information = MapBuilder.buildInfoMap(keys, args);
	}

	

}
