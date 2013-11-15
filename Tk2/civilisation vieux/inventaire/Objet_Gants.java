package civilisation.inventaire;

public class Objet_Gants extends Objet_Outil {

	static String[] cognitonsLies = {"civilisation.individu.cognitons.beliefs.BELIEF_Gants"};

	
	public Objet_Gants()
	{
		super();
		this.tauxAide = 20;
		CoutMetal = 1;
		CoutBois = 1;
	}
	
	public String toString()
	{
		return "Gants";
	}
	
	public boolean is(String s)
	{
		return super.is(s) || (s == "gants");
	}
	public String[] cognitonsLies()
	{
		return cognitonsLies;
	}
}
