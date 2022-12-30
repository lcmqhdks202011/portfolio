package main;

public class MementoEntite {
	
	private CommandType type;
	private Entite entite;
	private int index;
	
	MementoEntite(CommandType type, Entite entite, int index){
		this.type = type;
		this.entite = entite;
		this.index = index;
	}
	
	public CommandType getCommandType() {
		return this.type;
	}
	
	public Entite getEntite() {
		return this.entite;
	}
	
	public int getIndex() {
		return this.index;
	}

}
