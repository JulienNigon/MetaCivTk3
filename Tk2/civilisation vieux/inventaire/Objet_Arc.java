package civilisation.inventaire;

import civilisation.individu.cognitons.Belief;

public class Objet_Arc extends Objet_Arme{	
	int taux = 20;
	public Objet_Arc()
	{
		super();
		puissance = 15;
		portee = 5;
		CoutBois = 5;
	}
	
	public int getTaux()
	{
		return taux;
	}
	
	public String toString()
	{
		return "Arc";
	}

	public String[] cognitonsLies()
	{
		return cognitonsLies;
	}
	

}
