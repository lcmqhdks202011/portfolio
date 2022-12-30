import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class VisualiserMembres extends Main{

	private String numClient = "";
	
	public VisualiserMembres() {
		
		membres.setSize(400,400);
		membres.setLayout(new GridLayout(4,1));
 

        JPanel controlPanelMembre = new JPanel();
        membres.setLayout((new FlowLayout()));

        membres.add(controlPanelMembre);
        
        
      //Actions pour membres
      JRadioButton membre=new JRadioButton("Membre", true);
      JRadioButton prof=new JRadioButton("Professionel");

      ButtonGroup group=new ButtonGroup();
      group.add(membre);
      group.add(prof);

      JButton add=new JButton("Add");
      JButton update=new JButton("Update");
      JButton delete=new JButton("Delete");

      controlPanelMembre.add(membre);
      controlPanelMembre.add(prof);
      controlPanelMembre.add(add);
      controlPanelMembre.add(update);
      controlPanelMembre.add(delete);
      
      JPanel controlPanelAdd = new JPanel();
      controlPanelAdd.setLayout(new BoxLayout(controlPanelAdd, BoxLayout.PAGE_AXIS));
      
      JTextField nom = new JTextField(10);
      JTextField prenom = new JTextField(10);
      JTextField adresse = new JTextField(20);
      JTextField ville = new JTextField(10);
      JTextField province = new JTextField(10);
      JTextField codePostal = new JTextField(5);
      JTextField telephone = new JTextField(10);
      
      controlPanelAdd.add(new JLabel("Name:"));
      controlPanelAdd.add(nom);
      controlPanelAdd.add(new JLabel("First name:"));
      controlPanelAdd.add(prenom);
      controlPanelAdd.add(new JLabel("Adress:"));
      controlPanelAdd.add(adresse);
      controlPanelAdd.add(new JLabel("City:"));
      controlPanelAdd.add(ville);
      controlPanelAdd.add(new JLabel("Province:"));
      controlPanelAdd.add(province);
      controlPanelAdd.add(new JLabel("Postal Code:"));
      controlPanelAdd.add(codePostal);
      controlPanelAdd.add(new JLabel("Phone:"));
      controlPanelAdd.add(telephone);
      
    //Boutons pour accepter et finaliser le changement ou la creation d'un membre dans les champs inputs
    JButton okayAdd = new JButton("Add");
    JButton okayChange = new JButton("Modify");
    JButton cancel = new JButton("Cancel");

      add.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){

        	contentLayout.show(contentPanel, "addNew");
			naviguation.setVisible(false);
			addNew.add(controlPanelAdd);
			addNew.add(okayAdd);
			addNew.add(cancel);

          }
      });
      
      okayAdd.addActionListener(new ActionListener() {
  		
			@Override
			public void actionPerformed(ActionEvent e) {

          	  InformationPersonnelle info = new InformationPersonnelle(nom.getText(), prenom.getText(), adresse.getText(), ville.getText(), province.getText(), codePostal.getText(), telephone.getText());

	              if(membre.isSelected()){
	                  Membre compte=new Membre(Generator.generateNumMembre(), info);
	                  contM.addMembre(compte);
	              }
	              else{
	                  Professionnel compte=new Professionnel(Generator.generateNumMembre(), info);
	                  contP.addProfessionnel(compte);
	              }
	              
	            //clear les boutons car on utilise le panel addNew dans un autre gui
				addNew.removeAll();
					
				try {
					Enregistreur.getInstance();
					Enregistreur.saveAll();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
								
				contentLayout.show(contentPanel, "membres");
				naviguation.setVisible(true);
			}
      });

      update.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  
        	  InformationPersonnelle info = null;
        	  
        	  numClient = JOptionPane.showInputDialog(frameMain, "Please input client ID", JOptionPane.OK_CANCEL_OPTION);

				if(numClient != null) {//Si on cancel
					if(!Validateur.codeMembreValide(numClient)) {
						JOptionPane.showMessageDialog(frameMain, "Client not found or invalid code.", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						
						Professionnel p = contP.getProfByNumber(numClient);
						Membre m = contM.getMembreByNumber(numClient);
						
						if(p == null) {
							info = m.getInfoPerso();
						} else {
							info = p.getInfoPerso();
						}
						
						nom.setText(info.getNom());
						prenom.setText(info.getPrenom());
						adresse.setText(info.getAdresse());
						ville.setText(info.getVille());
						province.setText(info.getProvince());
						codePostal.setText(info.getCodePostal());
						telephone.setText(info.getTelephone());
						
						contentLayout.show(contentPanel, "change");
						naviguation.setVisible(false);
						change.add(controlPanelAdd);
						change.add(okayChange);
						change.add(cancel);
						
						
					}
				}
          }
      });
      
      okayChange.addActionListener(new ActionListener() {
    		
			@Override
			public void actionPerformed(ActionEvent e) {

        	  InformationPersonnelle info = new InformationPersonnelle(nom.getText(), prenom.getText(), adresse.getText(), ville.getText(), province.getText(), codePostal.getText(), telephone.getText());

				
				if(contP.getProfByNumber(numClient) == null) {
					contM.updateMembre(numClient, info);
				} else {
					contP.updateProfessionnel(numClient, info);
				}
	              
	            //clear les boutons car on utilise le panel addNew dans un autre gui
				change.removeAll();
					
				try {
					Enregistreur.getInstance();
					Enregistreur.saveAll();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
								
				contentLayout.show(contentPanel, "membres");
				naviguation.setVisible(true);
			}
    });

      delete.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  
        	  numClient = JOptionPane.showInputDialog(frameMain, "Please input client ID", JOptionPane.OK_CANCEL_OPTION);


				if(numClient != null) {//Si on cancel
					
					if(!Validateur.codeMembreValide(numClient)) {
						
						JOptionPane.showMessageDialog(frameMain, "Client not found or invalid code.", "Error", JOptionPane.ERROR_MESSAGE);
						
					} else {
						
						if(contP.getProfByNumber(numClient) == null) {
							contM.deleteMembre(numClient);
						} else {
							contP.deleteProfessionnel(numClient);
						}
						
						JOptionPane.showMessageDialog(frameMain, "Success!", "Yay!", JOptionPane.INFORMATION_MESSAGE);
						
					}
				}
				
				try {
					Enregistreur.getInstance();
					Enregistreur.saveAll();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
								
				contentLayout.show(contentPanel, "membres");
				naviguation.setVisible(true);
			
        	  
          }
      });
      
      cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//retourne au gui
				contentLayout.show(contentPanel, "membres");
				naviguation.setVisible(true);
				addNew.removeAll();
				change.removeAll();
			}
			
		});
	    
	}
	
}
