package main;

public class FabriqueCompagnieAerienne extends Fabrique {

	public FabriqueCompagnieAerienne() {
		super();
	}
	
	public Entite fabriquer(Object[] args) {
		return new CompagnieAerienne(args);
	}

}
