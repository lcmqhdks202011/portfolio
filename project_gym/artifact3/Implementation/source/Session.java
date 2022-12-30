import java.io.Serializable;
import java.util.ArrayList;

public class Session implements Serializable{

	public ArrayList<Inscription> ins = new ArrayList<Inscription>();
	
	private Day day;
	private String schedule;
	private String codeSession;
	private Service fromService;
	
	public Session(String hour, Day d, Service s) {
		this.day = d;
		this.schedule = hour;
		this.fromService = s;
		codeSession = Generator.generateNumSession(s);
		
	}
	public int getCapacity() {
		return (fromService.getCapacity() - ins.size());
	}
	public boolean isFull() {
		return ins.size() >= fromService.getCapacity();
	}
	public Service getService() {
		return fromService;
	}
	public String getDay() {
		return day.name();
	}
	public String getCodeSession() {
		return codeSession;
	}
	public String getSchedule() {
		return schedule;
	}
	
}
