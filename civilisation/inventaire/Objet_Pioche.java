package civilisation.inventaire;

public class Objet_Pioche extends Objet_Outil{

	static String[] cognitonsLies = {"civilisation.individu.cognitons.beliefs.BELIEF_Pioche"};

	public Objet_Pioche()
	{
		super();
		CoutBois = 1;
		CoutMetal = 2;
	}
	
	public String toString()
	{
		return "Pioche";
	}
	
	public boolean is(String s)
	{
		return super.is(s) || (s == "pioche");
	}
	
	public String[] cognitonsLies(){return cognitonsLies;}
}
