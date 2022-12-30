import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;

public class MainMenu extends Main{

	public MainMenu() {
		
		JPanel buttonContainer = new JPanel();
		JButton membreButton = new JButton("Membre");
	    JButton serviceButton = new JButton("Repertoire Service");
	    JButton rapportButton=new JButton("Rapport");
	    JButton validateButton = new JButton("Valider");
	    
	    membreButton.setBounds(30,60,140,20);
        serviceButton.setBounds(30,100,140,20);
        rapportButton.setBounds(30,220,140,20);
        validateButton.setBounds(30,260,140,20);
        buttonContainer.add(membreButton);
        buttonContainer.add(serviceButton);
        buttonContainer.add(rapportButton);
        buttonContainer.add(validateButton);
        
        mainMenu.add(buttonContainer);
        
        
      //Action pour tous les boutons car on aura toujours besoin de faire ce qui suit
      		ActionListener buttonAction = new ActionListener()
              {
                  @Override
                  public void actionPerformed(ActionEvent e)
                  {
                  	//On ajoute la barre de naviguation lorsqu'on passe la page main menu
                  	if(naviguationState == false) {
      					frameMain.add(naviguation, BorderLayout.PAGE_START);
      					naviguationState = true;
      				}
                  	
                  	
                  }
              };
      		
      		//Actions pour les boutons sur MainMenu
            membreButton.addActionListener(buttonAction);
            membreButton.addActionListener(new ActionListener() {
      			@Override
      			public void actionPerformed(ActionEvent e) {
      				contentLayout.show(contentPanel, "membres");
      			}
      		});
      		
            serviceButton.addActionListener(buttonAction);
            serviceButton.addActionListener(new ActionListener() {
      			@Override
      			public void actionPerformed(ActionEvent e) {
      				contentLayout.show(contentPanel, "services");
      				
      			}
      		});
      		
            rapportButton.addActionListener(buttonAction);
            rapportButton.addActionListener(new ActionListener() {
      			@Override
      			public void actionPerformed(ActionEvent e) {
      				ProcedureComptable.getInstance();
      			}
      		});
            validateButton.addActionListener(buttonAction);
            validateButton.addActionListener(new ActionListener() {
      			@Override
      			public void actionPerformed(ActionEvent e) {
      				String numClient = JOptionPane.showInputDialog(frameMain, "Please input member ID", JOptionPane.OK_CANCEL_OPTION);
      				Membre membre = contM.getMembreByNumber(numClient);
      				
      				if(numClient != null) { //Si on cancel
						if(!Validateur.codeMembreValide(numClient)) {
							JOptionPane.showMessageDialog(frameMain, "Member not found or invalid code.", "Error", JOptionPane.ERROR_MESSAGE);
						} else if(membre.getSuspendu()){
							JOptionPane.showMessageDialog(frameMain, "Member is suspended. Pay up!", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(frameMain, "Valid!", "Yay!", JOptionPane.INFORMATION_MESSAGE);
						}
      				}
      			}
      		});
	}
	
}
