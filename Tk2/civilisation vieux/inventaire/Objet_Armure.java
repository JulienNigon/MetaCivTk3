package civilisation.inventaire;

public class Objet_Armure extends ObjetInventaire {

	int defense;
	public Objet_Armure()
	{
		super();
		defense = 1;
		CoutMetal = 5;
	}
	
	public boolean is(String s)
	{
		return super.is(s) || (s == "armure");
	}
	
	public int getDefense()
	{
		return defense;
	}
}
