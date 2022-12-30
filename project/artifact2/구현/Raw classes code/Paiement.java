public class Paiement {
	
	public void payer(Float montant, Membre m) {
		float actual = m.getFrais();
		m.setFrais( actual - montant);
		if(m.getSuspendu()) {
			releaseSuspension(m);
		}
	}
	
	public void hasPayed(Membre m) {
		//Check si last.Payement du membre est plus d'un mois
		//If over un mois -> suspendre(m)
	}
	
	public void suspendre(Membre m) {
		m.setSuspendu(true);
	}
	
	public void releaseSuspension(Membre m) {
		m.setSuspendu(false);
	}

}