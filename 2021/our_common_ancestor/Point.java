public class Point {
	double t;
	int n;
	Sim sim;

	public Point(Sim o, double t, int n) {
		super();
		this.t = t;
		this.n = n;
		sim = o;
	}
	
	@Override 
	public String toString() {
		String str = "\n"+ t +
				"(" + t + ", " + n + ")\n";
		str += sim + "\n";
		return str;
	}
}
