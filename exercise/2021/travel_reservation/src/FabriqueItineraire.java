package main;

public class FabriqueItineraire extends Fabrique {

	public FabriqueItineraire() {
		super();
	}
	
	public Entite fabriquer(Object[] args) {
		return new Itineraire(args);
	}

}
