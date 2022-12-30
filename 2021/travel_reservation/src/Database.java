package main;

import java.util.ArrayList;
import java.util.List;

public class Database {
	
	private String dbName;
	private FType type;
	private List<Entite> datas;
	private List<Viewer> viewers;
	private List<MementoEntite> mementos;
	
	Database(FType type, String dbName){
		this.type = type;
		this.datas = new ArrayList<Entite>();
		this.viewers = new ArrayList<Viewer>();
		this.dbName = dbName;
		this.mementos = new ArrayList<MementoEntite>();
	}
	
	public void subscribe(Viewer v) {
		viewers.add(v);
		v.update(this.getInfo());
	}
	
	public void unsubscribe(Viewer v) {
		viewers.remove(v);
	}
	
	public void add(Entite e) {
		this.datas.add(e);
		int i = this.datas.size() - 1;
		this.mementos.add(new MementoEntite(CommandType.CREATE, e, i));
		notifyViewers();
		System.out.println("Added");
	}
	
	
	public void remove(int i) {
		Entite e = this.datas.get(i);
		this.mementos.add(new MementoEntite(CommandType.DELETE, e, i));
		this.datas.remove(e);
		notifyViewers();
		System.out.println("Deleted");
	}
	
	public void replace(Entite entite, int i) {
		Entite e = this.datas.get(i);
		this.datas.remove(i);
		this.datas.add(i, entite);
		this.mementos.add(new MementoEntite(CommandType.MODIFY, e, i));
		notifyViewers();
	}
	
	public Entite getEntite(int i) {
		return this.datas.get(i);
	}
	
	public String[][] getInfo() {
		String[][] res = new String[this.datas.size()][2];
		
		for(int i=0;i<this.datas.size();i++) {
			Entite e = this.datas.get(i);
			res[i][0] = i + "";
			res[i][1] = HashMapBuilder.informationBuild(e.information, this.type);
			
			for(Section s : e.getSections()){
				res[i][1] += HashMapBuilder.sectionDetails(s);
			}
		}
		
		return res;
	}
	
	public String[][] getInfoSingleSection(ClasseType ctype){
		String[][] res = new String[this.datas.size()][2];
		
		for(int i=0;i<this.datas.size();i++) {
			Entite e = this.datas.get(i);
			res[i][0] = i + "";
			res[i][1] = HashMapBuilder.informationBuild(e.information, this.type);
			for(Section s : e.getSections()){
				if(s.classe.equals(ctype))
				res[i][1] += HashMapBuilder.sectionDetails(s);
			}
		}
		
		return res;
	}
	
	public void notifyViewer(Viewer v) {
		if(this.viewers.contains(v)) {
			v.update(getInfo());
		}
	}
	
	public void notifyViewers() {
		for(Viewer v : viewers) {
			v.update(this.getInfo());
		}
	}
	
	public FType getFType() {
		return this.type;
	}
	
	public int getSize() {
		return this.datas.size();
	}
	
	public String[] getComboBoxOptions(String key) {
		int size = getSize();
		String[] res = new String[size];
		for(int i=0; i < size; i++) {
			res[i] = this.datas.get(i).information.get(key).toString();
		}
		
		return res;
	}
	
	public void restore() {
		int n = this.mementos.size();
		if(n==0) return;
		MementoEntite mem = this.mementos.get(n-1);
		int i = mem.getIndex();
		Entite e = mem.getEntite();
		switch(mem.getCommandType()) {
		
			 case CREATE:
				 this.datas.remove(e);
				 break;
			 case DELETE:
				 add(i, e);
				 break;
			 case MODIFY:
				 this.datas.remove(i);
				 add(i, e);
				 break;
			 default:
				 break;
		}
	
		this.mementos.remove(mem);
		notifyViewers();
	}
	
	public void add(int j, Entite e) {
		this.datas.add(j, e);
		notifyViewers();
		System.out.println("Added");
	}

	@Override
	public String toString(){
		return this.dbName;
	}
	
	

}
