package main;

public class EtatReserve extends Etat {
	
	private Reservation reservation;
	
	EtatReserve(Reservation r){
		this.reservation = r;
	}

	@Override
	public boolean isReservable() {
		return false;
	}
	
	public Reservation getReservation() {
		return this.reservation;
	}

}
