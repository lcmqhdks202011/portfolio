
public class Montant {

    private String prenom;
    private String nom;
    private String numero;
    private double montant;
    private int noCours;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Montant(String numero, double montant, int noCours, String prenom, String nom) {
        super();
        this.numero = numero;
        this.montant = montant;
        this.noCours = noCours;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getNoCours() {
        return noCours;
    }

    public void setNoCours(int noCours) {
        this.noCours = noCours;
    }



}
