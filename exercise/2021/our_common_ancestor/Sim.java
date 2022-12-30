/*
 * Copyright 2021 Mikl&oacute;s Cs&#369;r&ouml;s.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mikl&oacute;s Cs&#369;r&ouml;s
 */
public class Sim implements Comparable<Sim>
{
    private static int NEXT_SIM_IDX=0;
    
    public static double MIN_MATING_AGE_F = 16.0;
    public static double MIN_MATING_AGE_M = 16.0;
    public static double MAX_MATING_AGE_F = 50.0; // Janet Jackson
    public static double MAX_MATING_AGE_M = 73.0; // Charlie Chaplin
    private final double  fidelite = 0.9;
    
    ArrayList<Sim> children =  new ArrayList<Sim>();
    
    /** 
     * Ordering by death date.
     * 
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Sim o) 
    {
        return Double.compare(this.deathtime,o.deathtime);
    }
    
    public enum Sex {F, M};

    public final int sim_ident;
    private double birthtime;
    private double deathtime;
    private Sim mother;
    private Sim father;
    private Sim mate;
    
    private Sex sex;
    

    
    protected Sim(Sim mother, Sim father, double birth, Sex sex)
    {
    	
        this.mother = mother;
        this.father = father;
        
        this.birthtime = birth;
        this.deathtime = Double.POSITIVE_INFINITY;
        
        this.sex = sex;
        
        this.sim_ident = NEXT_SIM_IDX++;
    }
    
    /**
     * A founding Sim.
     * 
     */
    public Sim(Sex sex)
    {
        this(null, null, 0.0, sex);
    }
    
    /**
     * If this sim is of mating age at the given time
     * 
     * @param time
     * @return true if alive, sexually mature and not too old
     */
    public boolean isMatingAge(double time)
    {
        if (time<getDeathTime())
        {
            double age = time-getBirthTime();
            return 
                    Sex.F.equals(getSex())
                    ? age>=MIN_MATING_AGE_F && age <= MAX_MATING_AGE_F
                    : age>=MIN_MATING_AGE_M && age <= MAX_MATING_AGE_M;
        } else
            return false; // no mating with dead people
    }
    
    /**
     * Test for having a (faithful and alive) partner. 
     * 
     * @param time
     * @return 
     */
    public boolean isInARelationship(double time)
    {
        return mate != null && mate.getDeathTime()>time 
                && mate.getMate()==this;
    }
    
    public void setDeath(double death)
    {
        this.deathtime = death;
    }
    
    public Sex getSex()
    {
        return sex;
    }
    
    public double getBirthTime()
    {
        return birthtime;
    }
    
    public double getDeathTime()
    {
        return deathtime;
    }
    
    public void setDeathTime(double death_time)
    {
        this.deathtime = death_time;
    }
    
    /**
     * 
     * @return null for a founder
     */
    public Sim getMother()
    {
        return mother;
    }
    
    /**
     * 
     * @return null for a founder
     */
    public Sim getFather()
    {
        return father;
    }
    
    public Sim getMate()
    {
        return mate;
    }
    
    public void setMate(Sim mate){
    	this.mate = mate;
    	//Util.s(mate.sim_ident + " couples " + this.sim_ident);
    	}
    
    public boolean isFounder()
    {
        return (mother==null && father==null);
    }
    
    private static String getIdentString(Sim sim)
    {
        return sim==null?"":"sim."+sim.sim_ident+"/"+sim.sex;
    }
    
    /*
     *  returns whether the instance sim has a baby with another sim
     */
    public boolean hasBabyWith(Sim y) {
    	if(this.children.size() == 0) return false;
    	else {
    		for(int i=0; i < this.children.size() ; i++) {
    			for(int j=0; j<y.children.size(); j++) {
    				if(this.children.get(i).equals(y.children.get(j)))
    					return true;
    			}
    		}
    		
    		return false;
    	}
    }
    
    /*
     *  returns whether a instance sim is alive at the time of an event.
     */
    public boolean isAlive(double time) {
    	return time < this.deathtime;
    }
    
    
    /*
     *  returns whether a instance of sim is out of fidelity range.
     */
    public boolean isCheating() {
    	return (new Random().nextDouble() > fidelite);
    }
    
    /*
     *  returns a male sim that isn't in relationship with the instance sim.
     *  @param pop : the queue of population.
     *  @param time : the time of the event.
     *  @return groom : the Sim that will be a new husband of the instance.
     */
    public Sim findAnotherMan(ArrayList<Sim> pop, double time) {
    	Sim groom = null;
    	Sim ex = this.getMate();
    	int i = 0;
    	if(pop!=null) {
    	do {
    		if(i == pop.size()) break;
    		groom = pop.get(i);
    		i++;
    	}while( groom.sex.equals(Sex.F) ||
    		   !groom.isMatingAge(time)||
    		    groom.equals(ex) 	   ||
    		   !groom.isAlive(time)    || 
    		   !(i < pop.size()));
    	}
    	
		return groom;
    	
    }
    /*
     * returns a male sim in mating age who is not in a relationship, and who is in
     * a relationship but trying to cheat on his wife
     * @param pop : the queue of population
     * @param time : the time of event.
     * @return groom : the Sim that will be a new husband of the instance.
     */
    public Sim findAMan(ArrayList<Sim> pop, double time) {
    	Sim groom = null;
    	if(pop == null || pop.size() == 0) return null;
    	
    	int i = 0;
    	do {
    		if(i == pop.size()) break;
    		groom = pop.get(i);
    		i++;
    	}while(  groom.sex.equals(Sex.F) ||
    			groom == null ||
    			!groom.isMatingAge(time) ||
    			!groom.isAlive(time) ||
    			(groom.getMate() != null) &&
    			!groom.isCheating());
    	if(groom.sex.equals(Sex.F)) return null;
		return groom;
    	
    }
    /*
     *  returns randomly chosen Sex
     */
    public static Sex chooseSex() {
    	return (new Random().nextInt(2) == 0)?Sex.F:Sex.M;
    }
    
    @Override
    public String toString()
    {
        return getIdentString(this)+" ["+birthtime+".."+deathtime+", mate "+getIdentString(mate)+"\tmom "+getIdentString(getMother())+"\tdad "+getIdentString(getFather())
        +"]";
    }
}