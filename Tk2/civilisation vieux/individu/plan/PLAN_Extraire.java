package civilisation.individu.plan;

import java.util.ArrayList;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.ObjetInventaire;
import civilisation.inventaire.Objet_Arme;
import civilisation.inventaire.Objet_Baies;
import civilisation.inventaire.Objet_Or;
import civilisation.inventaire.Objet_Viande;
import edu.turtlekit2.kernel.environment.Patch;

public class PLAN_Extraire extends Plan {

	public PLAN_Extraire(Esprit e, Humain h) {
		super(e,h);
	}
	
	public String getNom(){
		return "Extraire";
	}

	/**
	 * L'agent cherche des filons d'or, si il en trouve, en récolte par extraction
	 */
	
	public void Activer()
	{
		if(h.getInventaire().getSize("or") >= 10)
		{
			h.rentrerDeposerRecolte();
		}
		else
		{		
			if (Math.random()*100 < h.smell("or"))
			{
				if(h.avoirUnePioche())
				{
					h.getInventaire().add(new Objet_Or());
					h.incrementPatchVariableAt("or", -1, 0, 0);
				}
				
				h.getInventaire().add(new Objet_Or());
				h.incrementPatchVariableAt("or", -1, 0, 0);
				
				if (Math.random() < 0.08)
				{
					String plan[] = {"PLAN_Extraire"};
					int poids[] = {1};
					h.getEsprit().ameliorerSkillTalent("TalentExtraire", plan, poids);
				}
			}
			else
			{
				Patch p = h.getPatchWithMaxOf("or", 1);
				h.setHeading(h.towards(p.x , p.y));
				h.fd(1);
			}
			if (h.smell("or") < 0)
			{
				h.incrementPatchVariableAt("or", -1*h.smell("or"), 0, 0);
			}
		}
	}
	

	public int getTempsMax() {
		return 100;
	}
	
	
}
