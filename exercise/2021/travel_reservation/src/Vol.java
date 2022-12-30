package main;

import java.util.ArrayList;

public class Vol extends Entite {
	
	private ArrayList<SectionVol> sections = new ArrayList<SectionVol>();
	
	private static ClasseType[] eligibleClasses = {ClasseType.PREMIERE,
												   ClasseType.AFFAIRE,
												   ClasseType.ECONOMIQUE_PREMIUM,
												   ClasseType.ECONOMIQUE};
	
	

	public Vol(Object[] args) {
		this.type = FType.VOYAGE_VOL;
		String[] keys = Form.getLabels(type);
		this.information = MapBuilder.buildInfoMap(keys, args);
	}
	
	public void setSections(ArrayList<SectionVol> sections2) {
		this.sections = sections2;
	}


	public static ClasseType[] getClasses() {
		return eligibleClasses;
	}
	
	public Section getSection(int i){
		return this.sections.get(i);
	}

	
}
