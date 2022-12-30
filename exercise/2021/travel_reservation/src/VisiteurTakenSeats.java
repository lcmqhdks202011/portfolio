package main;

public class VisiteurTakenSeats extends Visiteur {

	public Object visit(Object o) {
		Section s = (Section)o;
		return (Object) s.getTakenSeats();
		// TODO Auto-generated method stub
	}

}
