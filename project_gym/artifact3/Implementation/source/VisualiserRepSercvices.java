import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class VisualiserRepSercvices extends Main{
	
	protected static JPanel options = new JPanel();
	protected static JButton serviceAdd = new JButton("+ Add new");
	protected static JButton serviceModify = new JButton("Modify Service");
	protected static JToggleButton filterToday = new JToggleButton("Today");
	protected static JButton showInscriptions = new JButton("Show Inscriptions");
	protected static JButton subscribe = new JButton("Subscribe");
	protected static JButton confirmIns = new JButton("Confirm Presence");
	protected static JButton sessionDelete = new JButton("X Supprimer");
	
	protected static Session sessionSelected;
	protected static Service serviceSelected;
	protected static int selectedRow;	
	
	public VisualiserRepSercvices() {
		
		LocalDateTime now = LocalDateTime.now(); //heure locale
		String today = now.getDayOfWeek().name();//jour de la semaine
		
		services.setLayout(new BoxLayout(services, BoxLayout.PAGE_AXIS));
		
		//Initiation du panel ou l'on retrouve les boutons pour manipuler les services
		options.setLayout(new BoxLayout(options, BoxLayout.LINE_AXIS));
		options.setBorder(new EmptyBorder(20, 100, 10, 100));

		
		//On place tous les boutons et leurs espaces entre
		options.add(serviceAdd);
		options.add(Box.createRigidArea(new Dimension(5, 0)));
		options.add(serviceModify);
		options.add(Box.createRigidArea(new Dimension(30, 0)));
		options.add(filterToday);
		options.add(Box.createRigidArea(new Dimension(10, 0)));
		options.add(showInscriptions);
		options.add(Box.createRigidArea(new Dimension(30, 0)));
		options.add(Box.createHorizontalGlue());
		options.add(subscribe);
		subscribe.setForeground(Color.GREEN); 
		options.add(Box.createRigidArea(new Dimension(5, 0)));
		options.add(confirmIns);
		options.add(Box.createRigidArea(new Dimension(25, 0)));
		options.add(sessionDelete);
		
		services.add(options); //Les actions sur les boutons options du GUI sont � la fin de la m�thode
		
		//Cr�ation et formattage des �l�ments pour les panels de l'ajout d'un nouveau service et la modification d'un service existant
		JPanel dates = new JPanel();
		JTextField start = new JTextField("dd-MM-yyyy", 10);
		JTextField end = new JTextField("dd-MM-yyyy", 10);
			JPanel radioButtons = new JPanel();
			JRadioButton monday = new JRadioButton("MONDAY");
			JRadioButton tuesday = new JRadioButton("TUESDAY");
			JRadioButton wednesday = new JRadioButton("WEDNESDAY");
			JRadioButton thursday = new JRadioButton("THURSDAY");
			JRadioButton friday = new JRadioButton("FRIDAY");
			JRadioButton saturday = new JRadioButton("SATURDAY");
			JRadioButton sunday = new JRadioButton("SUNDAY");
			radioButtons.add(monday);
			radioButtons.add(tuesday);
			radioButtons.add(wednesday);
			radioButtons.add(thursday);
			radioButtons.add(friday);
			radioButtons.add(saturday);
			radioButtons.add(sunday);
		JTextField hour = new JTextField("HH:MM", 10);
		dates.add(radioButtons);
		dates.add(new JLabel("Start Date:"));
		dates.add(start);
		dates.add(new JLabel("End Date:"));
		dates.add(end);
		dates.add(new JLabel("Hour:"));
		dates.add(hour);
		
		JPanel containerProId = new JPanel();
		JTextField idPro = new JTextField(15);
		containerProId.add(new JLabel("ID Pro:"));
		containerProId.add(idPro);
		
		JPanel containerOther = new JPanel();
		JTextField name = new JTextField(20);
		JTextField capacity = new JTextField(5);
		JTextField price = new JTextField(5);
		JTextArea comments = new JTextArea(5, 20);
		containerOther.add(new JLabel("Name:"));
		containerOther.add(name);
		containerOther.add(new JLabel("Capacity (max 30):"));
		containerOther.add(capacity);
		containerOther.add(new JLabel("Price:"));
		containerOther.add(price);
		containerOther.add(new JLabel("Comments:"));
		containerOther.add(comments);
		
		
		//=========Affichage des inscrits========
		JButton ok = new JButton("OK");
		JTextArea text = new JTextArea();
		
		// Sets JTextArea font and color.
        Font font = new Font("Courrier New", Font.PLAIN, 16);
        text.setFont(font);
		
        text.setEditable(false); // Set textArea non-editable
        JScrollPane scroll = new JScrollPane(text);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		inscriptionList.add(text);
		inscriptionList.add(Box.createRigidArea(new Dimension(0, 20)));
		inscriptionList.add(ok);
		ok.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		inscriptionList.setLayout(new BoxLayout(inscriptionList, BoxLayout.PAGE_AXIS));
		inscriptionList.setBorder(new EmptyBorder(20, 100, 10, 100));
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				text.removeAll(); //reset pour prochains affichage
				contentLayout.show(contentPanel, "services");
				naviguation.setVisible(true);
			}
		});
		//=======================================
		
		
		
		//Boutons pour accepter et finaliser le changement ou la creation d'un service dans les champs inputs
		JButton okayAdd = new JButton("Add");
		JButton okayChange = new JButton("Modify");
		JButton cancel = new JButton("Cancel");
		
		//Actions sur ces boutons
		okayAdd.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//r�cupere les inputs
				String startDateEntered = start.getText();
				String endDateEntered = end.getText();
				Date date1 = null;
				Date date2 = null;
				//catch si le format input est correct
				try {
					date1 =new SimpleDateFormat("dd-MM-yyyy").parse(startDateEntered);
					date2 =new SimpleDateFormat("dd-MM-yyyy").parse(endDateEntered);
				} catch (ParseException e2) {
					JOptionPane.showMessageDialog(frameMain, "Invalid date format, please retry (dd-MM-yyyy).", "Error", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
					return;
				}  
				String hourEntered = hour.getText();
				String nameEntered = name.getText();
				String capacityEntered = capacity.getText();
				//rabaisse automatiquement la capacite si elle excede le max de 30
				if(Integer.parseInt(capacityEntered) > 30) {
					capacityEntered = "30";
				}
				String idProEntered = idPro.getText();
				String priceEntered = price.getText();
				String commentsEntered = comments.getText();
				
				//On cr�er un nouveau service
				Service s = new Service(idProEntered, nameEntered, date1, date2, commentsEntered, Integer.parseInt(capacityEntered), Float.parseFloat(priceEntered));
				
				
				//Complete le service avec les sessions selected
				ArrayList<Day> temp = new ArrayList<Day>();
				
				if(monday.isSelected()) {
					temp.add(Day.MONDAY);
				}
				if(tuesday.isSelected()) {
					temp.add(Day.TUESDAY);
				}
				if(wednesday.isSelected()) {
					temp.add(Day.WEDNESDAY);
				}
				if(thursday.isSelected()) {
					temp.add(Day.THURSDAY);
				}
				if(friday.isSelected()) {
					temp.add(Day.FRIDAY);
				}
				if(saturday.isSelected()) {
					temp.add(Day.SATURDAY);
				}
				if(sunday.isSelected()) {
					temp.add(Day.SUNDAY);
				}
				
				//ajout d'une session unique pour chaque jour de la semaine selectionn�
				for(Day d : temp) {
					s.sessions.add(new Session(hourEntered, d, s)) ;
				}
				
				//ajoute le service a notre liste generale
				contS.addService(s);
				
				//et on l'affiche dans notre tableau ses sessions
				for(Session sess : s.sessions) {
					sessionsInTable.add(sess);
					model.addRow(new Object[]{s.getName(), sess.getSchedule(), sess.getDay(), s.getCapacity(), s.getPro(), sess.getCodeSession(), s.getFees(), s.getDescription()});
				}
				
				
				//clear les boutons car on utilise le panel addNew dans un autre gui
				addNew.removeAll();
				
				//enregistrement des donn�es
				try {
					Enregistreur.getInstance();
					Enregistreur.saveAll();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				//reset de l'index de la rangee selectionee
				selectedRow = -1; //reset selection
				
				//retour
				contentLayout.show(contentPanel, "services");
				naviguation.setVisible(true);
			}
			
			
		});
		
		okayChange.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//r�pere le service s�lectionn�
				sessionSelected = getSelectedSession();
				serviceSelected = sessionSelected.getService();

				//on enleve les sessions du tableau car on va les changer
				for(Session s : serviceSelected.sessions) {
					
					String code = s.getCodeSession().substring(0, 3);
					
					if(code.equals(serviceSelected.getUID())) {
						
						int index = sessionsInTable.indexOf(s);
						sessionsInTable.remove(index);
						model.removeRow(index);
					}
				}

				
				//idem que okayAdd action
				String startDateEntered = start.getText();
				String endDateEntered = end.getText();
				Date date1 = null;
				Date date2 = null;
				try {
					date1 =new SimpleDateFormat("dd-MM-yyyy").parse(startDateEntered);
					date2 =new SimpleDateFormat("dd-MM-yyyy").parse(endDateEntered);
				} catch (ParseException e2) {
					JOptionPane.showMessageDialog(frameMain, "Invalid date format, please retry (dd-MM-yyyy).", "Error", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
					return;
				}  
				String hourEntered = hour.getText();
				String nameEntered = name.getText();
				String capacityEntered = capacity.getText();
				String commentsEntered = comments.getText();
				
				contS.updateService(serviceSelected, nameEntered, date1, date2, commentsEntered, Integer.parseInt(capacityEntered));
				
				serviceSelected.sessions.clear();
				ArrayList<Day> temp = new ArrayList<Day>();
				
				if(monday.isSelected()) {
					temp.add(Day.MONDAY);
				}
				if(tuesday.isSelected()) {
					temp.add(Day.TUESDAY);
				}
				if(wednesday.isSelected()) {
					temp.add(Day.WEDNESDAY);
				}
				if(thursday.isSelected()) {
					temp.add(Day.THURSDAY);
				}
				if(friday.isSelected()) {
					temp.add(Day.FRIDAY);
				}
				if(saturday.isSelected()) {
					temp.add(Day.SATURDAY);
				}
				if(sunday.isSelected()) {
					temp.add(Day.SUNDAY);
				}
				
				for(Day d : temp) {
					serviceSelected.sessions.add(new Session(hourEntered, d, serviceSelected)) ;
				}
				
				//ajoute la version modifi�
				for(Session sess : serviceSelected.sessions) {
					sessionsInTable.add(sess);
					model.addRow(new Object[]{serviceSelected.getName(), sess.getSchedule(), sess.getDay(), serviceSelected.getCapacity(), serviceSelected.getPro(), sess.getCodeSession(), serviceSelected.getDescription()});
				}
				
				//clear les boutons car on utilise le panel dans un autre GUI
				change.removeAll();
				
				try {
					Enregistreur.getInstance();
					Enregistreur.saveAll();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				selectedRow = -1; //reset selection
				
				contentLayout.show(contentPanel, "services");
				naviguation.setVisible(true);
				
			}
			
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//retourne au gui
				contentLayout.show(contentPanel, "services");
				naviguation.setVisible(true);
				addNew.removeAll();
				change.removeAll();
			}
			
		});
		
		
		//Actions sur les boutons du panel options du gui de Services
		serviceAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//formation du pannel addNew
				contentLayout.show(contentPanel, "addNew");
				naviguation.setVisible(false);
				addNew.add(radioButtons);
				addNew.add(dates);
				addNew.add(containerProId);
				addNew.add(containerOther);
				addNew.add(okayAdd);
				addNew.add(cancel);
			}
		});
		serviceModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				selectedRow = table.getSelectedRow();
				
				
				//Si service n'est pas s�l�ctionner dans le tableau, on demande � l'utilisateur de le faire avant de cliquer sur "modifier"
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null,"Please select a service.");
				} else {
					int msg = JOptionPane.showConfirmDialog(frameMain, "If you proceed with any modifications to your service, the incriptions currently in your sessions will be lost!", "Warning", JOptionPane.OK_CANCEL_OPTION);
					if (msg == JOptionPane.OK_OPTION) { //si l'utilisateur clique "okay" pour continuer, sinon rien ne se passe 
					
					
						sessionSelected = getSelectedSession();
						serviceSelected = sessionSelected.getService();
						
						System.out.println(serviceSelected.getUID());
						contentLayout.show(contentPanel, "change");
						naviguation.setVisible(false);
						
						//formation du pannel change
						change.add(radioButtons);
						change.add(dates);
						change.add(containerOther);
						change.add(okayChange);
						change.add(cancel);
						
						//Rempli les inputs pour etre ceux de la personne qu'on modifie (simplification de l'utilisation de l'interface)
						//start.setText(serviceSelected.getStartDate().);
						//end.setText(serviceSelected.getEndDate().toString());
						hour.setText(sessionSelected.getSchedule());
						name.setText(serviceSelected.getName());
						capacity.setText("" + serviceSelected.getCapacity());
						comments.setText(serviceSelected.getDescription());
						
					}
				
				}
			}
		});
		//Permet � l'utilisateur d'afficher toutes les s�ances d'aujourd'hui
		filterToday.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (filterToday.isSelected()) {
					rowSorter.setRowFilter(RowFilter.regexFilter(today)); //today est declare au debut
					filterToday.setText("All"); 
					filtered = true;
					
				} else {
					rowSorter.setRowFilter(null);
		        	filterToday.setText("Today"); 
		        	filtered = false;
		        	
				}
			
			}
		});
		
		showInscriptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				selectedRow = table.getSelectedRow();
				
				if(selectedRow == -1) {
					
					JOptionPane.showMessageDialog(null,"Please select a session.");
					
				} else {
					
					sessionSelected = getSelectedSession();
					
					contentLayout.show(contentPanel, "inscriptionList");
					naviguation.setVisible(false);
					
					//append de toutes les donnees necessaire
					text.setText("Inscriptions for session " + sessionSelected.getCodeSession() + " for service: " + sessionSelected.getService().getName() + " (" + sessionSelected.getService().getUID()+")\n\n\n");
					for(Inscription i : sessionSelected.ins) {
						String name = i.getMembre().getInfoPerso().getNom();
						String firstName = i.getMembre().getInfoPerso().getPrenom();
						String ID = i.getMembre().getCodeMembre();
						String creationDate = i.getCreationDate();
						String comment = i.getComments();
						text.append("Member " + name + " " + firstName + " (" + ID + ")\n" + "Inscription made the " + creationDate + ".\n" + "Comment: " + comment +"\n\n\n");
					}
					
				}
			}
			
		});
		
		subscribe.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				selectedRow = table.getSelectedRow();
				
				if(selectedRow == -1) {
					
					JOptionPane.showMessageDialog(null,"Please select a session.");
				
				//Il faut que la session selectionne soit d'aujourd'hui, si elle ne l'est pas on refuse et montre au user les seance d'aujourd'hui
				} else if(filtered == false && !sessionsInTable.get(selectedRow).getDay().equals(today)) {
					
					filterToday.doClick(); //action de filtrage
					filtered = true;
					JOptionPane.showMessageDialog(null,"Please select a session occuring today.");
					
				} else {
					
					sessionSelected = getSelectedSession();

					String numClient = JOptionPane.showInputDialog(frameMain, "Please input member ID", JOptionPane.OK_CANCEL_OPTION);
					

					if(numClient != null) {//Si on cancel
						if(!Validateur.codeMembreValide(numClient)) {
							JOptionPane.showMessageDialog(frameMain, "Member not found or invalid code.", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							
							for(Inscription i : sessionSelected.ins) {
								if(i.getMembre().getCodeMembre().equals(numClient)) {
									JOptionPane.showConfirmDialog(null, "Member is already subscribed to this session!","Already done!",JOptionPane.OK_OPTION);
									selectedRow = -1;
									return;
								}
							}
							
							Membre m = contM.getMembreByNumber(numClient);
								
							if(sessionSelected.isFull()) {
								JOptionPane.showMessageDialog(frameMain, "Sorry, this session is full", "Session full", JOptionPane.ERROR_MESSAGE);	
							} else {
								System.out.println();
								contI.addInscription(m, sessionSelected);
								JOptionPane.showMessageDialog(frameMain, "Succes!");
								table.setValueAt(sessionSelected.getCapacity(), selectedRow, 3);
							}
								
							
							
						}
					}
					
				}
				
				try {
					Enregistreur.getInstance();
					Enregistreur.saveAll();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				selectedRow = -1;
				
			}
			
		});
		confirmIns.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedRow = table.getSelectedRow();
				
				if(selectedRow == -1) {
					
					JOptionPane.showMessageDialog(null,"Please select a session.");
					
				} else {
					
					sessionSelected = getSelectedSession();

					String numClient = JOptionPane.showInputDialog(frameMain, "Please scan member QR code.", JOptionPane.OK_CANCEL_OPTION);
					
					if(numClient != null) {//Si on cancel
						if(!Validateur.codeMembreValide(numClient)) {
							JOptionPane.showMessageDialog(frameMain, "Member not found or invalid code.", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
		
							for(Inscription i : sessionSelected.ins) {
								
								if(contM.getMembreByNumber(numClient).equals(i.getMembre())) {
									
									i.setConfirmed(true);
									JOptionPane.showMessageDialog(frameMain, "Succes!");
									break;
									
								}
							}
							
							//debug
							for(Membre m : contM.membres)
							{
								System.out.println("Inscription for member:" + m.getCodeMembre());
								for(Inscription i : m.inscriptions) {
									System.out.println(i);
									System.out.println(i.isConfirmed());
								}
							}
							
						}
					}
					
				}
				try {
					Enregistreur.getInstance();
					Enregistreur.saveAll();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				selectedRow = -1;
				
			}
				
			
		});
		sessionDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				selectedRow = table.getSelectedRow();
				
				sessionSelected = getSelectedSession();
				serviceSelected = sessionSelected.getService();
				
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null,"Please select a service.");
				} else {
					int msg = JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete this session?","Warning!",JOptionPane.YES_NO_OPTION);
					if (msg == JOptionPane.YES_OPTION) {
						
						//debug
						System.out.println("Before delete, sessions in service: ");
						for(Session s : serviceSelected.sessions) {
							System.out.println(s.getCodeSession());
						}
						//=====
						
						//cherche session dans service pour supprimer
						for(Session serv : serviceSelected.sessions) {
							if(serv.getCodeSession().equals(sessionSelected.getCodeSession())) {
								serviceSelected.sessions.remove(serv);
								break;
							}
						}
						sessionsInTable.remove(sessionSelected);
						model.removeRow(selectedRow);
						
						
						//debug
						System.out.println("After delete, sessions in service: ");
						for(Session s : serviceSelected.sessions) {
							System.out.println(s.getCodeSession());
						}
						//=====
						
						//si servica n'a plus de session, on supprime le service
						if(serviceSelected.sessions.isEmpty()) {
							contS.deleteService(serviceSelected);;
						}
						
						
						//debug
						System.out.println("Services: ");
						for(Service s : contS.services) {
							System.out.println(s.getUID());
						}
						System.out.println("-------------");
						//=====
						
					}
				}
				try {
					Enregistreur.getInstance();
					Enregistreur.saveAll();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				selectedRow = -1;
			}
		});
	}
	
	//methode qui permet de cr�er la liste des sessions dans le JTable filtre
	private void tableListFiltered() {
		sessionsInFilteredTable.clear();
		
		ArrayList<String> sessionsID = new ArrayList<String>();
		
		int rows = table.getRowCount();
		System.out.println(rows);
		for(int i = 0; rows > i; i++) {
			sessionsID.add((String) table.getValueAt(i, 5));
		}
		
		for(var s : sessionsID) {
			sessionsInFilteredTable.add(contS.getSessionByNumber(s));
		}
		
	}
	
	//l'index du JTable sera completement different lorqu'on le filtre.
	//on appel donc tableListFiltered() pour faire un nouvelle liste de session
	//dans le JTable pour quand on a un filtre
	private Session getSelectedSession() {
		if(filtered) {
			tableListFiltered();
			return sessionsInFilteredTable.get(selectedRow);
		} else {
			return sessionsInTable.get(selectedRow);
		}
	}
	
}
