import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class ProcedureComptable extends TimerTask{

    private ControlleurServices contS = ControlleurServices.getInstance();
    private ControlleurProfessionnel contP = ControlleurProfessionnel.getInstance();
    private List<Service> servicesCetteSemaine = new ArrayList<Service>();
    private int thisWeek;
    private ArrayList<Montant> montants;

    // static variable single_instance of type ProcedureComptable
    private static ProcedureComptable single_instance = null;

    // static method to create instance of ProcedureComptable as a singleton class
    public static ProcedureComptable getInstance()
    {
        if (single_instance == null)
            single_instance = new ProcedureComptable();
            
            single_instance.run();

        return single_instance;
    }

    @Override
    public void run() {
        findThisWeek();
        trouverServicesCetteSemaine();
        makeListMontants();
        writeTEF();
        writeReport();
    }

    // Groupe 1 : Extraction des services offerts cette semaine.
    private void trouverServicesCetteSemaine() {
        for(Service s : contS.services){
            if(isCetteSemaine(s)){
                servicesCetteSemaine.add(s);
            }
        }
    }

    private Boolean isCetteSemaine (Service s)
    {
        if(thisWeek == findWeek(s))
            return true;

        else return false;
    }

    private int findWeek(Service s) {

            Date date = s.getEndDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int week = cal.get(Calendar.WEEK_OF_YEAR);

            return week;

    }

    private void findThisWeek(){

        Calendar cal = Calendar.getInstance();
        thisWeek = cal.get(Calendar.WEEK_OF_YEAR);
    }


    // Groupe 2 : Creation de liste de montant de chaque professionnel
    private void makeListMontants() {

        montants = new ArrayList<Montant>();

        for(Service s : contS.services) {

            String code = s.getPro();
            Professionnel pro = contP.getProfByNumber(code);

            if(pro == null) continue;
            Float frais = s.getFees();

            int numberOfInscriptions = 0;

            for(Session sess : s.sessions) {
                for(Inscription i : sess.ins) {
                    if(i.isConfirmed()) {
                        numberOfInscriptions += 1;
                    }
                }
            }

            int pos = position(pro);
            if(pos != -1) {
                Montant m = montants.get(pos);

                double newMon = m.getMontant() + numberOfInscriptions * frais;
                m.setMontant(newMon);
                m.setNoServices(m.getNoServices()+1);
                m.setNoSessions(m.getNoSessions()+numberOfInscriptions);

            } else {
                Montant m = new Montant(pro, numberOfInscriptions * frais, 1, numberOfInscriptions);
                montants.add(m);
            }
        }

    }

    private int position(Professionnel pro) {
        for(int i=0; i< montants.size(); i++) {
            if(pro.getCodeProfessionnel().equals(montants.get(i).getProfessionnel().getCodeProfessionnel())) {
                return i;
            }
        }
        return -1;
    }

    //Groupe 3 : Ecrire le fichier TEF
    private void writeTEF() {

        for(Montant m : montants) {

            String res = "";

            Professionnel p = m.getProfessionnel();
            InformationPersonnelle i = p.getInfoPerso();
            String nom = i.getNom();
            String prenom = i.getPrenom();
            String codeProfessionnel = p.getCodeProfessionnel();
            String montant = "" + m.getMontant();

            res += "Nom : " + nom +
                    "\nPrenom : " + prenom +
                    "\nCode du professionnel : " + codeProfessionnel +
                    "\nMontant : " + montant;


            try {

                File file = new File("tef_" + codeProfessionnel + ".tef");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(res);
                writer.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //Groupe 4 : Ecrire le rapport
    private void writeReport() {								// write le report cree par createReport()

        try {
            File file = new File("report.txt");
            String rep = this.createReport();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(rep);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    String createReport() {								// retourne report en String

        String res = "";
        int noC = 0;
        double total = 0;
        for(Montant m : montants) {

            Professionnel p = m.getProfessionnel();
            InformationPersonnelle i = p.getInfoPerso();
            String codeProfessionnel = p.getCodeProfessionnel();
            res += "Numero du professionnel : " + codeProfessionnel +
                    "\nMontant : " + m.getMontant() +
                    "\nNombre de Services : " + m.getNoServices() + "\n\n";
            noC += m.getNoServices();
            total += m.getMontant();
        }

        res += "Nombre de professionnel : " + montants.size() + "\n";
        res += "Total de frais : " + total + "\n";
        res += "Total de cours : " + noC + "\n";
        return res;
    }


}
