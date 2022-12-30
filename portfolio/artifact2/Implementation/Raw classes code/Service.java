import java.io.Serializable;

public class Service implements Serializable {

    //Attributs
    private final String dateEtHeureActuelles; 
    private final String dateDebutService;
    private final String dateFinService;
    private final String heureService;
    private final String recurrenceHebdomadaire;
    private final int capaciteMax;
    private final String codeProfessionnel;
    private final String codeService;
    private final Float frais;
    private final String commentaire;


    //Getters
    public String getDateEtHeureActuelles() { return dateEtHeureActuelles; }
    public String getDateDebutService() { return dateDebutService; }
    public String getDateFinService() { return dateFinService; }
    public String getHeureService() { return heureService; }
    public String getRecurrenceHebdomadaire() { return recurrenceHebdomadaire; }
    public int getCapaciteMax() { return capaciteMax; }
    public String getCodeService() { return codeService; }
    public String getCodeProfessionnel() { return codeProfessionnel; }
    public Float getFrais() { return frais; }
    public String getCommentaire() { return commentaire; }

    //Constructeur
    public Service(String codeService, String codeProfessionnel){

        this.codeProfessionnel = codeProfessionnel;
        this.codeService = codeService;
        //Solution temporaire. Ça nous évite d'initialiser les éléments moins importants à chaque fois
        this.dateEtHeureActuelles = "JJ-MM-AAAA HH:MM:SS";
        this.dateDebutService = "JJ-MM-AAAA";
        this.dateFinService = "JJ-MM-AAAA";
        this.heureService = "HH:MM";
        this.recurrenceHebdomadaire = null;
        this.capaciteMax = 4;
        this.frais = 10f;
        this.commentaire = null;
    }
    
    //Methodes
    
    public void printInfo(){
        //System.out.println("dateEtHeureActuelles : " + dateEtHeureActuelles);
        //System.out.println("dateFinService : " + dateFinService);
        //System.out.println("heureService : " + heureService);
        //System.out.println("recurrenceHebdomadaire : " + recurrenceHebdomadaire);
        //System.out.println("capaciteMax : " + capaciteMax);
        System.out.println("codeProfessionnel : " + codeProfessionnel);
        System.out.println("codeService : " + codeService);
        //System.out.println("frais : " + frais);
        //System.out.println("commentaire : " + commentaire);
    }
}
