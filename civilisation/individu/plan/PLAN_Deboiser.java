package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Bois;
import civilisation.inventaire.Objet_Scie;
import civilisation.world.World;
import edu.turtlekit2.kernel.environment.Patch;

public class PLAN_Deboiser extends PLAN_Recolter {

	public PLAN_Deboiser(Esprit e, Humain h) {
		super(e, h);
	}
	
	public String getNom()
	{
		return super.getNom() + "Deboiser";
	}
	
	public void Activer()
	{
		if(h.getInventaire().getSize("Bois") >= 5)
		{
			h.rentrerDeposerRecolte();
		}
		else
		{
			if(h.getInventaire().getSize("Viande") + h.getInventaire().getSize("Baies") <= 3)
			{
				//aller en acheter ou chasser
			}
			else
			{
				if(h.position.getColor() == World.getColorForets())
				{
					recolte = 3;
					Objet_Scie scie = new Objet_Scie();
					if(h.Possede("Scie"))
					{
						recolte += recolte * scie.getTaux()/100;
					}
					super.Activer(null,new Objet_Bois(),recolte);
					h.position.setColor(World.getColorPlaines());
				}
				else
				{
					h.randomHeading();
					//System.out.println(this.nextPatch().getColor() + "     " + this.smellNextPatch("gibier"));
					h.fd(1);
				}
			}
		}
	}

	
}
