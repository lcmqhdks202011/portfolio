import java.util.List;

public class ControlleurProfessionnel extends Enregistreur<Professionnel>{

    //Constructeur
    public ControlleurProfessionnel(String nomPath){
        super.nomPath = nomPath;
    }

    //Methodes
    public void addProfessionnel(Professionnel newPro){
        System.out.println("Professionnel added");
        newPro.printInfo();
        System.out.println("----------------------------");
        writeObject(newPro);
    }

    public void deleteProfessionnel(String codeProfessionnel){

        List<Professionnel> listProfs = readObjects();

        for( var professionnel : listProfs){
            if(professionnel.getCodeProfessionnel().equals(codeProfessionnel)){

                listProfs.remove(professionnel);
                writeList(listProfs);
                System.out.println("Professionnel deleted");
                return;
            }
        }
        System.out.println("Professionnel not found");
    }

    public void updateProfessionnel(String codeProfessionnel, Professionnel newProfessionnel){
    
        if(!Validateur.codeMembreValide(newProfessionnel.getCodeProfessionnel())){
            System.out.println("Invalid member code");
            return;
        }

        List<Professionnel> listProfs = readObjects();

        for( var pofessionnel : listProfs){
            if(pofessionnel.getCodeProfessionnel().equals(codeProfessionnel)){

                int index = listProfs.indexOf(pofessionnel);
                listProfs.set(index, newProfessionnel);
                writeList(listProfs);
                System.out.println("Professionnel updated");
                return;
            }
        }
        System.out.println("Member not found");
    }


}
