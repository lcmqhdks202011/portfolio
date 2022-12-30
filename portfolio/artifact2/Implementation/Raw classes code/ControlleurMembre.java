import java.util.List;
import java.lang.Math;  

public class ControlleurMembre extends Enregistreur<Membre> {

    //Constructeur
    public ControlleurMembre(String nomPath){
        super.nomPath = nomPath;
    }

    //Methodes
    public void addMembre(Membre newMembre){
        System.out.println("Member added");
        newMembre.printInfo();
        System.out.println("----------------------------");
        writeObject(newMembre);
    }

    public void deleteMembre(String codeMembre){

        List<Membre> listMembres = readObjects();

        for( var membre : listMembres){
            if(membre.getCodeMembre().equals(codeMembre)){

                listMembres.remove(membre);
                writeList(listMembres);
                System.out.println("Member deleted");
                return;
            }
        }
        System.out.println("Member not found");
    }

    public void updateMembre(String codeMembre, Membre newMembre){
    
        if(!Validateur.codeMembreValide(codeMembre)){
            System.out.println("Invalid member code");
            return;
        }

        List<Membre> listMembres = readObjects();

        for( Membre membre : listMembres){
            if(membre.getCodeMembre().equals(codeMembre)){

                int index = listMembres.indexOf(membre);
                listMembres.set(index, newMembre);
                writeList(listMembres);
                System.out.println("Member updated");
                return;
            }
        }
        System.out.println("Member not found");
    }

}
