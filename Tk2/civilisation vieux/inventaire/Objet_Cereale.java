package civilisation.inventaire;

public class Objet_Cereale extends ObjetInventaire{

	private static final int valeurNutritive = 35;
	static String[] cognitonsLies = {"civilisation.individu.cognitons.beliefs.BELIEF_PorteAliment"};

	public Objet_Cereale()
	{
		
	}
	
	public String toString()
	{
		return "Cereale";
	}
	
	public int getValeurNutritive()
	{
		return valeurNutritive;
	}
	
	public boolean is(String s)
	{
		return super.is(s) || (s == "cereale") || (s == "recolte");
	}
	
	public String getPathToIcon()
	{
		return "icones/bread.png";
	}
	
	public String[] cognitonsLies(){return cognitonsLies;}

}
