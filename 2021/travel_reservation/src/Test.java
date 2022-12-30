package main;


import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] args1 = Form.getLabels(FType.VOYAGE_ITINERAIRE);
//		Vol v = new Vol(args1);
//		System.out.println(v.getInfo());
//		
//		DBListBuilder db = new DBListBuilder();
//		HashMap<FType, Database> hm = db.dbBuild();
//		FType[] types = FType.values();
//		
//		for (FType type:types) {
//			System.out.println(type);
//			System.out.println(hm.get(type));
//			
//		}
		
//		EntryWindow e = new EntryWindow(args1);
		JFrame j = new JFrame();
		j.setLayout(null);
		j.setSize(300,400);
		ContainerText c_t = new ContainerText("ID");
		JPanel d = c_t.getPanel();
		d.setBounds(10,10,70,70);
		ContainerText c_d = new ContainerText("ID");
		JPanel h = c_d.getPanel();
		h.setBounds(10,100,100,100);
		j.add(d);
		j.add(h);
		j.setVisible(true);
	}

}
