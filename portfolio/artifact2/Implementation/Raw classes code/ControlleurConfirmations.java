public class ControlleurConfirmations extends Enregistreur<ConfirmationInscription>{

    //Constructeur
    public ControlleurConfirmations(String nomPath){
        super.nomPath = nomPath;
    }

    //Methodes
    public void addConfirmationInscription(ConfirmationInscription newConfirm){
        writeObject(newConfirm);
        System.out.println("New confirmation added");
    }

}
