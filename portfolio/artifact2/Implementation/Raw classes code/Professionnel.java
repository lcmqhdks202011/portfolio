import java.io.Serializable;

public class Professionnel implements Serializable {

    //Attributs
    private final String codeProfessionnel;
    private final InformationPersonnelle infoPerso;
    private final Float montantDu;

    //Getters
    public String getCodeProfessionnel() { return codeProfessionnel; }
    public InformationPersonnelle getInfoPerso() { return infoPerso; }
    public Float getMontantDu() { return montantDu; }

    //Constructeur
    public Professionnel(String codeProfessionnel){

        this.codeProfessionnel = codeProfessionnel;
        //Temporaire. Pour nous faciliter la vie
        this.infoPerso = new InformationPersonnelle();
        this.montantDu = 10f;
    }

    //Methodes
    public void printInfo(){
        System.out.println("codeProfessionnel : " + codeProfessionnel);
        //infoPerso.printInfo();
        //System.out.println("montantDu : " + montantDu);
    }
}
