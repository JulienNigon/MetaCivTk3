package civilisation.individu.plan.action;

import java.util.ArrayList;
import java.util.HashMap;

import civilisation.ItemPheromone;
import civilisation.group.GroupAndRole;
import civilisation.group.GroupModel;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.inventaire.Objet;


public class OptionsActions {

	String name;
	ArrayList<Object> parametres = new ArrayList<Object>();
    HashMap<Object,String> specialInfo = new HashMap<Object,String>();
	
    public OptionsActions(String name){
    	this.name = name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	@Override
	public String toString(){
		return name + " : " + parametres.size();
	}
    
	public void addParametre(Object o){
		parametres.add(o);
	}
	
	public void addParametre(Object o, String info){
		addParametre(o);
		specialInfo.put(o, info);
	}

	public ArrayList<Object> getParametres() {
		return parametres;
	}
	
	/**
	 * 
	 * @return String au format des fichiers textes .metaciv
	 */
	public String toFormatedString(){
		if (parametres.size() == 0){
			return name;
		}
		else{
			String s = "(";
			for (int i = 0; i < parametres.size(); i++){
				if (parametres.get(i).getClass().equals(Objet.class)){
					s += "Objet "; /*Note the space before the name*/
					s += ((Objet) parametres.get(i)).getNom();
					if (i < parametres.size() - 1){
						s += ";"; /*Separate multiple parameters*/
					}
				}
				else if (parametres.get(i).getClass().equals(GroupModel.class)){
					s += "Group "; /*Note the space before the name*/
					s += ((GroupModel) parametres.get(i)).getName();
					if (i < parametres.size() - 1){
						s += ";"; /*Separate multiple parameters*/
					}
				}
				else if (parametres.get(i).getClass().equals(GroupAndRole.class)){
					s += "GroupAndRole "; /*Note the space before the name*/
					s += ((GroupAndRole) parametres.get(i)).getGroupModel().getName() +":"+((GroupAndRole) parametres.get(i)).getRole();
					if (i < parametres.size() - 1){
						s += ";"; /*Separate multiple parameters*/
					}
				}
				else if (parametres.get(i).getClass().equals(Integer.class)){
					s += "Integer ";
					s += parametres.get(i).toString();
					if (i < parametres.size() - 1){
						s += ";";
					}
				}
				else if (parametres.get(i).getClass().equals(Double.class)){
					s += "Double ";
					s += parametres.get(i).toString();
					if (i < parametres.size() - 1){
						s += ";";
					}
				}
				else if (parametres.get(i).getClass().equals(String.class) && this.specialInfo.get(parametres.get(i)) != null && this.specialInfo.get(parametres.get(i)).equals("String")){
					s += "String ";
					s += parametres.get(i);
					if (i < parametres.size() - 1){
						s += ";";
					}
				}
				else if (parametres.get(i).getClass().equals(String.class)){
					s += "Attribute ";
					s += parametres.get(i);
					if (i < parametres.size() - 1){
						s += ";";
					}
				}

				else if (parametres.get(i).getClass().equals(ItemPheromone.class)){
					s += "Pheromone ";
					s += ((ItemPheromone) parametres.get(i)).getNom();
					if (i < parametres.size() - 1){
						s += ";";
					}
				}
				else if (parametres.get(i).getClass().equals(TypeCogniton.class)){
					s += "Cogniton ";
					s += ((TypeCogniton) parametres.get(i)).getNom();
					if (i < parametres.size() - 1){
						s += ";";
					}
				}
				else if (parametres.get(i).getClass().equals(Comparator.class)){
					s += "Comparator ";
					s += ((Comparator) parametres.get(i)).toSymbol();
					if (i < parametres.size() - 1){
						s += ";";
					}
				}
			}
			s+=")";
			return name + s;
		}
	}
	
}
