package main;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContainerSectionItineraire extends Container{
	
	JPanel jpanel;
	JTextField[] tf_cabins;
	JLabel[] l_cabins;
	JLabel[] l_classe;
	JLabel[] l_classeType;
	JPanel[] subPanel;
	
	ContainerSectionItineraire(){
		jpanel = new JPanel();
		ClasseType[] c_type = Itineraire.getClasses();
		int n = c_type.length;
		jpanel.setLayout(new GridLayout(n, 1));
		
		subPanel = new JPanel[n];
		tf_cabins = new JTextField[n];
		l_cabins = new JLabel[n];
		l_classe = new JLabel[n];
		l_classeType = new JLabel[n];
		for(int i=0;i<n;i++) {
			
			subPanel[i] = new JPanel();
			tf_cabins[i] = new JTextField();
			l_cabins[i] = new JLabel("Cabins");
			l_classe[i] = new JLabel("Class : ");
			l_classeType[i] = new JLabel(c_type[i].toString());
			
			subPanel[i].setLayout(null);
			tf_cabins[i].setBounds(110, 0, 30, 20);
			l_cabins[i].setBounds(0,0, 100, 20);
			l_classe[i].setBounds(0,30, 100, 20);
			l_classeType[i].setBounds(110, 30, 100, 20);
			subPanel[i].add(tf_cabins[i]);
			subPanel[i].add(l_cabins[i]);
			subPanel[i].add(l_classe[i]);
			subPanel[i].add(l_classeType[i]);
			jpanel.add(subPanel[i]);
		}
		
		
	}
	
	public Object[] getNbCabins() {
		Object[] res = new Object[tf_cabins.length];
		for(int i=0;i<tf_cabins.length;i++) {
			res[i] = tf_cabins[i].getText();
		}
		return res;
	}
	
	public ClasseType[] getClasses() {
		return Itineraire.getClasses();
	}
	
	public JPanel getPanel() {
		return jpanel;
	}
	
	@Override
	public Object getSelectedObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
