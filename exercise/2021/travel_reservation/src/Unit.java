package main;

public abstract class Unit {
	
	private String unitID;
	private Etat etat = new EtatLibre();
	
	Unit(String id) {
		unitID = id;
	}
	
	public void accept(VisitState v) {
		v.visit();
	}
	
	public boolean isReservable() {
		return this.etat.isReservable();
	}
	
	public String toString() {
		return unitID;
	}
	

}
