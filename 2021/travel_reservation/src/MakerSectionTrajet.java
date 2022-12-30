package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MakerSectionTrajet extends SectionMaker implements ActionListener {

	
	private JFrame window;
	private Trajet trajet;
	private JLabel classe1;
	private JLabel classe2;
	private JLabel rangees1;
	private JLabel rangees2;
	private JTextField tf1;
	private JTextField tf2;
	private JButton btn1;
	private JButton btn2;
	private ArrayList<SectionTrajet> sections = new ArrayList<SectionTrajet>();
	private DispositionType dtype = DispositionType.ETROIT;
	
	MakerSectionTrajet(Database db, int index){
		trajet = (Trajet) db.getEntite(index);
		window = new JFrame("Trajet Section...");
		window.setSize(500, 300);
		window.setLayout(null);
		classe1 = new JLabel("Premiere");
		classe2 = new JLabel("Economique");
		rangees1 = new JLabel("Rangees");
		rangees2 = new JLabel("Rangees");
		tf1 = new JTextField();
		tf2 = new JTextField();
		
		
		
		classe1.setBounds(10, 10, 150, 20);
		rangees1.setBounds(10,40 , 100, 20);
		tf1.setBounds(160, 40, 40, 20);
		
		classe2.setBounds(10, 110, 150, 20);
		rangees2.setBounds(10,140 , 100, 20);
		tf2.setBounds(160, 140, 40, 20);
		
		
		window.add(classe1);
		window.add(rangees1);
		window.add(tf1);
		
		window.add(classe2);
		window.add(rangees2);
		window.add(tf2);
		
		
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
				ClasseType ctype = ClasseType.PREMIERE;
				SectionTrajet section = new SectionTrajet(Integer.parseInt(tf1.getText()), ctype, dtype, trajet);
				this.sections.add(section);
			}
			
			if(true) {
				ClasseType ctype = ClasseType.ECONOMIQUE;
				SectionTrajet section = new SectionTrajet(Integer.parseInt(tf2.getText()), ctype,dtype, trajet);
				this.sections.add(section);
			}
			
			this.trajet.setSections(sections);
			window.dispose();
		} else if(a.equals(btn2)) {
			window.dispose();
		}
		
	}
	

}
