package civilisation.individu.plan;

import java.util.ArrayList;

import civilisation.amenagement.Amenagement;
import civilisation.amenagement.Amenagement_Champ;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Cereale;
import civilisation.inventaire.Objet_Outil;
import civilisation.inventaire.Objet_Viande;
import civilisation.world.World;
import edu.turtlekit2.kernel.environment.Patch;

public class PLAN_Cultiver extends Plan {

	Amenagement champCible;
	
	
	public PLAN_Cultiver(Esprit e, Humain h) {
		super(e,h);
	}

	public String getNom(){
		return "Cultiver";
	}
	
	public void Activer()
	{	

		
		Amenagement champ;
		Boolean fin = false;
		
		if(h.getInventaire().getSize("Cereale") >= 3)
		{
			h.rentrerDeposerRecolte();

		}
		else
		{
			//On vŽrifie d'abord si l'agent est sur un champ lui appartenant
			if (h.isMarkPresent("Champ"))
			{

				champ = (Amenagement) h.getMark("Champ");
				if (champ.getPossesseur().equals(h))
				{
					fin = true;
					recolter();
					if (Math.random() < 0.074)
					{
						String plan[] = {"PLAN_Cultiver"};
						int poids[] = {1};
						h.getEsprit().ameliorerSkillTalent("TalentCultiver", plan, poids);
					}
				}
				h.dropMark("Champ", champ);

			}
			

			if (!fin) // L'agent n'est pas sur un de ses champs
			{
				champ = h.getOneOfAmenagement("Champ");

	
				if (champCible != null) // L'agent se rend dŽjˆ vers un champ
				{

					h.setHeading(h.towards(champ.getPosition().x,champ.getPosition().y));
					h.fd(1);
				}
				else if (champ != null)  //Si l'agent poss�de un champ
				{
					champCible = champ;
					h.setHeading(h.towards(champ.getPosition().x,champ.getPosition().y));
					h.fd(1);
				}
				else if (!h.isMarkPresent("Champ") && h.getPatchColor().equals(World.getColorPlaines())) //Si l'agent n'a pas de champ et que la case peut en recevoir un
				{
					Amenagement_Champ nouveauChamp = new Amenagement_Champ(h.position, h);
					h.addAmenagement(nouveauChamp);
					h.dropMark("Champ", nouveauChamp);
				}
				else //L'agent cherche un endroit o� construire son champ
				{

					h.randomHeading();
					h.move(1);
				}
			}
			
		}

		
	}

	public int getTempsMax() {
		return 35;
	}
	
	private void recolter()
	{
		float recolte = 3;
		for(int i = -1; i<2;i++)
		{
			for(int j =-1;j<2;j++)
			{
				if(Math.abs(i) != Math.abs(j) && h.getPatchAt(i, j).color == World.getColorRivieres())
				{
					 recolte += (recolte*20)/100;
				}
			}
		}
		if(h.Possede("beche"))
		{
			Objet_Outil beche = (Objet_Outil) h.getInventaire().get("beche", 0);
			recolte += (recolte*beche.getTaux())/100;
		}
		for(int i = 0;i < (int)recolte; i++)
		{
			h.getInventaire().add(new Objet_Cereale());
		}
	}
	
	
	
}
