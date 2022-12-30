package main;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Entite {
	
	protected ArrayList<Section> sections = new ArrayList<Section>();
	protected FType type;
	protected HashMap<String, Object> information;
	
	public FType getType() {
		return type;
	};
	
	
	public HashMap<String, Object> getInfo() {
		return information;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (String)information.get("ID");
	}
	
	public HashMap<String, Object> getInformation(){
		return information;
	}

	public void accept(Visiteur v) {
		v.visit(this);
	}
	
	public ArrayList<Section> getSections(){
		return this.sections;
	}
}
