package main;

public class FabriqueCompagnieFerroviaire extends Fabrique {

	public FabriqueCompagnieFerroviaire() {
		super();
	}
	
	public Entite fabriquer(Object[] args) {
		return new CompagnieFerroviaire(args);
	}

}
