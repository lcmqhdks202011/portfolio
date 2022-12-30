package main;

public abstract class Commande {
	
	protected FType type;
	
	protected Database targetDatabase;
	
	public abstract void execute();  

}
