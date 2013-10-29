package civilisation.individu;

import java.awt.Component;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import civilisation.Configuration;
import civilisation.individu.cognitons.NCogniton;


import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.NPlanPondere;
import civilisation.individu.plan.action.Action;

/** 
 * Classe de gestion des comportements des agents humain
 * @author DTEAM
 * @version 1.0 - 2/2013
*/



public class Esprit {
	
	/* Les diff≈Ωrentes listes contenants les croyances de l'agent */

	ArrayList<NCogniton> cognitons;
	
	/* La liste des projets envisageable par l'agent*/
	ArrayList<NPlanPondere> plans;

	/*Autres attributs:*/
	Boolean engagement;
	Humain h;
	NPlanPondere planEnCours;
	Action actionEnCours;
	
	int poidsTotal;
	int timer;
	int progression;
	int poidsTotalPlan;
	


	
	
	public Esprit(Humain h)
	{

		cognitons = new ArrayList<NCogniton>();
		plans = new ArrayList<NPlanPondere>();

		engagement = false;
		this.h = h;
		timer = 0;
		progression = 0;
		poidsTotalPlan = 0;
		
		
		/*Si l'agent a des parents (ie : il n'a pas ≈Ωt≈Ω g≈Ωn≈Ωr≈Ω au d≈Ωbut), on calcul ce qu'il obtient de ses parents*/
		if (h.getMere() != null)
		{
			//TODO
		}
		else
		{
			initialisationStandard();
		}
	}
	
	/**
	 * Initialise les cognitons
	 */
	private void initialisationStandard()
	{


		cognitons.addAll(Configuration.cognitonsDeBase); /*Les cognitons de bases, definis a l'initialisation.*/
		for (int i = 0; i < cognitons.size(); i++){
			cognitons.get(i).mettreEnPlace(this);
		}

	}
	


	
	/**
	 * Main function for the mind.
	 * Define the next plan to execute.
	 */
	public void penser()
	{

		if ((planEnCours == null && actionEnCours == null)|| timer == 0)
		{
			//System.out.println("Poids total :" + poidsTotalPlan);
			int alea = (int) (Math.random()*(poidsTotalPlan + 1));
			int i = 0;
			while (alea > plans.get(i).getPoids() /*|| plans.get(i).getType() == 1*/)
			{
				if (plans.get(i).getPoids() > 0) {alea -= plans.get(i).getPoids();	} /*les poids negatifs ne sont pas pris en compte*/		
				i++;
			}
			planEnCours = plans.get(i);
			//System.out.println("Agent choisi le plan : " + planEnCours.toString());
		}
		planEnCours.activer(actionEnCours);
	}
	
	/**
	 * Modifie le poids total d'une valeur donn≈Ωe.
	 * @param variation de poids
	 */
	public void ajouterPoidsTotal(int poids)
	{
		poidsTotal += poids;
	}

	
	public Humain getHumain() {
		return h;
	}

	public Boolean getEngagement() {
		return engagement;
	}

	public Humain getH() {
		return h;
	}

	public int getPoidsTotal() {
		return poidsTotal;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int tempsMax) {
timer = tempsMax;		
	}
	
	//-------------------------------Nouvelles fonctions version Singleton-------------------------------
	
	public int getProgression() {
		return progression;
	}

	public ArrayList<NCogniton> getCognitons() {
		return cognitons;
	}

	/**
	 * Ajoute un nouveau plan aux plans disponible pour l'agent.
	 * @param nouveauProjet
	 */
	public void addPlan(NPlan plan)
	{
		plans.add(new NPlanPondere( 0 , plan , this.getHumain() , this));
	}

	public ArrayList<NPlanPondere> getPlans() {
		return plans;
	}

	/**
	 * Change le poids actuel d'un plan
	 * @param plan : Le plan à modifier
	 * @param p : le poids à ajouter
	 */
	public void modifierPoids(NPlan plan, int p) 
	{
		int i = 0;
		//System.out.println(projets.get(i).getClass().getName());
		while ( i < plans.size() && !plans.get(i).getPlan().equals(plan))
		{
			//System.out.println(plan);
			//System.out.println(plans.get(i).getPlan());
			//System.out.println(projets.get(i).getClass().getName() + "   " + nom);
			//System.out.println("While " + i);
			i++;
		}

		if (plans.size() > 0 && i < plans.size())
		{
			plans.get(i).changerPoids(p);
		}
		else{
		}
		
	}
	
	public void removePlan(NPlan plan) {
		int i = 0;
		while ( i < plans.size() && !plans.get(i).getPlan().equals(plan))
		{
			i++;
		}
		plans.remove(i);
	}

	/**
	 * Modifie le poids total d'une valeur donn≈Ωe.
	 * @param variation de poids
	 */
	public void addPoidsTotal(int p)
	{

		poidsTotalPlan += p;

	}

	public void redefinirPoids() {
		poidsTotalPlan = 0;

		for (int i = 0; i < plans.size(); i++)
		{
			plans.get(i).setPoids(0);
		}
		
		for (int i = 0; i < cognitons.size(); i++)
		{
			cognitons.get(i).appliquerPoids(this);
		}
		
	}

	public void progresser() {
		progression++;	
	}

	public Action getActionEnCours() {
		return actionEnCours;
	}

	public void setActionEnCours(Action actionEnCours) {
		this.actionEnCours = actionEnCours;
	}

	public NPlanPondere getPlanEnCours() {
		return planEnCours;
	}

	public void setPlanEnCours(NPlanPondere planEnCours) {
		this.planEnCours = planEnCours;
	}

	
	
	
	
	
	
}
