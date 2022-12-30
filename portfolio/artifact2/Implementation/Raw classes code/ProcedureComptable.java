import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProcedureComptable {
	private ArrayList<String[]> services = new ArrayList<String[]>();
    private ArrayList<Montant> montants = new ArrayList<Montant>();
    private String repertoireServices = readRepertoireServices();
	
	
	// lire le fichier rds
    String readRepertoireServices() {
		String res = "";
		try {

		File file = new File("rds.txt");
		FileReader filereader = new FileReader(file);
		int singleCh = 0;
		while((singleCh = filereader.read()) != -1) {
			res += (char)singleCh;
		}

		filereader.close();

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			System.out.println(e);
		}
		return res;
	}


	//tous les services -> array(chaque service)
	String[] splitListService(String s) {
		String[] res = s.split("\n");
		return res;
	}

	//un service -> array(prenom, nom, numero, frais)
	String[] splitService(String s) {
		String[] res = s.split(", ");
		return res;
	}


	// ArrayList<Services>.add(chaque array(prenom, nom, numero, frais))
	void makeListServices() {
		String str = repertoireServices;
		String[] strs2 = this.splitListService(str);

		for(int i=0; i< strs2.length; i++) {

			String[] strs3 = this.splitService(strs2[i]);
			String prenom = strs3[0];
            String nom = strs3[1];
            String frais = strs3[2];
            String codeNumero = strs3[3];

            String[] information = {nom, prenom, frais, codeNumero};
			this.services.add(information);
		}

	}

	// ArrayList<Services> -> ArrayList<Montants> : calcul de montant de chaque prof
	void makeListMontants() {

		for(String[] info : services) {

		    String prenom = info[0];
		    String nom = info[1];
            Float frais = Float.parseFloat(info[3]);
            String numero = info[2];
            int pos = posNumero(numero);

			if(pos != -1) {								//numero est deja dans ArrayList<Montants> -> le frais sera ajoute dans le montant
				Montant m = montants.get(pos);

				double newMon = m.getMontant() + frais;
				m.setMontant(newMon);
				m.setNoCours(m.getNoCours()+1);			// nombre de cours + 1

			} else {									// numero n'etait pas dans ArrayList<Montants>
				Montant m = new Montant(numero, 0, 0, prenom, nom); // initialiser un montant
				montants.add(m);
			}
		}

	}

	int posNumero(String numero) {						// retourne position de numero prof, -1 si on ne le trouve pas.
		for(int i=0; i< montants.size(); i++) {
			if(numero.equals(montants.get(i).getNumero())) {
				return i;
			}
		}
		return -1;
	}

	String createReport() {								// retourne report en String
		String res = "";
		res += "Semaine 1\n";
		int noC = 0;
		double total = 0;
		for(Montant m : montants) {
			res += "Numero : " + m.getNumero() + "\nMontants : " + m.getMontant() + "\nNombre de Cours : " + m.getNoCours() + "\n\n";
			noC += m.getNoCours();
			total += m.getMontant();
		}

		res += "Nombre de professionnel : " + montants.size() + "\n";
		res += "Total de frais : " + total + "\n";
		res += "Total de cours : " + noC + "\n";
		return res;
	}

	void writeReport() {								// write le report cree par createReport()

			try {
				File file = new File("report.txt");
				String rep = this.createReport();
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(rep);
				writer.close();
			} catch (IOException e){
				e.printStackTrace();
			}
    }


	void writeTEF() {

        for(Montant m : montants) {

            String res = "";

            res += "Nom : " + m.getNom() +
                    "\nPrenom : " + m.getPrenom() +
                    "\nCode du professionnel : " + m.getNumero() +
                    "\nMontant : " + m.getMontant();


            try {

                File file = new File("" + m.getNumero() + ".tef");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(res);
                writer.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

	public void main() {
		makeListServices();
		makeListMontants();
		writeReport();
        writeTEF();
	}

}
