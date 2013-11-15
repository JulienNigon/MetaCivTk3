package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Arc;


public class PLAN_Fabriquer_Arc extends PLAN_Fabriquer{

	public PLAN_Fabriquer_Arc(Esprit e, Humain h)
	{
		super(e,h);
		
	}
	
	public String getNom()
	{
		return super.getNom()+"Arc";
	}
	
	public void Activer()
	{
		Objet_Arc arc = new Objet_Arc();
		super.Activer(arc);
	}
}
