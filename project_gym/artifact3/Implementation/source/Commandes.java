import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Commandes {

    public static void printHelp(){
        System.out.println("----------------------");
        System.out.println("Commands : ");
        System.out.println("-> exit             : Quit / Return");
        System.out.println("-> login            : Connection as a member or professional");
        System.out.println("-> logout           : Disconnection");
        System.out.println("-> consultServices  : Show the list of all services");
        System.out.println();
        System.out.println("Member exclusive :");
        System.out.println("-> signup           : Inscription to a service");
        System.out.println("-> consultInvoice   : Show the invoice (!)");
        System.out.println();
        System.out.println("Professional exclusive :");
        System.out.println("-> addService       : Add a new service");
        System.out.println("-> deleteService    : Delete a service");
        System.out.println("-> consultInscriptions : Show all the inscriptions to a service");
        System.out.println("-> confirm             : Confirm the inscription of a member (!)");
        System.out.println("----------------------");
    }

    public static String login(){

        Scanner scan = new Scanner(System.in);
        String number = "";

        System.out.println("----------------------");
        while(!number.equals("exit")){
            System.out.println("Enter your id : ");
            number = scan.nextLine();
            if(Validateur.validerMembre(number)){

                //Print info
                if(isProf(number)){
                    Main.contP.getProfByNumber(number).printInfo();
                }
                else{
                    Main.contM.getMembreByNumber(number).printInfo();
                }

                System.out.println("----------------------");
                return number;
            }
            else{
                System.out.println();
            }
        }

        System.out.println("Login cancelled");
        System.out.println("----------------------");
        return null;

    }

    public static void logout(String numMembre){
        if(numMembre != null){
            System.out.println("Member #" + numMembre + " disconnected");
            System.out.println("----------------------");
        }
        else {
            System.out.println("Disconnection failed, you are not logged in");
            System.out.println("----------------------");
        }
    }

    public static void showServices(){
        Main.contentLayout.show(Main.contentPanel, "services");
        //Main.currentPanel = "services";

        for(var service : Main.contS.services){
            service.printInfo();
            System.out.println("----------------------");
        }
    }

    public static void addService(String codeMembre){

        try {

            Scanner scan = new Scanner(System.in);

            if (!isProf(codeMembre)) {
                System.out.println("You must be a professional to use this command.");
                return;
            }
            //Service information
            System.out.println("Enter a service name :");
            String name = scan.nextLine();
            System.out.println("Enter a start date :");
            String startDate = scan.nextLine();
            System.out.println("Enter an end date :");
            String endDate = scan.nextLine();
            System.out.println("Enter an comment (Optional) :");
            String comment = scan.nextLine();
            System.out.println("Enter the capacity :");
            int capacity = scan.nextInt();
            System.out.println("Enter the cost :");
            Float fees = scan.nextFloat();

            Date startDate1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
            Date endDate1 = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);

            Service s = new Service(codeMembre, name, startDate1, endDate1, comment, capacity, fees);

            Main.contS.addService(s);
            System.out.println("Service added successfully");
            s.printInfo();
            System.out.println("----------------------");
        }
        catch (ParseException e){
            System.out.println("Parse Error");
        }

    }

    public static void removeService(String numMembre){

        Scanner scan = new Scanner(System.in);

        if(!isProf(numMembre)){
            System.out.println("You must be a professional to use this command.");
            return;
        }

        System.out.println("Enter the service id :");
        String id = scan.nextLine();
        Service service = Main.contS.getServiceByNumber(id);

        if(service == null){
            System.out.println("This service doesn't exist");
            return;
        }

        if(!service.getPro().equals(numMembre)){
            System.out.println("You must be the owner of that service!");
            return;
        }

        Main.contS.deleteService(service);
        System.out.println("Service removed successfully");
        service.printInfo();
        System.out.println("----------------------");
    }

    public static void inscription(String numMembre){
        if(isProf(numMembre)){
            System.out.println("You must be a member to use this command.");
            return;
        }
    }

    public static void consultInvoice(String numMembre){
        if(isProf(numMembre)){
            System.out.println("You must be a member to use this command.");
            return;
        }

        Membre membre = Main.contM.getMembreByNumber(numMembre);

        System.out.println("Invoice :");
        System.out.println(membre.getInfoPerso().getNom());
        System.out.println(membre.getInfoPerso().getPrenom());
        System.out.println(numMembre);
        System.out.println(membre.getInfoPerso().getAdresse());
        System.out.println("----------------------");
        for(var inscription : membre.inscriptions){
            System.out.println(inscription.getStartDate());
            System.out.println(inscription.getSession().getService().getPro());
            System.out.println(inscription.getSession().getService().getName());
            System.out.println("----------------------");
        }
    }

    public static void confirmMember(String numMembre){
        if(!isProf(numMembre)){
            System.out.println("You must be a professional to use this command.");
            return;
        }
    }

    public static void showInscriptions(String codeProf){

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the service id :");
        String codeService = scan.nextLine();

        Service service = Main.contS.getServiceByNumber(codeService);

        if(service == null){
            System.out.println("This service doesn't exist");
            return;
        }
        if(!service.getPro().equals(codeProf)){
            System.out.println("You must be the owner of this service");
            return;
        }

        System.out.println("Enter the session id :");
        String codeSession = scan.nextLine();

        Session session = service.getSessionByNumber(codeSession);

        if(session == null){
            System.out.println("This session doesn't exist");
            return;
        }

        for(var inscription : session.ins){
            inscription.printInfo();
            System.out.println("----------------------");
        }


    }

    private static boolean isProf(String number){

        Professionnel profConnnecte = Main.contP.getProfByNumber(number);

        return profConnnecte != null;
    }


}
