package main;

public class VisiteurArrive extends Visiteur {

	public Object visit(Object o) {
		Entite e = (Entite)o;
		return (Object) e.information.get("Arrival");
		// TODO Auto-generated method stub

	}

}
