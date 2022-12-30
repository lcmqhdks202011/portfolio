import java.util.List;

public class Validateur {

    public static boolean codeServiceValide(String codeSeance){
        try{
            Integer.parseInt(codeSeance);
        }
        catch (NumberFormatException e){
            return false;
        }
        return codeSeance.length() == 3;
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

    public static boolean validerMembre(String code){
    	
    	ControlleurMembre contM = ControlleurMembre.getInstance();

        //Verifie si le code existe chez les membres
        for(var membre : contM.membres){

            String codeAvalider = membre.getCodeMembre();
            if(codeAvalider.equals(code)){

                if(membre.getSuspendu()){
                    System.out.println("Invalide : Membre suspendu");
                    return false;
                }
                else{
                    System.out.println("Valide!");
                    return true;
                }
            }
        }

        ControlleurProfessionnel contP = ControlleurProfessionnel.getInstance();
        
        //Verifie si le code existe chez les professionnels
        for(var prof : contP.pros){

            String codeAvalider = prof.getCodeProfessionnel();
            if(codeAvalider.equals(code)){
                System.out.println("Valide!");
                return true;
            }
        }

        System.out.println("Invalide : Membre inexistant");
		return false;
    }

}
