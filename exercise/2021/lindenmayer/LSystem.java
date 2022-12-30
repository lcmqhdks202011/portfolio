/*
 * IFT2015 H21 Devoir1
 * Author : Laurent Charlebois, Changmin Lee
 * Filename : LSystem.java
 * Description : This class implements AbstractLSystem
 * 				to generate a L-System.
 * 
 */

package lindenmayer;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lindenmayer.Symbol.Seq;

import org.json.*;


public class LSystem extends AbstractLSystem {
	
	
	Symbol.Seq axiom = new Sequence();
	static Map<Character, Symbol> symbols = new HashMap<Character, Symbol>();
	Map<Symbol, List<Seq>> rules = new HashMap<Symbol, List<Seq>>();
	ArrayList<Integer> randomTable;
	
	public LSystem () {}

	@Override
	public Symbol addSymbol(char sym) {
		// TODO Auto-generated method stub
		Symbol symbol = new Symbol(sym);
		symbols.put(sym, symbol);
		return symbol;
	}

	@Override
	public void addRule(Symbol sym, String expansion) {
		// TODO Auto-generated method stub
    	Symbol.Seq receivedRule = new Sequence();
    	List<Seq> multipleRules = new ArrayList<Seq>();
    	for(int i = 0 ; i< expansion.length(); i++) {
    		char letter = expansion.charAt(i);
    		
    		receivedRule.add(symbols.get(letter));
    		
    	}
    	if (!rules.containsKey(sym)) {
    		multipleRules.add(receivedRule);
    		rules.put(sym, multipleRules);
    	} else {
    		rules.get(sym).add(receivedRule);
    	}
		
		
	}

	@Override
	public void setAction(Symbol sym, String action) {
		// TODO Auto-generated method stub
		sym.action = action;
		
	}

	@Override
	public void setAxiom(String str) {
		Symbol.Seq receivedSymbol = new Sequence();
		// TODO Auto-generated method stub
		for(int i=0; i <str.length(); i++) {
			receivedSymbol.add(symbols.get(str.charAt(i)));
		}
		axiom = receivedSymbol;
		
	}

	@Override
	public Seq getAxiom() {

		return axiom;
	}

	@Override
	public Symbol.Seq rewrite(Symbol sym) {
		
		if(rules.get(sym)==null || rules.get(sym).isEmpty()) 
		return null;
		
		else {
			
			int index = randomTable.iterator().next();
			Symbol.Seq sequence = getRule(sym, index);
			return sequence;
			
		}
		
	}
	
	private Symbol.Seq getRule(Symbol sym, int index) {
		
		Symbol.Seq seq = new Sequence();
		Iterator<Symbol> itr = rules.get(sym).get(index).iterator();
		
		while(itr.hasNext()) 
			seq.add(itr.next());
			
		rules.get(sym).get(index).indexToZero();
		return seq;
	}

	@Override
	public void tell(Turtle turtle, Symbol sym) {
	
		switch (sym.action) {
        case "draw":
            turtle.draw();
            break;
        case "move":
            turtle.move();
            break;
        case "push":
            turtle.push();
            break;
        case "pop":
            turtle.pop();
            break;
        case "turnR":
            turtle.turnR();
            break;
        case "turnL":
            turtle.turnL();
            break;
		}
	}

	@Override
	public Symbol.Seq applyRules(Symbol.Seq seq, int n) {
		// TODO Auto-generated method stub
		if(randomTable!=null) this.setRandomTable(seq.iterator().next(), n);
		Symbol.Seq result = new Sequence();
		recursivelyApply(seq, result, n);
		return result;
	}

	public void recursivelyApply (Seq seq1, Seq seq2, int n) {
		
		if(n==0) {
			seq2.concat(seq1);
		}
		
		else {
			if(seq1!=null && seq1.iterator().hasNext())
			{
				while(seq1.iterator().hasNext()) {
				Symbol sym = seq1.iterator().next();
				Seq newSeq = rewrite(sym);
				recursivelyApply(newSeq, seq2, n-1);
				}
			} else {
				
				recursivelyApply(seq1, seq2, n-1);
				
			}
		}
	}
	
	
	@Override
	public Rectangle2D tell(Turtle turtle, Symbol sym, int rounds) {
		// TODO Auto-generated method stub
		
		this.setRandomTable(sym, rounds);
		
		
		Seq seq = new Sequence();
		seq.add(sym);
		
		recursion(turtle, sym, rounds);
		Rectangle2D box = getBoundingBox((Imaginaire)turtle);
		return box;
	}
	
	
	
	public void recursion(Turtle turtle, Symbol sym, int rounds) {

		// TODO Auto-generated method stub
		if(rounds==0) {
			tell(turtle, sym);
		} else {
			Symbol.Seq itr = rewrite(sym);
			if(itr != null) {
				while (itr.iterator().hasNext()) {
					recursion(turtle, itr.iterator().next(), rounds - 1);
				}
			} else {
				recursion(turtle, sym, rounds - 1);
			}
		}
		
		
	}
    public Rectangle2D getBoundingBox(Imaginaire turtle) {
        double minX = 0;
        double maxX = 0;
        double minY = 0;
        double maxY = 0;        
       
            minX = turtle.getMinX()/2;
            maxX = turtle.getMaxX();
            minY = turtle.getMinY();
            maxY = turtle.getMaxY()*1.2;
            Rectangle2D bbox = new Rectangle2D.Double(minX, minY, maxX, maxY); 

        return bbox;
    }
	
	public static void readJSONFile(String file, LSystem S, Turtle T) 
			throws java.io.IOException {
		
		JSONObject input = new JSONObject
							(new JSONTokener
								(new java.io.FileReader(file)));
		
        JSONArray alphabet = input.getJSONArray("alphabet");
        String axiom = input.getString("axiom");
        JSONObject rules = input.getJSONObject("rules");
        JSONObject actions = input.getJSONObject("actions");
        
        JSONObject params = input.getJSONObject("parameters");
        JSONArray start = params.getJSONArray("start");
        T.init(new Point2D.Double(start.getDouble(0), 
        						  start.getDouble(1)), 
        					      start.getDouble(2));
        
        double step = params.getDouble("step");
        double angle = params.getDouble("angle");
        T.setUnits(step, angle);
        
        
        
        for (int i=0; i<alphabet.length(); i++){
            String letter = alphabet.getString(i);
            Symbol sym = S.addSymbol(letter.charAt(0));
            
            S.setAction(sym, actions.getString(letter));
            
        }
        
        for (int i=0; i<alphabet.length(); i++){
        	String letter = alphabet.getString(i);
        	Symbol sym = symbols.get(letter.charAt(0));
        	if(rules.has(letter)) {
    		
        	JSONArray rules1 = rules.getJSONArray(letter);
        		for(int j=0; j<rules1.length(); j++) {
        			S.addRule(sym, rules1.getString(j));
        		}
        		
        	}
        }
        	
        	
        S.setAxiom(axiom);
		
	}
	
	public void setRandomTable(Symbol sym, int n) {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int bound_size = rules.get(sym).size();
		randomTable = new ArrayList<Integer>();
		
		while(randomTable.size()!=n)
		randomTable.add(rand.nextInt(bound_size));
	}

}
