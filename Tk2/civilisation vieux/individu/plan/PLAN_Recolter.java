package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.ObjetInventaire;

public class PLAN_Recolter extends Plan{

	protected int recolte;
	public PLAN_Recolter(Esprit e, Humain h)
	{
		super(e,h);
	}
	
	public String getNom()
	{
		return "Recolte de ";
	}
	
	public void Activer(String ressource,ObjetInventaire objet, float recolte)
	{
		if(ressource != null)
		{
			for(int i = 0; i < (int)recolte; i++)
			{
				h.getInventaire().add(objet);
				h.incrementPatchVariableAt(ressource, -1, 0, 0);
			}
			if (h.smell(ressource) < 0)
			{
				h.incrementPatchVariableAt(ressource, -1*h.smell(ressource), 0, 0);
			}
		}
		else
		{
			for(int i = 0; i < (int)recolte; i++)
			{
				h.getInventaire().add(objet);
			}
		}
			
	}
	
	public int getTempsMax()
	{
		return 100;
	}
}
