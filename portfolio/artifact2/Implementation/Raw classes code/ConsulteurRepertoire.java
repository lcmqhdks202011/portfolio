//Exemple d'utilisation dans main: ConsulteurRepertoire.displayRepertoire(... instance de controlleur ...)

public class ConsulteurRepertoire {

    public static void displayRepertoire(ControlleurInscriptions controlleur){
        System.out.println("Liste des inscriptions :");
        for( var inscription : controlleur.readObjects()){
            inscription.printInfo();
            System.out.println("_____________________");
        }
    }

    public static void displayRepertoire(ControlleurConfirmations controlleur){
        System.out.println("Liste des confirmations :");
        for( var confirmation : controlleur.readObjects()){
            confirmation.printInfo();
            System.out.println("_____________________");
        }
    }

    public static void displayRepertoire(ControlleurServices controlleur){
        System.out.println("Liste des services :");
        for( var service : controlleur.readObjects()){
            service.printInfo();
            System.out.println("_____________________");
        }
    }

    public static void displayRepertoire(ControlleurMembre controlleur){
        System.out.println("Liste des membres :");
        for( var membre : controlleur.readObjects()){
            membre.printInfo();
            System.out.println("_____________________");
        }
    }

    public static void displayRepertoire(ControlleurProfessionnel controlleur){
        System.out.println("Liste des professionnels :");
        for( var prof : controlleur.readObjects()){
            prof.printInfo();
            System.out.println("_____________________");
        }
    }
}
