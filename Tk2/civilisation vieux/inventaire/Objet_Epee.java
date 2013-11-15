package civilisation.inventaire;

public class Objet_Epee extends Objet_Arme {

	public Objet_Epee()
	{
		super();
		puissance = 45;
		portee = 1;
		CoutMetal = 3;
		CoutBois = 2;
	}
	
	public String toString()
	{
		return "Epée";
	}
	
	public String[] cognitonsLies()
	{
		return cognitonsLies;
	}
}
