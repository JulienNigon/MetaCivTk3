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
import civilisation.GroupAndRole;
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
	
	/* Les différentes listes contenants les croyances de l'agent */
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
		
		initialisationStandard();
		
	}
	
	/**
	 * Initialize cognitons
	 */
	private void initialisationStandard()
	{
		for (NCogniton cogni : Configuration.cognitonsDeBase) {
			if (Math.random() * 100.0 < (double)cogni.getStartChance())
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
				//System.out.println("a depop : " + a + "depop" + actions);
				Configuration.autoPlan.activer(h, a);
			}
		}

		/* Select the new plan if there are no action to do */
		if ((/*planEnCours == null && */actionEnCours == null))
		{
			computeTotalWeight(); //TODO : remove and re-write dynamic evolution of total weight
			int alea = (int) (Math.random()*(poidsTotalPlan));
			int i = 0;
			while (alea >= plans.get(i).getPoids() /*|| plans.get(i).getType() == 1*/)
			{
				if (plans.get(i).getPoids() > 0) {alea -= plans.get(i).getPoids();	} /*les poids negatifs ne sont pas pris en compte*/		
				i++;
			}
			planEnCours = plans.get(i);
			actions.push(null); //end of plan marker
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
		cogni.mettreEnPlace(this , 1.0); //1.0 is the standard weigth for new cogniton
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
	
	public HashMap<Group, String> getGroups() {
		return groups;
	}

	public void setGroups(HashMap<Group, String> groups) {
		this.groups = groups;
	}

	public boolean ownCogniton(NCogniton cogniton) {
		
		for (int i = 0 ; i < this.cognitons.size(); i++) {
			if (cognitons.get(i).getCogniton() == cogniton) {
				return true;
			}
		}
		return false;
	}

	public void computeTotalWeight() {
		poidsTotalPlan = 0;
		for (int i = 0 ; i < plans.size() ; i++) {
			if (plans.get(i).getPoids() > 0) {
				poidsTotalPlan += plans.get(i).getPoids();
			}
		}
	}
	
	/**
	 * Return the cogniton of type t
	 * @param t
	 * @return a cogniton of type t (or null if there is any cogniton of this type)
	 */
	public CCogniton getCognitonOfType(NCogniton t) {
		CCogniton c = null;
		for (int i = 0 ; i < cognitons.size() ; i++) {
			if (cognitons.get(i).getCogniton().equals(t)) {
				c = cognitons.get(i);
			}
		}
		return c;
	}
	
	/**
	 * Change the weight of a cogniton of type t
	 * @param t : the type of cogniton to change
	 */
	public void changeWeightOfCognitonOfType(NCogniton t , Double d) {
		for (int i = 0 ; i < cognitons.size() ; i++) {
			if (cognitons.get(i).getCogniton().equals(t)) {
				cognitons.get(i).setWeigth(cognitons.get(i).getWeigth() + d);
			}
		}
		this.redefinirPoids();
	}

	public boolean hasGroupAndRole(GroupAndRole groupAndRoleToMap) {
		Object[] tab = groups.keySet().toArray();

		for (int i = 0 ; i < groups.size(); i++) {
			System.out.println(groupAndRoleToMap.getGroupModel().getName() + " " + ((Group)tab[i]).getGroupModel().getName());
			System.out.println(groupAndRoleToMap.getRole() + " " + groups.get(tab[i]));
			
			if (((Group)tab[i]).getGroupModel().getName().equals(groupAndRoleToMap.getGroupModel().getName()) && groups.get(tab[i]).equals(groupAndRoleToMap.getRole())) {
				System.out.println("has g&r");
				return true;
			}
		}
		
		return false;
	}
	
	
} 


