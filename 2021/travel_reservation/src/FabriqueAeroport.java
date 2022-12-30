package main;

public class FabriqueAeroport extends Fabrique {

	public FabriqueAeroport() {
		super();
	}
	
	public Entite fabriquer(Object[] args) {
		return new Aeroport(args);
	}

}
