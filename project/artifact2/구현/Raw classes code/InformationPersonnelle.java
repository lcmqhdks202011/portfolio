import java.io.Serializable;

public class InformationPersonnelle implements Serializable {

    private final String nom;
    private final String prenom;
    private final String adresse;
    private final String telephone;

    //Getters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getAdresse() { return adresse; }
    public String getTelephone() { return telephone; }

    //Constructeur
    public InformationPersonnelle(){
        //Temporaire. Pour nous faciliter la vie
        this.prenom = "John";
        this.nom = "Wayne";
        this.adresse = "123 rue 456";
        this.telephone = "937-0707";
    }

    //Methodes
    public void printInfo(){
        System.out.println("nom : " + nom);
        System.out.println("prenom : " + prenom);
        System.out.println("adresse : " + adresse);
        System.out.println("telephone : " + telephone);
    }
}
