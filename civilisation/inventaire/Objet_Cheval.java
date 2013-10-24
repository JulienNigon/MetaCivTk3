package civilisation.inventaire;

import civilisation.individu.cognitons.Belief;

public class Objet_Cheval extends ObjetInventaire{	

	static String[] cognitonsLies = {"civilisation.individu.cognitons.beliefs.BELIEF_Cheval"};

	
	public Objet_Cheval()
	{
		super();
		
	}
	
	public String toString()
	{
		return "Cheval";
	}

	public String[] cognitonsLies()
	{
		return cognitonsLies;
	}
	

}
