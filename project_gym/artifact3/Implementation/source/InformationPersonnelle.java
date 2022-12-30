import java.io.Serializable;

public class InformationPersonnelle implements Serializable {

    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String province;
    private String codePostal;
    private String telephone;

    //Getters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getAdresse() { return adresse; }
    public String getTelephone() { return telephone; }
    public String getVille() {
		return ville;
	}
	public String getProvince() {
		return province;
	}
	public String getCodePostal() {
		return codePostal;
	}
	//Constructeur
    public InformationPersonnelle(String nom, String prenom, String adresse, String ville, String province, String codePostal, String telephone){
        this.prenom = prenom;
        this.nom = nom;
        this.ville = ville;
        this.province = province;
        this.codePostal = codePostal;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    //Methodes
    public void printInfo(){
        System.out.println("nom : " + nom);
        System.out.println("prenom : " + prenom);
        System.out.println("adresse : " + adresse);
        System.out.println("telephone : " + telephone);
    }
}
