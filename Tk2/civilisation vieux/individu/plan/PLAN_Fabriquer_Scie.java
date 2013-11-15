package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Scie;


public class PLAN_Fabriquer_Scie extends PLAN_Fabriquer{

	public PLAN_Fabriquer_Scie(Esprit e, Humain h)
	{
		super(e,h);
		
	}
	
	public String getNom()
	{
		return super.getNom()+"Scie";
	}
	
	public void Activer()
	{
		Objet_Scie scie = new Objet_Scie();
		super.Activer(scie);
	}
}
