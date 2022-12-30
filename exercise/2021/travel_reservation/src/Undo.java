package main;

public class Undo extends Commande {
	
	Undo(Database db){
		this.targetDatabase = db;
		execute();
	}

	@Override
	public void execute() {
		
		this.targetDatabase.restore();

	}

}
