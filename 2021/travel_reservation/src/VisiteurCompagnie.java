package main;

public class VisiteurCompagnie extends Visiteur {

	public Object visit(Object o) {
		Entite e = (Entite)o;
		return (Object) e.information.get("Company");
		// TODO Auto-generated method stub

	}

}
