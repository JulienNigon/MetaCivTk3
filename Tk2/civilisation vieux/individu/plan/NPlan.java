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
import civilisation.individu.cognitons.Cogniton;
import civilisation.individu.plan.action.Action;

public class NPlan {

	String nom;
	ArrayList<Action> actions;

	public NPlan(){
		actions = new ArrayList<Action>();
	}

	/**
	 * Active le plan en effectuant l'action correspondant ˆ la progression de l'agent
	 * @param h : L'agent effectuant l'action
	 */
	public void activer(Humain h , Action action){
		
		if (action == null){
		//	System.out.println("plan lance : " + nom);
			h.getEsprit().setActionEnCours(actions.get(0).effectuer(h));
		}
		else {
			h.getEsprit().setActionEnCours(action.effectuer(h));
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
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String toString(){
		return nom;
	}
	
	public void enregistrer(File cible) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(cible.getPath()+"/"+getNom()+Configuration.getExtension()));
			out.println("Nom : " + getNom());
			if (actions.isEmpty() !=  true){
				ecrireAction(out,0,actions.get(0));
			}
			
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void ecrireAction(PrintWriter out, int iteration, Action a){
		for (int j = 0; j < iteration; j++){
			out.print("\t");
		}
		out.print("Action : " + a.toString());
		for (int i = 0; i < a.getOptions().size(); i++){
			out.print("," + a.getOptions().get(i));
		}
		out.println();

		if (!a.getListeActions().isEmpty()){
			ecrireAction(out,iteration+1,a.getListeActions().get(0));
		}
		if (a.getNextAction()!=null){
			ecrireAction(out,iteration,a.getNextAction());
		}
		
	}
	
	/*
	 Nom : Gigoter

Action : L_Random
	Action : A_Move,EAST
	Action : A_Move,WEST
	 */
}
