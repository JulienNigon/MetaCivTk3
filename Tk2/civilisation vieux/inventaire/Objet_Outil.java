package civilisation.inventaire;

public class Objet_Outil extends ObjetInventaire{

	static int tauxAide;
	
	public Objet_Outil()
	{
		super();
		int tauxAide = 0;
	}
	
	public String toString()
	{
		return "Outil";
	}
	
	public boolean is(String s)
	{
		return super.is(s) || (s == "outil");
	}
	
	public int getTaux()
	{
		return this.tauxAide;
	}
}
