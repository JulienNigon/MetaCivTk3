package civilisation.inventaire;

public class Objet_Scie extends Objet_Outil {

	static String[] cognitonsLies = {"civilisation.individu.cognitons.beliefs.BELIEF_Scie"};

	
	public Objet_Scie()
	{
		super();
		this.tauxAide = 20;
		CoutBois = 2;
		CoutMetal = 1;
	}
	
	public String toString()
	{
		return "Scie";
	}
	
	public boolean is(String s)
	{
		return super.is(s) || (s == "scie");
	}
	
	public String[] cognitonsLies()
	{
		return cognitonsLies;
	}
}
