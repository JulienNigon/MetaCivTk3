package civilisation;

import java.util.ArrayList;
import java.util.HashMap;

import madkit.kernel.Agent;
import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Culturon;
import civilisation.individu.cognitons.CCogniton;
import civilisation.individu.cognitons.NCogniton;
import turtlekit.kernel.Turtle;
import turtlekit.kernel.Patch;

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
		System.out.println(c.size() + " " + c.get(0).getCogniton().getNom());
		for (int i = 0 ; i < c.size() ; i++){
			c.get(i).mettreEnPlace(e);
		}
		if (parent != null) parent.setupCulturons(role, e);
	}

	public Group getParent() {
		return parent;
	}

	public void setParent(Group parent) {
		this.parent = parent;
	}

	public GroupModel getGroupModel() {
		return groupModel;
	}

	public void setGroupModel(GroupModel groupModel) {
		this.groupModel = groupModel;
	}

	public Patch getPosition() {
		return position;
	}

	public void setPosition(Patch position) {
		this.position = position;
	}
	
	
	
}
