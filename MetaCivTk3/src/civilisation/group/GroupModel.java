package civilisation.group;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import civilisation.Configuration;
import civilisation.individu.cognitons.Cogniton;
import civilisation.individu.cognitons.TypeCogniton;

public class GroupModel {
	
	String name;
	//Group have an HashMap, which associate Role (String) to an arraylist of CCogniton.
	//Each PCogniton contain a Culturon instead of a standard Cogniton.
	HashMap<String,ArrayList<TypeCogniton>> rolesAndCulturons = new HashMap<String,ArrayList<TypeCogniton>>();
	
	public GroupModel(String name) {
		super();
		this.name = name;
	}

	public void setRole(String role){
		rolesAndCulturons.put(role , new ArrayList<TypeCogniton>());
	}
	
	public void setRole(ArrayList<TypeCogniton> newCulturons , String role){
		rolesAndCulturons.put(role , newCulturons);
	}
	
	public void removeRole(String role) {
		rolesAndCulturons.remove(role);
	}
	
	public void changeRoleName(String oldName, String newName){
		rolesAndCulturons.put(newName, rolesAndCulturons.get(oldName));
		rolesAndCulturons.remove(oldName);
	}
	
	public void addCulturonToRole(String role , TypeCogniton culturon){
		if (!rolesAndCulturons.containsKey(role)) {
			ArrayList<TypeCogniton> lc = new ArrayList<TypeCogniton>();
			lc.add(culturon);
			rolesAndCulturons.put(role , lc);
		} else {
			rolesAndCulturons.get(role).add(culturon);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, ArrayList<TypeCogniton>> getCulturons() {
		return rolesAndCulturons;
	}

	public void setCulturons(HashMap<String, ArrayList<TypeCogniton>> rolesAndCulturons) {
		this.rolesAndCulturons = rolesAndCulturons;
	}
	
	public void enregistrer(File cible) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(cible.getPath()+"/"+name+Configuration.getExtension()));
			out.println("Name : " + name);
			
			Object[] keysRoles = (Object[]) rolesAndCulturons.keySet().toArray();
			for (int i = 0; i < keysRoles.length ;i++){
				ArrayList<TypeCogniton> arrayList = rolesAndCulturons.get((String)keysRoles[i]);
				
				for (int j = 0 ; j < arrayList.size() ; j++) {
					out.println("Culturon : " + (String)keysRoles[i] +"," + arrayList.get(j).getNom());
				}
			} 
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	
	public ArrayList<TypeCogniton> getCulturonFromRole(String role) {
		return rolesAndCulturons.get(role);
	}
	

}
