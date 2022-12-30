//But: Permet aux classes enfantes d'enregistrer des objets de type T dans un fichier
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Enregistreur extends Main{ 
	
	// static variable single_instance of type Enregistreur
    private static Enregistreur single_instance = null;
	
	// static method to create instance of Enregistreur as a singleton class
    public static Enregistreur getInstance()
    {
        if (single_instance == null)
            single_instance = new Enregistreur();
  
        return single_instance;
    }

	public static void saveAll() throws IOException {
		savePros();
		saveMembers();
		saveServices();
	}
	
	public static void loadAll() throws IOException, ClassNotFoundException {
		loadServices();
		loadPros();
		loadMembers();
	}
	
	protected static void savePros() throws IOException {
        File f = new File("professionnelFile.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(contP.pros);
        oos.flush();
        oos.close();
    }
	
	protected static void saveMembers() throws IOException {
        File f = new File("membresFile.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(contM.membres);
        oos.flush();
        oos.close();
    }
	
	protected static void saveServices() throws IOException {
        File f = new File("servicesFile.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(contS.services);
        oos.flush();
        oos.close();
    }
	
	@SuppressWarnings({ "unchecked", "resource" })
	protected static void loadServices() throws IOException, ClassNotFoundException {
		
		FileInputStream fileIn = new FileInputStream("servicesFile.txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        
        contS.services = (ArrayList<Service>) in.readObject();
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	protected static void loadPros() throws IOException, ClassNotFoundException {
		
		FileInputStream fileIn = new FileInputStream("professionnelFile.txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        
        contP.pros = (ArrayList<Professionnel>) in.readObject();
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	protected static void loadMembers() throws IOException, ClassNotFoundException {
		
		FileInputStream fileIn = new FileInputStream("membresFile.txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        
        contM.membres = (ArrayList<Membre>) in.readObject();
	}
	
	//M�thode pour charger les comptes dans notre table
	protected static void loadServicesTable() throws Exception {
		
		ControlleurServices contS = ControlleurServices.getInstance();
		
		for(var serv : contS.services) {
			for(var sess : serv.sessions) {
				sessionsInTable.add(sess);
				model.addRow(new Object[]{serv.getName(), sess.getSchedule(), sess.getDay(), sess.getCapacity(), serv.getPro(), sess.getCodeSession(),serv.getFees() ,serv.getDescription()});
			}
		}
		
		System.out.println("Services loaded to table");
	
	} 
}
