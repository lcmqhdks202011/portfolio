//But: Permet aux classes enfantes d'enregistrer des objets de type T dans un fichier
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Enregistreur<T> { //Exemple de type T: (Service, Membre, Professionnel, Inscription, etc...)

    protected String nomPath; //Chemin du fichier (ex: "fichierRepertoire.ser")

    //Prend un objet de type T en entrée et l'ajoute au fichier
    public void writeObject(T obj){
        List<T> listObj = readObjects();
        listObj.add(obj); //Adds to the current list
        try {
            FileOutputStream fileOut = new FileOutputStream(nomPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(listObj);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    //Prend une liste de type T en entrée et l'écrit dans le fichier
    public void writeList(List<T> list){
        try {
            FileOutputStream fileOut = new FileOutputStream(nomPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    //Lit le fichier et retourne une liste de type T
    @SuppressWarnings("unchecked")
    public List<T> readObjects(){
        List<T> listObj = new ArrayList<>();
        try
        {
            FileInputStream fileIn = new FileInputStream(nomPath);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            listObj = (List<T>) in.readObject();

            in.close();
            fileIn.close();
        }
        catch (IOException | ClassNotFoundException ioe)
        {
            return listObj;
        }
        return listObj;
    }

    }
