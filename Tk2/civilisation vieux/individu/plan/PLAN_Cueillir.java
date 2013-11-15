package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Baies;
import civilisation.inventaire.Objet_Outil;
import civilisation.inventaire.Objet_Viande;
import edu.turtlekit2.kernel.environment.Patch;

public class PLAN_Cueillir extends PLAN_Recolter {

	public PLAN_Cueillir(Esprit e, Humain h) {
		super(e,h);
	}
	
	public String getNom(){
		return super.getNom()+"Baies";
	}

	/**
	 * L'agent cherche des baies et, si il en trouve, en rcolte
	 */
	
	public void Activer()
	{
		if(h.getInventaire().getSize("Baies") >= 8)
		{
			h.rentrerDeposerRecolte(); // Si l'agent a ramass le quota de baies, il rentre
		}
		else
		{		
			if (Math.random()*100 < h.smell("baies"))
			{
				float recolte = 2;
				if(h.Possede("gants"))
				{
					Objet_Outil gants = (Objet_Outil) h.getInventaire().get("gants", 0);
					recolte += (recolte*gants.getTaux())/100;
				}
				super.Activer("baies",new Objet_Baies(),recolte);
				if (Math.random() < 0.08)
				{
					String plan[] = {"PLAN_Cueillir"};
					int poids[] = {1};
					h.getEsprit().ameliorerSkillTalent("TalentCueillir", plan, poids);
				}
			}
			else
			{
				//System.out.println("heading  " + getHeadingToMaxOf("gibier", 1, true) +"    "+ this.getPatchWithMaxOf("gibier", 1).smell("gibier"));
				Patch p = h.getPatchWithMaxOf("baies", 1);
				h.setHeading(h.towards(p.x , p.y));
				//System.out.println(this.nextPatch().getColor() + "     " + this.smellNextPatch("gibier"));
				h.fd(1);
			}
		}
	}
	

	public int getTempsMax() {
		return 100;
	}
	
	
}
