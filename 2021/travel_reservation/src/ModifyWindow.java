package main;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ModifyWindow extends EntryWindow {
	
	private int i;

	public ModifyWindow(String[] args, Viewer caller, HashMap<FType, Database> DBList, int i) {
		
		super(args, caller, DBList);
		this.i = i;
		
		System.out.println("test");
		
		HashMap<String, Object> data = caller.current.getEntite(i).getInformation();
		
		for(Container c : this.containers) {
			Object o = data.get(c.getJLabel());
			switch(c.containerType) {
			
				case TEXT:
					JTextField tf = (JTextField)c.getObject();
					tf.setText(o.toString());
					tf.revalidate();
					tf.repaint();
					break;
				case COMBOBOX:
					JComboBox<?> cb = (JComboBox<?>)(c.getObject());
					cb.setSelectedItem(o);
					cb.revalidate();
					cb.repaint();
					break;
				default:
					break;
				
			}
		}
		
		
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if(s.equals(btn_ok)) {
			caller.accept(this, i);
			j.setVisible(false);
		} else if(s.equals(btn_cancel)) {
			j.setVisible(false);
		
		}
		
		
		
	}
}
