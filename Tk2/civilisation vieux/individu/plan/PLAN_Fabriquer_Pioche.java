package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Pioche;


public class PLAN_Fabriquer_Pioche extends PLAN_Fabriquer{

	public PLAN_Fabriquer_Pioche(Esprit e, Humain h)
	{
		super(e,h);
		
	}
	
	public String getNom()
	{
		return super.getNom()+"Pioche";
	}
	
	public void Activer()
	{
		Objet_Pioche pioche = new Objet_Pioche();
		super.Activer(pioche);
	}
}
