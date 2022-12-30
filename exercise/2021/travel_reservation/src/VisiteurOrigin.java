package main;

public class VisiteurOrigin extends Visiteur {

	public Object visit(Object o) {
		Entite e = (Entite) o;
		
		return (Object)e.information.get("Origin");

	}

}
