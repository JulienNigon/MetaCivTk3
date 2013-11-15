package civilisation.individu.plan;

import java.util.ArrayList;

import civilisation.Communaute;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.ObjetInventaire;
import civilisation.inventaire.Objet_Arme;
import civilisation.inventaire.Objet_Viande;
import civilisation.world.World;
import edu.turtlekit2.kernel.agents.Turtle;
import edu.turtlekit2.kernel.environment.Patch;

/**WARNING
 * Pour l'instant, fonctionne avec des communautés! Attention, passage vers des civilisations requis
 *
 */


public class PLAN_Combattre extends Plan {

	public PLAN_Combattre(Esprit e, Humain h) {
		super(e,h);
	}

	public String getNom(){
		return "Combattre";
	}
	
	public void Activer()
	{
		Activer(h.MinOneOfOtherCommunaute(h.getTurtlesListWithRole("Communaute")));
	}
	
	public void Activer(Communaute communaute)
	{
		
		int portee_max = 0;
		Objet_Arme attaque;
		ArrayList<ObjetInventaire> armes = h.getInventaire().getListObjet("Objet_Arme");
		for(int i =0;i<armes.size();i++)
		{
				Objet_Arme arme = (Objet_Arme)armes.get(i);
				if(arme.getPortee() > portee_max)
				{
					attaque = arme;
					portee_max = arme.getPortee();
				}
		}
		
		ArrayList<Humain> cibles = h.HumaininRadius(portee_max);
		for(int i = 0; i < cibles.size(); i++ )
		{
			if(!cibles.get(i).getCommunaute().equals(communaute))
			{
				cibles.remove(i);
				i--; /*NOTE D'INTEGRATION : Pour ne pas "avancer deux fois" à cause de la suppression*/
			}
		}

		
		if(cibles.isEmpty())
		{

			/*if(h.isHere(communaute)) Si communautÈ peuvent etre detruites
			{
				h.Attaquer(communaute);
			}*/
			if (!h.position.equals(communaute.position))
			{
				h.face(communaute.position);
			}

			h.allerVers(communaute);


		}
		else
		{

			Humain cible = h.oneOfHumain(cibles);
			this.Activer(cible);
		}
	}
	
	public void Activer(Humain cible)
	{
		
		int portee_max = 0;
		Objet_Arme attaque = null;
		double distance = h.distanceTo(cible);
		ArrayList<ObjetInventaire> armes = h.getInventaire().getListObjet("Objet_Arme");
		for(int i =0;i<armes.size();i++)
		{
			if(armes.get(i).is("arme"))
			{
				Objet_Arme arme = (Objet_Arme)armes.get(i);
				if(arme.getPortee() > portee_max && arme.getPortee() > distance )  /*NOTE D'INTEGRATION : Inversion du signe de comparaison?!*/
				{
					attaque = arme;
					portee_max = arme.getPortee();
				}
			}
		}
		if(attaque != null)
		{
			h.Attaquer(cible, attaque);
		}
		else
		{
			if(distance <= 1)
			{
				h.Attaquer(cible, null);
			}
			if (!h.position.equals(cible.position))
			{
				h.face(cible);
				h.move(1);
			}

		}
	}

	public int getTempsMax() {
		return 30;
	}
	
	
	
}
