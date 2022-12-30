package main;

public class FabriquePort extends Fabrique {

	public FabriquePort() {
		super();
	}
	
	public Entite fabriquer(Object[] args) {
		return new Port(args);
	}

}
