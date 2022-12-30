import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Main{

	protected static ControlleurProfessionnel contP= ControlleurProfessionnel.getInstance();
	protected static ControlleurMembre contM= ControlleurMembre.getInstance();
	protected static ControlleurServices contS= ControlleurServices.getInstance();
	protected static ControlleurInscriptions contI= ControlleurInscriptions.getInstance();
	
	protected static JFrame frameMain = new JFrame("#GYM");
	
	//CardLayout et ses panels
	protected static CardLayout contentLayout = new CardLayout(); //Creation d'une variable pour le type de layout
	protected static JPanel contentPanel = new JPanel(contentLayout); //Panel qui contient tous les autres panels et dont on applique le CardLayout
	protected static JPanel mainMenu = new JPanel();
	protected static JPanel naviguation = new JPanel();
	protected static JPanel membres = new JPanel();
	protected static JPanel services = new JPanel();
	protected static JPanel addNew = new JPanel();
	protected static JPanel change = new JPanel();
	protected static JPanel inscriptionList = new JPanel();
		
	//Naviguation bar stuff
	protected static JButton search = new JButton("Search <");
	protected static Boolean searchState = false;
	protected static JPanel searchPanel = new JPanel(new BorderLayout());
	protected static JTextField searchInput = new JTextField(20);
	protected static Boolean naviguationState = false;
	
	//Gui2 table stuff
	protected static String[] columnNames = {"Name",
            "Hour",
            "Day",
            "Places left",
            "Professional",
            "Session",
            "Price",
            "Description"};
	protected static DefaultTableModel model = new DefaultTableModel(0, columnNames.length){

	    @Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};
	protected static JTable table = new JTable(model);
	protected static JScrollPane scrollPane = new JScrollPane(table);
	protected static TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
	protected static ArrayList<Session> sessionsInTable = new ArrayList<Session>();
	protected static ArrayList<Session> sessionsInFilteredTable = new ArrayList<Session>();
	protected static boolean filtered;


	public static void main(String[] args) {
		
		contentPanel.add(mainMenu, "mainMenu"); //Ajoute "mainMenu" comme une "card"
		contentPanel.add(membres, "membres"); //Ajoute "membres" comme une "card"
		contentPanel.add(services, "services"); //Ajoute "services" comme une "card"
		contentPanel.add(addNew, "addNew");
		contentPanel.add(change, "change");
		contentPanel.add(inscriptionList, "inscriptionList");
		
		contentLayout.show(contentPanel, "mainMenu"); //Indique quelle page � afficher en premier
		
		try {
			Enregistreur.loadAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//====Verify list data=====
		System.out.println("Services registered: ");
        for (Service serv : contS.services) {
            System.out.println(serv);
        }
        System.out.println("-----------");
        System.out.println("Pros registered: ");
        for (Professionnel pro : contP.pros) {
            System.out.println(pro.getCodeProfessionnel());
            System.out.println("His services provided: ");
            for(var serv : pro.services) {
            	System.out.println(serv);
            }
        }
        System.out.println("-----------");
        System.out.println("Membres registered: ");
        for (Membre m : contM.membres) {
            System.out.println(m.getCodeMembre());
        }
        System.out.println("-----------");
        //=========================
        
        //Initialisation du menu d'acceuil
		new MainMenu();
		
		//Initialisation de la barre de naviguation
		Nav(); 
		
		//Initialisation du repertoire de service
		new VisualiserRepSercvices();
		services.add(scrollPane);
		model.setColumnIdentifiers(columnNames);
		try {
			Enregistreur.loadServicesTable();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Initialisation du GUI pour la gestion des client
		new VisualiserMembres();

		/*
		 * Pour que ProcedureComptable s'execute tous les vendredis � 
		 * minuit. Mis en commentaire car lors de l'execution du code ca
		 * le lance. Donc pas ideal.
		 * 
		 * 
		Timer timer = new Timer();
	    Calendar date = Calendar.getInstance();
	    date.set(
	      Calendar.DAY_OF_WEEK,
	      Calendar.FRIDAY
	    );
	    date.set(Calendar.HOUR, 23);
	    date.set(Calendar.MINUTE, 59);
	    date.set(Calendar.SECOND, 59);
	    date.set(Calendar.MILLISECOND, 99);
	    // Schedule to run every friday at midnight
	    timer.schedule(
	      new ProcedureComptable(),
	      date.getTime(),
	      1000 * 60 * 60 * 24 * 7
	    );*/
		
		
		
		frameMain.add(contentPanel); //Ajoute � notre frame le container des autres panels
		frameMain.setPreferredSize(new Dimension(1000,500));
		frameMain.pack();
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.setVisible(true);
		
		
	}
	
	
	public static void Nav() {
				
		//Creation de la barre de naviguation
		naviguation.setLayout(new BoxLayout(naviguation, BoxLayout.LINE_AXIS));
		naviguation.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JButton backHome = new JButton("Home");
		naviguation.add(backHome);
		naviguation.add(Box.createRigidArea(new Dimension(5, 0)));
		naviguation.add(Box.createRigidArea(new Dimension(500, 0)));
		naviguation.add(Box.createHorizontalGlue());
		naviguation.add(search);
		searchPanel.setVisible(false);
		naviguation.add(searchPanel);
		
		backHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Enregistreur.saveMembers();
					Enregistreur.savePros();;
					Enregistreur.saveServices();;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//TODO enregistrer
				
				contentLayout.first(contentPanel);
				
				//enleve notre naviguation bar
				if(naviguationState == true) {
					frameMain.remove(naviguation);
					naviguationState = false;
					search.setVisible(true);
				}
				
				table.getSelectionModel().clearSelection();

			}
		});
		
		//Creation du search text input
		table.setRowSorter(rowSorter);
		searchPanel.add(searchInput, BorderLayout.CENTER);
		
		searchInput.getDocument().addDocumentListener(new DocumentListener(){
		
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        String text = searchInput.getText();
		
		        if (text.trim().length() == 0) {
		        	filtered = false;
		            rowSorter.setRowFilter(null);
		        } else {
		        	filtered = true;
		            rowSorter.setRowFilter(RowFilter.regexFilter(text));
		        }
		    }
		
		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        String text = searchInput.getText();
		
		        if (text.trim().length() == 0) {
		        	filtered = false;
		            rowSorter.setRowFilter(null);
		        } else {
		        	filtered = true;
		            rowSorter.setRowFilter(RowFilter.regexFilter(text));
		        }
		    }
		
		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        throw new UnsupportedOperationException("Not supported yet.");
		    }
		
		});
				
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Toggle de montrer ou pas le text input du search
				if(searchState == false) {
					searchPanel.setVisible(true);
					search.setText("Search >");
					searchState = true;
				} else if(searchState == true){
					searchPanel.setVisible(false);
					search.setText("Search <");
					searchInput.setText(null);
					searchState = false;
				}
			}
		});
		//////////////////////////////////////
	}
	


}


//import java.awt.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//
//import javax.swing.*;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//
//public class Main {
//
//    ControlleurProfessionnel contP=new ControlleurProfessionnel();
//    ControlleurMembre contM=new ControlleurMembre();
//    ControlleurServices contS=new ControlleurServices();
//    ControlleurInscriptions contI=new ControlleurInscriptions();
//
//    private JFrame confirmWindow;
//    private JPanel controlPanelConfirm;
//    protected JFrame mainWindow;
//    private JFrame memberSelection;
//    private JFrame serviceSelection;
//    private JFrame inscriptionWindow;
//    private JPanel controlPanelInscription;
//    private JPanel controlPanelService;
//    private JLabel headerLabel;
//    private JLabel statusLabel;
//    private JPanel controlPanelMembre;
//    private JPanel contentPaneMain;
//    private JTextField validateText=new JTextField();
//    
//    
//	protected static JPanel gui2 = new JPanel();
//	protected static JPanel addNew = new JPanel();
//	protected static JPanel change = new JPanel();
//
//
//    public Main(){
//        prepareGUI();
//        
//    }
//    public static void main(String[] args){
//        Main swingControlDemo = new Main();
//        swingControlDemo.showEventDemo();
//    }
//    private void prepareGUI(){
//
//        int width = 500;
//        //Set up pour le main
//        mainWindow = new JFrame("Main");
//        mainWindow.setBounds(100,100,width, 360);
//        //mainWindow.setSize(400,400);
//        contentPaneMain = new JPanel();
//        contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
//        contentPaneMain.setLayout(null);
//        mainWindow.setContentPane(contentPaneMain);
//
//        headerLabel = new JLabel("");
//        headerLabel.setBounds((width-100)/2, 20, 200, 20);
//        headerLabel.setText("Choose operation");
//        contentPaneMain.add(headerLabel);
//
//        JLabel memberDesc = new JLabel("");
//        memberDesc.setText("Creation/Update/Deletion of accounts");
//        memberDesc.setBounds(30+140+10, 60, 400, 20);
//        contentPaneMain.add(memberDesc);
//
//        JLabel serviceDesc = new JLabel("");
//        serviceDesc.setText("Creation/Update/Deletion of services");
//        serviceDesc.setBounds(30+140+10, 100, 400, 20);
//        contentPaneMain.add(serviceDesc);
//
//        JLabel inscriptionDesc = new JLabel("");
//        inscriptionDesc.setText("Course registration");
//        inscriptionDesc.setBounds(30+140+10, 140, 400, 20);
//        contentPaneMain.add(inscriptionDesc);
//
//        JLabel attendanceDesc = new JLabel("");
//        attendanceDesc.setText("Confirmation of attendance");
//        attendanceDesc.setBounds(30+140+10, 180, 400, 20);
//        contentPaneMain.add(attendanceDesc);
//
//        JLabel rapportDesc = new JLabel("");
//        rapportDesc.setText("Creation of report");
//        rapportDesc.setBounds(30+140+10, 220, 400, 20);
//        contentPaneMain.add(rapportDesc);
//
//        JLabel validerDesc = new JLabel("Validate Member");
//        validerDesc.setBounds(180,260,400,20);
//        contentPaneMain.add(validerDesc);
//
//        JButton membreButton = new JButton("Membre");
//        JButton serviceButton = new JButton("Repertoire Service");
//        JButton rapportButton=new JButton("Rapport");
//        JButton validateButton = new JButton("Valider");
//
//        membreButton.setActionCommand("Membre");
//        serviceButton.setActionCommand("Repertoire Service");
//        rapportButton.setActionCommand("Rapport");
//        validateButton.setActionCommand("Valider");
//
//        membreButton.addActionListener(new ButtonClickListener());
//        serviceButton.addActionListener(new ButtonClickListener());
//        rapportButton.addActionListener(new ButtonClickListener());
//        validateButton.addActionListener(new ButtonClickListener());
//
//        membreButton.setBounds(30,60,140,20);
//        serviceButton.setBounds(30,100,140,20);
//        rapportButton.setBounds(30,220,140,20);
//        validateButton.setBounds(30,260,140,20);
//        validateText.setBounds(30,280,140,20);
//        contentPaneMain.add(membreButton);
//        contentPaneMain.add(serviceButton);
//        contentPaneMain.add(rapportButton);//
//        contentPaneMain.add(validateButton);
//        contentPaneMain.add(validateText);
//
//
//
//        statusLabel = new JLabel("",JLabel.CENTER);
//        statusLabel.setSize(350,100);
//
//        mainWindow.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent windowEvent){
//                System.exit(0);
//            }
//        });
//
//        mainWindow.setVisible(true);
//
//        //Set up pour les membres
//        memberSelection = new JFrame("Membres");
//        memberSelection.setSize(400,400);
//        memberSelection.setLayout(new GridLayout(4,1));
//
//        memberSelection.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent windowEvent){
//                System.exit(0);
//            }
//        });
//
//        controlPanelMembre = new JPanel();
//        controlPanelMembre.setLayout((new FlowLayout()));
//
//        memberSelection.add(controlPanelMembre);
//
//        //Set up pour les services
//        serviceSelection = new JFrame("Services");
//        serviceSelection.setSize(700,400);
//        serviceSelection.setLayout(new GridLayout(4,1));
//
//        serviceSelection.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent windowEvent){
//                System.exit(0);
//            }
//        });
//
//        controlPanelService = new JPanel();
//        controlPanelService.setLayout(new FlowLayout());
//
//        serviceSelection.add(controlPanelService);
//
//        //Set up pour les inscriptions
//        inscriptionWindow = new JFrame("Inscriptions");
//        inscriptionWindow.setSize(600,400);
//        inscriptionWindow.setLayout(new GridLayout(4,1));
//
//        inscriptionWindow.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent windowEvent){
//                System.exit(0);
//            }
//        });
//
//        controlPanelInscription = new JPanel();
//        controlPanelInscription.setLayout(new FlowLayout());
//
//        inscriptionWindow.add(controlPanelInscription);
//
//        //Set up pour la confirmation
//        confirmWindow = new JFrame("Confirmation");
//        confirmWindow.setSize(600,400);
//        confirmWindow.setLayout(new GridLayout(4,1));
//
//        confirmWindow.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent windowEvent){
//                System.exit(0);
//            }
//        });
//
//        controlPanelConfirm = new JPanel();
//        controlPanelConfirm.setLayout(new FlowLayout());
//
//        confirmWindow.add(controlPanelConfirm);
//
//    }
//    private void showEventDemo(){
//
//        //Actions pour le main
//
//
//        mainWindow.setVisible(true);
//
//        //Actions pour membres
//        JRadioButton membre=new JRadioButton("Membre", true);
//        JRadioButton prof=new JRadioButton("Professionel");
//
//        ButtonGroup group=new ButtonGroup();
//        group.add(membre);
//        group.add(prof);
//
//        JButton add=new JButton("Add");
//        JTextField number=new JTextField("Number");
//        JButton update=new JButton("Update");
//        JButton delete=new JButton("Delete");
//        JTextField name =new JTextField("Name");
//        JTextField uName=new JTextField("Name");
//        JTextField uNumber=new JTextField("number");
//        JButton back=new JButton("Back");
//
//
//        add.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//
//                if(membre.isSelected()){
//                    Membre compte=new Membre(Generator.generateNumMembre());
//                    contM.addMembre(compte);
//                }
//                else{
//                    Professionnel compte=new Professionnel(Generator.generateNumMembre());
//                    contP.addProfessionnel(compte);
//                }
//            }
//        });
//
//        update.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                if(membre.isSelected()){
//                    Membre compte=new Membre(uName.getText());
//                    contM.updateMembre(uNumber.getText(), compte);
//                }
//                else{
//                    Professionnel compte=new Professionnel(uName.getText());
//                    contP.updateProfessionnel(uNumber.getText(), compte);
//                }
//            }
//        });
//
//        delete.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                if(membre.isSelected()){
//                    contM.deleteMembre(number.getText());
//                }
//                else{
//                    contP.deleteProfessionnel(number.getText());
//                }
//            }
//        });
//
//        back.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                memberSelection.setVisible(false);
//                mainWindow.setVisible(true);
//            }
//        });
//        controlPanelMembre.add(membre);
//        controlPanelMembre.add(prof);
//        controlPanelMembre.add(add);
//        controlPanelMembre.add(name);
//        controlPanelMembre.add(update);
//        controlPanelMembre.add(uName);
//        controlPanelMembre.add(uNumber);
//        controlPanelMembre.add(delete);
//        controlPanelMembre.add(number);
//        controlPanelMembre.add(back);
//
//        
//    }
//    private class ButtonClickListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            String command = e.getActionCommand();
//
//            if( command.equals( "Membre" ))  {
//                mainWindow.setVisible(false);
//                memberSelection.setVisible(true);
//            } else if( command.equals( "Repertoire Service" ) )  {
//                mainWindow.setVisible(false);
//                new VisualiserRepSercvices();
//                gui2.setVisible(true);
//            } else if(command.equals("Valider")){
//                Validateur.validerMembre(validateText.getText(), contM, contP);
//            }
//            else{
//                ProcedureComptable proc = new ProcedureComptable();
//                proc.main();
//            }
//        }
//    }
//
//
//}