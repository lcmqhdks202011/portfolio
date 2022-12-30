package main;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContainerText extends Container{
	
	JPanel jpanel;
	JTextField t_box;
	JLabel label;
	
	ContainerText(String key){
		this.containerType = Form.InputType.TEXT;
		jpanel = new JPanel();
		jpanel.setLayout(null);
		t_box = new JTextField();
		label = new JLabel(key);
		label.setBounds(0,0, 100, 20);
		t_box.setBounds(100,0, 100, 20);
		jpanel.add(label);
		jpanel.add(t_box);
		
	}
	
	public Object getSelectedObject() {
		return t_box.getText();
	}
	
	public String getJLabel() {
		return label.getText();
	}
	
	public JPanel getPanel() {
		return jpanel;
	}

	@Override
	public Object getObject() {
		// TODO Auto-generated method stub
		return t_box;
	}
}
