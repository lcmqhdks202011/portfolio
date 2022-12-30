import java.io.Serializable;
import java.util.ArrayList;

public class Professionnel implements Serializable {

	public ArrayList<Service> services = new ArrayList<Service>();
	
    //Attributs
    private final String codeProfessionnel;
    private InformationPersonnelle infoPerso;
    private Float montantDu;

    //Getters
    public String getCodeProfessionnel() { return codeProfessionnel; }
    public InformationPersonnelle getInfoPerso() { return infoPerso; }
    public Float getMontantDu() { return montantDu; }
    
    //Setters
    public void setInfoPerso(InformationPersonnelle infoPerso) {
		this.infoPerso = infoPerso;
	}

    //Constructeur
    public Professionnel(String codeProfessionnel, InformationPersonnelle info){

        this.codeProfessionnel = codeProfessionnel;
        //Temporaire. Pour nous faciliter la vie
        this.infoPerso = info;
    }


	//Methodes
    public void printInfo(){
        System.out.println("codeProfessionnel : " + codeProfessionnel);
        infoPerso.printInfo();
    }
}
