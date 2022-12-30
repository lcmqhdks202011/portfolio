package main;

public class FabriqueTrajet extends Fabrique {

	public FabriqueTrajet() {
		super();
	}
	
	public Entite fabriquer(Object[] args) {
		return new Trajet(args);
	}

}
