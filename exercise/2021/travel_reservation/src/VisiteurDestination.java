package main;

public class VisiteurDestination extends Visiteur {

	public Object visit(Object o) {
		Entite e = (Entite)o;
		return (Object) e.information.get("Destination");
		// TODO Auto-generated method stub

	}

}
