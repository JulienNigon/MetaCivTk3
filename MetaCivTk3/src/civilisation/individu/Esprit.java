package civilisation.individu;

import java.awt.Component;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

import civilisation.Communaute;
import civilisation.Configuration;
import civilisation.group.Group;
import civilisation.group.GroupAndRole;
import civilisation.group.GroupModel;
import civilisation.individu.cognitons.NCogniton;
import civilisation.individu.cognitons.CCogniton;
import civilisation.individu.decisionMaking.DecisionMaker;
import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.NPlanPondere;
import civilisation.individu.plan.action.Action;

/** 
 * Classe de gestion des comportements des agents humain
 * @author DTEAM
 * @version 1.0 - 2/2013
*/



public class Esprit {
	
	/* Les diff______rentes listes contenants les croyances de l'agent */
	ArrayList<CCogniton> cognitons;
	
	/* La liste des projets envisageable par l'agent*/
	ArrayList<NPlanPondere> plans;
	
	/* Hashmap to keep informations about actions*/
	HashMap<Action , Object> actionsData;
	
	/* HashMap to store the groups of the agent and his role in this group*/
	HashMap<Group , String> groups;
	
	/*Tags are used to tell other agents informations about this agent*/
	HashSet<String> tags;

	Map<Object,Object> memory;
	
	/*Autres attributs:*/
	Humain h;
	NPlanPondere planEnCours;
	Action actionEnCours;
	Stack<Action> actions = new Stack<Action>();
	
	int poidsTotal;
	float poidsTotalPlan;

	private DecisionMaker decisionMaker;
	
	
	public Esprit(Humain h)
	{
		cognitons = new ArrayList<CCogniton>();
		plans = new ArrayList<NPlanPondere>();
		actionsData = new HashMap<Action , Object>();
		groups = new HashMap<Group , String>();
		decisionMaker = Configuration.decisionMaker.createNewDecisionMaker(this);
		tags = new HashSet<String>();
		
		this.h = h;
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

		decisionMaker.selectPlan();

		planEnCours.activer(actionEnCours);
		this.actionEnCours = actions.pop();

	}
	
	/**
	 * Modifie le poids total d'une valeur donn_______________e.
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
	 * @param plan : Le plan ______ modifier
	 * @param f : le poids ______ ajouter
	 */
	public void modifierPoids(NPlan plan, float f) 
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
			plans.get(i).changerPoids(f);
		}
		else{
		}
		
	}
	
	/**
	 * Remove the specified plan from the mind of the agent
	 * @param plan
	 */
	public void removePlan(NPlan plan) {
		int i = 0;
		while ( i < plans.size() && !plans.get(i).getPlan().equals(plan))
		{
			i++;
		}
		plans.remove(i);
	}

	/**
	 * Modifie le poids total d'une valeur donn_______________e.
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

	public float getPoidsTotalPlan() {
		return poidsTotalPlan;
	}

	public void setPoidsTotalPlan(float poidsTotalPlan) {
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

	/**
	 * Return true if the agent own a specific type of cogniton
	 * @param cogniton
	 * @return
	 */
	public boolean ownCogniton(NCogniton cogniton) {
		
		for (int i = 0 ; i < this.cognitons.size(); i++) {
			if (cognitons.get(i).getCogniton() == cogniton) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return true if the agent own a specific plan
	 * @param plan
	 * @return
	 */
	public boolean ownPlan(NPlan plan) {
		
		for (int i = 0 ; i < this.plans.size(); i++) {
			if (plans.get(i).getPlan().equals(plan)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return the concrete plan of the type of plan in parameter
	 * @param plan
	 * @return
	 */
	public NPlanPondere getPlan(NPlan plan) {
		
		for (int i = 0 ; i < this.plans.size(); i++) {
			if (plans.get(i).getPlan().equals(plan)) {
				return plans.get(i);
			}
		}
		return null;
	}

	/**
	 * Recompute the total weigth from plans
	 */
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
	
	/**
	 * Set the weight of a cogniton of type t
	 * @param t
	 * @param d
	 */
	public void setWeightOfCognitonOfType(NCogniton t , Double d)
	{
		for (int i = 0 ; i < cognitons.size() ; i++) {
			if (cognitons.get(i).getCogniton().equals(t)) {
				cognitons.get(i).setWeigth(d);
			}
		}
		this.redefinirPoids();
	}
	
	
	
	/**
	 * Ajoute un cogniton si il n'est pas présent
	 * Si il est présent change le poid
	 * 
	 */


	
	
	/**
	 * Ajoute un cogniton si il n'est pas présent
	 * Si il est présent change le poid
	 * 
	 */

	public void setCogniton(NCogniton t, Double d)
	{
		int i = 0;
		while(i < cognitons.size() && !cognitons.get(i).getCogniton().equals(t))
		{
			i++;
		}
		if(i >= cognitons.size())
		{
			cognitons.add(new CCogniton(t));
			t.mettreEnPlace(this , d);
		}
		else
		{
			this.setWeightOfCognitonOfType(t, d);
		}
	}
	
	
	/**
	 * Ajoute un poid au cogniton si il est présent
	 * 
	 */
	public void AddWeightToCogniton(NCogniton t, Double d)
	{
		int i = 0;
		while(i < cognitons.size() && !cognitons.get(i).getCogniton().equals(t))
		{
			i++;
		}
		if(i < cognitons.size())
		{
			this.changeWeightOfCognitonOfType(t, d);
		}
		else
		{
			this.setCogniton(t, d);
		}
	}
	
	
	/**
	 * Check if the agent own a specific combination of a group and a role
	 * @param groupAndRoleToMap
	 * @return
	 */
	public boolean hasGroupAndRole(GroupAndRole groupAndRoleToMap) {
		Object[] tab = groups.keySet().toArray();

		for (int i = 0 ; i < groups.size(); i++) {
		if (((Group)tab[i]).getGroupModel().getName().equals(groupAndRoleToMap.getGroupModel().getName()) && groups.get(tab[i]).equals(groupAndRoleToMap.getRole())) {
				return true;
			}
		}	
		return false;
	}
	
	/**
	 * Check if the agent is part of a group with the specified structural organisation
	 * @param gm
	 * @return
	 */
	public boolean hasStructuralGroup(GroupModel gm) {
		Object[] tab = groups.keySet().toArray();

		for (int i = 0 ; i < groups.size(); i++) {
		if (((Group)tab[i]).getGroupModel().equals(gm)) {
				return true;
			}
		}	
		return false;
	}
	
	/**
	 * Check if the agent own a specific combination of a concrete group and a role
	 * @return true/false
	 */
	public boolean hasConcreteGroupAndRole(Group g , String r) {
		Object[] tab = groups.keySet().toArray();

		for (int i = 0 ; i < groups.size(); i++) {
		if (((Group)tab[i]).equals(g) && groups.get(tab[i]).equals(r)) {
				return true;
			}
		}	
		return false;
	}
	
	/**
	 * @param gm : the group model researched
	 * @return the concrete group instanciating GroupModel gm of this agent, null if none
	 */
	public Group getConcreteGroup(GroupModel gm) {
		Object[] tab = groups.keySet().toArray();
		for (int i = 0 ; i < groups.size(); i++) {
			if (((Group)tab[i]).getGroupModel().equals(gm)) {
				return ((Group)tab[i]);
			}
		}
		return null;
	}
	
	/**
	 * The agent join a specified restrictive group g and play the role r
	 * Join restrictive group doesn't allow an agent to be part of an other group with the same structural organization,
	 * or to play two different roles in the same group
	 */
	public void joinRestrictiveGroup(Group g , String r) {
		if (!this.hasStructuralGroup(g.getGroupModel())) {
			g.joinGroup(this, r);
		}
	}

	public void runInitiatePlan() {
		if (Configuration.initiatePlan != null && Configuration.initiatePlan.getActions().size() > 0) {
			actions.push(null); //end of self-plan marker

			Configuration.initiatePlan.activer(h, Configuration.initiatePlan.getActions().get(0));
			
			Action a = null;

			while (( a = actions.pop()) != null) {
				//System.out.println("a depop : " + a + "depop" + actions);
				Configuration.initiatePlan.activer(h, a);
			}
		}
	}
	
	public Map<Object, Object> getMemory() {
		return memory;
	}

	public void setMemory(Map<Object, Object> memory) {
		this.memory = memory;
	}

	public HashSet<String> getTags() {
		return tags;
	}

	public void setTags(HashSet<String> tags) {
		this.tags = tags;
	}
	
	public boolean ownTag(String tag) {
		return tags.contains(tag);
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}
	
	public void removeTag(String tag) {
		tags.remove(tag);
	}
} 


