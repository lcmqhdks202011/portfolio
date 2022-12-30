package main;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JTable;

public class ViewerAdmin extends Viewer {
	
	
	private JButton btn_new;
	private JButton btn_mod;
	private JButton btn_del;
	private JButton btn_und;
	private JButton btn_sec;
	private JButton btn_arrets;
	
	ViewerAdmin(HashMap<FType, Database> DBList){
		super(DBList);
		
		btn_new = new JButton("Creer");
		btn_mod = new JButton("Modifier");
		btn_del = new JButton("Supprimer");
		btn_und = new JButton("Undo");
		btn_sec = new JButton("Section");
		btn_arrets = new JButton("Arrets");
		btn_new.addActionListener(this);
		btn_mod.addActionListener(this);
		btn_del.addActionListener(this);
		btn_und.addActionListener(this);
		btn_sec.addActionListener(this);
		btn_arrets.addActionListener(this);
		btn_sec.setBounds(10, 270, 120, 20);
		btn_new.setBounds(10, 110, 120, 20);
		btn_mod.setBounds(10, 160, 120, 20);
		btn_del.setBounds(10, 210, 120, 20);
		btn_und.setBounds(10, 240, 120, 20);
		btn_arrets.setBounds(10, 300, 120, 20);
		this.right_panel.add(btn_new);
		this.right_panel.add(btn_mod);
		this.right_panel.add(btn_del);
		this.right_panel.add(btn_und);
		this.right_panel.add(btn_arrets);
		this.right_panel.add(btn_sec);
		hideSection();
	}
	

	@Override
	public void update(String[][] info) {
		String[] headers = { "Index", "Information" };
		t_list = new JTable(info, headers);
		sp.getViewport().setView(t_list);
		sp.getViewport().repaint();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if(s.equals(btn_new)) {
			String[] str = Form.getLabels(this.current.getFType());
			new EntryWindow(str, this, DBList);
		} else if (s.equals(cb_type)) {
			current.unsubscribe(this);
			current = (Database) cb_type.getSelectedItem();
			hideSection();
			current.subscribe(this);
			System.out.println(current.getFType());
			new Refresh(current, this);
		} else if (s.equals(btn_del)) {
			if(t_list.getSelectedRow()!=-1) {
				int index = t_list.getSelectedRow();
				current.remove(index);
			}
		} else if (s.equals(btn_mod)) {
			if(t_list.getSelectedRow()!=-1) {
				String[] str = Form.getLabels(this.current.getFType());
				int index = t_list.getSelectedRow();
				System.out.println(index);
				new ModifyWindow(str, this, DBList, index);
			}
		} else if (s.equals(btn_und)) {
			new Undo(current);
		} else if(s.equals(btn_sec)) {
			int index = t_list.getSelectedRow();
			if(index == -1) return;
			switch(current.getFType()) {
				case VOYAGE_VOL:
					 new MakerSectionVol(current, index);
					break;
				case VOYAGE_ITINERAIRE:
					 new MakerSectionItineraire(current, index);
					break;
				case VOYAGE_TRAJET:
					 new MakerSectionTrajet(current, index);
					break;
			default:
				break;
				
			}
		
			new Refresh(current, this);
		}
	}


	@Override
	public void accept(EntryWindow w) {
		Object[] args = w.visit();
		new Create(current, args);
	}
	

	@Override
	protected void accept(ModifyWindow w, int i) {
		Object[] args = w.visit();
		System.out.println(i);
		new Modify(current, args, i);
	}
	
	public void hideSection() {
		
		if(current.getFType() == FType.VOYAGE_VOL ||
				   current.getFType() == FType.VOYAGE_ITINERAIRE ||
				   current.getFType() == FType.VOYAGE_TRAJET)
			this.btn_sec.setVisible(true);
		else this.btn_sec.setVisible(false);
		
		if(current.getFType() == FType.VOYAGE_ITINERAIRE ||
		   current.getFType() == FType.VOYAGE_TRAJET)
			this.btn_arrets.setVisible(true);
		else this.btn_arrets.setVisible(false);
		
	}

}
