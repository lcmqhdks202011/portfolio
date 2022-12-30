package main;

public class VisiteurEntiteID extends Visiteur {

	public Object visit(Object o) {
		
		Entite e = (Entite) o;
		
		return (Object)e.information.get("ID");
	}
}
