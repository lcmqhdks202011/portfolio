package main;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContainerSectionVol extends Container{
	
	JPanel jpanel;
	JTextField tf_nbRangee;
	JLabel l_nbRangee;
	JLabel l_classe;
	JComboBox<ClasseType> c_box;
	
	ContainerSectionVol(){
		jpanel = new JPanel();
		jpanel.setLayout(null);
		tf_nbRangee = new JTextField();
		
		l_nbRangee = new JLabel("Rangees");
		l_nbRangee.setBounds(0,20, 100, 20);
		tf_nbRangee.setBounds(100,20, 100, 20);
		
		l_classe = new JLabel("Classe");
		c_box = new JComboBox<ClasseType>(getClasses());
		l_classe.setBounds(0,40, 100, 20);
		c_box.setBounds(100,40, 100, 20);
		jpanel.add(tf_nbRangee);
		jpanel.add(l_nbRangee);
		jpanel.add(l_classe);
		jpanel.add(c_box);
	}
	
	public Object getNbRangee() {
		return tf_nbRangee.getText();
	}
	
	public ClasseType[] getClasses() {
		return Vol.getClasses();
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
		return l_nbRangee.getText();
	}

	@Override
	public Object getObject() {
		// TODO Auto-generated method stub
		return tf_nbRangee;
	}
}
