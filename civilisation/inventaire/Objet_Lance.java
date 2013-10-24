package civilisation.inventaire;

import civilisation.individu.cognitons.Belief;

public class Objet_Lance extends Objet_Arme{	
	
	public Objet_Lance()
	{
		super();
		puissance = 30;
		portee = 3;
		CoutBois = 5;
		CoutMetal = 1;
	}
	
	public String toString()
	{
		return "Lance";
	}

	public String[] cognitonsLies()
	{
		return cognitonsLies;
	}
	

}
