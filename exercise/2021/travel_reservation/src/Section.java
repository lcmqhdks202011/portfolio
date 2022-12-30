package main;

import java.util.List;

public abstract class Section {
	
	protected String sectionId;
	protected Entite parent;
	protected ClasseType classe;
	protected DispositionType disposition;
	protected List<Unit> units;
	
	Section(Entite parent){
		this.parent = parent;
	}
	
	public abstract double getPrix();
	
	public abstract int getTakenSeats();
	
	public abstract int getMaxSeats();
	
	public abstract String getSectionId();
	
	
	public Object accept(Visiteur v) {
		return v.visit(this);
	}

	public String sectionId() {
		// TODO Auto-generated method stub
		return null;
	}


}
