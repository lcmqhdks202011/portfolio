package main;

public class Refresh extends Commande {

	private Viewer viewer;
			
	public Refresh(Database db, Viewer v) {
		this.targetDatabase = db;
		this.viewer = v;
		execute();
	}

	@Override
	public void execute() {
		this.targetDatabase.notifyViewer(viewer);
	}

}
