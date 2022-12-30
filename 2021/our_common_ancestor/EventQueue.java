import java.util.ArrayList;
import java.util.Random;



public class EventQueue {
	
	private ArrayList<Event> events; 
	private ArrayList<Sim> population;
	private ArrayList<Point> pop2;
	int counter = 0;
	Coalescence male;
	Coalescence female;
	
	/*
	 * @param events : A queue of events in a min heap form
	 * @param population : A queue of population alive in a min heap form
	 */
	public EventQueue() {
		events = new ArrayList<>();
		population = new ArrayList<Sim>();
		pop2 = new ArrayList<Point>();
	}
	
	/*
	 * @param E : Event generated
	 * 
	 * insertEvent : adds an event to the queue of events.
	 */
	public void insertEvent(Event E) {
		
		events.add(E);
		int p = events.size() - 1;
		
		while(p > 0 && events.get(p / 2).time > events.get(p).time) {
			Event tmp = events.get(p/2);
			events.set(p/2, events.get(p));
			events.set(p, tmp);
			
			p /= 2;
		}
		
	}
	
	/*
	 * deleteEvent : removes the event that has the least occurred time
	 * 				 from the queue of events and returns the event that
	 * 				 is supposed to be executed. 
	 */
	public Event deleteEvent() {
		
		// return null if the queue is empty.
		if(events.isEmpty()) {
			return null;
		}
		
		// if not empty, reorganize the min heap
		Event deleteItem = events.get(0);
		
		events.set(0, events.get(events.size()-1));
		events.remove(events.size() - 1);
		
		int pos = 0;
		while((pos * 2) + 1 < events.size()) {
			double min = events.get(pos * 2 + 1).time;
			int minPos = pos * 2 + 1;
			
			if((((pos+1) * 2) < events.size() && min > events.get((pos+1) * 2).time)) {
				min = events.get((pos+1) * 2).time;
				minPos = (pos+1) * 2;
			}
			
			if(events.get(pos).time < min) 
				break;
			
				Event tmp = events.get(pos);
				events.set(pos, events.get(minPos));
				events.set(minPos, tmp);
				pos = minPos;
			
			
		}
		
		return deleteItem;
		
	}
	
	/* add2Population
	 * Upon a birth Event, add a sim in the list (population)
	 * and reorganize to keep the min heap form.
	 * @param x : a Sim to add to the list.
	 */
	public void add2Population(Sim x) {
		population.add(x);
		
		int  p = population.size() - 1;
		
		while (p > 0 && 
				population.get(p).getDeathTime() < population.get(p/2).getDeathTime())
		{
			Sim tmp = population.get(p/2);
			population.set(p/2, population.get(p));
			population.set(p, tmp);
			
			p /= 2;
		}
		
			
		
	}
	
	/*
	 *  deletePopulation
	 *  Upon a death event, removes the sim that is a subject of the death event,
	 *  and reorganize the min heap form of the queue, and returns the sim removed from
	 *  the list.
	 *  @return deleteItem : the sim who had a death event.
	 */
	public Sim deletePopulation() {
		if(population.size() - 1 < 1) {
			return null;
		}
		
		Sim deleteItem = population.get(0);
		
		population.set(0, population.get(population.size()-1));
		population.remove(population.size() - 1);
		
		int pos = 0;
		while((pos * 2 + 1) < population.size()) {
			double min = population.get(pos * 2 + 1).getDeathTime();
			int minPos = pos * 2 + 1;
			
			if((((pos+1) * 2) < population.size() && min > population.get((pos+1) * 2).getDeathTime())) {
				min = population.get((pos+1) * 2).getDeathTime();
				minPos = (pos+1) * 2;
			}
			
			if(population.get(pos).getDeathTime() < min) 
				break;
			
				Sim tmp = population.get(pos);
				population.set(pos, population.get(minPos));
				population.set(minPos, tmp);
				pos = minPos;
			
		}
		
		return deleteItem;
	}
	
	/*
	 * executeEvent
	 * executes an event according to its type (Birth, Death, Reproduction)
	 * @param E : the event to execute
	 * @param n : the number of sims to be maintained
	 */
	public void executeEvent(Event E, int n) {
		Sim x = E.subject;

		//Util.s(population.isEmpty());
		switch (E.type) {
			case "Birth":
				//n1
				double D = new AgeModel().randomAge(new Random());
				Event death = new Event(x, E.time + D, "Death");

				x.setDeathTime(E.time+D);
				this.insertEvent(death);
				//n2
				if(x.getSex() == Sim.Sex.F) {
					double A = new Random().nextDouble() + new Random().nextInt((int) (Sim.MAX_MATING_AGE_F - Sim.MIN_MATING_AGE_F)) +
							   Sim.MIN_MATING_AGE_F;
							this.insertEvent(new Event(x, E.time + A, "Reproduction"));
				}
				//n3
				this.add2Population(x);
				if(population.size() % 25 == 0)
				pop2.add(new Point(x, E.time, population.size()));
				break;
				
			case "Death":
				this.deletePopulation();
				//Util.s(population.size());
				
				break;
				
			case "Reproduction":
				//Util.s(population.isEmpty());
				//r1
				if(!x.isAlive(E.time)) break;
				Sim y = x.getMate();
				Sim z = null;
				//r2
				if(x.isMatingAge(E.time)) {
					//select partner
					if(x.hasBabyWith(y) 
							&& y.isAlive(E.time)
							&& !y.isCheating()) {
						
						if(!x.isCheating()) {
						//Util.s("not Cheating!");
						z = x.getMate();
						}
						else {
							//Util.s("Cheating!");
							z = x.findAnotherMan(population, E.time);
						}
						
					} else {
						z = x.findAMan(population, E.time);
					}
					
					if(z != null) {
					Sim bebe = new Sim(x, z, E.time, Sim.chooseSex());
					this.insertEvent(new Event(bebe, E.time, "Birth"));
					x.children.add(bebe);
					z.children.add(bebe);
					z.setMate(x);
					x.setMate(z);
					//Util.s("Number of children : " + x.children.size());
					}
					
					//double f = 2.0;
					//if(population.size() < n) f+=2;
					//else f-=2;
					//double r = f/new AgeModel().expectedParenthoodSpan(Sim.MIN_MATING_AGE_F, Sim.MAX_MATING_AGE_F);
					if(x.children.size() <3 && population.size() < n) {
					double A = 0.1;
							//AgeModel.randomWaitingTime(new Random(), r);
					this.insertEvent(new Event(x, E.time + A, "Reproduction"));
					}
				}
				break;
				
				
				
				
				
		}
	}
	
	public void printPop() {
		for(Sim i : population) {
			System.out.println(i);
		}
		System.out.println("");
	}
	

	
	@Override
	public String toString() {
		String str = "";
		for(int p = 0; p < this.events.size() ; p++) {
			str +=  
					"\n\nEvent No : " + p + 
					"\nSubject : " + this.events.get(p).subject + 
					"\nEvent :" + this.events.get(p).type +
					"\nTime : " + this.events.get(p).time;
					
		}
		System.out.println(str);

		return str;
	}
	
	/*
	 * simulate
	 * @param n : the number of sims to simulate with
	 * @param Tmax : the duration of simulation
	 */
	void simulate(int n, double Tmax) {
		//Util.s(population.isEmpty());
		for(int i=0; i<n; i++) {
			Sim fondateur = new Sim(Sim.chooseSex());
			//System.out.println(fondateur);
			Event E = new Event(fondateur, 0.0, "Birth");
			//Util.s(population.isEmpty());
			this.insertEvent(E);
			//this.toString();
		}
		
		
		
		while(!this.events.isEmpty()) {
			Event E = this.deleteEvent();
			
			//System.out.println(E.time);
			if(E.time > Tmax) break;
			if(E.subject.isAlive(E.time));
			{
				
				this.executeEvent(E, n);
				
			}
			//
			//Util.s(population.isEmpty());
			
		}
		
		female = new Coalescence(population, Sim.Sex.F);
		male = new Coalescence(population, Sim.Sex.M);
		
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue EQ = new EventQueue();
		EQ.simulate(5000, 20000);
		
		Util.s(EQ.population.isEmpty());
		AwtCanvasControl awtControlDemo = new AwtCanvasControl();
		awtControlDemo.showControl(EQ.male, EQ.female, EQ.pop2, 20000, 5000);


	}

}
