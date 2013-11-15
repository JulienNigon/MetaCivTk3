package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Bois;
import civilisation.inventaire.Objet_Fer;
import civilisation.inventaire.Objet_Pioche;
import civilisation.inventaire.Objet_Scie;
import civilisation.world.World;
import edu.turtlekit2.kernel.environment.Patch;

public class PLAN_Miner extends PLAN_Recolter {

	public PLAN_Miner(Esprit e, Humain h) {
		super(e, h);
	}
	
	public String getNom()
	{
		return super.getNom() + "Miner";
	}
	
	public void Activer()
	{
		if(h.getInventaire().getSize("Fer") >= 5)
		{
			h.rentrerDeposerRecolte();
		}
		else
		{

			if(h.position.getColor() == World.getColorMontagnes())
			{
				recolte = 3;
				Objet_Pioche pioche = new Objet_Pioche();
				if(h.Possede("Pioche"))
				{
					recolte += recolte * pioche.getTaux()/100;
				}
				super.Activer(null,new Objet_Fer(),recolte);
				h.position.setColor(World.getColorCollines());
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
