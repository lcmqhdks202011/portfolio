//Ce controlleur prend deux path. Un pour l'emplacement du fichier des inscriptions
//et un pour le fichier des confirmations d'inscription

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControlleurInscriptions {
	
	// static variable single_instance of type VisualiserRepSercvices
    private static ControlleurInscriptions single_instance = null;
	
	// static method to create instance of VisualiserRepSercvices as a singleton class
    public static ControlleurInscriptions getInstance()
    {
        if (single_instance == null)
            single_instance = new ControlleurInscriptions();
  
        return single_instance;
    }
	
    //Methodes
    public void addInscription(Membre m, Session s){
    	
    	LocalDateTime now = LocalDateTime.now(); 
    	
    	Inscription ins = new Inscription(m, s);
    	
    	if(s.getDay() == now.getDayOfWeek().name()) {
			s.ins.add(ins);
        }
    	
    	m.inscriptions.add(ins);
    	
    	//Debugging
        System.out.println("Inscription added");
        System.out.println("Session: " + s.getCodeSession() + " has now the following members subscribed: ");
        for(Inscription i : s.ins) {
        	System.out.println(i.getMembre().getCodeMembre());
        }
        System.out.println("Member: " + m.getCodeMembre() + " is subscribed to the following sessions: ");
        for(Inscription i : m.inscriptions) {
        	System.out.println(i.getSession().getCodeSession());
        }
        System.out.println("----------------------------");

    }
    

    public void confirmerInscription(String codeMembre, String codeService){
    	
    	ControlleurMembre contM = ControlleurMembre.getInstance();
    	
    	Membre m = contM.getMembreByNumber(codeMembre);
    	
    	ControlleurServices contS = ControlleurServices.getInstance();
    	
    	for(var serv : contS.services) {
    		for(var sess : serv.sessions) {
    			for(var ins : sess.ins) {
    				if(ins.getMembre().equals(m)) {
    					ins.setConfirmed(true);
    					break;
    				}
    			}
    		}
    	}
    	
    	
    }

}
