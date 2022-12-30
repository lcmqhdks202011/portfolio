package main;

import java.util.ArrayList;

public class SectionItineraire extends Section {
	
	
	SectionItineraire(int nbCabines, ClasseType ctype, Entite parent){
		super(parent);
		this.classe = ctype;
		this.units = createSeatsList(nbCabines);
		
	}


	@Override
	public double getPrix() {
		CompagnieAerienne c = (CompagnieAerienne)this.parent.information.get("Company");
		double tarif = c.getTarif();
		return Classe.getTaux(classe) * tarif;
	}

	@Override
	public int getTakenSeats() {
		int i = 0;
		for(Unit u : units) {
			if(!u.isReservable()) i++;
		}
		
		return i;
	}

	@Override
	public int getMaxSeats() {
		return units.size();
	}
	
	private ArrayList<Unit> createSeatsList(int nbCabines){
		ArrayList<Unit> res = new ArrayList<Unit>();
		for(int i=0;i<nbCabines;i++) {
			
			String cls = Classe.getClasseLetter(classe);
			String unitID = cls + i;
			res.add(new Cabine(unitID));
		}
		return res;
	}
	
	@Override
	public String getSectionId() {
		return Classe.getClasseLetter(classe);
	}

}
