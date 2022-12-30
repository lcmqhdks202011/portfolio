/*
 * IFT2015 H21 Devoir1
 * Author : Laurent Charlebois, Changmin Lee
 */



package lindenmayer;

import java.util.Iterator;

/**
 * Symbol in an L-system's alphabet. 
 * 
 * @author Mikl&oacute;s Cs&#369;r&ouml;s
 */
public class Symbol 
{
    char value;
    String action;
    
    public Symbol(char c)
    {
        this.value = c;
    }
    
    @Override
    public String toString()
    {
        return Character.toString(value);
    }
    
    /**
     * Common interface to a string of symbols. 
     * 
     */
    public interface Seq extends Iterable<Symbol>
    {
    	int size();
    	boolean isEmpty();
    	void add(Symbol sym);
    	Iterator<Symbol> iterator();
    	void showInfo();
    	void indexToZero();
    	void concat(Symbol.Seq seq);
    	Symbol get(int i);
    	
    	
    }
    

	
    
public static void main(String [] args) {
	
	Symbol.Seq myc = new Sequence();
	
	myc.add(new Symbol('c'));
	myc.add(new Symbol('d'));
	myc.add(new Symbol('+'));
	myc.add(new Symbol('f'));
	//myc.add(new Symbol('e'));
	//myc.add(new Symbol('e'));
	//myc.add(new Symbol('e'));
	//myc.add(new Symbol('e'));
	System.out.println(myc);
	System.out.println(myc);
	System.out.println(myc.iterator().next());
	System.out.println(myc.iterator().next());
	System.out.println(myc.iterator().next());
	System.out.println(myc.iterator().next());
	//System.out.println(myc.iterator().next());
	myc.showInfo();
	myc.showInfo();
    
    	
    }
    
    
    
}

