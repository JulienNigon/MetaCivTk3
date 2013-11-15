package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.Cogniton;

/** 
 * Classe abstraite représentant un "plan", projet au sens large poursuivi par un agent
 * @author DTEAM
 * @version 1.0 - 2/2013
*/


public abstract class Plan {

	protected int poids; //Détermine les chances de choisir cette action plutôt qu'une autre
    protected Esprit e;
    protected Humain h;
	
	public Plan (Esprit e, Humain h)
	{
		poids = 0;
		this.e = e;
		this.h = h;
	}
	
	public void Activer()
	{
		
	}
	
	/**
	 * Modifie le poids du projet d'un poids p,
	 * Demande à l'esprit concerné de modifier le poids total en conséquence
	 */
	public void changerPoids(int p)
	{
		if (poids > 0 && p < 0 && (-1)*p > poids)
		{
			e.ajouterPoidsTotal(-1*poids); // On modifie le poids total des projets dans l'esprit, en ne comptant pas la partie négative
		}
		else if (poids <= 0 && p < 0)
		{
			// Le poids total n'est pas modifié
		}
		else if (poids < 0 && p > 0 && p > (-1)*poids)
		{
			e.ajouterPoidsTotal(p+poids); // On modifie le poids total des projets dans l'esprit, en ne comptant pas la partie négative
		}
		else
		{
			e.ajouterPoidsTotal(p);
		}
		poids += p;
	}
	
	public int getPoids()
	{
		return poids;
	}

	public int getTempsMax() {
		return -1; //-1 indique un projet qui n'est pas limité dans le temps
	}

	public void setPoids(int i) {
		poids = i;
	}
	
	public String getNom(){
		return "plan";
	}
	
	public int getType(){
		return 0; //0 = Plan normal (pas un reflexe)
	}
	
	public boolean equals(GroupPlan p)
	{
		return false;	
	}
	
	public boolean equals(Plan p)
	{
		return this.getPoids() == p.getPoids() && this.getNom().equals(p.getNom());	
	}
	
}
