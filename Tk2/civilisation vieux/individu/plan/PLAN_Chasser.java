package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Viande;
import edu.turtlekit2.kernel.environment.Patch;

public class PLAN_Chasser extends PLAN_Recolter {

	public PLAN_Chasser(Esprit e, Humain h) {
		super(e,h);
	}

	public String getNom(){
		return "Chasser";
	}
	
	public void Activer()
	{
		if(h.getInventaire().getSize("Viande") >= 6)
		{
			h.rentrerDeposerRecolte();
		}
		else
		{
			if (Math.random()*100 < h.smell("gibier")) //Test simple pour dŽterminer si une proie est trouvŽe
			{
				double resultat = Math.random();
				if (resultat <= 0.01) // Accident de chasse
				{
					//h.die();  //Desactive pour rendre l'execution plus lisible
				}
				else if (resultat <= 0.90) // Petit gibier
				{
					float recolte = 2;
					if(h.Possede("arc"))
					{
						recolte += recolte*20/100;
					}
					super.Activer("gibier",new Objet_Viande(),recolte);
					h.addInfluence(3);
				}
				else // Gros gibier
				{
					float recolte = 3;
					if(h.Possede("arc"))
					{
						recolte += recolte*20/100;
					}
					super.Activer("gibier",new Objet_Viande(),recolte);
					//Le chasseur est douŽ : il progresse
					String plan[] = {"PLAN_Chasser"};
					int poids[] = {1};
					h.getEsprit().ameliorerSkillTalent("TalentChasse", plan, poids);
					h.addInfluence(2);
				}
				}
				if (h.smell("gibier") < 0)
				{
					h.incrementPatchVariableAt("gibier", -1*h.smell("gibier"), 0, 0);
				}
	
			else
			{
				Patch p = h.getPatchWithMaxOf("gibier", 1);
				h.setHeading(h.towards(p.x , p.y));
				h.fd(1);
			}
		}
	}

	public int getTempsMax() {
		return 100;
	}
	
	
	
}
