package civilisation.individu.plan;

import java.util.HashMap;
import java.util.HashSet;

import civilisation.group.Group;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.CCogniton;
import civilisation.individu.plan.action.Action;

public class NPlanPondere {
	
	int poids;
	NPlan plan;
	Humain h;
	Esprit e;
	
	HashMap<CCogniton,Double> influences = new HashMap<CCogniton,Double>();
	HashSet<CCogniton> conditions = new HashSet<CCogniton>();

	//The conditional CCogniton associated (could be null)
	CCogniton cCogniton;
	
	
	public NPlanPondere(int poids, NPlan plan, Humain h, Esprit e) {
		super();
		this.poids = poids;
		this.plan = plan;
		this.h = h;
		this.e = e;

	}
	
	/**
	 * Apply the action "action" in the plan
	 * @param action
	 */
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
	public CCogniton getcCogniton() {
		return cCogniton;
	}
	public void setcCogniton(CCogniton cCogniton) {
		this.cCogniton = cCogniton;
	}
	
	public Humain getH() {
		return h;
	}

	public void setH(Humain h) {
		this.h = h;
	}

	/**
	 * Modifie le poids du projet d'un poids p,
	 * Demande ___ l'esprit concern___ de modifier le poids total en cons___quence
	 */
	public void changerPoids(int p)
	{
		//TODO : Just a fast solution, MUST be improved for performance issues
		e.computeTotalWeight();
		
		//This code must be rewrite to optimize
	/*	if (poids > 0 && p < 0 && (-1)*p > poids)
		{
			e.addPoidsTotal(-1*poids); // On modifie le poids total des projets dans l'esprit, en ne comptant pas la partie n___gative
		}
		else if (poids <= 0 && p < 0)
		{
			// Le poids total n'est pas modifi___
		}
		else if (poids < 0 && p > 0 && p > (-1)*poids)
		{
			e.addPoidsTotal(p+poids); // On modifie le poids total des projets dans l'esprit, en ne comptant pas la partie n___gative
		}
		else if (poids < 0 && (-1)*p <= poids ) {
			
		}
		else if (poids > 0 && p < 0 && p > (-1)*poids)
		{
			e.addPoidsTotal(p+poids); // On modifie le poids total des projets dans l'esprit, en ne comptant pas la partie n___gative
		}
		else
		{
			e.addPoidsTotal(p);
		}*/
		poids += p;
	}
	
	@Override
	public String toString(){
		return "[Plan : " + plan.getNom() + " , Poids : " + poids + "]";
	}
	
	public void addInfluence(CCogniton cogni) {
		this.influences.put(cogni,cogni.getWeigth());
	}
	

}
