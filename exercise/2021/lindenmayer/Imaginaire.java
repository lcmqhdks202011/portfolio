/*
 * IFT2015 H21 Devoir1
 * Author : Laurent Charlebois, Changmin Lee
 */

package lindenmayer;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.PrintStream;
import java.lang.Math;
import java.util.Stack;


public class Imaginaire implements Turtle {
	
	private final PrintStream print;
		
	double delta;
	double step;
	
	Stack<State> states;
	State currentPos;
	
	double maxY, minY;
	double maxX, minX;
	
	class State {
		
		double x;
		double y;
		double theta;
		
		private State() {
		};
		
		private State(double x, double y, double theta) {
			this.x = x;
			this.y = y;
			this.theta = theta;
		}
		
	}
	
	
	public Imaginaire(Turtle turtle) {
		this(turtle, System.out);
		states = new Stack<State>();
		currentPos = new State();
		
	}
	
	
	public Imaginaire(Turtle turtle, PrintStream print) {
		this.print = print;
		this.Header();
	}
	
	public void plot(LSystem lsystem, int n)
    {
        
        Symbol.Seq axiom = lsystem.getAxiom();
        while (axiom.iterator().hasNext())
        {
            Symbol s = axiom.iterator().next();
            lsystem.tell(this, s, n);
        }
        print.println("stroke");


        Rectangle2D bbox = lsystem.getBoundingBox(this);

        print.println("%%Trailer");
        print.println("%%BoundingBox: "
            +Integer.toString((int)bbox.getMinX())
                    +" "+Integer.toString((int)bbox.getMinY())
                    +" "+Integer.toString((int)bbox.getMaxX())
                    +" "+Integer.toString((int)bbox.getMaxY()));
        print.println("%%EOF");
    }
	
	private void Header()
    {
        print.println("%!PS-Adobe-3.0 EPSF-3.0");
        print.println("%%Title: Devoir1 - IFT2015");
        print.println("%%Creator: Laurent Charlebois, Changmin Lee");
        print.println("%%BoundingBox: (atend)");  
        print.println("0.5 setlinewidth");
    }
	
	private static final String POSITION_FORMAT = "%.1f %.1f";
	
	private void print2D() {
		Point2D pos = this.getPosition();
		print.printf(POSITION_FORMAT, pos.getX(), pos.getY());
	}
	
	


	@Override
	public void draw() {
		
		currentPos.x += step * Math.cos(Math.toRadians(currentPos.theta));
		currentPos.y += step * Math.sin(Math.toRadians(currentPos.theta));
		
		if(maxX <= currentPos.x) maxX = currentPos.x;
		if(maxY <= currentPos.y) maxY = currentPos.y;
		if(minX > currentPos.x) minX = currentPos.x;
		if(minY > currentPos.y) minY = currentPos.y;
		
		print2D();
		print.println(" lineto ");
		

	}

	@Override
	public void move() {
		
		currentPos.x += step * Math.cos(Math.toRadians(currentPos.theta));
		currentPos.y += step * Math.sin(Math.toRadians(currentPos.theta));
		
		if(maxX <= currentPos.x) maxX = currentPos.x;
		if(maxY <= currentPos.y) maxY = currentPos.y;
		if(minX > currentPos.y) minX = currentPos.y;
		if(minY > currentPos.y) minY = currentPos.y;
		
		print2D();
		print.println(" moveto ");
		
	}

	@Override
	public void turnR() {
		
		currentPos.theta -= delta;
		
	}

	@Override
	public void turnL() {
		currentPos.theta += delta;
	}

	@Override
	public void push() {
		State pos = new State();
        pos.x = currentPos.x;
        pos.y = currentPos.y;
        pos.theta = currentPos.theta;
        states.push(pos);
        print.println("currentpoint stroke newpath moveto ");
		
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub
		currentPos = states.pop();
		print.print("stroke ");
		print2D();
		print.println(" newpath moveto ");
		
	}

	@Override
	public void stay() {}

	@Override
	public void init(Point2D pos, double angle_deg) {
		currentPos = new State(pos.getX(), pos.getY(), angle_deg);
		print2D();
		print.println(" newpath moveto ");
		this.minX = pos.getX();
		this.maxX = pos.getX();
		this.minY = pos.getY();
		this.maxY = pos.getY();
		

	}

	@Override
	public Point2D getPosition() {
		double x = currentPos.x;
		double y = currentPos.y;
		Point2D position = new Point2D.Double(x,y);
		return position;
	}

	@Override
	public double getAngle() {
		return currentPos.theta;
	}

	@Override
	public void setUnits(double step, double delta) {
		this.step = step;
		this.delta = delta;
		
	}
	
	public double getMaxX() {
		return maxX;
	}
	
	public double getMaxY() {
		return maxY;
	}
	
	public double getMinX() {
		return minX;
	}
	
	public double getMinY() {
		return minY;
	}
	
 
}
