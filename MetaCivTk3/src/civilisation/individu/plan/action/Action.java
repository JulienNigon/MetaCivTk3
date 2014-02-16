package civilisation.individu.plan.action;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.Configuration;
import civilisation.GroupAndRole;
import civilisation.individu.Humain;

public abstract class Action {

	protected Action nextAction;
	protected ArrayList<Action> listeActions;
	protected ArrayList<OptionsActions> options;
	protected ArrayList<String[]> schemaParametres;

	
	public Action(){
		listeActions = new ArrayList<Action>();
		options = new ArrayList<OptionsActions>();

	}

	public static Action actionFactory(String[] options){
		
		String s = options[0].split("\\(")[0];
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

	/**
	 * Use an array of string and convert it into options for the action.
	 * @param options : array of string
	 */
	public void parametrer(String[] options){

		for (int i = 1; i < options.length; i++){ /*Le premier terme est le nom de l'action, on l'ignore donc*/
			//System.out.println("Chargement d'une action");
			OptionsActions option = new OptionsActions(options[i].split("\\(")[0]);
			if (options[i].split("\\(").length > 1){
				//System.out.println("Chargement d'une action avec parametres");
				String param[] = options[i].split("\\(")[1].split("\\)")[0].split(";");
				for(int j = 0; j < param.length; j++){
					
					/* To manage a new kind of parameter, you must :
					 * 	1 : adapt this function
					 * 	2 : adapt the "DialogueEditerAction", to show and to treat the new parameter
					 * 	3 : adapt the save function in "OptionsActions"
					 */
					
					if (param[j].split(" ")[0].equals("Objet")){
						option.addParametre(Configuration.getObjetByName(param[j].split(" ")[1]));
					}
					if (param[j].split(" ")[0].equals("Pheromone")){
						option.addParametre(Configuration.getPheromoneByName(param[j].split(" ")[1]));
					}
					if (param[j].split(" ")[0].equals("Integer")){
						option.addParametre(Integer.parseInt(param[j].split(" ")[1]));
					}
					if (param[j].split(" ")[0].equals("Double")){
						option.addParametre(Double.parseDouble(param[j].split(" ")[1]));
					}
					if (param[j].split(" ")[0].equals("Attribute")){
						option.addParametre(param[j].split(" ")[1]);
					}
					if (param[j].split(" ")[0].equals("Cogniton")){
						option.addParametre(Configuration.getCognitonByName(param[j].split(" ")[1]));
					}
					if (param[j].split(" ")[0].equals("Group")){
						option.addParametre(Configuration.getGroupModelByName(param[j].split(" ")[1]));
					}
					if (param[j].split(" ")[0].equals("GroupAndRole")){
						option.addParametre(new GroupAndRole(param[j].split(" ")[1]));
					}
					if (param[j].split(" ")[0].equals("Comparator")){
						option.addParametre(Comparator.toComparator(param[j].split(" ")[1]));
					}
				}
			}
			parametrerOption(option);
		}
	}
	
	/**
	 * Retourne la structure des paramètres.
	 * Permet de déterminer la présentation de la fenêtre de réglages.
	 */
	public ArrayList<String[]> getSchemaParametres(){
		
		//Retourne null si n'est pas redéfini dans les classes filles
		return schemaParametres;	
	}
	
	
	public void parametrerOption(OptionsActions option){
		options.add(option);
	}
	
	public Action getNextAction() {
		return nextAction;
	}

	public void setNextAction(Action nextAction) {
		this.nextAction = nextAction;
	}
	
	@Override
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

	/**
	 * Supprime les options de cette action.
	 */
	public void clearOptions(){
		options = new ArrayList<OptionsActions>();
	}

	public String getName() {
		return this.getClass().getName();
	}

	public String getSimpleName(){
		return this.getClass().getSimpleName();
	}
	
	public boolean isLogical() {
		return false;
	}
	
	public void addActionAfter(Action action, Action ref) {
		for (int i = 0 ; i < listeActions.size(); i++){
			if (listeActions.get(i).equals(ref)){
				//System.out.println("Action ajoutée : " + (i+1));
				listeActions.add(i+1,action);
				listeActions.get(i).setNextAction(action);
				
				if (i+2 < listeActions.size()){
					action.setNextAction(listeActions.get(i+2)); /*On reconstruit le chainage avant*/
				}
				break;
			}
			if (listeActions.get(i).getListeActions() != null){
				listeActions.get(i).addActionAfter( action , ref);
			}
		}		
	}
	
	public void addActionBefore(Action action , Action ref) {
		for (int i = 0 ; i < listeActions.size(); i++){
			if (listeActions.get(i).equals(ref)){
				listeActions.add(i,action);
				if (i>0){
					listeActions.get(i-1).setNextAction(action);
				}
				action.setNextAction(listeActions.get(i+1)); /*On reconstruit le chainage*/
				break;
			}
			if (listeActions.get(i).getListeActions() != null){
				listeActions.get(i).addActionBefore( action , ref);
			}
		}
	}
	
	public void addSubAction(Action action, Action ref) {
		for (int i = 0 ; i < listeActions.size(); i++){
			if (listeActions.get(i).equals(ref)){
				listeActions.get(i).getListeActions().add(0,action);
				if (listeActions.get(i).getListeActions().size()>1){
					action.setNextAction(listeActions.get(i).getListeActions().get(1));
				}
				break;
			}
			if (listeActions.get(i).getListeActions() != null){
				listeActions.get(i).addSubAction( action , ref);
			}
		}		
	}
	
	public void removeAction(Action action) {
		for (int i = 0 ; i < listeActions.size(); i++){
			if (listeActions.get(i).equals(action)){
				listeActions.remove(i);
				if (i>0 && i<listeActions.size()){
					listeActions.get(i-1).setNextAction(listeActions.get(i));
				} else if (i>0) {
					listeActions.get(i-1).setNextAction(null);
				}
				break;
			}
			if (listeActions.get(i).getListeActions() != null){
				listeActions.get(i).removeAction(action);
			}
		}		
	}
	
	/**
	 * 
	 * @return String au format des fichiers textes .metaciv (pour enregistrer)
	 */
	public String toFormatedString(){
		if (options.size() == 0){
			return toString();
		}
		else{
			String s = toString();
			for (int i = 0; i < options.size(); i++){
				s += ",";
				s += options.get(i).toFormatedString();	
			}
			return s;
		}
	}
	
	/**
	 * Redéfinir cette fonction pour les actions qui jouent le rôle de contrôleurs logiques.
	 * @return 0 si l'action est simple, sinon le nombre de sous-actions qu'elle comporte, -1 si ce nombre est illimité
	 */
	public int getNumberActionSlot(){
		return 0;
	}

	/**
	 * Redéfinir cette fonction pour donner un texte informatif aux différentes actions
	 * @return un texte descriptif de l'action
	 */
	public String getInfo() {
		return "<html><b style=\"color:pink\">" + this.getSimpleName() +  " : </b>" ;
	}
}
