package main;

public class FabriqueGare extends Fabrique {

	public FabriqueGare() {
		super();
	}
	
	public Entite fabriquer(Object[] args) {
		return new Gare(args);
	}

}
