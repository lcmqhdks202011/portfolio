/*
 * IFT2015 H21 Devoir1
 * Author : Laurent Charlebois, Changmin Lee
 */


package lindenmayer;

import java.util.Iterator;

public class Sequence implements Symbol.Seq{
	private int pos=0;
	private Symbol[] elements;
	private int nelem;
	private static final int CAPACITE_DEFAUT = 1;
	
	public Sequence() {this(CAPACITE_DEFAUT);}
	public Sequence(int capacite) { elements = new Symbol[capacite]; nelem=0;}
	
	
	public int size() {
		// TODO Auto-generated method stub
		return nelem;
	}

	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void add(Symbol sym) {
		if(nelem == elements.length) reallocation(2*nelem);
		elements[nelem++] = sym;
		
		

		
	}
	
	
	public void concat(Symbol.Seq seq) {
		if(seq!=null) {
		while(seq.iterator().hasNext()) {
			this.add(seq.iterator().next());
		}
		seq.indexToZero();
		}
	}
	
	public void showInfo() {
		System.out.print("{");
		for(int i=0; i<elements.length;i++)
			System.out.print(elements[i]+ ",");
		System.out.println("}");
	}
	
	private void reallocation(int capacite)
	{
		Symbol[] symbols = new Symbol[capacite];
		for(int i=0; i<nelem ; ++i) 
			symbols[i] = elements[i];
			elements = symbols;
		
	}
	
	


	@Override
	public Iterator<Symbol> iterator() {
		
		class Iter implements Iterator<Symbol>
		{
			
			public boolean hasNext() {
				return pos < nelem;
			}
			
			public Symbol next() { 
				if (this.hasNext()) {
					return elements[pos++]; }
				return null;
				}
			
			
			
		}
		return new Iter();
	}
	
	@Override
	public void indexToZero() {
		// TODO Auto-generated method stub
		pos = 0;
	}
	@Override
	public Symbol get(int i) {
		// TODO Auto-generated method stub
		pos = 0;
		while(this.iterator().hasNext() && pos <= i) {
			
			if(pos==i) {
				pos=0;
				return elements[pos];
			} else {
				pos++;
			}
			
		}
		pos = 0;
		return null;
	}
	

}  
