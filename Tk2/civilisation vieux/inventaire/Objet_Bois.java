package civilisation.inventaire;

public class Objet_Bois extends ObjetInventaire{

	static String[] cognitonsLies = {};

	
	public Objet_Bois()
	{
		super();
	}
	
	public String toString()
	{
		return "Bois";
	}
	

	
	public boolean is(String s)
	{
		return super.is(s) || (s == "Bois");
	}
	
	public String getPathToIcon()
	{
		return "icones/fruit-grape.png";
	}
	
	public String[] cognitonsLies(){return cognitonsLies;}
}
