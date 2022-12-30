import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Inscription implements Serializable {

    //Attributs
	private Session session;
	private Membre membre;
	private String creationDate;
	private String startDate;
	private boolean isConfirmed;
    private String comments;


    //Constructeur
    public Inscription(Membre m, Session s){
    	
    	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
    	DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
    	LocalDateTime now = LocalDateTime.now();  

       this.session = s;
       this.startDate = date.format(now);
    	this.isConfirmed = false;
    	this.creationDate = dateTime.format(now);  
    	this.membre = m;
   
    	
    	
    }
    
    //Getters
    public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public Membre getMembre() {
		return membre;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getComments() {
		return comments;
	}

	//Methodes
    public void printInfo(){
        //System.out.println("dateHeureActuelles : " + dateHeureActuelles);
        //System.out.println("dateService : " + dateService);
        //System.out.println("codeProfessionnel : " + codeProfessionnel);
        //System.out.println("codeMembre : " + codeMembre);
        //System.out.println("codeService : " + codeService);
        //System.out.println("commentaire : " + commentaire);
    }
}
