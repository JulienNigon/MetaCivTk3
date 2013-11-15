package civilisation.individu.plan.action;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.individu.Humain;
import civilisation.inspecteur.simulation.NodeArbreActions;

public abstract class Action {

	protected Action nextAction;
	protected ArrayList<Action> listeActions;
	protected ArrayList<OptionsActions> options;

	public Action(){
		listeActions = new ArrayList<Action>();
		options = new ArrayList<OptionsActions>();

	}

	public static Action actionFactory(String[] options){
		
		String s = options[0];
		String nom = "civilisation.individu.plan.action." + s;
		//System.out.println("nom : " + nom);

		Class c;
		Action action;
		try {
			c = Class.forName(nom);
			Constructor constructor  = null;
			
			Object[] valeurs = new Object[]{};
			Class[] parametres = new Class[]{};
			
			constructor = c.getConstructor(parametres);
			action  = (Action) constructor.newInstance(valeurs);
			
			action.parametrer(options);
			return action;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	
	public Action effectuer(Humain h){
		return null;
	}

	public void parametrer(String[] options){
		
		for (int i = 1; i < options.length; i++){ /*Le premier terme est le nom de l'action, on l'ignore donc*/
			parametrerOption(OptionsActions.toOption(options[i]));
		}
	}
	
	protected void parametrerOption(OptionsActions option){
		options.add(option);
	}
	
	public Action getNextAction() {
		return nextAction;
	}

	public void setNextAction(Action nextAction) {
		this.nextAction = nextAction;
	}
	
	public String toString(){
		return this.getClass().getSimpleName();
	}
	
	public ImageIcon getIcon(){
		return new ImageIcon(this.getClass().getResource("../../../inspecteur/icones/arrow-000-medium.png"));
	}

	public ArrayList<Action> getListeActions() {
		return listeActions;
	}

	public void setListeActions(ArrayList<Action> listeActions) {
		this.listeActions = listeActions;
	}
	
	public void addSousAction(Action action){
		listeActions.add(action);
	}

	public ArrayList<OptionsActions> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<OptionsActions> options) {
		this.options = options;
	}

	
}
