package civilisation.inventaire;

public class Objet_Fer extends ObjetInventaire{

	private static final int valeurNutritive = 0;
	static String[] cognitonsLies = {};

	
	public Objet_Fer()
	{
		super();
	}
	
	public String toString()
	{
		return "Fer";
	}
	
	public int getValeurNutritive()
	{
		return valeurNutritive;
	}
	
	public boolean is(String s)
	{
		return super.is(s) || (s == "fer");
	}
	
	public String getPathToIcon()
	{
		return "icones/fruit-grape.png";
	}
	
	public String[] cognitonsLies(){return cognitonsLies;}
}
