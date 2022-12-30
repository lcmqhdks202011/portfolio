package main;

public class Gare extends Entite {
	
	

	public Gare(Object[] args) {
		this.type = FType.STATION_GARE;
		String[] keys = Form.getLabels(type);
		this.information = MapBuilder.buildInfoMap(keys, args);
	}


	

}
