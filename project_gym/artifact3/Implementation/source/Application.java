import java.util.Scanner;

public class Application{

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String command = "";
        String numMembre = null;

        //Simulation du serveur
        Main.main(null);


        //Boucle interactive
        System.out.println("(GYM application) : Enter 'help' to show a list of every command");

        while(!command.equals("EXIT")){

            System.out.println("Enter a command :");
            command = scan.nextLine().toUpperCase();

            switch (command) {

                //General
                case "HELP" -> {
                    Commandes.printHelp();
                }
                case "LOGIN" -> {
                    numMembre = Commandes.login();
                }
                case "LOGOUT" -> {
                    Commandes.logout(numMembre);
                    numMembre = null;
                }
                case "CONSULTSERVICES" -> {
                    Commandes.showServices();
                }

                //Professional exclusive
                case "ADDSERVICE" -> {
                    Commandes.addService(numMembre);
                }
                case "DELETESERVICE" -> {
                    Commandes.removeService(numMembre);
                }
                case "CONSULTINSCRIPTIONS" -> {
                    Commandes.showInscriptions(numMembre);
                }
                case "CONFIRM" -> { 
                    Commandes.confirmMember(numMembre);
                }

                //Member exclusive
                case "SIGNUP" -> { 
                    Commandes.inscription(numMembre);
                }
                case "CONSULTINVOICE" -> { 
                    Commandes.consultInvoice(numMembre);
                }

            }

        }
        System.exit(1);

    }
}
