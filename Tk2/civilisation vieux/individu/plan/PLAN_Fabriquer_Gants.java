package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Gants;


public class PLAN_Fabriquer_Gants extends PLAN_Fabriquer{

	public PLAN_Fabriquer_Gants(Esprit e, Humain h)
	{
		super(e,h);
		
	}
	
	public String getNom()
	{
		return super.getNom()+"Gants";
	}
	
	public void Activer()
	{
		Objet_Gants gants = new Objet_Gants();
		super.Activer(gants);
	}
}
