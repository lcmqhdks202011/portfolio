import java.util.ArrayList;

public class Coalescence {
	
	ArrayList<Sim> list;
	ArrayList<Sim> list2;
	ArrayList<Point> points;
	
	Coalescence(ArrayList<Sim> pop, Sim.Sex sex){
		list = new ArrayList<Sim>();
		list2 = new ArrayList<Sim>();
		points = new ArrayList<Point>();
		start(pop, sex);
		
	}
	
	public void insertSim(Sim x) {
		
		list.add(x);
		
		int  p = list.size() - 1;
		//System.out.println(x);
		while (p > 0 && 
				list.get(p).getBirthTime() < list.get(p/2).getBirthTime())
		{
			Sim tmp = list.get(p/2);
			list.set(p/2, list.get(p));
			list.set(p, tmp);
			
			p /= 2;
			
		}
		
	}
	
	public Sim deleteSim() {
		if(list.size() == 0) {
			return null;
		}
		int i = 0;
		while(list.get(i).isFounder()) {
			i++;
			if(i == list.size() -1) break;
		}
		
		
		Sim deleteItem = list.get(i);
		
		list.set(i, list.get(list.size()-1));
		list.remove(i);
		
		int pos = 0;
		while((pos * 2 + 1) < list.size()) {
			
			double min = list.get(pos * 2 + 1).getBirthTime();
			int minPos = pos * 2 + 1;
			
			if((((pos+1) * 2) < list.size() && min > list.get((pos+1) * 2).getBirthTime())) {
				min = list.get((pos+1) * 2).getBirthTime();
				minPos = (pos+1) * 2;
			}

			if(list.get(pos).getBirthTime() <= min) 
				break;
			
				Sim tmp = list.get(pos);
				list.set(pos, list.get(minPos));
				list.set(minPos, tmp);
				pos = minPos;
			
			
		}
		
		return deleteItem;
	}
	
	public void abc(Sim.Sex sex) {
		//System.out.println("loop");

		
		Sim i = this.deleteSim();
		//System.out.println(i.sim_ident);

		if(i!=null && i.isFounder())list2.add(i);
		
		
		if(i!=null && list2!=null) {
			int ind = 0;
			
			for(Sim o : this.list2) {
				if(sex.equals(Sim.Sex.F)) {
					if(o.equals(i.getMother())) {
						ind = 1;
						points.add(new Point(i, i.getBirthTime(),list.size()+list2.size()));
						//System.out.println(i.sim_ident + "added");
						break;
					}
				}else {
					
					if(o.equals(i.getFather())) {
						ind = 1;
						points.add(new Point(i, i.getBirthTime(),list.size()+list2.size()));
						//System.out.println(i.sim_ident + "added");
						
						break;
					}
					
				}
			}

			for(Sim o : this.list) {
				if(sex.equals(Sim.Sex.F)) {
					if(o.equals(i.getMother())) {
						ind = 1;
						points.add(new Point(i, i.getBirthTime(),list.size()+list2.size()));
						//System.out.println(i.sim_ident + "added");
						break;
					}
				}else {

					if(o.equals(i.getFather())) {
						ind = 1;
						points.add(new Point(i, i.getBirthTime(),list.size()+list2.size()));
						//System.out.println(i.sim_ident + "added");

						break;
					}

				}
			}
			
			if(ind == 0 && !i.isFounder()) {
				if(i.getSex().equals(Sim.Sex.M))
				this.list2.add(i.getFather());
				else {
					
					this.list2.add(i.getMother());
				}
			}
		}
		
	}
	
	public void showPoints() {
		System.out.println("Point");
		for(Point i : points) {
			System.out.print(i);
		}
		
		//System.out.println("");
	}

	public void list22() {
		System.out.println("asd");
		for(Sim i : list2) {
			System.out.println(i);
		}

		//System.out.println("");
	}
	
	@Override 
	public String toString() {
		String str = "list1\n";
		for(Sim o : list) {
			str += o + "\n";
		}
		 str += "\n";
		return str;
	}

	public boolean isAllFounder(){
		int a = 0;
		if(list2.isEmpty()) return false;
		for(Sim i : list2){
			if(!i.isFounder()) return false;
		}
		return true;
	}
	
	public void start(ArrayList<Sim> pop, Sim.Sex sex){
	
		for(Sim o : pop) {
			
			if(o!=null)
			this.insertSim(o);
			
		}
		
		while(list.size()>0) {
			//if(this.isAllFounder() && list.isEmpty()) break;
			this.abc(sex);
			if(list.size()==0){
				for(Sim i : list2){
					if(!i.isFounder()) {
						this.insertSim(i);
						list2.remove(i);
						break;
					}
				}

			}


			//this.list22();
			//System.out.println(this);

		}

		//this.showPoints();
	}

}
