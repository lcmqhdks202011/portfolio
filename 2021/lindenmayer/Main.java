/*
 * IFT2015 H21 Devoir1
 * Author : Laurent Charlebois, Changmin Lee
 */

package lindenmayer;

import java.io.PrintStream;


public class Main {

	Main(LSystem lsystem, Imaginaire turtle)
	{
		this.lsystem = lsystem;
		this.turtle = turtle;
	}
	
	private LSystem lsystem;
	private Imaginaire turtle;
	private PrintStream out = System.out;
	
	private Main() {}
	
	private void init(String [] args) throws Exception
	{
		int index = 0;
		while (index < args.length && args[index].startsWith("-")) {
			
			String key = args[index++];
			if(index == args.length)
				throw new IllegalArgumentException("Missing option" + key);
			
				if("-o".equals(key)) 
				{
					String output = args[index++];
					out = "=".equals(output)? System.out : new PrintStream(output);
				} else
				{
				throw new IllegalArgumentException(key + " is not recognized");
				
				}
			}
			
			if(index == args.length) 
				throw new IllegalArgumentException("JSON filename error");
			
			if(index == args.length) 
				throw new IllegalArgumentException("Number of repetition error");
			
			int n = Integer.parseInt(args[index++]);
			
			this.lsystem = new LSystem();
			this.turtle = new Imaginaire(turtle);
			
			LSystem.readJSONFile(args[0], lsystem, turtle);
			
			turtle.plot(lsystem, n);
		}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Main M = new Main();
		M.init(args);
	}

}
