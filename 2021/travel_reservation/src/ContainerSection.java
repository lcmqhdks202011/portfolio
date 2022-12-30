package main;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContainerSection extends Container{
	
	JPanel jpanel;
	JTextField tf_nbRangee;
	JLabel l_nbRangee;
	JLabel l_classe;
	JComboBox<ClasseType> c_box;
	FType type;
	
	ContainerSection(FType type){
		this.type = type;
		jpanel = new JPanel();
		jpanel.setLayout(null);
		tf_nbRangee = new JTextField();
		
		l_nbRangee = new JLabel("Rangees");
		l_nbRangee.setBounds(0,0, 140, 20);
		tf_nbRangee.setBounds(160,0, 140, 20);
		
		l_classe = new JLabel("Classe");
		c_box = new JComboBox<ClasseType>(getClasses());
	}
	
	public Object getNbRangee() {
		return tf_nbRangee.getText();
	}
	
	public ClasseType[] getClasses() {
		switch(type) {
		
			case VOYAGE_VOL:
				return Vol.getClasses();
				
			case VOYAGE_ITINERAIRE:
				return Itineraire.getClasses();
				
			case VOYAGE_TRAJET:
				return Trajet.getClasses();
			default:
				break;
			
		}
		return null;
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
