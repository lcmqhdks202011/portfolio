package main;

public class VisiteurPrix extends Visiteur {

	public Object visit(Object o) {
		Section s = (Section)o;
		return (Object) s.getPrix();
		// TODO Auto-generated method stub

	}

}
