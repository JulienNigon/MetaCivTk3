package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.individu.plan.action.Action;

public class NPlanPondere {
	
	int poids;
	NPlan plan;
	Humain h;
	Esprit e;

	
	
	
	public NPlanPondere(int poids, NPlan plan, Humain h, Esprit e) {
		super();
		this.poids = poids;
		this.plan = plan;
		this.h = h;
		this.e = e;

	}
	
	public void activer(Action action){
		plan.activer(h , action);
	}
	
	
	public int getPoids() {
		return poids;
	}
	public void setPoids(int poids) {
		this.poids = poids;
	}
	public NPlan getPlan() {
		return plan;
	}
	public void setPlan(NPlan plan) {
		this.plan = plan;
	}

	/**
	 * Modifie le poids du projet d'un poids p,
	 * Demande à l'esprit concerné de modifier le poids total en conséquence
	 */
	public void changerPoids(int p)
	{

		if (poids > 0 && p < 0 && (-1)*p > poids)
		{
			e.addPoidsTotal(-1*poids); // On modifie le poids total des projets dans l'esprit, en ne comptant pas la partie négative
		}
		else if (poids <= 0 && p < 0)
		{
			// Le poids total n'est pas modifié
		}
		else if (poids < 0 && p > 0 && p > (-1)*poids)
		{
			e.addPoidsTotal(p+poids); // On modifie le poids total des projets dans l'esprit, en ne comptant pas la partie négative
		}
		else
		{
			e.addPoidsTotal(p);
		}
		poids += p;
	}
	
	public String toString(){
		return "[Plan : " + plan.getNom() + " , Poids : " + poids + "]";
	}
	
	
	

}
