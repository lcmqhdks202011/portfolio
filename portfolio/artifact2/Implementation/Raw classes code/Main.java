import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Main {






    ControlleurProfessionnel contP=new ControlleurProfessionnel("professionelFile.txt");
    ControlleurMembre contM=new ControlleurMembre("membreFile.txt");
    ControlleurServices contS=new ControlleurServices("serviceFile.txt");
    ControlleurConfirmations contC=new ControlleurConfirmations("confirmationFile.txt");
    ControlleurInscriptions contI=new ControlleurInscriptions("inscriptionFile.txt", "confirmationFile.txt");

    private JFrame confirmWindow;
    private JPanel controlPanelConfirm;
    private JFrame mainWindow;
    private JFrame memberSelection;
    private JFrame serviceSelection;
    private JFrame inscriptionWindow;
    private JPanel controlPanelInscription;
    private JPanel controlPanelService;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanelMembre;
    private JPanel contentPaneMain;
    private JTextField validateText=new JTextField();


    public Main(){
        prepareGUI();
    }
    public static void main(String[] args){
        Main swingControlDemo = new Main();
        swingControlDemo.showEventDemo();
    }
    private void prepareGUI(){

        int width = 500;
        //Set up pour le main
        mainWindow = new JFrame("Main");
        mainWindow.setBounds(100,100,width, 360);
        //mainWindow.setSize(400,400);
        contentPaneMain = new JPanel();
        contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPaneMain.setLayout(null);
        mainWindow.setContentPane(contentPaneMain);

        headerLabel = new JLabel("");
        headerLabel.setBounds((width-100)/2, 20, 200, 20);
        headerLabel.setText("Choose operation");
        contentPaneMain.add(headerLabel);

        JLabel memberDesc = new JLabel("");
        memberDesc.setText("Creation/Update/Deletion of accounts");
        memberDesc.setBounds(30+140+10, 60, 400, 20);
        contentPaneMain.add(memberDesc);

        JLabel serviceDesc = new JLabel("");
        serviceDesc.setText("Creation/Update/Deletion of services");
        serviceDesc.setBounds(30+140+10, 100, 400, 20);
        contentPaneMain.add(serviceDesc);

        JLabel inscriptionDesc = new JLabel("");
        inscriptionDesc.setText("Course registration");
        inscriptionDesc.setBounds(30+140+10, 140, 400, 20);
        contentPaneMain.add(inscriptionDesc);

        JLabel attendanceDesc = new JLabel("");
        attendanceDesc.setText("Confirmation of attendance");
        attendanceDesc.setBounds(30+140+10, 180, 400, 20);
        contentPaneMain.add(attendanceDesc);

        JLabel rapportDesc = new JLabel("");
        rapportDesc.setText("Creation of report");
        rapportDesc.setBounds(30+140+10, 220, 400, 20);
        contentPaneMain.add(rapportDesc);

        JLabel validerDesc = new JLabel("Validate Member");
        validerDesc.setBounds(180,260,400,20);
        contentPaneMain.add(validerDesc);

        JButton membreButton = new JButton("Membre");
        JButton serviceButton = new JButton("Service");
        JButton inscriptionButton = new JButton("Inscription");
        JButton confirmationButton=new JButton("Confirmation");
        JButton rapportButton=new JButton("Rapport");
        JButton validateButton = new JButton("Valider");

        membreButton.setActionCommand("Membre");
        serviceButton.setActionCommand("Service");
        inscriptionButton.setActionCommand("Inscription");
        confirmationButton.setActionCommand("Confirmation");
        rapportButton.setActionCommand("Rapport");
        validateButton.setActionCommand("Valider");

        membreButton.addActionListener(new ButtonClickListener());
        serviceButton.addActionListener(new ButtonClickListener());
        inscriptionButton.addActionListener(new ButtonClickListener());
        confirmationButton.addActionListener(new ButtonClickListener());
        rapportButton.addActionListener(new ButtonClickListener());
        validateButton.addActionListener(new ButtonClickListener());

        membreButton.setBounds(30,60,140,20);
        serviceButton.setBounds(30,100,140,20);
        inscriptionButton.setBounds(30,140,140,20);
        confirmationButton.setBounds(30,180,140,20);
        rapportButton.setBounds(30,220,140,20);
        validateButton.setBounds(30,260,140,20);
        validateText.setBounds(30,280,140,20);
        contentPaneMain.add(membreButton);
        contentPaneMain.add(serviceButton);
        contentPaneMain.add(inscriptionButton);
        contentPaneMain.add(confirmationButton);
        contentPaneMain.add(rapportButton);//
        contentPaneMain.add(validateButton);
        contentPaneMain.add(validateText);



        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        mainWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        mainWindow.setVisible(true);

        //Set up pour les membres
        memberSelection = new JFrame("Membres");
        memberSelection.setSize(400,400);
        memberSelection.setLayout(new GridLayout(4,1));

        memberSelection.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        controlPanelMembre = new JPanel();
        controlPanelMembre.setLayout((new FlowLayout()));

        memberSelection.add(controlPanelMembre);

        //Set up pour les services
        serviceSelection = new JFrame("Services");
        serviceSelection.setSize(700,400);
        serviceSelection.setLayout(new GridLayout(4,1));

        serviceSelection.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        controlPanelService = new JPanel();
        controlPanelService.setLayout(new FlowLayout());

        serviceSelection.add(controlPanelService);

        //Set up pour les inscriptions
        inscriptionWindow = new JFrame("Inscriptions");
        inscriptionWindow.setSize(600,400);
        inscriptionWindow.setLayout(new GridLayout(4,1));

        inscriptionWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        controlPanelInscription = new JPanel();
        controlPanelInscription.setLayout(new FlowLayout());

        inscriptionWindow.add(controlPanelInscription);

        //Set up pour la confirmation
        confirmWindow = new JFrame("Confirmation");
        confirmWindow.setSize(600,400);
        confirmWindow.setLayout(new GridLayout(4,1));

        confirmWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        controlPanelConfirm = new JPanel();
        controlPanelConfirm.setLayout(new FlowLayout());

        confirmWindow.add(controlPanelConfirm);

    }
    private void showEventDemo(){

        //Actions pour le main


        mainWindow.setVisible(true);

        //Actions pour membres
        JRadioButton membre=new JRadioButton("Membre", true);
        JRadioButton prof=new JRadioButton("Professionel");

        ButtonGroup group=new ButtonGroup();
        group.add(membre);
        group.add(prof);

        JButton add=new JButton("Add");
        JTextField number=new JTextField("Number");
        JButton update=new JButton("Update");
        JButton delete=new JButton("Delete");
        JTextField name =new JTextField("Name");
        JTextField uName=new JTextField("Name");
        JTextField uNumber=new JTextField("number");
        JButton back=new JButton("Back");


        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                if(membre.isSelected()){
                    Membre compte=new Membre(Generator.generateNumMembre());
                    contM.addMembre(compte);
                }
                else{
                    Professionnel compte=new Professionnel(Generator.generateNumMembre());
                    contP.addProfessionnel(compte);
                }
            }
        });

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(membre.isSelected()){
                    Membre compte=new Membre(uName.getText());
                    contM.updateMembre(uNumber.getText(), compte);
                }
                else{
                    Professionnel compte=new Professionnel(uName.getText());
                    contP.updateProfessionnel(uNumber.getText(), compte);
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(membre.isSelected()){
                    contM.deleteMembre(number.getText());
                }
                else{
                    contP.deleteProfessionnel(number.getText());
                }
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                memberSelection.setVisible(false);
                mainWindow.setVisible(true);
            }
        });
        controlPanelMembre.add(membre);
        controlPanelMembre.add(prof);
        controlPanelMembre.add(add);
        controlPanelMembre.add(name);
        controlPanelMembre.add(update);
        controlPanelMembre.add(uName);
        controlPanelMembre.add(uNumber);
        controlPanelMembre.add(delete);
        controlPanelMembre.add(number);
        controlPanelMembre.add(back);

        //Actions services
        JButton addS=new JButton("Add");
        JTextField start=new JTextField("Début des scéances");
        JTextField end=new JTextField("Fin des scéances");
        JTextField time=new JTextField("Heure des scéances");
        JTextField day=new JTextField("jours des scéances");
        JTextField capacity=new JTextField("Capacité");
        JTextField numberS=new JTextField("Numéro de professionel");
        JTextField cost=new JTextField("coût des scéances");
        JButton updateS=new JButton("Update");
        JTextField code=new JTextField("Code de scéance");
        JTextField parametre=new JTextField("Paramètre");
        JTextField changement=new JTextField("Changement");
        JButton deleteS=new JButton("Delete");
        JTextField codeD=new JTextField("Code de scéance");
        JButton consult = new JButton("Consulter");
        JButton backS=new JButton("Back");


        controlPanelService.add(addS);
        controlPanelService.add(start);
        controlPanelService.add(end);
        controlPanelService.add(time);
        controlPanelService.add(day);
        controlPanelService.add(capacity);
        controlPanelService.add(numberS);
        controlPanelService.add(cost);
        controlPanelService.add(updateS);
        controlPanelService.add(code);
        controlPanelService.add(parametre);
        controlPanelService.add(changement);
        controlPanelService.add(deleteS);
        controlPanelService.add(codeD);
        controlPanelService.add(consult);
        controlPanelService.add(backS);

        consult.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConsulteurRepertoire.displayRepertoire(contS);
            }
        }));

        backS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                serviceSelection.setVisible(false);
                mainWindow.setVisible(true);
            }
        });

        addS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Service serv=new Service(Generator.generateNumService(), numberS.getText());
                contS.addService(serv);
            }
        });

        updateS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Service serv=new Service(codeD.getText(), numberS.getText());
                contS.updateService(code.getText(), numberS.getText(), serv);
            }
        });

        deleteS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contS.deleteService(codeD.getText(), numberS.getText());
            }
        });

        //Actions inscription
        JButton addI=new JButton("Inscription");
        JTextField codeI=new JTextField("Code de service");
        JTextField date=new JTextField("Date du service");
        JTextField numberI=new JTextField("Numéro client");
        JTextField numberIP=new JTextField("Numéro du professionel");
        JButton backI=new JButton("Back");
        JButton remove=new JButton("Remove Inscription");
        JTextField numbers=new JTextField("Numéro du client");
        JTextField codes=new JTextField("Code de service");
        JTextField numberIPs=new JTextField("Numéro du professionel");

        controlPanelInscription.add(backI);
        controlPanelInscription.add(addI);
        controlPanelInscription.add(codeI);
        controlPanelInscription.add(date);
        controlPanelInscription.add(numberI);
        controlPanelInscription.add(remove);
        controlPanelInscription.add(numbers);
        controlPanelInscription.add(codes);
        controlPanelInscription.add(numberIPs);

        backI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inscriptionWindow.setVisible(false);
                mainWindow.setVisible(true);
            }
        });

        addI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Inscription ins=new Inscription(numberIP.getText(), numberI.getText(), codeI.getText());
                contI.addInscription(ins);
            }
        });

        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contI.deleteInscription(numberIPs.getText(), numbers.getText(), code.getText());
            }
        });

        //Actions confirm

        JButton confirm=new JButton("Confirm");
        JTextField codeSc=new JTextField("Code scéance");
        JTextField numberC=new JTextField("Numéro de client");
        JTextField numberP=new JTextField("Numéro de professionel");
        JButton backC=new JButton("Back");

        controlPanelConfirm.add(confirm);
        controlPanelConfirm.add(codeSc);
        controlPanelConfirm.add(numberC);
        controlPanelConfirm.add(backC);

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConfirmationInscription conf=new ConfirmationInscription(numberP.getText(), numberC.getText(), codeSc.getText());
                contC.addConfirmationInscription(conf);
            }
        });

        backC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmWindow.setVisible(false);
                mainWindow.setVisible(true);
            }
        });

    }
    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if( command.equals( "Membre" ))  {
                mainWindow.setVisible(false);
                memberSelection.setVisible(true);
            } else if( command.equals( "Service" ) )  {
                mainWindow.setVisible(false);
                serviceSelection.setVisible(true);
            } else if( command.equals("Inscription")){
                mainWindow.setVisible(false);
                inscriptionWindow.setVisible(true);
            }
            else if(command.equals("Confirmation")){
                mainWindow.setVisible(false);
                confirmWindow.setVisible(true);
            }
            else if(command.equals("Valider")){
                Validateur.validerMembre(validateText.getText(), contM, contP);
            }
            else{
                ProcedureComptable proc = new ProcedureComptable();
                proc.main();
            }
        }
    }


}