package main;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.HashMap;

public abstract class Viewer implements ActionListener {

	protected HashMap<FType, Database> DBList;
	protected JFrame view;
	protected JTable t_list;
	protected JButton btn_src;
	protected JTextField tf_src;
	protected JComboBox<Database> cb_type;
	protected JComboBox<String> cb_src;
	protected JPanel right_panel;
	protected Database current;
	protected JScrollPane sp;
	
	Viewer(HashMap<FType, Database> DBList){
		this.DBList = DBList;
		
		cb_type = new JComboBox<Database>();
		for (FType type : FType.values())
			cb_type.addItem(DBList.get(type));
		cb_type.setBounds(10, 30, 120, 20);
		cb_type.setSelectedIndex(0);
		cb_type.addActionListener(this);
		this.current = (Database) cb_type.getSelectedItem();
		System.out.println(current);
		
		
		
		
        String[][] data = {
                { "", ""},
                { "", ""}
            };
     
            // Column Names
        String[] columnNames = { "Index", "Information" };
		t_list = new JTable(data, columnNames);
		
		t_list.setBounds(10,10,500,500);


		String[] a = {"ID", "Origin", "Destination"};
		cb_src = new JComboBox<String>(a);
		cb_src.setBounds(10, 50, 120, 20);
		cb_src.addActionListener(this);
		tf_src = new JTextField();
		tf_src.setBounds(10, 70, 120, 20);
		btn_src = new JButton("Search");
		btn_src.setBounds(10, 90, 120, 20);
		
		view = new JFrame("Viewer");
		view.setSize(700,600);
		view.setLayout(null);
		right_panel = new JPanel();
		right_panel.setLayout(null);
		right_panel.add(cb_src);
		right_panel.add(btn_src);
		right_panel.add(tf_src);
		right_panel.add(cb_type);
		
		sp = new JScrollPane(t_list);
		sp.setBounds(10,10,500,500);
		right_panel.setBounds(530, 10, 200, 500);
		view.add(sp);
		view.add(right_panel);
		view.setVisible(true);
		current.subscribe(this);
	}
	
	public abstract void update(String[][] info);
	
	public abstract void accept(EntryWindow w);

	protected abstract void accept(ModifyWindow modifyWindow, int i);

	
}
