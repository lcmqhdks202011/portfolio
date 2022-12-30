package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MakerSectionItineraire extends SectionMaker implements ActionListener {

	
	private JFrame window;
	private Itineraire itineraire;
	private JLabel classe1;
	private JLabel classe2;
	private JLabel classe3;
	private JLabel classe4;
	private JLabel classe5;
	private JLabel cabines1;
	private JLabel cabines2;
	private JLabel cabines3;
	private JLabel cabines4;
	private JLabel cabines5;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	private JTextField tf5;
	private JButton btn1;
	private JButton btn2;
	private ArrayList<SectionItineraire> sections = new ArrayList<SectionItineraire>(); 
	
	MakerSectionItineraire(Database db, int index){
		itineraire = (Itineraire) db.getEntite(index);
		window = new JFrame("Itineraire Section...");
		window.setSize(500, 500);
		window.setLayout(null);
		classe1 = new JLabel("Interieure");
		classe2 = new JLabel("Vue de l'Ocean");
		classe3 = new JLabel("Suite");
		classe4 = new JLabel("Famille");
		classe5 = new JLabel("Famille Deluxe");
		cabines1 = new JLabel("Cabines");
		cabines2 = new JLabel("Cabines");
		cabines3 = new JLabel("Cabines");
		cabines4 = new JLabel("Cabines");
		cabines5 = new JLabel("Cabines");
		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		tf4 = new JTextField();
		tf5 = new JTextField();
		
		
		
		classe1.setBounds(10, 10, 150, 20);
		cabines1.setBounds(10,40 , 100, 20);
		tf1.setBounds(160, 40, 40, 20);
		
		classe2.setBounds(10, 110, 150, 20);
		cabines2.setBounds(10,140 , 100, 20);
		tf2.setBounds(160, 140, 40, 20);
		
		classe3.setBounds(10, 210, 150, 20);
		cabines3.setBounds(10,240 , 100, 20);
		tf3.setBounds(160, 240, 40, 20);
		
		classe4.setBounds(10, 310, 150, 20);
		cabines4.setBounds(10,340 , 100, 20);
		tf4.setBounds(160, 340, 40, 20);
		
		classe5.setBounds(10, 410, 150, 20);
		cabines5.setBounds(10,440 , 100, 20);
		tf5.setBounds(160, 440, 40, 20);
		
		window.add(classe1);
		window.add(cabines1);
		window.add(tf1);
		
		window.add(classe2);
		window.add(cabines2);
		window.add(tf2);
		
		window.add(classe3);
		window.add(cabines3);
		window.add(tf3);
		
		window.add(classe4);
		window.add(cabines4);
		window.add(tf4);
		
		btn1 = new JButton("OK");
		btn2 = new JButton("Cancel");
		
		btn1.setBounds(360, 10, 100, 20);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn2.setBounds(360, 40, 100, 20);
		
		window.add(btn1);
		window.add(btn2);
		
		window.setVisible(true);
		
	}
	@Override
	public void acceptVisit() {
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent e) {

		Object a = e.getSource();
		
		if(a.equals(btn1)) {
			if(true) {
				ClasseType ctype = ClasseType.INTERIEURE;
				SectionItineraire section = new SectionItineraire(Integer.parseInt(tf1.getText()), ctype, itineraire);
				this.sections.add(section);
			}
			
			if(true) {
				ClasseType ctype = ClasseType.VUEOCEAN;
				SectionItineraire section = new SectionItineraire(Integer.parseInt(tf2.getText()), ctype, itineraire);
				this.sections.add(section);
			}
			
			if(true) {
				ClasseType ctype = ClasseType.SUITE;
				SectionItineraire section = new SectionItineraire(Integer.parseInt(tf3.getText()), ctype, itineraire);
				this.sections.add(section);
			}
			
			if(true) {
				ClasseType ctype = ClasseType.FAMILLE;
				SectionItineraire section = new SectionItineraire(Integer.parseInt(tf4.getText()), ctype, itineraire);
				this.sections.add(section);
			}
			
			if(true) {
				ClasseType ctype = ClasseType.FAMILLEDELUXE;
				SectionItineraire section = new SectionItineraire(Integer.parseInt(tf5.getText()), ctype, itineraire);
				this.sections.add(section);
			}
			
			this.itineraire.setSections(sections);
			window.dispose();
		} else if(a.equals(btn2)) {
			window.dispose();
		}
		
	}
	

}
