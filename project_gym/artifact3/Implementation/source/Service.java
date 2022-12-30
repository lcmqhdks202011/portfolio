import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Service implements Serializable {

    //Attributs
	
	public ArrayList<Session> sessions = new ArrayList<Session>();
	
	private String uID;
	private String pro;
    private String name; 
	private Date startDate;
    private Date endDate;
    private String description;
    private final int MAX_CAPACITY = 30;
    private int capacity;
    private Float fees;

    //Getters
    public String getUID() { return uID; }
    public String getPro() { return pro; }
    public String getName() { return name; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public String getDescription() { return description; }
    public int getCapacity() { return capacity; }
    public Float getFees() { return fees; }
    
    //Setters
    
    public void setName(String name) {
		this.name = name;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setFees(Float fees) {
		this.fees = fees;
	}


    //Constructeur
    public Service(String pro, String name, Date start, Date end, String description, int capacity, Float fees){

    	if(!Validateur.codeMembreValide(pro)){
            System.out.println("Invalid professional code");
            return;
        }
    	this.pro = pro;
    	this.uID = Generator.generateNum(3);
    	this.capacity = capacity;
    	this.name = name;
    	this.startDate = start;
    	this.endDate = end;
    	this.fees = fees;
    	this.description = description;
    	
    	ControlleurProfessionnel contP = ControlleurProfessionnel.getInstance();

    	try{
            contP.getProfByNumber(pro).services.add(this);
        }
    	catch (NullPointerException ignored){
    	    System.out.println("The professional doesn't exist, this service has no parent");
        }
    	
    }
    
    //Methodes

    public void printInfo(){
        System.out.println("id : " + uID);
        System.out.println("idProfessional : " + pro);
        System.out.println("name : " + name);
        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("description : " + description);
        System.out.println("capacity : " + capacity);
        System.out.println("fees : " + fees);
    }
    
     public Session getSessionByNumber(String sessionID) {
        for (Session s : this.sessions) {
            if (s.getCodeSession().equals(sessionID)) {
                return s;
            }
        }

        System.out.print("Session number not found");
        return null;
    }
    
}
