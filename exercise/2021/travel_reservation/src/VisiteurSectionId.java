package main;

public class VisiteurSectionId extends Visiteur {

	public Object visit(Object o) {
		Section s = (Section)o;
		return (Object) s.sectionId();
		// TODO Auto-generated method stub

	}

}
