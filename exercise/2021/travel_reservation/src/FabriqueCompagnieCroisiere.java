package main;

public class FabriqueCompagnieCroisiere extends Fabrique {

	public FabriqueCompagnieCroisiere() {
		super();
	}
	
	public Entite fabriquer(Object[] args) {
		return new CompagnieCroisiere(args);
	}

}
