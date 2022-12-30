package main;

public class Port extends Entite {
	
	

	public Port(Object[] args) {
		this.type = FType.STATION_PORT;
		String[] keys = Form.getLabels(type);
		this.information = MapBuilder.buildInfoMap(keys, args);
	}


	

}
