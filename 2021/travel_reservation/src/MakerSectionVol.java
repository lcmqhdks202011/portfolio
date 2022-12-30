package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MakerSectionVol extends SectionMaker implements ActionListener {

	
	private JFrame window;
	private Vol vol;
	private JLabel classe1;
	private JLabel classe2;
	private JLabel classe3;
	private JLabel classe4;
	private JLabel rangee1;
	private JLabel rangee2;
	private JLabel rangee3;
	private JLabel rangee4;
	private JLabel dp1;
	private JLabel dp2;
	private JLabel dp3;
	private JLabel dp4;
	private JCheckBox chk1;
	private JCheckBox chk2;
	private JCheckBox chk3;
	private JCheckBox chk4;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	private JComboBox<DispositionType> cb1;
	private JComboBox<DispositionType> cb2;
	private JComboBox<DispositionType> cb3;
	private JComboBox<DispositionType> cb4;
	private JButton btn1;
	private JButton btn2;
	private ArrayList<SectionVol> sections = new ArrayList<SectionVol>(); 
	
	MakerSectionVol(Database db, int index){
		vol = (Vol) db.getEntite(index);
		window = new JFrame("Vol Section...");
		window.setSize(500, 500);
		window.setLayout(null);
		DispositionType[] dispositions = DispositionType.values();
		classe1 = new JLabel("Premiere");
		chk1 = new JCheckBox();
		classe2 = new JLabel("Affaire");
		chk2 = new JCheckBox();
		classe3 = new JLabel("Economique Premium");
		chk3 = new JCheckBox();
		classe4 = new JLabel("Economique");
		chk4 = new JCheckBox();
		rangee1 = new JLabel("Rangees");
		rangee2 = new JLabel("Rangees");
		rangee3 = new JLabel("Rangees");
		rangee4 = new JLabel("Rangees");
		
		dp1 = new JLabel("Disposition");
		dp2 = new JLabel("Disposition");
		dp3 = new JLabel("Disposition");
		dp4 = new JLabel("Disposition");
		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		tf4 = new JTextField();
		cb1 = new JComboBox<DispositionType>(dispositions);
		cb2 = new JComboBox<DispositionType>(dispositions);
		cb3 = new JComboBox<DispositionType>(dispositions);
		cb4 = new JComboBox<DispositionType>(dispositions);
		
		
		
		classe1.setBounds(10, 10, 150, 20);
		chk1.setBounds(160,10,20, 20);
		cb1.setBounds(200, 70, 100, 20);
		rangee1.setBounds(10,40 , 100, 20);
		tf1.setBounds(160, 40, 40, 20);
		dp1.setBounds(10, 70, 150, 20);
		
		classe2.setBounds(10, 110, 150, 20);
		chk2.setBounds(160,110, 20, 20);
		cb2.setBounds(200, 170, 100, 20);
		rangee2.setBounds(10,140 , 100, 20);
		tf2.setBounds(160, 140, 40, 20);
		dp2.setBounds(10, 170, 150, 20);
		
		classe3.setBounds(10, 210, 150, 20);
		chk3.setBounds(160,210,20, 20);
		cb3.setBounds(200, 270, 100, 20);
		rangee3.setBounds(10,240 , 100, 20);
		tf3.setBounds(160, 240, 40, 20);
		dp3.setBounds(10, 270, 150, 20);
		
		classe4.setBounds(10, 310, 150, 20);
		chk4.setBounds(160,310,20, 20);
		cb4.setBounds(200, 370, 100, 20);
		rangee4.setBounds(10,340 , 100, 20);
		tf4.setBounds(160, 340, 40, 20);
		dp4.setBounds(10, 370, 150, 20);
		
		window.add(classe1);
		window.add(chk1);
		window.add(cb1);
		window.add(rangee1);
		window.add(tf1);
		window.add(dp1);
		
		window.add(classe2);
		window.add(chk2);
		window.add(cb2);
		window.add(rangee2);
		window.add(tf2);
		window.add(dp2);
		
		window.add(classe3);
		window.add(chk3);
		window.add(cb3);
		window.add(rangee3);
		window.add(tf3);
		window.add(dp3);
		
		window.add(classe4);
		window.add(chk4);
		window.add(cb4);
		window.add(rangee4);
		window.add(tf4);
		window.add(dp4);
		
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
			if(chk1.isSelected()) {
				DispositionType dtype = (DispositionType) cb1.getSelectedItem();
				ClasseType ctype = ClasseType.PREMIERE;
				SectionVol section = new SectionVol(Integer.parseInt(tf1.getText()), ctype, dtype, vol);
				this.sections.add(section);
			}
			
			if(chk2.isSelected()) {
				DispositionType dtype = (DispositionType) cb2.getSelectedItem();
				ClasseType ctype = ClasseType.AFFAIRE;
				SectionVol section = new SectionVol(Integer.parseInt(tf2.getText()), ctype, dtype, vol);
				this.sections.add(section);
			}
			
			if(chk3.isSelected()) {
				DispositionType dtype = (DispositionType) cb3.getSelectedItem();
				ClasseType ctype = ClasseType.ECONOMIQUE_PREMIUM;
				SectionVol section = new SectionVol(Integer.parseInt(tf3.getText()), ctype, dtype, vol);
				this.sections.add(section);
			}
			
			if(chk4.isSelected()) {
				DispositionType dtype = (DispositionType) cb4.getSelectedItem();
				ClasseType ctype = ClasseType.ECONOMIQUE;
				SectionVol section = new SectionVol(Integer.parseInt(tf4.getText()), ctype, dtype, vol);
				this.sections.add(section);
			}
			
			this.vol.setSections(sections);
			window.dispose();
		} else if(a.equals(btn2)) {
			window.dispose();
		}
		
	}
	

}
