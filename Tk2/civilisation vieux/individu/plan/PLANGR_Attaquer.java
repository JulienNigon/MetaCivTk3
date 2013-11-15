package civilisation.individu.plan;

import java.util.ArrayList;

import civilisation.Civilisation;
import civilisation.Communaute;
import civilisation.Configuration;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.ObjetInventaire;
import civilisation.inventaire.Objet_Arme;
import civilisation.inventaire.Objet_Or;
import edu.turtlekit2.kernel.agents.Turtle;
import edu.turtlekit2.kernel.environment.Patch;

public class PLANGR_Attaquer extends GroupPlan{

	Communaute communauteTarget;
	
	public PLANGR_Attaquer(Esprit e, Humain h, Object target, Humain leader) {
		super(e,h,target,leader);
		communauteTarget = (Communaute) target;
	}

	public String getNom(){
		return "Attaquer";
	}
	
	public int getPoidsInfluence(){
		return 60;
	}
	
	public void Activer()
	{
		//System.out.println("Fonder une ville");
		
		if(leader == null){
			activerLeader();
		}
		else{		
			activerFollower();
		}
	}
	
	public void activerLeader(){
		if (ordreDonne)
		{

			frapper();
		}
		
		else
		{
			if (h.isHere(h.getCommunaute())){
				ordreDonne = true;
				//System.out.println("chez soi");
				communauteTarget = h.MinOneOfOtherCommunauteOfOtherCiv(h.getTurtlesListWithRole("Communaute"), h.getCiv());
				if (h.emettreInfluence(this)){
					e.setTimer(this.getTempsMax());
					h.face(communauteTarget);
				}
				else
				{
					e.finProjet();
				}
			}
			else{
				h.rentrer();
			}
		}

	}
	
	public void activerFollower(){
		//System.out.println("Follow!");
		if (leader != null)
		{
			if(!leader.isHere(h)){
				h.face(leader);
				h.fd(1);
			}
			frapper();
		}

		else
		{

			e.finProjet();
		}

	}
	
	

	
	
	public void frapper()
	{

		
		if (!h.isHere(communauteTarget)){
			e.finProjet();	
		}
		
		int portee_max = 0;
		Objet_Arme attaque;
		ArrayList<ObjetInventaire> armes = h.getInventaire().getListObjet("Arc");  // Amelioration requise
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
			if(!cibles.get(i).getCiv().equals(communauteTarget.getCiv()))
			{
				cibles.remove(i);
				i--;
			}
		}

		
		if(cibles.isEmpty())
		{

			if (!h.position.equals(communauteTarget.position))
			{
				h.face(communauteTarget.position);
			}

			h.allerVers(communauteTarget);


		}
		else
		{

			Humain cible = h.oneOfHumain(cibles);
			this.frapper(cible);
		}
	}
	
	public void frapper(Humain cible)
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
				if(arme.getPortee() > portee_max && arme.getPortee() > distance )
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
		return 100;
	}
}
