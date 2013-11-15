package civilisation.inventaire;

public class Objet_Baies extends ObjetInventaire{

	private static final int valeurNutritive = 40;
	static String[] cognitonsLies = {"civilisation.individu.cognitons.beliefs.BELIEF_PorteAliment"};

	
	public Objet_Baies()
	{
		
	}
	
	public String toString()
	{
		return "Baies";
	}
	
	public int getValeurNutritive()
	{
		return valeurNutritive;
	}
	
	public boolean is(String s)
	{
		return super.is(s) || (s == "baie") || (s == "recolte");
	}
	
	public String getPathToIcon()
	{
		return "icones/fruit-grape.png";
	}
	
	public String[] cognitonsLies(){return cognitonsLies;}
}
