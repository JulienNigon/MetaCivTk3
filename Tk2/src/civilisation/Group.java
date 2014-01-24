package civilisation;

import java.util.ArrayList;
import java.util.HashMap;

import madkit.kernel.Agent;
import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Culturon;
import civilisation.individu.cognitons.CCogniton;
import edu.turtlekit2.kernel.agents.Turtle;
import edu.turtlekit2.kernel.environment.Patch;

public class Group extends Turtle
{

	Group parent;
	GroupModel groupModel;
	Patch position;
	
	public Group() {
	}
	
	public Group(Group parent , GroupModel groupModel, Patch position){
		this.parent = parent;
		this.groupModel = groupModel;
		this.position = position;
	}
	
	public void applyCulturons(String role , Esprit e){
		ArrayList<CCogniton> c = groupModel.getCulturons().get(role);
		for (int i = 0 ; i < c.size() ; i++){
			c.get(i).appliquerPoids(e);
		}
		if (parent != null) parent.applyCulturons(role, e);
	}
	
	public void setupCulturons(String role , Esprit e){
		ArrayList<CCogniton> c = groupModel.getCulturons().get(role);
		for (int i = 0 ; i < c.size() ; i++){
			c.get(i).mettreEnPlace(e);
		}
		if (parent != null) parent.setupCulturons(role, e);
	}
	
}
