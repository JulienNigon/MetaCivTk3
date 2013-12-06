package civilisation.individu;

import java.awt.Component;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import civilisation.Configuration;
import civilisation.Group;
import civilisation.individu.cognitons.NCogniton;
import civilisation.individu.cognitons.CCogniton;


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
	ArrayList<CCogniton> cognitons;
	
	/* La liste des projets envisageable par l'agent*/
	ArrayList<NPlanPondere> plans;
	
	/* Hashmap to keep informations about actions*/
	HashMap<Action , Object> actionsData;
	
	/* HashMap to store the groups of the agent and his role in this group*/
	HashMap<Group , String> groups;

	/*Autres attributs:*/
	Humain h;
	NPlanPondere planEnCours;
	Action actionEnCours;
	Stack<Action> actions = new Stack<Action>();
	
	int poidsTotal;
	int progression;
	int poidsTotalPlan;
	


	
	
	public Esprit(Humain h)
	{
		cognitons = new ArrayList<CCogniton>();
		plans = new ArrayList<NPlanPondere>();
		actionsData = new HashMap<Action , Object>();
		groups = new HashMap<Group , String>();
			
		this.h = h;
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
		for (NCogniton cogni : Configuration.cognitonsDeBase) {
			cognitons.add(new CCogniton(cogni));
		}
		for (int i = 0; i < cognitons.size(); i++) {
			cognitons.get(i).mettreEnPlace(this);
		}
	}
	
	/**
	 * Main function for the mind.
	 * Define the next plan to execute.
	 */
	public void penser()
	{
		
		
		/*Apply the Self-plan*/
		if (Configuration.autoPlan != null) {
			actions.push(null); //end of self-plan marker

			Configuration.autoPlan.activer(h, Configuration.autoPlan.getActions().get(0));
			
			Action a = null;

			while (( a = actions.pop()) != null) {
				System.out.println("a depop : " + a + "depop" + actions);
				Configuration.autoPlan.activer(h, a);
			}
		}

		/* Select the new plan if there are no plan to do */
		if ((/*planEnCours == null && */actionEnCours == null))
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
			actions.push(null); //end of plan marker
			//System.out.println("Agent choisi le plan : " + planEnCours.toString());
		} else {
			System.out.println(actions);
		}
		planEnCours.activer(actionEnCours);
		this.actionEnCours = actions.pop();

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
	
	public Humain getH() {
		return h;
	}

	public int getPoidsTotal() {
		return poidsTotal;
	}
		
	public int getProgression() {
		return progression;
	}

	public ArrayList<CCogniton> getCognitons() {
		return cognitons;
	}

	public void addPlan(NPlan plan)
	{
		plans.add(new NPlanPondere( 0 , plan , this.getHumain() , this));
	}
	
	public void addPlan(NPlan plan , CCogniton cCogniton)
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
		
		for (Group g : groups.keySet()) {
			g.applyCulturons(groups.get(g), this);
		}
	}
	
	/**
	 * Save data used by an action in the mind of the agent
	 * @param act : action associated with data
	 * @param data : data to be saved
	 * 
	 */
	public void setActionData (Action act , Object data) {
		actionsData.put(act, data);
	}
	
	/**
	 * Return the data associated with an action
	 * @param act : the action associated with data
	 * @return the data of the action
	 */
	public Object getActionData (Action act) {
		return actionsData.get(act);
	}
	
	/**
	 * Remove data of an action
	 * @param act : the action which data must be cleaned
	 */
	public void cleanActionData (Action act) {
		actionsData.remove(act);
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

	public void addCogniton(NCogniton cogni){
		cognitons.add(new CCogniton(cogni));
		cogni.mettreEnPlace(this);
	}

	public void removeCogniton(NCogniton c) {
		//System.out.println("remove cogniton " + c.getNom());
		for (int i = 0 ; i < this.cognitons.size(); i++) {
			if (cognitons.get(i).getCogniton() == c) {
				c.modifierPlans(false, this);
				cognitons.remove(i);
				this.redefinirPoids();
			}
		}
	}

	public int getPoidsTotalPlan() {
		return poidsTotalPlan;
	}

	public void setPoidsTotalPlan(int poidsTotalPlan) {
		this.poidsTotalPlan = poidsTotalPlan;
	}

	public Stack<Action> getActions() {
		return actions;
	}

	public void setActions(Stack<Action> actions) {
		this.actions = actions;
	}
	
	public boolean ownCogniton(NCogniton cogniton) {
		
		for (int i = 0 ; i < this.cognitons.size(); i++) {
			if (cognitons.get(i).getCogniton() == cogniton) {
				return true;
			}
		}
		return false;
	} 

	
	
	
	
}
