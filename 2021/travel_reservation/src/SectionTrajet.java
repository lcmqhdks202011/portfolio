package main;

import java.util.ArrayList;

public class SectionTrajet extends Section {
	
	SectionTrajet(int nbRangee, ClasseType ctype, DispositionType dtype, Entite parent){
		super(parent);
		this.classe = ctype;
		this.disposition = dtype;
		int nbColumn = Disposition.getInstance().getNumberColumn(dtype);
		this.units = createSeatsList(nbRangee, nbColumn);
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
	
	private ArrayList<Unit> createSeatsList(int nbRangee, int nbColumn){
		ArrayList<Unit> res = new ArrayList<Unit>();
		for(int i=0;i<nbColumn;i++) {
			for(int j=0;j<nbRangee;j++) {
				String cls = Classe.getClasseLetter(classe);
				String unitId = cls + (char)(65 + i) + j;
				res.add(new Siege(unitId));
			}
		}
		return res;
	}
	
	@Override
	public String getSectionId() {
		return Classe.getClasseLetter(classe) + Disposition.getInstance().getDispositionLetter(disposition);
	}

}
