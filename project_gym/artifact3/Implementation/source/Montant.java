public class Montant {

    private Professionnel pro;
    private double montant;
    private int noServices;
    private int noSessions;

    public Montant(Professionnel pro, double montant, int noServices,int noSessions) {
        this.pro = pro;
        this.montant = montant;
        this.noSessions = noSessions;
        this.noServices = noServices;
    }

    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getNoServices() {
        return noServices;
    }

    public void setNoServices(int noServices) {
        this.noServices = noServices;
    }
    
    public int getNoSessions() {
        return noSessions;
    }

    public void setNoSessions(int noSessions) {
        this.noSessions = noSessions;
    }

    public Professionnel getProfessionnel(){
        return this.pro;
    }


}
