package civilisation.inventaire;

public class Objet_Or extends ObjetInventaire{

	static String[] cognitonsLies = {"civilisation.individu.cognitons.beliefs.BELIEF_PorteOr"};

	public Objet_Or()
	{
		super();
	}
	
	public String toString()
	{
		return "Or";
	}
	
	public boolean is(String s)
	{
		return super.is(s) || (s == "or") || (s == "recolte");
	}
	
	public String getPathToIcon()
	{
		return "icones/money.png";
	}
	
	public String[] cognitonsLies(){return cognitonsLies;}
}
