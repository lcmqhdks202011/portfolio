package main;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContainerComboBox extends Container{

	JPanel jpanel;
	JComboBox<Object> c_box;
	JLabel label;
	
	ContainerComboBox(Object[] obj, String key){
		this.containerType = Form.InputType.COMBOBOX;
		jpanel = new JPanel();
		jpanel.setLayout(null);
		c_box = new JComboBox<Object>(obj);
		label = new JLabel(key);
		label.setBounds(0, 0, 100, 20);
		c_box.setBounds(100, 0, 100, 20);
		jpanel.add(c_box);
		jpanel.add(label);
		
	}
	
	public Object getSelectedObject() {
		return c_box.getSelectedItem();
	}
	
	public JPanel getPanel() {
		return jpanel;
	}

	@Override
	public String getJLabel() {
		// TODO Auto-generated method stub
		return label.getText();
	}

	@Override
	public Object getObject() {
		// TODO Auto-generated method stub
		return c_box;
	}
	
}
