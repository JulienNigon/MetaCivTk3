package civilisation.individu.plan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import civilisation.Configuration;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.individu.plan.action.Action;

public class NPlan {

	String nom;
	ArrayList<Action> actions;
	Boolean isBirthPlan = false;
	Boolean isSelfPlan = false;

	public NPlan(){
		actions = new ArrayList<Action>();
	}

	/**
	 * Active le plan en effectuant l'action correspondant � la progression de l'agent
	 * @param h : L'agent effectuant l'action
	 */
	public void activer(Humain h , Action action){
	    //System.out.println("this lance : " + this.getNom());

		if (action == null){
		  //  System.out.println("plan lance : " + nom);
			Action a = actions.get(0).effectuer(h);
			if (a != null) h.getEsprit().getActions().push(a);
			h.getEsprit().setActionEnCours(a);
		}
		else {
			//System.out.println("else" + nom);
			Action a = action.effectuer(h);
			if (a != null) h.getEsprit().getActions().push(a);
		}
		
	}
	
	public ArrayList<Action> getActions() {
		return actions;
	}

	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}
	
	public void addAction(Action action) {
			actions.add(action);
	}
	
	/**
	 * Add an action after a specified action
	 * @param action : action to add
	 * @param ref : "action" is added after "ref"
	 */
	public void addActionAfter(Action action , Action ref) {
		for (int i = 0 ; i < actions.size(); i++){
			if (actions.get(i).equals(ref)){
				actions.add(i+1,action);
				actions.get(i).setNextAction(action); /*On reconstruit le chainage*/
				if (i+2 < actions.size()){
					action.setNextAction(actions.get(i+2)); /*On reconstruit le chainage*/
				}
				break;
			}
			if (actions.get(i).getListeActions() != null){
				actions.get(i).addActionAfter( action , ref);
			}
		}
	}
	
	/**
	 * Add an action before a specified action
	 * @param action : action to add
	 * @param ref : "action" is added before "ref"
	 */
	public void addActionBefore(Action action , Action ref) {
		for (int i = 0 ; i < actions.size(); i++){
			if (actions.get(i).equals(ref)){
				actions.add(i,action);
				if (i>0){
					actions.get(i-1).setNextAction(action);
				}
				action.setNextAction(actions.get(i+1)); /*On reconstruit le chainage*/
				break;
			}
			if (actions.get(i).getListeActions() != null){
				actions.get(i).addActionBefore( action , ref);
			}
		}
	}
	
	/**
	 * Add an action included in another action
	 * @param action : action to add
	 * @param ref : "action" is added inside "ref"
	 */
	public void addSubAction(Action action, Action ref) {
		for (int i = 0 ; i < actions.size(); i++){
			if (actions.get(i).equals(ref)){
				actions.get(i).getListeActions().add(0,action);
				if (actions.get(i).getListeActions().size()>1){
					action.setNextAction(actions.get(i).getListeActions().get(1));
				}
				break;
			}
			if (actions.get(i).getListeActions() != null){
				actions.get(i).addSubAction( action , ref);
			}
		}		
	}
	
	/**
	 * Add an action at the begining of the plan
	 * @param action : action to add
	 */
	public void addFirstAction(Action action) {
		actions.add(0,action);
		if (actions.size() > 1){
			action.setNextAction(actions.get(1));
		}
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString(){
		return nom;
	}
	
	public Boolean getIsBirthPlan() {
		return isBirthPlan;
	}

	public void setIsBirthPlan(Boolean isBirthPlan) {
		this.isBirthPlan = isBirthPlan;
	}

	public Boolean getIsSelfPlan() {
		return isSelfPlan;
	}

	public void setIsSelfPlan(Boolean isSelfPlan) {
		this.isSelfPlan = isSelfPlan;
	}

	/**
	 * Save the plan in the target file
	 * @param targetFile
	 */
	public void enregistrer(File targetFile) {
		PrintWriter out;
		System.out.println("Sauvegarde du plan : " + nom);
		try {
			out = new PrintWriter(new FileWriter(targetFile.getPath()+"/"+getNom()+Configuration.getExtension()));
			out.println("Nom : " + getNom());
			out.println("Birth : " + isBirthPlan);
			out.println("Self : " + isSelfPlan);

			if (actions.isEmpty() !=  true){
				ecrireAction(out,0,actions.get(0));
			}
			
			
			out.close();
		} catch (IOException e) {
			// TODO Self-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void ecrireAction(PrintWriter out, int iteration, Action a){
		for (int j = 0; j < iteration; j++){
			out.print("\t");
		}
		System.out.println("Sauvegarde de l'action : " + a.getName());

		out.print("Action : " + a.toFormatedString());
		out.println();

		if (!a.getListeActions().isEmpty()){
			ecrireAction(out,iteration+1,a.getListeActions().get(0));
		}
		if (a.getNextAction()!=null){
			ecrireAction(out,iteration,a.getNextAction());
		}
		
	}
	
	/*A des fins de debuggage�*/
	public void seDecrire(){
		for (int i = 0 ; i < actions.size(); i++){
			System.out.println(actions.get(i).toString());
		}
	}

	public void removeAction(Action action) {
		for (int i = 0 ; i < actions.size(); i++){
			if (actions.get(i).equals(action)){
				actions.remove(i);
				if (i>0 && i<actions.size()){
					actions.get(i-1).setNextAction(actions.get(i));
				} else if (i>0) {
					actions.get(i-1).setNextAction(null);
				}
				break;
			}
			if (actions.get(i).getListeActions() != null){
				actions.get(i).removeAction(action);
			}
		}		
	}

	



}
