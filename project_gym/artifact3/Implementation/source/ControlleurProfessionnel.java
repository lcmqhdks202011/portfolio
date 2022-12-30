import java.util.ArrayList;

public class ControlleurProfessionnel{

	public ArrayList<Professionnel> pros = new ArrayList<Professionnel>();
	
	// static variable single_instance of type VisualiserRepSercvices
    private static ControlleurProfessionnel single_instance = null;
	
	// static method to create instance of VisualiserRepSercvices as a singleton class
    public static ControlleurProfessionnel getInstance()
    {
        if (single_instance == null)
            single_instance = new ControlleurProfessionnel();
  
        return single_instance;
    }

    //Methodes
    public void addProfessionnel(Professionnel newPro){
        System.out.println("Professionnel added");
        newPro.printInfo();
        System.out.println("----------------------------");
        pros.add(newPro);
    }

    public void deleteProfessionnel(String codeProfessionnel){


    	pros.remove(this.getProfByNumber(codeProfessionnel));
        System.out.println("Professionnel deleted");

    }
    
	public void updateProfessionnel(String codePro, InformationPersonnelle infoPerso){
	    
	    if(!Validateur.codeMembreValide(codePro)){
	        System.out.println("Invalid member code");
	        return;
	    }
	
	    for( var pro : pros){
	        if(pro.getCodeProfessionnel().equals(codePro)){
	
	            pro.setInfoPerso(infoPerso);
	            System.out.println("Pro updated");
	            return;
	        }
	    }
	    System.out.println("Pro not found");
	}
    
    public Professionnel getProfByNumber(String number){
        for(var prof : pros){
            if(prof.getCodeProfessionnel().equals(number)){
                return prof;
            }
        }
        return null;
    }


}
