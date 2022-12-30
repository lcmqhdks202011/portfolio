package main;

public class FabriqueVol extends Fabrique {

	public FabriqueVol() {
		super();
	}
	
	public Entite fabriquer(Object[] args) {
		return new Vol(args);
	}

}
