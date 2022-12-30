package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainMenu implements ActionListener {

	JFrame main;
	JButton btn_client;
	JButton btn_admin;
	Viewer view;
	HashMap<FType, Database> DBList;
	
	
	MainMenu(HashMap<FType, Database> DBList){
		this.DBList = DBList;
		main = new JFrame(GUIStrings.main_menu);
		main.setSize(300, 180);
		main.setLayout(null);
		btn_client = new JButton(GUIStrings.btn_client);
		btn_client.setBounds(10, 10, 100, 50);
		btn_client.addActionListener(this);
		btn_admin = new JButton(GUIStrings.btn_admin);
		btn_admin.setBounds(10, 60, 100, 50);
		btn_admin.addActionListener(this);
		
		main.add(btn_client);
		main.add(btn_admin);
		main.setVisible(true);
		main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals(btn_client)) {
			new ViewerAdmin(DBList);
		} 
		
		else if (source.equals(btn_admin)) {
			new ViewerAdmin(DBList);
		}
		
	}
	
	public void openClientWindow() {
		
	}
}
