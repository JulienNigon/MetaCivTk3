package civilisation;

import java.util.ArrayList;
import java.util.HashMap;

import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Culturon;
import civilisation.individu.cognitons.CCogniton;

public class Group {

	//Group have an HashMap, which associate Role (String) to an arraylist of PCogniton.
	//Each PCogniton contain a Culturon instead of a standard Cogniton.
	HashMap<String,ArrayList<CCogniton>> culturons = new HashMap<String,ArrayList<CCogniton>>();
	Group parent;
	
	public Group() {
	}
	
	public Group(Group parent){
		this.parent = parent;
	}
	
	public void setRole(String role){
		culturons.put(role , null);
	}
	
	public void setRole(ArrayList<CCogniton> newCulturons , String role){
		culturons.put(role , newCulturons);
	}

	public HashMap<String,ArrayList<CCogniton>> getCulturons() {
		return culturons;
	}

	public void setCulturons(HashMap<String,ArrayList<CCogniton>> culturons) {
		this.culturons = culturons;
	}
	
	public void applyCulturons(String role , Esprit e){
		ArrayList<CCogniton> c = culturons.get(role);
		for (int i = 0 ; i < c.size() ; i++){
			c.get(i).appliquerPoids(e);
		}
		parent.applyCulturons(role, e);
	}
	
	public void setupCulturons(String role , Esprit e){
		ArrayList<CCogniton> c = culturons.get(role);
		for (int i = 0 ; i < c.size() ; i++){
			c.get(i).mettreEnPlace(e);
		}
		parent.setupCulturons(role, e);
	}
	
}
