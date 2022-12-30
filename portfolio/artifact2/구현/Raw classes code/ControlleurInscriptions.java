//Ce controlleur prend deux path. Un pour l'emplacement du fichier des inscriptions
//et un pour le fichier des confirmations d'inscription

import java.util.List;

public class ControlleurInscriptions extends Enregistreur<Inscription>{

    //Attributs
    private final ControlleurConfirmations ctrlConfirmations;

    //Getters
    public ControlleurConfirmations getCtrlConfirmations() { return ctrlConfirmations; }

    //Constructeur
    public ControlleurInscriptions(String nomPath, String nomPathRepertoireConfirm){
        super.nomPath = nomPath;
        this.ctrlConfirmations = new ControlleurConfirmations(nomPathRepertoireConfirm);
    }

    //Methodes
    public void addInscription(Inscription newInscription){
        System.out.println("Inscription added");
        newInscription.printInfo();
        System.out.println("----------------------------");
        writeObject(newInscription);
    }

    public void deleteInscription(String codeProf, String codeMembre, String codeSeance){

        List<Inscription> listInscriptions = readObjects();

        for( var inscription : listInscriptions){

            String cp = inscription.getCodeProfessionnel();
            String cm = inscription.getCodeMembre();
            String cs = inscription.getCodeService();

            if(cp.equals(codeProf) && cm.equals(codeMembre) && cs.equals(codeSeance)){

                listInscriptions.remove(inscription);
                writeList(listInscriptions);
                System.out.println("Inscription deleted");
                return;
            }
        }
        System.out.println("Member not found");
    }

    public void confirmerInscription(String codeMembre, String codeSeance){

        List<Inscription> listInscriptions = readObjects();

        for(var inscription : listInscriptions){

            if(inscription.getCodeMembre().equals(codeMembre)){

                if(inscription.getCodeService().equals(codeSeance)){

                    System.out.println("Valide");

                    String cp = inscription.getCodeProfessionnel();
                    String cm = inscription.getCodeMembre();
                    String cs = inscription.getCodeService();
                    ConfirmationInscription enregistrement = new ConfirmationInscription(cp,cm,cs);

                    ctrlConfirmations.addConfirmationInscription(enregistrement);
                    deleteInscription(cp,cm,cs);

                    return;
                }
            }
        }
        System.out.println("Invalide");
    }

}
