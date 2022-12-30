import java.io.Serializable;

public class Inscription implements Serializable {

    //Attributs
    private final String dateHeureActuelles;
    private final String dateService;
    private final String codeProfessionnel;
    private final String codeMembre;
    private final String codeService;
    private final String commentaire;

    //Getters
    public String getDateHeureActuelles() { return dateHeureActuelles; }
    public String getDateService() { return dateService; }
    public String getCodeProfessionnel() { return codeProfessionnel; }
    public String getCodeMembre() { return codeMembre; }
    public String getCodeService() { return codeService; }
    public String getCommentaire() { return commentaire; }

    //Constructeur
    public Inscription(String codeProfessionnel, String codeMembre, String codeService){
        this.codeProfessionnel = codeProfessionnel;
        this.codeMembre = codeMembre;
        this.codeService = codeService;
        //Temporaire. Pour nous faciliter la vie
        this.dateHeureActuelles = "JJ-MM-AAAA HH:MM:SS";
        this.dateService = "JJ-MM-AAAA";
        this.commentaire = null;
    }

    //Methodes
    public void printInfo(){
        //System.out.println("dateHeureActuelles : " + dateHeureActuelles);
        //System.out.println("dateService : " + dateService);
        System.out.println("codeProfessionnel : " + codeProfessionnel);
        System.out.println("codeMembre : " + codeMembre);
        System.out.println("codeService : " + codeService);
        //System.out.println("commentaire : " + commentaire);
    }
}
