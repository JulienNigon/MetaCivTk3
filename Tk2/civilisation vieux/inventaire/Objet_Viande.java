package civilisation.inventaire;

public class Objet_Viande extends ObjetInventaire{

	private static final int valeurNutritive = 55;
	static String[] cognitonsLies = {"civilisation.individu.cognitons.beliefs.BELIEF_PorteAliment"};

	public Objet_Viande()
	{
		
	}
	
	public String toString()
	{
		return "Viande";
	}
	
	public int getValeurNutritive()
	{
		return valeurNutritive;
	}
	
	public boolean is(String s)
	{
		return super.is(s) || (s == "viande") || (s == "recolte");
	}
	
	public String getPathToIcon()
	{
		return "icones/hamburger.png";
	}
	
	public String[] cognitonsLies(){return cognitonsLies;}
}
