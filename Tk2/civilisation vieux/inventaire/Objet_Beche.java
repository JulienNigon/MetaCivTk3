package civilisation.inventaire;

public class Objet_Beche extends Objet_Outil {

	static String[] cognitonsLies = {"civilisation.individu.cognitons.beliefs.BELIEF_Gants"};
	
	public Objet_Beche()
	{
		super();
		this.tauxAide = 20;
		CoutBois = 2;
		CoutMetal = 1;
	}
	
	public String toString()
	{
		return "Beche";
	}
	
	public boolean is(String s)
	{
		return super.is(s) || (s == "beche");
	}
	
	public String[] cognitonsLies()
	{
		return cognitonsLies;
	}
}
