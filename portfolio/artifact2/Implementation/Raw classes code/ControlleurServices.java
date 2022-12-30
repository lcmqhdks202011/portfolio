//But: Traiter des objets de type Service dans un fichier
import java.util.List;

public class ControlleurServices extends Enregistreur<Service>{

    //Constructeur
    public ControlleurServices(String nomPath){
        super.nomPath = nomPath;
    }

    //M�thodes
    public void addService(Service newService){
    
        if(!Validateur.codeMembreValide(newService.getCodeProfessionnel())){
            System.out.println("Invalid prof number");
            return;
        }
    
        System.out.println("Service added");
        newService.printInfo();
        System.out.println("----------------------------");
        writeObject(newService);
    }


    public void deleteService(String codeService, String codeProfessionnel){
    
        if(!Validateur.codeSeanceValide(codeService)){
            System.out.println("Invalid service code");
            return;
        }

        List<Service> listServices = readObjects();

        for( var service : listServices){
            if(service.getCodeService().equals(codeService) && service.getCodeProfessionnel().equals(codeProfessionnel)){

                listServices.remove(service);
                writeList(listServices);
                System.out.println("Service deleted");
                return;
            }
        }
        System.out.println("Service not found");
    }

    public void updateService(String codeService, String codeProfessionnel, Service newService){

        List<Service> listServices = readObjects();

        for( var service : listServices){
            if(service.getCodeService().equals(codeService) && service.getCodeProfessionnel().equals(codeProfessionnel)){

                int index = listServices.indexOf(service);
                listServices.set(index, newService);
                writeList(listServices);
                System.out.println("Service updated");
                return;
            }
        }
        System.out.println("Service not found");
    }



}