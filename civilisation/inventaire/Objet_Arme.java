package civilisation.inventaire;

public class Objet_Arme extends ObjetInventaire{

	static String[] cognitonsLies = {"civilisation.individu.cognitons.beliefs.BELIEF_Arme"};
	int puissance;
	int portee;
	
	public Objet_Arme()
	{
		super();
		puissance = 0;
		portee = 0;
	}
	
	public String toString()
	{
		return "Arme";
	}

	public String[] cognitonsLies()
	{
		return cognitonsLies;
	}
	
	public boolean is(String s)
	{
		return super.is(s) || (s == "arme");
	}
	
	public int getPortee()
	{
		return portee;
	}
	
	public int getPuissance()
	{
		return puissance;
	}
	
	public String getPathToIcon()
	{
		return "icones/target.png";
	}
}
