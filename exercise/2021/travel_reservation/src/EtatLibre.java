package main;

public class EtatLibre extends Etat {

	@Override
	public boolean isReservable() {
		return false;
	}

}
