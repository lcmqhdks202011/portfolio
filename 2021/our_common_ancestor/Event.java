import java.util.Random;

public class Event {
	
	Sim subject;
	
	double time = 0.0;
	
	String type;

	
	/*
	 * @param subject : the subject of this event
	 * @param time : the time when the event occurred
	 * @param type : the type of the event
	 */
	public Event(Sim subject, double time, String type) {
		super();
		this.subject = subject;
		this.time = time;
		this.type = type;
	}
	

	@Override 
	public String toString() {
		String str = "";
		return str + " " + subject + " " + time + " " + type;
	}


	public static void main(String[] args) {

	}

}
