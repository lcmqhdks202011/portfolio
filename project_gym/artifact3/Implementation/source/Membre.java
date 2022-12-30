import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Membre implements Serializable {

	public ArrayList<Inscription> inscriptions = new ArrayList<Inscription>();
	
    //Attributs
    private final String codeMembre;
    private InformationPersonnelle infoPerso;
    private boolean suspendu;
    private Date lastPayement;
	private Float frais;

    //Getters
    public Membre getMembre() { return this; }
    public String getCodeMembre() { return codeMembre; }
    public InformationPersonnelle getInfoPerso() { return infoPerso; }
    public boolean getSuspendu() { return suspendu; }
    public Float getFrais() { return frais;}
    
    
    //Setters
    public void setSuspendu(boolean suspendu) {
		this.suspendu = suspendu;
	}
	public void setFrais(Float frais) {
		this.frais = frais;
	}
    public void setInfoPerso(InformationPersonnelle infoPerso) {
		this.infoPerso = infoPerso;
	}
    
    //Constructeur
    public Membre(String codeMembre, InformationPersonnelle info){

        this.codeMembre = codeMembre;
        this.suspendu = false;
        this.infoPerso = info;
    }

    //Methodes
    public void printInfo(){
        System.out.println("codeMembre : " + codeMembre);
        infoPerso.printInfo();
        if(suspendu){System.out.println("SUSPENDU");}
    }
}
