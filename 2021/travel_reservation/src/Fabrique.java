package main;

public class Fabrique {
	
	protected static Fabrique singleton;
	
	public Fabrique(){
		Fabrique.singleton = this;
	}
	
	public static Fabrique getInstance() {
		if(singleton == null) return new Fabrique();
		else return singleton;
	}
	
	public Entite fabriquer(Object[] args) {
		return null;
	}

}
