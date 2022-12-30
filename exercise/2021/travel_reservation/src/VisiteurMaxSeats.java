package main;

public class VisiteurMaxSeats extends Visiteur {

	public Object visit(Object o) {
		Section s = (Section)o;
		return (Object) s.getMaxSeats();
		// TODO Auto-generated method stub

	}

}
