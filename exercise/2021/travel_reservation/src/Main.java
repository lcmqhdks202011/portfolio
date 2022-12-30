package main;

public class Main {
	
	
	public static void run() {
		new MainMenu(DBListBuilder.dbBuild(GUIStrings.cb_type));
	}
	
	public static void main(String[] args) {
		run();
	}
}
