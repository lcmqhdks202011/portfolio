//But: Traiter des objets de type Service dans un fichier
import java.util.ArrayList;
import java.util.Date;

public class ControlleurServices{
	
	public ArrayList<Service> services = new ArrayList<Service>();
	
	// static variable single_instance of type VisualiserRepSercvices
    private static ControlleurServices single_instance = null;
	
	// static method to create instance of VisualiserRepSercvices as a singleton class
    public static ControlleurServices getInstance()
    {
        if (single_instance == null)
            single_instance = new ControlleurServices();
  
        return single_instance;
    }

    //M�thodes
    public void addService(Service s){
    
        System.out.println("Service added");
        System.out.println(s.getUID());
        System.out.println("----------------------------");
        services.add(s);
    }


    public void deleteService(Service s){
    	ControlleurProfessionnel contP = ControlleurProfessionnel.getInstance();
    
    	String codeService = s.getUID();
        services.remove(this.getServiceByNumber(codeService));
        for(Service serv : contP.getProfByNumber(s.getPro()).services){
        	if(serv.getPro().equals(s.getPro())) {
        		contP.getProfByNumber(s.getPro()).services.remove(serv);
        		break;
        	}
        }
        System.out.println("Service deleted");

    }

    public void updateService(Service s, String name, Date startDate, Date endDate, String description, int capacity){

    	String codeService = s.getUID();
    	
        Service service = this.getServiceByNumber(codeService);

        service.setName(name);
        service.setStartDate(startDate);
        service.setEndDate(endDate);
        service.setDescription(description);
        service.setCapacity(capacity);
        System.out.println("Service updated");

    }
    
    public Service getServiceByNumber(String number){
        for(var service : services){
            if(service.getUID().equals(number)){
                return service;
            }
        }
        return null;
    }
    
    public Session getSessionByNumber(String number){
        for(var service : services){
            for(var session : service.sessions) {
            	if(session.getCodeSession().equals(number)) {
            		return session;
            	}
            }
        }
        return null;
    }



}