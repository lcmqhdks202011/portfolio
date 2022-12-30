package main;

import java.util.ArrayList;

public class Trajet extends Entite {
	
	private ArrayList<SectionTrajet> sections = new ArrayList<SectionTrajet>();

	
	private static ClasseType[] eligibleClasses = {ClasseType.PREMIERE,
			   ClasseType.ECONOMIQUE};

	public Trajet(Object[] args) {
		this.type = FType.VOYAGE_TRAJET;
		String[] keys = Form.getLabels(type);
		this.information = MapBuilder.buildInfoMap(keys, args);
	}

	public static ClasseType[] getClasses() {
		return eligibleClasses;
	}

	public void setSections(ArrayList<SectionTrajet> s) {
			this.sections = s;
	}
	
	public SectionTrajet getSection(int i){
		return this.sections.get(i);
	}

}
