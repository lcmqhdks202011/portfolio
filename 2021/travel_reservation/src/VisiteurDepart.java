package main;

public class VisiteurDepart extends Visiteur {

	public Object visit(Object o) {
		Entite e = (Entite)o;
		return (Object) e.information.get("Depart");
		// TODO Auto-generated method stub

	}

}
