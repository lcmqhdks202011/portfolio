import java.io.Serializable;
import java.util.Date;

public class Membre implements Serializable {

    //Attributs
    private final String codeMembre;
    private final InformationPersonnelle infoPerso;
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
    
    //Constructeur
    public Membre(String codeMembre){

        this.codeMembre = codeMembre;
        this.suspendu = false;
        //Temporaire. Pour nous faciliter la vie
        this.infoPerso = new InformationPersonnelle();
    }

    //Methodes
    public void printInfo(){
        System.out.println("codeMembre : " + codeMembre);
        //infoPerso.printInfo();
        if(suspendu){System.out.println("�tat : SUSPENDU");}
    }
}
