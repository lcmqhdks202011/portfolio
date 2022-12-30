package main;

import java.util.ArrayList;

public class Itineraire extends Entite {
	
	private ArrayList<SectionItineraire> sections = new ArrayList<SectionItineraire>();
	
	private static ClasseType[] eligibleClasses = {ClasseType.INTERIEURE,
			   									   ClasseType.ECONOMIQUE,
			   									   ClasseType.SUITE,
			   									   ClasseType.FAMILLE,
			   									   ClasseType.FAMILLEDELUXE};
	public Itineraire(Object[] args) {
		this.type = FType.VOYAGE_ITINERAIRE;
		String[] keys = Form.getLabels(type);
		this.information = MapBuilder.buildInfoMap(keys, args);
	}

	public static ClasseType[] getClasses() {
		return eligibleClasses;
	}

	public void setSections(ArrayList<SectionItineraire> sections) {
		this.sections = sections;
	}
	
	public SectionItineraire getSection(int i) {
		return this.sections.get(i);
	}
	

}
