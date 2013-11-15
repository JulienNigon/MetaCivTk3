package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Beche;

public class PLAN_Fabriquer_Beche extends PLAN_Fabriquer{

	public PLAN_Fabriquer_Beche(Esprit e, Humain h)
	{
		super(e,h);
		
	}
	
	public String getNom()
	{
		return super.getNom()+"Beche";
	}
	
	public void Activer()
	{
		Objet_Beche beche = new Objet_Beche();
		super.Activer(beche);
	}
}
