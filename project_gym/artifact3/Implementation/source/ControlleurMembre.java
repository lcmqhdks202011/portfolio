import java.util.ArrayList;

public class ControlleurMembre {

	public ArrayList<Membre> membres = new ArrayList<Membre>();
	
	// static variable single_instance of type VisualiserRepSercvices
    private static ControlleurMembre single_instance = null;
	
	// static method to create instance of VisualiserRepSercvices as a singleton class
    public static ControlleurMembre getInstance()
    {
        if (single_instance == null)
            single_instance = new ControlleurMembre();
  
        return single_instance;
    }

    //Methodes
    public void addMembre(Membre newMembre){
        System.out.println("Member added");
        newMembre.printInfo();
        System.out.println("----------------------------");
        membres.add(newMembre);
    }

    public void deleteMembre(String codeMembre){

    	membres.remove(this.getMembreByNumber(codeMembre));
    	System.out.println("Member deleted");

    }

    public void updateMembre(String codeMembre, InformationPersonnelle infoPerso){
    
        if(!Validateur.codeMembreValide(codeMembre)){
            System.out.println("Invalid member code");
            return;
        }

        for( Membre membre : membres){
            if(membre.getCodeMembre().equals(codeMembre)){

                membre.setInfoPerso(infoPerso);
                System.out.println("Member updated");
                return;
            }
        }
        System.out.println("Member not found");
    }
    
    public Membre getMembreByNumber(String number){
        for(var membre : membres){
            if(membre.getCodeMembre().equals(number)){
                return membre;
            }
        }
        return null;
    }

}
