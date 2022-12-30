import java.util.List;

public class Validateur {

    public static boolean codeSeanceValide(String codeSeance){
        try{
            Integer.parseInt(codeSeance);
        }
        catch (NumberFormatException e){
            return false;
        }
        return codeSeance.length() == 7;
    }

    public static boolean codeMembreValide(String codeMembre){
        try{
            Integer.parseInt(codeMembre);
        }
        catch (NumberFormatException e){
            return false;
        }
        return (codeMembre.length() == 9);
    }

    public static void validerMembre(String code, ControlleurMembre controlleurMembre, ControlleurProfessionnel controlleurProfessionnel){

        //Verifie si le code existe chez les membres
        List<Membre> listMembres = controlleurMembre.readObjects();
        for(var membre : listMembres){

            String codeAvalider = membre.getCodeMembre();
            if(codeAvalider.equals(code)){

                if(membre.getSuspendu()){
                    System.out.println("Invalide : Membre suspendu");
                    return;
                }
                else{
                    System.out.println("Valide!");
                    return;
                }
            }
        }

        //Verifie si le code existe chez les professionnels
        List<Professionnel> listProfs = controlleurProfessionnel.readObjects();
        for(var prof : listProfs){

            String codeAvalider = prof.getCodeProfessionnel();
            if(codeAvalider.equals(code)){
                System.out.println("Valide!");
                return;
            }
        }

        System.out.println("Invalide : Membre inexistant");
    }

}
