package civilisation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import civilisation.individu.cognitons.CCogniton;

public class GroupModel {
	
	String name;
	//Group have an HashMap, which associate Role (String) to an arraylist of CCogniton.
	//Each PCogniton contain a Culturon instead of a standard Cogniton.
	HashMap<String,ArrayList<CCogniton>> culturons = new HashMap<String,ArrayList<CCogniton>>();
	
	public GroupModel(String name) {
		super();
		this.name = name;
	}

	public void setRole(String role){
		culturons.put(role , null);
	}
	
	public void setRole(ArrayList<CCogniton> newCulturons , String role){
		culturons.put(role , newCulturons);
	}
	
	public void addCulturonToRole(String role , CCogniton culturon){
		if (!culturons.containsKey(role)) {
			ArrayList<CCogniton> lc = new ArrayList<CCogniton>();
			lc.add(culturon);
			culturons.put(role , lc);
		} else {
			culturons.get(role).add(culturon);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, ArrayList<CCogniton>> getCulturons() {
		return culturons;
	}

	public void setCulturons(HashMap<String, ArrayList<CCogniton>> culturons) {
		this.culturons = culturons;
	}

	public void enregistrer(File cible) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(cible.getPath()+"/"+name+Configuration.getExtension()));
			out.println("Name : " + name);
			
			Object[] keysRoles = (Object[]) culturons.keySet().toArray();
			for (int i = 0; i < keysRoles.length ;i++){
				ArrayList<CCogniton> arrayList = culturons.get((String)keysRoles[i]);
				
				for (int j = 0 ; j < arrayList.size() ; j++) {
					out.println("Culturon : " + (String)keysRoles[i] +"," + arrayList.get(j).getCogniton().getNom());
				}
			} 
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	

}
