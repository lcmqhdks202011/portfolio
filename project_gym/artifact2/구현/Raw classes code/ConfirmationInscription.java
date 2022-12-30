import java.io.Serializable;

public class ConfirmationInscription implements Serializable {

    //Attributs
    private final String dateEtHeureActuelles;
    private final String codeProfessionnel;
    private final String codeMembre;
    private final String codeService;
    private final String commentaire;

    //Getters
    public String getDateEtHeureActuelles() { return dateEtHeureActuelles; }
    public String getCodeProfessionnel() { return codeProfessionnel; }
    public String getCodeMembre() { return codeMembre; }
    public String getCodeService() { return codeService; }
    public String getCommentaire() { return commentaire; }

    //Constructeur
    public ConfirmationInscription(String codeProfessionnel, String codeMembre, String codeService){
        this.codeProfessionnel = codeProfessionnel;
        this.codeMembre = codeMembre;
        this.codeService = codeService;
        //Temporaire. Pour nous faciliter la vie
        this.dateEtHeureActuelles = "JJ-MM-AAAA HH:MM:SS";
        this.commentaire = null;
    }

    //Methodes
    public void printInfo(){
        //System.out.println("dateEtHeureActuelles : " + dateEtHeureActuelles);
        System.out.println("codeProfessionnel : " + codeProfessionnel);
        System.out.println("codeMembre : " + codeMembre);
        System.out.println("codeService : " + codeService);
        //System.out.println("commentaire : " + commentaire);
    }
}
