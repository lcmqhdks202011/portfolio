package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;

public class EntryWindow implements ActionListener {

	protected JFrame j;
	private JPanel up;
	private JPanel down;
	protected JButton btn_ok;
	protected JButton btn_cancel;
	protected Viewer caller;
	protected ArrayList<Container> containers = new ArrayList<Container>();
	private FType type;
	protected CommandType ctype;
	
	EntryWindow(String[] args, Viewer caller, HashMap<FType, Database> DBList){
		this.caller = caller;
		this.type = caller.current.getFType();
		
		j  = new JFrame("Create");
		j.setLayout(null);
		int nb_lb = args.length;
		j.setSize(400, nb_lb * 80);
		up = new JPanel();
		down = new JPanel();
		up.setLayout(new GridLayout(nb_lb, 1));
		down.setLayout(null);
		up.setBounds(10, 10, 250, nb_lb * 60);
		down.setBounds(260, 10, 120, 300);
		FType[] ftype = Form.getFTypeList(type);
		for(int i=0;i<nb_lb;i++) {
			Database db;
			if(ftype != null) db = DBList.get(ftype[i]);
			else db = null;
			addContainer(db, args[i], Form.getInputType(type)[i]);
		}
		
		btn_ok = new JButton("OK");
			
		btn_cancel = new JButton("Cancel");
		btn_ok.setBounds(0, 0, 100, 20);
		btn_cancel.setBounds(0, 30, 100, 20);
		btn_ok.addActionListener(this);
		btn_cancel.addActionListener(this);
		down.add(btn_ok);
		down.add(btn_cancel);
		j.add(up);
		j.add(down);
		j.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		j.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if(s.equals(btn_ok)) {
			caller.accept(this);
			j.setVisible(false);
		} else if(s.equals(btn_cancel)) {
			j.setVisible(false);
		} 
		
		
		
	}
	
	
	public Object[] visit() {
	int n = containers.size();
	Object[] res = new Object[n];
			
			for(int i=0; i < n ; i++) {
				res[i] = containers.get(i).getSelectedObject();
			}
			
			return res;
		
	}
	
	
	public void addContainer(Database db, String key, Form.InputType i) {
		switch(i) {
			case TEXT:
				ContainerText c = new ContainerText(key);
				containers.add(c);
				up.add(c.getPanel());
				break;
		
			case COMBOBOX:
				if(db!=null) {
				Object[] obj = db.getComboBoxOptions("ID");
				ContainerComboBox c_box = new ContainerComboBox(obj, key);
				containers.add(c_box);
				up.add(c_box.getPanel());
				}
				break;
			default:
			break;
			
		}
	}
	
	
	
}
